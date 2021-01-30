import java.util.Random;
public class AleaObjet{
	Random r= new Random();
	private int poids;
	private static int cpt=1;
	private final int id;
	private final Object mutex = new Object();
	
	public AleaObjet(int max, int min){
		poids= r.nextInt(max-min +1) + min;
		synchronized(mutex){
			id = cpt++;
		}
	}

	public int getPoids(){
		return poids;
	}
	
	public int getId() {
		return id;
	}

	public String toString(){
		return "Marchandise de poids: "+poids;
	}
}
