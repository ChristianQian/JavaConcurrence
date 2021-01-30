import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;

public class Client implements Runnable{
	private static final Object mutex= new Object();
	private static int cpt = 1, actif=0;
	private final Object reveil = new Object();
	public final int idC;
	private ArrayBlockingQueue<Requete> a;
	private ArrayList<ReponseRequete> rr;
	private Future<ReponseRequete> fres;


	public Client(ArrayBlockingQueue<Requete> a) {
		synchronized(mutex) {
			idC=cpt++;
			actif++;
		}
		this.a=a;
		rr = new ArrayList<>();
		fres= null;
	}

	public void requeteTraitee(){
		synchronized(reveil){
			reveil.notifyAll();
		}
	}

	public void newFuture(Future<ReponseRequete> rep){
		fres= rep;
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
				rr.add(fres.get()); //stock reponserequete depuis un future
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("Client "+idC+" requete "+(i+1)+" traite");
		}
		System.out.println("Client "+idC+" a fini");
	}
}
