import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable{
	private PoolHangars p;
	private boolean requete, stable, entre, sortie, stdispo;
	private final Lock l = new ReentrantLock();
	private final Condition appelle= l.newCondition();
	private final Condition mvt= l.newCondition();
	private final Condition charge= l.newCondition();
	private final Condition decharge= l.newCondition();
	private final Condition ancien= l.newCondition();
	
	public SegTournant(PoolHangars p) {
		this.p= p;
		requete= false;
		stable= false;
		entre= false;
		sortie= false;
		stdispo= true;
	}
	
	public void run() {
		try {
			while (true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}catch (InterruptedException e) {
			System.out.println("Terminaison sur interruption du segment tournant");
		}
	}

	public void attendreAppel() throws InterruptedException{
		l.lock();
		try {
			while(!requete) {
				appelle.await();
			}
			requete= false;
		}finally{
			l.unlock();
		}
	}
	
	public void appeler(int ind) throws InterruptedException{
		l.lock();
		try {
			while(!stdispo) {  //wait pour attendre la liberation de ST
				ancien.await();
			}
			stdispo= false;
			requete= true;
			appelle.signalAll();
			System.out.println("SegTournant appelle a l'acceuil");
		}finally {
			l.unlock();
		}
	}
	
	public void seDeplacer() throws InterruptedException{
		l.lock();
		try {
			System.out.println("SegTournant en mvt");
			Thread.sleep(500);
			stable= true;
			mvt.signalAll();
		}finally{
			l.unlock();
		}
	}
	
	public void attendrePositionOK() throws InterruptedException{
		l.lock();
		try {
			while(!stable) {
				mvt.await();
			}
			stable= false;
		}finally{
			l.unlock();
		}
	}
	
	public void entrer(int id) throws InterruptedException{
		l.lock();
		try {
			System.out.println("Loco "+id+" entre sur ST");
			entre= true;
			charge.signalAll();
			
			/*while(!stable) {
				System.out.println("Att ST Stable "+id);
				mvt.await();
			}*/
		}finally {
			l.unlock();
		}
	}
	
	public void attendreEntree() throws InterruptedException{
		l.lock();
		try {
			while(!entre) {
				charge.await();
			}
			entre= false;
		}finally {
			l.unlock();
		}
	}
	
	public void sortir(int id) {
		l.lock();
		try {
			System.out.println("Loco "+id+" sort de ST");
			sortie= true;
			decharge.signalAll();
		}finally {
			l.unlock();
		}
	}
	
	public void attendreVide() throws InterruptedException{
		l.lock();
		try {
			while(!sortie) {
				decharge.await();
			}
			sortie= false;
			stdispo= true;
			ancien.signalAll(); //debloque loco bloque a appeler
		}finally {
			l.unlock();
		}
	}
	
	public int getPosition() {
		return p.numHVide();
	}
}
