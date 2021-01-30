import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class TestProduitParallele{
	public static void main(String[] args){
		File f = new File("1");
		File f2 = new File("1bis");
		MatriceEntiere m= new MatriceEntiere(f);
		MatriceEntiere m2= new MatriceEntiere(f2);	
		MatriceEntiere m3= new MatriceEntiere(m.getNbLignes(), m2.getNbColonnes());
		int to= m.getNbLignes()*m2.getNbColonnes();
		Thread [] th= new Thread[to];
		for(int i=0; i<m.getNbLignes(); i++){
			for(int j=0; j<m2.getNbColonnes(); j++){
				th[i*m2.getNbColonnes() + j]= new Thread(new CalculElem(m3, i, j, m, m2));
				th[i*m2.getNbColonnes() + j].start();
			}
		}
		for(int i=0; i< to; i++){
			try{
				th[i].join();
			}catch(InterruptedException e){
				e.getMessage();
			}
		}
		System.out.println(m3.toString());
	}
}
		
	
