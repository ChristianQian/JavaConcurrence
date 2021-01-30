import java.util.concurrent.CyclicBarrier;
import java.util.Random;
public class Client implements Runnable{
	private GroupeClients g;
	private final int id;
	private CyclicBarrier c;
	private Random gen= new Random();

	public Client(GroupeClients g, int id, CyclicBarrier c) {
		this.g= g;
		this.id= id;
		this.c= c;
	}

	public void run() {
		try{
			g.reserver(id);
			Thread.sleep(gen.nextInt(3000));
			c.await(); 
		}catch(Exception e) {}
	}
}
