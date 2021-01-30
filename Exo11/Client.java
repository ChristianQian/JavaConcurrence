import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
public class Client implements Runnable{
	private Serveur s;
	private static final Object mutex= new Object();
	private static int cpt = 1, actif=0;
	private final Object reveil = new Object();
	public final int idC;
	private ArrayBlockingQueue<Requete> a;
	private ArrayList<ReponseRequete> rr;
	private Thread ts;

	public Client(ArrayBlockingQueue<Requete> a, Thread ts) {
		synchronized(mutex) {
			idC=cpt++;
			actif++;
		}
		this.s= s;
		this.ts= ts;
		this.a=a;
		rr = new ArrayList<>();
	}

	public void requeteTraitee(ReponseRequete rep){
		
		synchronized(reveil){
			reveil.notifyAll();
		}
	}

	public int getidC(){
		return idC;
	}

	public void run(){
		for(int i=0; i<5 ; i++){
			try{
				a.put(new Requete(this, i+1, ((idC % 3!=0)? 1 : 2)));
				synchronized(reveil){
					reveil.wait();
				}
			}catch(InterruptedException e){
				System.out.println("wait client prob "+e);
			}
			System.out.println("Client "+idC+" requete "+(i+1)+" traite");
		}
		System.out.println("Client "+idC+" a fini");
		synchronized(mutex){
			actif--;
			if(actif==0){
				System.out.println("Client "+idC+" est le dernier");
				ts.interrupt();
			}
		}
	}

}
