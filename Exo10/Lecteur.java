
public class Lecteur implements Runnable{
	private EnsembleDonnees e;
	private static final Object mutex= new Object();
	private static int cpt = 1;
	public final int idL;
	public Lecteur(EnsembleDonnees ed) {
		synchronized(mutex) {
			idL=cpt++;
		}
		e=ed;
	}
	
	public void run() {
		for(int i=0; i < 3 ; i++) {
			System.out.println("Lect"+idL+" lit : "+e);
			try {
				Thread.sleep(300);
			}catch(InterruptedException err) {
				System.out.println("Erreur Sleep Lecteur "+idL);
			}
		}
	}
	
}
