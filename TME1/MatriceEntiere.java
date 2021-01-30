import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class MatriceEntiere{
	private int lignes,colonnes;
	private int [][] matrice;

	public MatriceEntiere(int lignes, int colonnes){
		this.lignes= lignes;
		this.colonnes= colonnes;
		matrice= new int [lignes][colonnes];
	}

	public MatriceEntiere(File fichier){
		try{
			Scanner s= new Scanner(fichier);
			lignes= s.nextInt();
			colonnes= s.nextInt();
			matrice= new int [lignes][colonnes];
			for(int i=0; i<lignes; i++){
				for(int j=0; j<colonnes; j++)
					setElem(i,j,s.nextInt());
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
	}
	
	public int getElem(int i,int j){
		return matrice[i][j];
	}

	public void setElem(int i, int j, int val){
		matrice[i][j]=val;
	}


	public String toString(){
		String m="";
		for(int i=0; i<lignes; i++){
			for(int j=0; j<colonnes; j++)
				m+= getElem(i,j)+" ";	
			m+="\n";
		}
		return m;
	}
	

	public void initM(){
		for(int i=0; i<lignes; i++){
			for(int j=0; j<colonnes; j++)
				matrice[i][j]=0;
			
		}
	}

	public MatriceEntiere sommeM(MatriceEntiere m2) throws TaillesNonConcordantesException{
		if(lignes!=m2.getNbLignes() || colonnes!=m2.getNbColonnes())
			throw new TaillesNonConcordantesException();
		
		MatriceEntiere s= new MatriceEntiere(lignes, colonnes);
		for(int i=0; i<s.getNbLignes(); i++){
			for(int j=0; j<s.getNbColonnes(); j++)
				s.setElem(i,j, matrice[i][j] + m2.getElem(i,j));
		}
		return s;
	}

	public void prodS(int p){
		for(int i=0; i<lignes; i++){
			for(int j=0; j<colonnes; j++)
				matrice[i][j]*= p;
		}
	}

	public void transM(){
		int [][] t= new int [colonnes][lignes];
		for(int j=0; j<colonnes; j++){
			for(int i=0; i<lignes; i++)
				t[j][i]= matrice[i][j];
		}
		matrice= t;
	}

	public MatriceEntiere prodM(MatriceEntiere m2) throws TaillesNonConcordantesException{
		if(colonnes != m2.getNbLignes())
			throw new TaillesNonConcordantesException();
		MatriceEntiere p= new MatriceEntiere(lignes, m2.getNbColonnes());
		int som;
		for(int i=0; i<lignes; i++){
			for(int j=0; j<m2.getNbColonnes(); j++){
				som=0;
				for(int k=0; k<colonnes; k++)
					som+= matrice[i][k] * m2.getElem(k,j);
				p.setElem(i,j,som);
			}
		}
		return p;
	}
}

			
		
	







		

	
			
			
