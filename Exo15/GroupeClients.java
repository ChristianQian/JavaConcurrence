import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class GroupeClients {
	private static Object mutex= new Object();
	private static int id=1;
	private final int nbC, idGrp;
	private Integer numT;
	private Restaurant r;
	private Thread [] tabC;
	private CyclicBarrier c;
	private Semaphore s;
	
	public GroupeClients(int nbC, Restaurant r) {
		this.nbC= nbC;
		this.r= r;
		numT= null;
		s= new Semaphore(1);
		c= new CyclicBarrier(nbC, new Signaleur());
		synchronized(mutex){
			idGrp= id++;
		}
		tabC= new Thread [nbC];
		for(int i=0; i < nbC; i++) tabC[i]= new Thread(new Client(this,i+1,c));
		
		for(Thread t: tabC) t.start();
	}
	
	public void reserver(int id) throws InterruptedException{
		s.acquire();
		try{
			if(numT == null) {
				System.out.println("Groupe "+idGrp+" ("+nbC+" clients) | Client "+id+" tente de reserver");
				numT= r.reserver(nbC);
				if(numT == null) {
					System.out.println("Il n'y a plus de place pour le groupe "+ idGrp);
					for(Thread t: tabC)	t.interrupt();
					s.acquire(); //le client qui annule s'interrompt tout seul
				}	
			}
		}finally{
			s.release();
		}
	}
	
	public class Signaleur implements Runnable{
		public void run() {
			System.out.println("Groupe "+idGrp+" est pret a passer commande");
		}
	}
	
}
