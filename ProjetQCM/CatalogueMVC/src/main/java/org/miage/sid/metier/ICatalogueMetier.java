 package org.miage.sid.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.miage.sid.dao.Produit;

public interface ICatalogueMetier {
	public void addproduit(Produit p);
	public List<Produit> getAllProduits();
	public List<Produit> getAllProduitsParMC(String mc);
	public Produit getProduit(String ref);
	public void deleteProduit(String ref);
	public void updateProduits(Produit p);
	public void CreatePageDeBaseAngular(Produit p) throws FileNotFoundException, UnsupportedEncodingException, Exception;
	public  void copyFolder(File src, File dest) throws IOException;
	public void loginController(String login, String pwd)throws FileNotFoundException, UnsupportedEncodingException, Exception;
	public void ControllerCrudBasic(String[] champ, String AppName, String CRUD) throws Exception; 
	public void createFile(String content, String Path);
	public void generateArchitecture(String Name);	
	public void ViewCrudbasic(String[] champ, String AppName, String CRUD) throws Exception;
}
