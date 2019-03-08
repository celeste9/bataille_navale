package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;

public class BattleShipUI extends JFrame {

	private static final long serialVersionUID = 1L;
	//attributs
	public static int nbCases;
	int points = 20;
	int pointsPersonnels;
	int pointsEnnemi;
	int pointsOrdi;
	int scoreAatteindre;
	Controleur contre;
	//principaux jpanels
	JPanel jp_Menu;
	JPanel jp_Instructions_Jeux;
	JPanel jp_Partie_Solo;
	JPanel jp_Partie_Multijoueur;
	JPanel jp_Mode_De_Jeu;

	//contenus des principaux jpanels
	JPanel jp_Demarrer;
	JPanel jp_instruction;
	JPanel jp_quitter;
	JPanel jp_jouerContreOrdi;
	JPanel jp_multijoueur;
	JPanel jp_retour;
	JPanel jp_haut;
	JPanel jp_bas;
	JPanel jp_gauche;
	JPanel jp_droite;
	JPanel jp_1;   //
	JPanel jp_2;	//
	JPanel jp_3;	//

	//les boutons des jpanels
	JButton jb_Demarrer;
	JButton jb_instruction;
	JButton jb_solo;
	JButton jb_multijoueur;
	JButton jb_retour_menu;
	JButton jb_retour_instructions;
	public JButton jb_hote;   //
	public JButton jb_inviter; //
	JButton jb_ok;    //
	JButton jb_start;
	JButton jb_pause;
	JButton jb_quitter_partie;
	JButton jb_quitter_jeu;
	JButton jb_valider;
	//boutons de jeux
	JButton positionAlier;
	JButton positionOrdi;
	JButton positionEnnemi;

	//les instructions
	JLabel instructions = new JLabel("INSTRUCIONS");
	JTextArea listeInstruction = new JTextArea();
	JLabel imageJeu = new JLabel(new ImageIcon("image/ecran_de_jeu.jpg"));
	
	//les affichages
	JLabel role = new JLabel("qui voulez-vous etre ?");
	JLabel ip_hote = new JLabel("IP de l'Hote:");
	JLabel scorePersonel = new JLabel("VOTRE SCORE : xpts");
	JLabel scoreOrdi = new JLabel("SCORE Ordi : xpts");
	JLabel scoreEnnemi = new JLabel("SCORE Ennemi : xpts");

	//les champs de saisi
	JTextField saisir_ip = new JTextField(); 

	//scroll
	JScrollPane scroll;

	//liste des navires disponibles
	public JTextArea listeNaviresDisponibles;
	public static JTextArea historiqueDesAction;

	public ArrayList<JButton> casesOrdi = new ArrayList<JButton>();
	public ArrayList<JButton> casesAlier = new ArrayList<JButton>();
	public static ArrayList<ActionEvent> lienVersNosCases = new ArrayList<ActionEvent>();

	public int getPointsPersonnels() {
		return pointsPersonnels;
	}

	public int getPointsEnnemi() {
		return pointsEnnemi;
	}

	public int getPointsOrdi() {
		return pointsOrdi;
	}

	public int getScoreAatteindre() {
		return scoreAatteindre;
	}

	public String getSaisir_ip() {
		return saisir_ip.getText();
	}

