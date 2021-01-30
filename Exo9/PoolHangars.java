import java.util.ArrayList;
public class PoolHangars {
	private int taille;
	private ArrayList<Hangar> listh;
	
	public PoolHangars(int n) { // creer array de n hangar
		taille= n;
		listh= new ArrayList<Hangar>();
		for(int i=0; i<taille ; i++)
			listh.add(new Hangar());
	}
	
	/*public Hangar getHangar(int index) {
		return listh.get(index);
	}*/
	
	public int numHVide() { //rend le numero 1er hangar libre(toujours 1 libre)
		int i=0;
		while(i<taille && listh.get(i).occup()) i++;
		return i;
	}
	
	public Hangar getHangar(int i) {  //retourne le hangar i 
		return listh.get(i);
	}
}
