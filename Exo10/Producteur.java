public class Producteur implements Runnable{
	private EnsembleDonnees e;
	private static final Object mutex= new Object();
	private static int cpt = 1;
	public final int idP;
	private int nbA;
	public Producteur(EnsembleDonnees ed, int nbAjout) {
		synchronized(mutex) {
			idP=cpt++;
		}
		e=ed;
		nbA=nbAjout;
	}
	
	public void run() {
		int k;
		for(int i=0; i<3; i++) {
			for(int j=0; j<nbA; j++) {
				k = (int)(Math.random()*11); // 1 a 10
				e.ajoutDonnee(k);
				System.out.println("Prod "+idP+" ajoute "+k);
				Thread.yield();
			}
			try {
				Thread.sleep(300);
			}catch(InterruptedException e) {
				System.out.println("Erreur Sleep Producteur "+idP);
			}
		}
	}
}
