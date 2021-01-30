//QIAN Christian(3520242) & BALO Harun(3521435)

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
public class Test{
	final static int NB_CLIENTS= 5;
	public static void main(String [] args){
		ExecutorService ec = Executors.newFixedThreadPool(NB_CLIENTS); 
		ArrayBlockingQueue<Requete> ar= new ArrayBlockingQueue<Requete>(NB_CLIENTS*5); // 5 requete par client
		Thread ts= new Thread(new Serveur(ar, ec));
		ts.start();
		for(int i=0; i < NB_CLIENTS ; i++)
			ec.execute(new Client(ar));
		ec.shutdown();
	}
}