	public BattleShipUI(Controleur controle) {
		super("BattleShip");
		setSize(1200, 650);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/BS.png").getImage());
		contre = controle;

		//principaux jpanels
		jp_Menu = new JPanel();
		jp_Instructions_Jeux = new JPanel();
		jp_Mode_De_Jeu = new JPanel();
		jp_Partie_Solo = new JPanel();
		jp_Partie_Multijoueur = new JPanel();

		//contenus des principaux jpanels
		jp_Demarrer = new JPanel();
		jp_instruction = new JPanel();
		jp_quitter = new JPanel();
		jp_jouerContreOrdi = new JPanel();
		jp_multijoueur = new JPanel();
		jp_retour = new JPanel();
		jp_haut = new JPanel();
		jp_bas = new JPanel();
		jp_droite = new JPanel();
		jp_gauche = new JPanel();
		jp_1 = new JPanel();
		jp_2 = new JPanel();
		jp_3 = new JPanel();

		//configuration des panels
		jp_Menu.setLayout(new GridLayout(3,0));
		jp_Mode_De_Jeu.setLayout(new GridLayout(3,0));
		jp_Partie_Solo.setLayout(new GridLayout(2,0));
		jp_Partie_Multijoueur.setLayout(new GridLayout(3,0));
		jp_haut.setBackground(Color.cyan);
		jp_bas.setLayout(new GridLayout(0, 2));
		jp_droite.setLayout(new GridLayout(10,10));
		jp_gauche.setLayout(new GridLayout(10,10));

		//les boutons des jpanels
		jb_Demarrer = new JButton("DEMARRER");
		jb_instruction = new JButton("INSTRUCTIONS");
		jb_start = new JButton("START");
		jb_pause = new JButton("PAUSE");
		jb_quitter_jeu = new JButton("QUITTER");
		jb_quitter_partie = new JButton("QUITTER LA PARTIE");
		jb_solo = new JButton("SOLO");
		jb_multijoueur = new JButton("VS ADVERSAIRE (RESEAU)");
		jb_retour_menu = new JButton("RETOUR");
		jb_retour_instructions = new JButton("ACCEUIL");
		jb_valider = new JButton("valider le navire");
		jb_hote = new JButton("HOTE");
		jb_inviter = new JButton("INVITEE");
		jb_ok = new JButton("OK");

		//configurations des boutons des jpanels
		jb_start.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_pause.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_instruction.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_Demarrer.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_quitter_jeu.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_quitter_partie.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_solo.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_multijoueur.addActionListener(new EcouteurBoutonsBasics(controle));
		jb_hote.addActionListener(new EcouteurBoutonsBasics(controle));
		jb_inviter.addActionListener(new EcouteurBoutonsBasics(controle));
		jb_ok.addActionListener(new EcouteurBoutonsBasics(controle));
		jb_retour_menu.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_retour_instructions.addActionListener(new EcouteurBoutonsBasics(controle)); //this fait reference au JFrame
		jb_valider.addActionListener(new EcouteurValider());

		//configurations de la liste d'instructions
		listeInstruction.setPreferredSize(new Dimension(500,200));
		listeInstruction.setEditable(false);
		listeInstruction.append("-Cliquez sur les cases de droite pour deviner la position des navires adverses" + '\n');
		listeInstruction.append("-Les cases de gauche sont votre flote de navires" + '\n');
		listeInstruction.append("les cases de vos navires doivent etres placees horizontalement ou verticalement" + '\n');
		listeInstruction.append("-Le joueur inviter commence toujours en premier (MULTIJOUEUR RESEAU)" + '\n');
		
		//configurations de la liste des navires
		historiqueDesAction = new JTextArea("			Historiques des actions :" + '\n');
		historiqueDesAction.setEditable(false);
		listeNaviresDisponibles = new JTextArea(6,100);
		listeNaviresDisponibles.setEditable(false);
		listeNaviresDisponibles.append("						Navires Disponibles : " + '\n');

		//configuration du scroll
		scroll = new JScrollPane(historiqueDesAction);
		scroll.setPreferredSize(new Dimension(600,100));

		//configuration des boutons de jeux
		for (int x = 0; x < 10; x++) {
			for (int j = 0; j < 10;j++) {
				positionAlier = new JButton("t" + x + j);
				positionAlier.setIcon(new ImageIcon("image/water.jpg"));
				positionAlier.addActionListener(new EcouteurAllier(controle));
				casesAlier.add(positionAlier);
				jp_gauche.add(positionAlier);
			}

		}
		for (int x = 0; x < 10; x++) {
			for (int j = 0; j < 10;j++) {
				positionOrdi = new JButton("i" + x + j);
				positionOrdi.addActionListener(new EcouteurOrdiEtEnnemi(controle));
				int w = (int) (Math.random() * 9);
				if (j == w) {
					casesOrdi.add(positionOrdi);
					scoreAatteindre+=20;
				}
				jp_droite.add(positionOrdi);
			}
		}


		//configuration du champ de texte
		saisir_ip.setPreferredSize(new Dimension(150,25));
		saisir_ip.setEnabled(false);

		//on ajoute les elements necessaires aux panels
		jp_Demarrer.add(jb_Demarrer);
		jp_instruction.add(jb_instruction);
		jp_quitter.add(jb_quitter_jeu);

		jp_jouerContreOrdi.add(jb_solo);
		jp_multijoueur.add(jb_multijoueur);
		jp_retour.add(jb_retour_menu);

		jp_bas.add(jp_gauche);
		jp_bas.add(jp_droite);

		jp_haut.add(jb_start);
		jp_haut.add(jb_pause);
		jp_haut.add(jb_quitter_partie);
		jp_haut.add(jb_valider);
		jp_haut.add(listeNaviresDisponibles);
		jp_haut.add(scorePersonel);
		jp_haut.add(scoreOrdi);
		jp_haut.add(scroll);

		jp_1.add(role);
		jp_1.add(jb_hote);
		jp_1.add(jb_inviter);
		jp_2.add(ip_hote);
		jp_2.add(saisir_ip);
		jp_3.add(jb_ok);

		//jpanels principaux du jeux
		jp_Menu.add(jp_Demarrer);
		jp_Menu.add(jp_instruction);
		jp_Menu.add(jp_quitter);

		jp_Instructions_Jeux.add(instructions);
		jp_Instructions_Jeux.add(listeInstruction);
		jp_Instructions_Jeux.add(imageJeu);
		jp_Instructions_Jeux.add(jb_retour_instructions);
		
		jp_Mode_De_Jeu.add(jp_jouerContreOrdi);
		jp_Mode_De_Jeu.add(jp_multijoueur);
		jp_Mode_De_Jeu.add(jp_retour);

		jp_Partie_Solo.add(jp_haut);
		jp_Partie_Solo.add(jp_bas);

		jp_Partie_Multijoueur.add(jp_1);
		jp_Partie_Multijoueur.add(jp_2);
		jp_Partie_Multijoueur.add(jp_3);

		this.add(jp_Menu);
		setVisible(true);
	}

