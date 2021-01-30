import java.util.ArrayList;

public class TestStation {
	public static void main(String [] args) {
		int n= 5;
		PoolHangars p= new PoolHangars(n);
		SegAcceuil a= new SegAcceuil();
		SegTournant t= new SegTournant(p);
		ArrayList<Thread> l= new ArrayList<>();
		
		Thread tt= new Thread(t); //thread SegTournant
		tt.start();
		for(int i=0; i<n ; i++)  //initalisation loco et de leur thread
			l.add(new Thread(new Loco(a,t,p)));
		
		for(Thread x: l) x.start();
		
		
		try {
			for(Thread x: l) x.join();
		}catch(Exception e) {
			System.out.print("Problem arret du programme");
		}
		tt.interrupt();
		
	}
}
