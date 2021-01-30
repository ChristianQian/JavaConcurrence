import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
public class Test{
	final static int NB_CLIENTS= 5;
	public static void main(String [] args){
		ArrayBlockingQueue<Requete> ar= new ArrayBlockingQueue<Requete>(NB_CLIENTS*5); // 5 requete par client
		Serveur s= new Serveur(ar);
		Thread ts= new Thread(s);
		ArrayList<Thread> lc= new ArrayList<>();
		ts.start();
		for(int i=0; i < NB_CLIENTS ; i++)
			lc.add(new Thread(new Client(ar,ts)));
		for(Thread c: lc)
			c.start();
	}
}
