import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cube {

	Face[] faces;

	public Cube() {
		faces = new Face[6];
		for(int i = 0 ; i < 6 ; i++) {
			faces[i] = new Face(i);
		}
		faces[0].ajouterVoisins(faces[1], 0, faces[2], 0, faces[3], 0, faces[4], 0);
		faces[1].ajouterVoisins(faces[5], 1, faces[2], 1, faces[0], 1, faces[4], 3);
		faces[2].ajouterVoisins(faces[3], 1, faces[0], 2, faces[1], 3, faces[5], 0);
		faces[3].ajouterVoisins(faces[0], 3, faces[2], 3, faces[5], 3, faces[4], 1);
		faces[4].ajouterVoisins(faces[0], 0, faces[3], 3, faces[5], 2, faces[1], 1);
		faces[5].ajouterVoisins(faces[4], 2, faces[3], 2, faces[2], 2, faces[1], 2);
	}
	
	public Cube(String s) {
		faces = new Face[6];
		for(int i = 0 ; i < 6 ; i++) {
			faces[i] = new Face(s.substring(9*i, 9*(i+1)));
		}
		faces[0].ajouterVoisins(faces[1], 0, faces[2], 0, faces[3], 0, faces[4], 0);
		faces[1].ajouterVoisins(faces[5], 1, faces[2], 1, faces[0], 1, faces[4], 3);
		faces[2].ajouterVoisins(faces[3], 1, faces[0], 2, faces[1], 3, faces[5], 0);
		faces[3].ajouterVoisins(faces[0], 3, faces[2], 3, faces[5], 3, faces[4], 1);
		faces[4].ajouterVoisins(faces[0], 0, faces[3], 3, faces[5], 2, faces[1], 1);
		faces[5].ajouterVoisins(faces[4], 2, faces[3], 2, faces[2], 2, faces[1], 2);
	}

	public void rotate(int face, int n) {
		switch(n) {
		case 0:
			return;
		case 1:
			faces[face].quartTourDirect();
			break;
		case 3:
			faces[face].quartTourIndirect();
			break;
		case 2:
			faces[face].demiTour();
			break;
		default:
			rotate(face, ((n%4)+4)%4);
		}
	}
	
	public void combinaison(String s) {
		for(int i = 0 ; i < s.length()/2 ; i++) {
			rotate(((int) s.charAt(2*i))-48, ((int) s.charAt(2*i+1))-48);
		}
	}
	
	public void melange(int n) {
		for(int i = 0 ; i < n ; i++) {
			rotate((int) (6*Math.random()), (int) (4*Math.random()));
		}
	}

	public boolean estResolu() {
		boolean rep = true;
		for(int i = 0 ; rep && i < 6 ; i++) {
			rep &= faces[i].estResolue();
		}
		return rep;
	}
	
	public void display() {
		JFrame display = new JFrame();
		display.setSize(500, 400);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setLocationRelativeTo(null);
		display.setVisible(true);
		display.setResizable(false);
		display.setContentPane(new Display());
	}

	public class Display extends JPanel {

		private static final long serialVersionUID = 1L;
		private int taille = 30;
		private Color[] color = {Color.WHITE, Color.GREEN, Color.RED, Color.BLUE, new Color(255, 120, 0), Color.YELLOW};
		private int[] positionx = {1, 0, 1, 2, 3, 1};
		private int[] positiony = {0, 1, 1, 1, 1, 2};

		public void paint(Graphics g) {
			for(int i = 0 ; i < 6 ; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					for(int k = 0 ; k < 3 ; k++) {
						g.setColor(color[faces[i].couleurs[j][k][0]%6]);
						g.fillRect(50 + (3*taille+2)*positionx[i] + k*taille + 1, 50 + (3*taille+2)*positiony[i] + j*taille + 1, taille - 1, taille - 1);
						g.setColor(Color.BLACK);
						g.drawRoundRect(50 + (3*taille+2)*positionx[i] + k*taille, 50 + (3*taille+2)*positiony[i] + j*taille, taille, taille, 8, 8);
					}
				}
			}
		}

	}

}
