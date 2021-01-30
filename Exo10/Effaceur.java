import java.util.Random;

public class Effaceur implements Runnable{
	private Random gen= new Random();
	private EnsembleDonnees e;
	private static final Object mutex= new Object();
	private static int cpt = 1;
	public final int idE;
	
	public Effaceur(EnsembleDonnees ed) {
		synchronized(mutex) {
			idE=cpt++;
		}
		e=ed;
	}
	
	public void run() {
			int r;
			for(int i=0; i<3; i++) {
				try {
					r= gen.nextInt(11);
					e.suppDonnee(r);
					System.out.println("Eff"+idE+" a efface "+r);
					try {
						Thread.sleep(300);
					}catch(InterruptedException e) {
						System.out.println("Erreur Sleep Effaceur "+idE);
					}
				}catch(NoElementException err) {
					System.out.println(err.getMessage());
					i--;
				}
			}
	}
}
