import java.util.ArrayList;

public class TestDonnees {

	public static void main(String[] args) {
		EnsembleDonnees e = new EnsembleDonnees();
		ArrayList<Thread> l= new ArrayList<>();
		for(int i=0; i<2 ; i++) {
			l.add(new Thread(new Producteur(e,4)));
			l.add(new Thread(new Lecteur(e)));
			l.add(new Thread(new Effaceur(e)));
		}
		
		for(Thread t: l)
			t.start();
	}

}
