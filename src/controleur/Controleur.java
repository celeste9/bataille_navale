package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.BateauPatrouille;
import modele.Destroyer;
import modele.PorteAvion;
import modele.SousMarin;
import vue.BattleShipUI;


public class Controleur {
	public boolean gameover = false;
	public int start;
	public int pause = 2;
	public int pointMaxAllier;
	public String ip_hote;

	Serveur p1;
	Client p2;
	BattleShipUI jeux;
	BateauPatrouille patrouille;
	Destroyer destroyer;
	PorteAvion porteavion;
	SousMarin sousmarin;

	public Controleur(BateauPatrouille p,Destroyer e,PorteAvion r,SousMarin o) {
		patrouille = p;
		destroyer = e;
		porteavion = r;
		sousmarin = o;
	}

	public void setVue(BattleShipUI vue) {
		jeux = vue;
	}

	public void connexionServeur(String ip) {
		p2 = new Client();
		p2.setControleur(this);
		p2.setIP(ip);
		p2.start();

	}

	public void creationServeur() {
		p1 = new Serveur();
		p1.setControleur(this);
		p1.start();
	}

	public void naviresDisponibles() {
		jeux.listeNaviresDisponibles.append("Bateau de Patrouille : (" + patrouille.getTaille() + " cases )" + '\n');
		jeux.listeNaviresDisponibles.append("Destroyer : (" + destroyer.getTaille() + " cases )" + '\n');
		jeux.listeNaviresDisponibles.append("Porte avion : (" + porteavion.getTaille() + " cases )" + '\n');
		jeux.listeNaviresDisponibles.append("Sous marin : (" + sousmarin.getTaille() + " cases )" + '\n');
	}

	public void placerNavire(ActionEvent e) {
		pointMaxAllier+=20;
		JButton s = (JButton) e.getSource();
		s.setIcon(new ImageIcon("image/bateau.png"));
	}

	public void effectuerTirSurOrdiOuEnnemi(ActionEvent e) {	//parce que j'utilise les memes boutons pour l'ordi et l'adversaire en reseau
		if (jeux.jb_hote.isEnabled() == false) {
			p1.recoitBouton(e);
		}
		else if (jeux.jb_inviter.isEnabled() == false) {
			p2.recoitBouton(e);
		}
		else {
			int i = 0;
			JButton s = (JButton) e.getSource();
			while (s != jeux.casesOrdi.get(i)) {
				i++;
			}
			if (s == jeux.casesOrdi.get(i)) {	//si la case cocher est l'emplacement d'un bateau ennemi alors elle devient rouge et on gagne un point
				jeux.ordiToucher(s);
				jeux.historiqueDesAction.append("Moi : TOUCHER !!" + '\n');
				jeux.majScorePersonnel();
			}
			if (jeux.getPointsPersonnels() == jeux.getScoreAatteindre()) {
				JOptionPane victoire = new JOptionPane();
				victoire.showMessageDialog(null, "Felicitation!! vous avez gagnez !!!", "VICTOIRE!!", JOptionPane.INFORMATION_MESSAGE);
			}
			int aleatoire = (int) (Math.random() * 99);		//on genere un nombre aleatoire pour prendre une case au hasard dans notre liste
			JButton v = (JButton)jeux.lienVersNosCases.get(aleatoire).getSource(); //on retourne la source d'un actionevent de notre liste
			if ( v != null) {	//si l'adresse du bouton n'est pas null alors l'ordi marque un point
				jeux.allierToucher(v);
				jeux.historiqueDesAction.append("ORDI : TOUCHER!!" + '\n');
				jeux.majScoreOrdi();
			}
			if (jeux.getPointsOrdi() == pointMaxAllier) {
				JOptionPane defaite = new JOptionPane();
				defaite.showMessageDialog(null, "Domage!! vous avez perdu!!", "DEFAITE!!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void actionBasics(ActionEvent e) {
		String texte = ((JButton)e.getSource()).getText();
		switch(texte) {
		case "DEMARRER" :
			jeux.MenuVersModeDeJeux();
			break;
		case "SOLO" :
			jeux.ModeDeJeuxVersSolo();
			break;
		case "VS ADVERSAIRE (RESEAU)" :
			jeux.ModeDeJeuxVersMultiloueur();
			break;
		case "HOTE" :
			jeux.majHote();
			break;
		case "INVITEE" :
			jeux.majInvitee();
			break;
		case "OK" :
			jeux.creationDesConnexions();
			break;
		case "VALIDER" :

			break;
		case "START" :
			this.start = 1;
			JButton s = (JButton) e.getSource();
			s.setEnabled(false);	//le bouton start devient indisponible
			break;
		case "PAUSE" :
			this.pause++;
			if (this.pause%2 != 0) {
				JOptionPane pause = new JOptionPane();
				pause.showMessageDialog(null, "PAUSE EN COURS", "PAUSE", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "QUITTER LA PARTIE" :
			jeux.SoloVersMenu();
			break;
		case "RETOUR" :
			jeux.ModeDeJeuxVersMenu();
			break;
		case "INSTRUCTIONS":
			jeux.MenuVersInstructions();
			break;
		case "ACCEUIL" :
			jeux.InstructionsVersMenu();
			break;
		case "QUITTER" :
			System.exit(0);
			break;
		}
	}


}
