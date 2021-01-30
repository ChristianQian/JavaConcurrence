
public class Salon {
	private int placesLibres;
	private int taille;
	private boolean barbierOccupe;
	private final Object synchro = new Object();
	public Salon(int taille) {
		this.taille=taille;
		placesLibres=taille;
		barbierOccupe=false;
	}
	public boolean entrer(int id) {
		synchronized(synchro) {
			if(placesLibres==0) {
				System.out.println("Le salon est plein, le client "+id+" s'en va");
				return false;
			}
			else {
				System.out.println("Le client "+id+" entre dans le salon");
				placesLibres--;
				synchro.notifyAll();
				return true;
			}
		}
	}
	
	
	public void actionBarbier(){
		synchronized(synchro) {
				while(placesLibres==taille) {
					try {
						System.out.println("Le barbier attend des clients");
						synchro.wait();
					}catch (InterruptedException e){
						System.err.println(e+" action barbier");				
					}
				}
				System.out.println("Le barbier est pret a recevoir un client");
				synchro.notifyAll();
		}
	}
	
	public void actionClient(int id) {
		synchronized(synchro) {
			while(barbierOccupe) {
				try {
					System.out.println("Le client "+id+" attend le barbier dans la salle d'attente, le barbier est occupe");
					synchro.wait();
				}catch(InterruptedException e) {
					System.err.println(e+" action client "+id);
				}
			}
			barbierOccupe=true;
			return;
		}
	}
	
	public void sortir(int id) {
		synchronized(synchro) {
			placesLibres++;
			barbierOccupe=false;
			System.out.println("Le client "+id+" sort du salon");
			synchro.notifyAll();
		}
	}
	
	
}
