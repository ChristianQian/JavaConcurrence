import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Serveur implements Runnable{
	private ArrayBlockingQueue<Requete> a;
	private static final Object synchro = new Object();
	private ExecutorService pc, exec;
	private ExecutorCompletionService<ReponseRequete> execP;
	
	public Serveur(ArrayBlockingQueue<Requete> a, ExecutorService ec){
		this.a = a;
		exec= Executors.newCachedThreadPool();
		execP= new ExecutorCompletionService<ReponseRequete>(exec);
		pc= ec;	
	}
	
	public void traiterRequete() throws InterruptedException{
		Requete r= a.poll(1, TimeUnit.SECONDS);
		if(r != null){
			System.out.println("Traitement Client "+r.getClient().getidC()+" Requete "+r.getNum()+" Type "+r.getType());
			r.getClient().newFuture(execP.submit(new Servant(r)));
		}
	}
		
	public void run(){
		while(!pc.isTerminated()){
			try{
				traiterRequete();
			}catch(InterruptedException e) {
				System.out.println("probleme serveur");
			}
		}
		exec.shutdown();
	}

}
