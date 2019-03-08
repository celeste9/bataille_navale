import controleur.Controleur;
import modele.BateauPatrouille;
import modele.Destroyer;
import modele.PorteAvion;
import modele.SousMarin;
import vue.BattleShipUI;

public class Principale {

	public static void main(String[] args) {
		BateauPatrouille pat = new BateauPatrouille("patrouille",2);
		Destroyer dest = new Destroyer("destroyer",3);
		PorteAvion avion = new PorteAvion("porte avion",4);
		SousMarin smarin = new SousMarin("sous marin",3);
		Controleur controleur = new Controleur(pat,dest,avion,smarin);
		BattleShipUI bat = new BattleShipUI(controleur);
		controleur.setVue(bat);
	}

}
