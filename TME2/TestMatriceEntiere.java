import java.io.File;
public class TestMatriceEntiere{
	public static void main(String [] args){
		File f = new File("3");
		File f2 = new File("3bis");
		MatriceEntiere m= new MatriceEntiere(f);
		MatriceEntiere m2= new MatriceEntiere(f2);
		MatriceEntiere m3= null;
		try{
			m3= m.sommeM(m2);
		}catch(TaillesNonConcordantesException e){
			System.out.println(e);
		}
		System.out.println("Somme de 2 matrices\n"+m3.toString()+"\n\n");

		m.prodS(2);
		System.out.println("Mult d'une matrice par un scalaire\n"+m.toString()+"\n\n");

		m.transM();
		System.out.println("Transposée de la matrice d'avant\n"+m.toString()+"\n\n");
		
		f= new File("1");
		f2= new File("1bis");
		m= new MatriceEntiere(f);
		m2= new MatriceEntiere(f2);
		try{
			m3= m.prodM(m2);
		}catch(TaillesNonConcordantesException e){
			System.out.println(e);
		}
		System.out.println("Produit de 2 matrice(dans fichier réponse)\n"+m3.toString()+"\n\n");
	}
}

