
public class Loco implements Runnable{
	private SegAcceuil a;
	private SegTournant t;
	private PoolHangars p;
	private static int cpt=1;
	private final int id;
	private final Object mutex = new Object();
	
	public Loco(SegAcceuil a, SegTournant t, PoolHangars p) {
		this.a= a;
		this.t= t;
		this.p= p;
		synchronized(mutex){
			id = cpt++;
		}
	}

	public void run() {
		try {
			a.reserver();
			t.appeler(0);
			t.attendrePositionOK();
			a.liberer();
			t.entrer(id);
			t.attendrePositionOK();
			p.getHangar( t.getPosition() ).entrer(id);
			t.sortir(id);
		}
			catch (InterruptedException e) {
				System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
		}
	}
}
