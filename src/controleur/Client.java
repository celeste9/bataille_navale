package controleur;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Client extends Thread {
	int pointsEnnemi;
	boolean bateauAlierToucher = false;
	String ip;
	String texteRecu;
	String texte = null;
	String texte2;
	public ActionEvent z = null;
	String etat = "";
	JButton a;
	JButton b;
	JButton s;
	Controleur controleur;
	public void setControleur(Controleur cont) {
		controleur = cont;
	}
	public void setIP(String adresse) {
		ip = adresse;
	}
	public void recoitBouton(ActionEvent event) {
		z = event;
	}
	public void attendre(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			Socket socket = new Socket(ip,1234);
			System.out.println("connexion etablie");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();
			PrintWriter pr = new PrintWriter(os);
			controleur.jeux.lancerMultijoueur();
			while(controleur.gameover == false) {
				if(z != null) {
					s = (JButton)z.getSource();
					texte = s.getText();
					texte = texte.replace("i", "t");
					pr.println(texte);
					pr.flush();
					System.out.println("texte envoyer :" + texte);
					z = null;
					attendre(1000);		//attendre 1s
					etat = br.readLine();
					System.out.println("etat : " + etat);
					if (Integer.valueOf(etat) == 1) {
						controleur.jeux.ennemiToucher(s);
						controleur.jeux.historiqueDesAction.append("MOI : TOUCHER !!" + '\n');
						controleur.jeux.majScorePersonnel();
					}
					else if (Integer.valueOf(etat) == 0) {
						controleur.jeux.historiqueDesAction.append("MOI : COULER !!"  + '\n');
					}
					/////////////////////////////////////////////////////////////////////////////////
					JOptionPane tour = new JOptionPane();
					tour.showMessageDialog(null, "En attente de l'adversiare...", "TOUR", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("attente du tour du joueur hote..");
					texteRecu = br.readLine();
					System.out.println("message recu : " + texteRecu );
					texteRecu = texteRecu.replace("t", "");		//on enleve t car les lettres fausse la comparaison
					for (int i = 0; i < controleur.jeux.lienVersNosCases.size();i++) {
						a = (JButton)controleur.jeux.lienVersNosCases.get(i).getSource();
						texte2 = a.getText();
						texte2 = texte2.replace("t", "");	//on enleve t car les lettres fausse la comparaison
						System.out.println("texte2 a recu : " + texte2);
						if (Integer.valueOf(texteRecu) == Integer.valueOf(texte2)) {	//on fait la comparaison en fonction des chiffres restants
							bateauAlierToucher = true;		//si cela correspond au chiffre d'un de nos bateau places alors vrai
							b = (JButton)controleur.jeux.lienVersNosCases.get(i).getSource();
						}
					}
					if (bateauAlierToucher == true) {
						System.out.println("NAVIRE TOUCHER");
						pointsEnnemi+=20;
						controleur.jeux.allierToucher(b);
						controleur.jeux.historiqueDesAction.append("ADVERSAIRE : TOUCHER !!" + '\n');
						pr.println("1");	//on dit a l'adversaire qu'il nous a toucher
						pr.flush();
					}
					else {
						System.out.println("COULER");
						System.out.println("ADVERSAIRE : COULER !!");
						pr.println("0");	//on dit a l'adversaire qu'il nous a rater
						pr.flush();
					}
					bateauAlierToucher = false;		//on remet a false au cas ou l'ennemi a toucher un de nos bateaux
					tour.showMessageDialog(null, "A votre tour!!!", "TOUR", JOptionPane.INFORMATION_MESSAGE);
				}
				/*if (pointsEnnemi == controleur.pointMaxAllier) {
					JOptionPane defaite = new JOptionPane();
					defaite.showMessageDialog(null, "Domage!! vous avez perdu!!", "DEFAITE!!", JOptionPane.INFORMATION_MESSAGE);
					controleur.gameover = true;		//a ce moment la partie s'arrete
				}*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
