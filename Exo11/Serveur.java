import java.util.concurrent.ArrayBlockingQueue;
public class Serveur implements Runnable{
	private ArrayBlockingQueue<Requete> a;
	private static final Object synchro = new Object();
	
	public Serveur(ArrayBlockingQueue<Requete> a){
		this.a = a;
	}
	
	
	public void soumettre(){
		synchronized(synchro){
			synchro.notifyAll();
		}
	}

	public void attendreRequete() throws InterruptedException{
		synchronized(synchro){
			synchro.wait();
		}
	}

	public void traiterRequete() throws InterruptedException{
		Requete r= a.take();
		System.out.println("Traitement Client "+r.getClient().getidC()+" Requete "+r.getNum()+" Type "+r.getType());
		new Thread(new Servant(r)).start();
	}
		
		
	public void run(){
		try{
			while(true) {
				//attendreRequete(); //plus besoin d'attendre la terminaison d'une requete
				traiterRequete();
			}	
		}catch(InterruptedException e) {
			System.out.println("Serveur interrompu");
		}
	}

}
