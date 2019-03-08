package modele;
public  class SousMarin {

	protected String nom;		//type du bateau
	protected int taille;		//taille du bateau

	
	//constructeur 
	public SousMarin(String n, int nbCases){
		nom = n;
		taille = nbCases;
		
	}
	
	//retourne le nom du bateau (type)
	public String getNom(){
		return nom;
	}
	
	//retourne la taille du bateau
	public int getTaille(){
		return taille;
	}
}
	