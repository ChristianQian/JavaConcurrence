//QIAN Christian(3520242) & BALO Harun(3521435)

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String [] args) {
		ExecutorService exec= Executors.newFixedThreadPool(3);
		Restaurant r= new Restaurant(10,2);
		new Thread(new TestRestaurant(r,4)).start();
		exec.shutdown();
	}
}
