package org.miage.sid.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.miage.sid.dao.ICatalogueDAO;
import org.miage.sid.dao.Produit;

public class CatalogueMetierImpl implements ICatalogueMetier {
private ICatalogueDAO dao;

/*Setter pour l'injection des dependances*/
	public void setDao(ICatalogueDAO dao) {
	this.dao = dao;
}

	@Override
	public void addproduit(Produit p) {
		// TODO Auto-generated method stub
		dao.addproduit(p);
		
	}

	@Override
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		return dao.getAllProduits();
	}

	@Override
	public List<Produit> getAllProduitsParMC(String mc) {
		// TODO Auto-generated method stub
		return dao.getAllProduitsParMC(mc);
	}

	@Override
	public Produit getProduit(String ref) {
		// TODO Auto-generated method stub
		return dao.getProduit(ref);
	}

	@Override
	public void deleteProduit(String ref) {
		// TODO Auto-generated method stub
		dao.deleteProduit(ref);
		
	}

	@Override
	public void updateProduits(Produit p) {
		// TODO Auto-generated method stub
		dao.updateProduits(p);
	}

	@Override
	public void CreatePageDeBaseAngular(Produit p) throws Exception, FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
	dao.CreatePageDeBaseAngular(p);
		
	}

	@Override
	public void copyFolder(File src, File dest) throws IOException {
		// TODO Auto-generated method stub
		dao.copyFolder(src, dest);
		
	}

	@Override
	public void loginController(String login, String pwd)
			throws Exception {
		// TODO Auto-generated method stub
		dao.loginController(login, pwd);
	}

	@Override
	public void ControllerCrudBasic(String[] champ, String AppName, String CRUD)
			throws Exception {
		// TODO Auto-generated method stub
		dao.ControllerCrudBasic(champ, AppName, CRUD);
		
	}

	@Override
	public void createFile(String content, String Path) {
		// TODO Auto-generated method stub
		dao.createFile(content, Path);
	}

	@Override
	public void generateArchitecture(String Name) {
		// TODO Auto-generated method stub
		dao.generateArchitecture(Name);
		
	}

	@Override
	public void ViewCrudbasic(String[] champ, String AppName, String CRUD)
			throws Exception {
		// TODO Auto-generated method stub
		dao.ViewCrudbasic(champ, AppName, CRUD);
		
	}

}