	public void MenuVersModeDeJeux() {
		this.remove(jp_Menu);
		this.add(jp_Mode_De_Jeu);
		this.repaint();
		this.setSize(1201, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void MenuVersInstructions() {
		this.remove(jp_Menu);
		this.add(jp_Instructions_Jeux);
		this.repaint();
		this.setSize(1204, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void InstructionsVersMenu() {
		this.remove(jp_Instructions_Jeux);
		this.add(jp_Menu);
		this.repaint();
		this.setSize(1200, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}
	
	public void ModeDeJeuxVersMenu() {
		this.remove(jp_Mode_De_Jeu);
		this.add(jp_Menu);
		this.repaint();
		this.setSize(1200, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void ModeDeJeuxVersSolo() {
		contre.naviresDisponibles();
		this.remove(jp_Mode_De_Jeu);
		this.add(jp_Partie_Solo);
		this.repaint();
		this.setSize(1202, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void ModeDeJeuxVersMultiloueur() {
		this.remove(jp_Mode_De_Jeu);
		this.add(jp_Partie_Multijoueur);
		this.repaint();
		this.setSize(1203, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void lancerMultijoueur() {
		contre.naviresDisponibles();
		/*for (int x = 0; x < 100; x++) {
			positionOrdi.addActionListener(new EcouteurEnnemi(contre));
		}*/
		this.remove(jp_Partie_Multijoueur);
		this.add(jp_Partie_Solo);		//on utilisera le panel de la partie solo pour la partie multijoueur
		this.repaint();
		this.setSize(1200, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void SoloVersMenu() {
		this.remove(jp_Partie_Solo);
		this.add(jp_Menu);
		this.repaint();
		this.setSize(1200, 650);	//OBLIGER POUR FAIRE APPARAITRE LES ELEMENTS
	}

	public void allierToucher(JButton s) {
		s.setIcon(new ImageIcon("image/boum.png"));
		s.setEnabled(false);
	}

	public void majHote() {
		jb_hote.setEnabled(false);
	}

	public void majInvitee() {
		jb_inviter.setEnabled(false);
		saisir_ip.setEnabled(true);
	}

	public void creationDesConnexions() {
		jb_ok.setEnabled(false);
		if (jb_hote.isEnabled() == false && jb_ok.isEnabled() == false) {
			contre.creationServeur();
		}
		if (jb_inviter.isEnabled() == false && jb_ok.isEnabled() == false) {
			contre.connexionServeur(this.getSaisir_ip());
		}
	}

	public void majScorePersonnel() {
		pointsPersonnels+= points;
		scorePersonel.setText("VOTRE SCORE : " + pointsPersonnels + "pts");
	}

	public void majScoreOrdi() {
		pointsOrdi+= points;
		scoreOrdi.setText("SCORE ORDI : " + pointsOrdi + "pts");
	}

	public void ordiToucher(JButton s) {
		s.setBackground(Color.RED);
		s.setEnabled(false);
	}

	public void ennemiToucher(JButton s) {
		s.setIcon(new ImageIcon("image/fire.jpg"));
		s.setEnabled(false);
	}
}



//////////////////////////////////////////////////////////////////////////////////////////



class EcouteurBoutonsBasics implements ActionListener {
	Controleur controle;
	public EcouteurBoutonsBasics(Controleur contr) {
		controle = contr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controle.actionBasics(e);
	}
}

class EcouteurValider implements ActionListener {
	int nbNavires = 0; //SOLUTION TEMPORAIRE
	@Override
	public void actionPerformed(ActionEvent e) {
		if (BattleShipUI.nbCases == 2 || BattleShipUI.nbCases == 3 || BattleShipUI.nbCases == 4 ) {
			nbNavires++;
			BattleShipUI.historiqueDesAction.append("NAVIVE PLACER !!" + '\n');
			BattleShipUI.nbCases = 0;
		}
		if (nbNavires == 4) {
			JButton s = (JButton) e.getSource();
			s.setEnabled(false);
		}
	}
}

//ecouteur pour chaque case de navire(boutons) qu'on placera
class EcouteurAllier implements ActionListener {
	Controleur controle;
	public EcouteurAllier(Controleur contr) {
		controle = contr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		BattleShipUI.nbCases++;
		controle.placerNavire(e);
		BattleShipUI.lienVersNosCases.add(e);	//chaque bouton est mis dans notre liste sous forme de ActionEvent
	}
}

class EcouteurOrdiEtEnnemi implements ActionListener {		//meme ecouteur pour les parties SOLO et MULTIJOUEUR
	Controleur controle;
	public EcouteurOrdiEtEnnemi(Controleur contr) {
		controle = contr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (controle.start != 0 && (controle.pause%2 == 0) ){ //start et pause doivent etre bon pour lancer le jeu
			controle.effectuerTirSurOrdiOuEnnemi(e);	//dans cette methode l'ordi replique en fesant un tir aussi
		}
	}
}
