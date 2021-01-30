
public class Client implements Runnable{
	
	private static int cpt = 1;
	private static final Object mutex = new Object();
	private int id;
	private Salon s;
	
	public Client (Salon s) {
		synchronized (mutex) {
			id = cpt++;
		}
		this.s = s;
	}
	
	public void run(){ 
		if(s.entrer(id)) {
			s.actionClient(id);
			System.out.println("Le client "+id+" est recu par le barbier");
			s.sortir(id);
		}
	}
}
