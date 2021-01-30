import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegAcceuil {
	private boolean res;
	private final Lock l = new ReentrantLock();
	private final Condition occupe= l.newCondition();
	
	public SegAcceuil() {
		res= false;
	}
	
	public void reserver() throws InterruptedException{
		l.lock();
		try {
			while(res) {
				occupe.await();
			}
			System.out.println("Nouvelle loco in Acceuil");
			res= true;
		}finally {
			l.unlock();
		}
	}
	
	public void liberer() throws InterruptedException{
		l.lock();
		try {
			res= false;
			occupe.signalAll();
		}finally {
			l.unlock();
		}
	}
	
}
