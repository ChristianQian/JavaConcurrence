import java.util.ArrayList;

public class Dechargeur implements Runnable{
	private Chariot c;
	private AleaStock s;
	private ArrayList<AleaObjet> dest;
	
	public Dechargeur(Chariot c, AleaStock s){
		this.c= c;
		this.s= s;
		dest= new ArrayList<>();
	}
	
	public void addDest(AleaObjet o) {
		dest.add(o);
	}

	public void run(){
		while(!c.getRemplir() || !s.stockVide()){ //while(true){  
			try {
				c.vider(this);
			}catch(InterruptedException e) {
				System.out.println("Problem thread dechargeur");
			}
		}
	}
	
}
