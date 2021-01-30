//QIAN Christian(3520242) & BALO Harun(3521435)
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRestaurant implements Runnable{
	private Restaurant r;
	private int nbGrp;
	Random gen= new Random();
	private GroupeClients [] tabg;
	
	public TestRestaurant(Restaurant r, int nbg) {
		this.r= r;
		this.nbGrp= nbg;
		tabg= new GroupeClients [nbg];
	}
	
	public void run() {
		for(int i=0; i<nbGrp; i++) 
			tabg[i]= new GroupeClients(gen.nextInt(9)+1, r);
	}
}
