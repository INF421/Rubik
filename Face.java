public class Face {

	int[][][] couleurs; // Grille 3*3 des couleurs de la face
	int[][][] voisins; // Grille 4*3 des couleurs des zones adjacentes à la face

	public Face() {
		couleurs = new int[3][3][1];
		voisins = new int[4][3][1];
	}

	public Face(int coul) {
		couleurs = new int[3][3][1];
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				couleurs[i][j][0] = coul;
			}
		}
		voisins = new int[4][3][1];
	}

	public Face(String coul) {
		couleurs = new int[3][3][1];
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				couleurs[i][j][0] = ((int) coul.charAt(3*i+j)) - 48;
			}
		}
		voisins = new int[4][3][1];
	}

	public void ajouterVoisins(Face f0, int i0, Face f1, int i1, Face f2, int i2, Face f3, int i3) {
		voisins[0] = extracteur(f0, i0);
		voisins[1] = extracteur(f1, i1);
		voisins[2] = extracteur(f2, i2);
		voisins[3] = extracteur(f3, i3);
	}

	public int[][] extracteur(Face f, int i) {
		int[][] rep = new int[3][1];
		switch(i) {
		case 0:
			for(int j = 0 ; j < 3 ; j++) {
				rep[j] = f.couleurs[0][2-j];
			}
			break;
		case 2:
			for(int j = 0 ; j < 3 ; j++) {
				rep[j] = f.couleurs[2][j];
			}
			break;
		case 1:
			for(int j = 0 ; j < 3 ; j++) {
				rep[j] = f.couleurs[j][0];
			}
			break;
		case 3:
			for(int j = 0 ; j < 3 ; j++) {
				rep[j] = f.couleurs[2-j][2];
			}
			break;
		default:
		}
		return rep;
	}

	public void quartTourDirect() {
		int[] buff0 = new int[2];
		buff0[0] = couleurs[0][2][0];
		buff0[1] = couleurs[1][2][0];
		couleurs[1][2][0] = couleurs[2][1][0];
		couleurs[2][1][0] = couleurs[1][0][0];
		couleurs[1][0][0] = couleurs[0][1][0];
		couleurs[0][2][0] = couleurs[2][2][0];
		couleurs[2][2][0] = couleurs[2][0][0];
		couleurs[2][0][0] = couleurs[0][0][0];
		couleurs[0][0][0] = buff0[0];
		couleurs[0][1][0] = buff0[1];
		
		int[] buff1 = new int[3];
		for(int i = 0 ; i < 3 ; i++) buff1[i] = voisins[3][i][0];
		for(int i = 3 ; i > 0 ; i--) {
			for(int j = 0 ; j < 3 ; j++) voisins[i][j][0] = voisins[i-1][j][0];
		}
		for(int i = 0 ; i < 3 ; i++) voisins[0][i][0] = buff1[i];
	}
	
	public void quartTourIndirect() {
		int[] buff0 = new int[2];
		buff0[0] = couleurs[0][2][0];
		buff0[1] = couleurs[1][2][0];
		couleurs[1][2][0] = couleurs[0][1][0];
		couleurs[0][1][0] = couleurs[1][0][0];
		couleurs[1][0][0] = couleurs[2][1][0];
		couleurs[0][2][0] = couleurs[0][0][0];
		couleurs[0][0][0] = couleurs[2][0][0];
		couleurs[2][0][0] = couleurs[2][2][0];
		couleurs[2][2][0] = buff0[0];
		couleurs[2][1][0] = buff0[1];
		
		int[] buff1 = new int[3];
		for(int i = 0 ; i < 3 ; i++) buff1[i] = voisins[0][i][0];
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) voisins[i][j][0] = voisins[i+1][j][0];
		}
		for(int i = 0 ; i < 3 ; i++) voisins[3][i][0] = buff1[i];
	}
	
	public void demiTour() {
		int[] buff0 = new int[2];
		buff0[0] = couleurs[0][2][0];
		buff0[1] = couleurs[1][2][0];
		int[] buff1 = new int[2];
		buff1[0] = couleurs[0][0][0];
		buff1[1] = couleurs[0][1][0];
		couleurs[1][2][0] = couleurs[1][0][0];
		couleurs[0][1][0] = couleurs[2][1][0];
		couleurs[0][2][0] = couleurs[2][0][0];
		couleurs[0][0][0] = couleurs[2][2][0];
		couleurs[2][2][0] = buff1[0];
		couleurs[2][1][0] = buff1[1];
		couleurs[2][0][0] = buff0[0];
		couleurs[1][0][0] = buff0[1];
		
		int[] buff3 = new int[3];
		int[] buff4 = new int[3];
		for(int i = 0 ; i < 3 ; i++) {
			buff3[i] = voisins[0][i][0];
			buff4[i] = voisins[1][i][0];
		}
		for(int i = 0 ; i < 3 ; i++) {
			voisins[0][i][0] = voisins[2][i][0];
			voisins[1][i][0] = voisins[3][i][0];
		}
		for(int i = 0 ; i < 3 ; i++) {
			voisins[3][i][0] = buff4[i];
			voisins[2][i][0] = buff3[i];
		}
	}
	
	public boolean estResolue() {
		boolean rep = true;
		for(int i = 0 ; rep && i < 3 ; i++) {
			for (int j = 0 ; rep && j < 3 ; j++) {
				rep &= (couleurs[i][j][0] == couleurs[1][1][0]);
			}
		}
		return rep;
	}
}
