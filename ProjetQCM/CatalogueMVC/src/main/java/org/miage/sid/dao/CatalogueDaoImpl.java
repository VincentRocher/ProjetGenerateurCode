package org.miage.sid.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;


public class CatalogueDaoImpl implements ICatalogueDAO{

	private Map<String, Produit> produits= new HashMap<String, Produit>();
	Logger logger= Logger.getLogger(CatalogueDaoImpl.class);
	@Override 
	public void addproduit(Produit p) {
		// TODO Auto-generated method stub
		produits.put(p.getReference(), p);
		
	}

	@Override
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		Collection<Produit> prods=produits.values();
		return new ArrayList<Produit>(prods);
	}

	@Override
	public List<Produit> getAllProduitsParMC(String mc) {
		// TODO Auto-generated method stub
		List<Produit> prods= new ArrayList<Produit>();
		for(Produit p:produits.values())
			if(p.getDesignation().indexOf(mc)>=0)
				prods.add(p);
		return prods;
	}

	@Override
	public Produit getProduit(String ref) {
		// TODO Auto-generated method stub
		return produits.get(ref);
	}

	@Override
	public void deleteProduit(String ref) {
		// TODO Auto-generated method stub
		 produits.remove(ref);
		
	}

	@Override
	public void updateProduits(Produit p) {
		// TODO Auto-generated method stub
		produits.put(p.getReference(), p);
	}
	
	public void init(){
		logger.info("Initialisation du catalogue");
		addproduit(new Produit("P1","PC1", 8000,10));
		addproduit(new Produit("P2","PC2", 7000,11));
		addproduit(new Produit("P3","PC3", 6000,12));
		addproduit(new Produit("P4","PC4", 5000,13));
	}

	@Override
	public void CreatePageDeBaseAngular(Produit p)
			throws Exception {
		String fileName= p.getReference();
		String extension= ".hml";
		File files = new File("/home/djerbienne/Bureau/AngularTestJ/t/"+fileName+extension);
		files.getParentFile().mkdir();
		//files.createNewFile();
		if (files.exists()) {
			if (files.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
			
		}// Velocity
		
		//Ancien writer Print
		PrintWriter writerPrint = new PrintWriter(files, "UTF-8");
		String nameTemplate="pageHtml.vm";
		StringWriter writerResult = getTemplatePage(nameTemplate);
		writerPrint.println(writerResult.toString());
		
		writerPrint.close();
		
	}

	@Override
	public void copyFolder(File src, File dest) throws IOException {
		// TODO Auto-generated method stub
		if(src.isDirectory()){
			 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
		}
    		else{
        		//if file, then copy it
        		//Use bytes stream to support all file types
        		InputStream in = new FileInputStream(src);
        	        OutputStream out = new FileOutputStream(dest); 
     
        	        byte[] buffer = new byte[1024];
     
        	        int length;
        	        //copy the file content in bytes 
        	        while ((length = in.read(buffer)) > 0){
        	    	   out.write(buffer, 0, length);
        	        }
     
        	        in.close();
        	        out.close();
        	        System.out.println("File copied from " + src + " to " + dest);
    		}
	}

	@Override
	public void loginController(String login, String pwd) 
		throws Exception {
			String fileName= "loginController";
			String extension= ".js";
			File files = new File("/home/djerbienne/Bureau/AngularTestJ/t/"+fileName+extension);
			files.getParentFile().mkdir();
			//files.createNewFile();
			if (files.exists()) {
				if (files.mkdirs()) {
					System.out.println("Multiple directories are created!");
				} else {
					System.out.println("Failed to create multiple directories!");
				}
				
			}
			
			
			PrintWriter writerPrint = new PrintWriter(files, "UTF-8");
			String nameTemplate="Controller.vm";
			StringWriter writerResult = getTemplatePageAuthentification(nameTemplate, login, pwd);
			writerPrint.println(writerResult.toString());
			
			writerPrint.close();
			
		
	}

	@Override
	public StringWriter getTemplatePage(String nameTemplate) throws Exception {
		 VelocityEngine ve = new VelocityEngine();

		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		         ve.init();
		         /*  next, get the Template  */
		         Template t = ve.getTemplate( "/templates/"+nameTemplate );
		         /*  create a context and add data */
		         VelocityContext context = new VelocityContext();
		         context.put("name", "imen");
		        
		         /* add that list to the context  */
		        
		         /* now render the template into a StringWriter */
		         StringWriter writer = new StringWriter();
		         t.merge( context, writer );
		         /* show the World */
		         System.out.println( writer.toString() ); 
		return writer;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ControllerCrudBasic(String[] champ, String AppName, String CRUD)
			throws Exception {
		 VelocityEngine ve = new VelocityEngine();

		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		         ve.init();
		         /*  next, get the Template  */
		         Template t = ve.getTemplate( "/templates/htmlTemplate.vm");
		         /*  create a context and add data */
		         VelocityContext context = new VelocityContext();
		         context.put("name", "imen");
		         context.put("AppName","controller");
		         /* add that list to the context  */
		         
		         context.put("petList", champ);
		         /* now render the template into a StringWriter */
		         StringWriter writer = new StringWriter();
		         t.merge( context, writer );
		         /* show the World */
		         System.out.println( writer.toString() ); 
		
	    	//JSONObject test = new JSONObject();
	    	String content = writer.toString(); 
	    			
	    	
	    	
	    	createFile(content, System.getProperty("user.dir")+"/AngularOutput/src/controller/Default.js");
	    	System.out.println("content: "+content);
	   
		
	}

	@Override
	public void createFile(String content, String Path) {
		try { 
    		/* vue */

			File file = new File(Path);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		
	}

	@Override
	public void generateArchitecture(String Name) {
		// TODO Auto-generated method stub
		new File(System.getProperty("user.dir")+"/AngularOutput").mkdirs();
    	/* premier niveau de dossiers */
    	new File(System.getProperty("user.dir")+"/AngularOutput/css").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/js").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/server").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/src").mkdirs();
    	/* dossiers de src */
    	new File(System.getProperty("user.dir")+"/AngularOutput/src/controller").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/src/factory").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/src/route").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/src/service").mkdirs();
    	new File(System.getProperty("user.dir")+"/AngularOutput/src/view").mkdirs();
    	
    	try {
			Files.copy(Paths.get(System.getProperty("user.dir")+"/angular-resource.js"),Paths.get(System.getProperty("user.dir")+"/AngularOutput/js/angular-resource.js"), StandardCopyOption.REPLACE_EXISTING );
			Files.copy(Paths.get(System.getProperty("user.dir")+"/angular-route.js"), Paths.get(System.getProperty("user.dir")+"/AngularOutput/js/angular-route.js"),StandardCopyOption.REPLACE_EXISTING );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    	String content = "'use strict';"
    			+"var app = angular.module('"+Name+"',['ngResource','ngRoute']);";



    	createFile(content,System.getProperty("user.dir")+"/AngularOutput/src/controller/app.js" );
   
	}

	@Override
	public void ViewCrudbasic(String[] champ, String AppName, String CRUD)
			throws Exception {
		 VelocityEngine ve = new VelocityEngine();

		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		         ve.init();
		         /*  next, get the Template  */
		         Template t = ve.getTemplate( "/templates/indexHtml.vm");
		         /*  create a context and add data */
		         VelocityContext context = new VelocityContext();
		         context.put("name", "reda");
		         context.put("AppName",AppName);
		         context.put("CRUD",CRUD);
		         /* add that list to the context  */
		         
		         context.put("petList", champ);
		         /* now render the template into a StringWriter */
		         StringWriter writer = new StringWriter();
		         t.merge( context, writer );
		         /* show the World */
		         System.out.println( writer.toString() ); 
		
	    	//JSONObject test = new JSONObject();
	    	String content = writer.toString(); 
	    	System.out.println("content: "+content);
	    	createFile(content, System.getProperty("user.dir")+"/AngularOutput/index.html");
	    	
		
	}

	@Override
	public StringWriter getTemplatePageAuthentification(String nameTemplate, String name, String password)
			throws Exception {
		 VelocityEngine ve = new VelocityEngine();

		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		         ve.init();
		         /*  next, get the Template  */
		         Template t = ve.getTemplate( "/templates/"+nameTemplate );
		         /*  create a context and add data */
		         VelocityContext context = new VelocityContext();
		         context.put("name", name);
		         context.put("password", password);
		         /* add that list to the context  */
		        
		         /* now render the template into a StringWriter */
		         StringWriter writer = new StringWriter();
		         t.merge( context, writer );
		         /* show the World */
		         System.out.println( writer.toString() ); 
		return writer;
		// TODO Auto-generated method stub
	}


 
    	
	}

