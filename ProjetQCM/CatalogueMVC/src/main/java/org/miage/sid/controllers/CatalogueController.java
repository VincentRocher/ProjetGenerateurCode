package org.miage.sid.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.miage.sid.dao.Produit;
import org.miage.sid.metier.ICatalogueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CatalogueController {
	
	@Autowired
	private ICatalogueMetier metier; 
	public Produit prod= new Produit(); 
	
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		model.addAttribute("produit",new Produit());
		model.addAttribute("produits", metier.getAllProduits());
		return "produits";
	}
	
	@RequestMapping(value="/saveProduit")
	public String save(Produit p, Model model)
	{
		metier.addproduit(p);
		model.addAttribute("produit",new Produit());
		model.addAttribute("produits", metier.getAllProduits());
		return "produits";
	}
	
	@RequestMapping(value="/delete")
	public String delete(Produit p, Model model, String ref)
	{
		metier.deleteProduit(ref);
		model.addAttribute("produit",new Produit());
		model.addAttribute("produits", metier.getAllProduits());
		return "produits";
	}
	
	@RequestMapping(value="/update")
	public String edit(Produit p, Model model, String ref)
	{
		prod=metier.getProduit(ref);
		metier.updateProduits(prod);
		model.addAttribute("produit",prod);
		model.addAttribute("produits", metier.getAllProduits());
		
		return "produits";
	}
	
	@RequestMapping(value="/saveFiles")
	public String ajouter(HttpServletRequest request, Produit p, Model model) throws Exception
	{
		File src = new File(System.getProperty("user.dir")+"/Bureau/copieOrigine");
    	File dest = new File(System.getProperty("user.dir")+"/AngularOutput/js");
    	metier.copyFolder(src, dest);
    	String login=request.getParameter("login");
    	String pwd=request.getParameter("pwd");
    	metier.loginController(login, pwd);
    	metier.CreatePageDeBaseAngular(p);
		model.addAttribute("produit",new Produit());
		model.addAttribute("produits", metier.getAllProduits());
		return "produits";
		
	}

	@RequestMapping(value="/creer")
    public String creer(HttpServletRequest request, Produit p, Model model) throws Exception {
    	
    	String crud = request.getParameter("CRUD");
    	String Name = request.getParameter("name");
    	String champ[] = request.getParameter("champ").split(",");
    	if(crud == null)
    	{
    		crud = "off";
    	}
    	if(Name.equals(null) )
    		Name = "DefaultName";
    	metier.generateArchitecture(Name);
		metier.ViewCrudbasic ( champ, Name, crud);
		metier.ControllerCrudBasic(champ, Name, crud);
		//ControllerCrudBasic(champ, Name, crud);

		System.out.println("Name: "+Name);
		int xx=0;
		for(xx=0;xx<champ.length;xx++)
		{
			System.out.println("champ: "+champ[xx]);
		}
		
		System.out.println("crud: "+crud);
		System.out.println("Director courant: "+ System.getProperty("user.dir"));
		
		File src = new File(System.getProperty("user.dir")+"/Bureau/copieOrigine");
    	File dest = new File(System.getProperty("user.dir")+"/AngularOutput/js");
    	metier.copyFolder(src, dest);
    	model.addAttribute("produit",new Produit());
		model.addAttribute("produits", metier.getAllProduits());
    	return "produits";
        
    }
	
	  
	
	  

}
