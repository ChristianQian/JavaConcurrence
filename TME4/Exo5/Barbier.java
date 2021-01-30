
public class Barbier implements Runnable{
	private Salon s;
	
	public Barbier(Salon s) {
		this.s=s;
	}
	
	public void run() {
	//	while(!Thread.currentThread().isInterrupted()){
		s.actionBarbier();
	//	}
	}
}
