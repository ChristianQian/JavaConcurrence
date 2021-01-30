import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Chariot{
	private ArrayList<AleaObjet> liste;
	private int nbMax, poidsMax;
	private final Lock l = new ReentrantLock();
	private final Condition ajouter= l.newCondition();
	private final Condition enlever= l.newCondition();
	private boolean remplir;
	
	public Chariot(int nbMax,int poidsMax){
		this.nbMax=nbMax;
		this.poidsMax=poidsMax;
		liste= new ArrayList<>();
		remplir= true;
	}
	
	public boolean ifplein() {  
		if(liste.size() == nbMax) {
			return true;
		}
		int p=0;
		for(AleaObjet a: liste)
			p+= a.getPoids();
		return p >= poidsMax;
	}	
	
	public void notifDecharg() { //signal a la terminaison de chargeur
		l.lock();
		try {
			System.out.println("Stock vide");
			remplir= false;
			enlever.signalAll();
		}finally {
			l.unlock();
		}
	}
	
	public boolean getRemplir() { //pour la terminaison de dechargeur
		return remplir;
	}

	public void remplir(AleaStock s) throws InterruptedException{
		l.lock();
		try {
			while(! remplir) { //wait si chariot non vide
				ajouter.await();
			}
			if(s.stockVide()){
				System.out.println("Stock vide");
				remplir= false;
				enlever.signalAll();
			}else{
				AleaObjet o= s.recup();
				liste.add(o); //ajout dans le chariot
				if(ifplein()) {
					System.out.println("Ajoute objet "+o.getId()+" et chariot plein");
					remplir= false;
					enlever.signalAll();
				}else System.out.println("Ajout de l'objet "+o.getId());
			}
		}finally {
			l.unlock();	
		}
	}
	
	public void vider(Dechargeur d) throws InterruptedException{
		l.lock();
		try {
			while(remplir) { //wait si chariot non plein
				enlever.await();
			}
			if(liste.size()==0){
				System.out.println("Chariot vide");
				remplir= true;
				enlever.signalAll();
			}else{
				AleaObjet o= liste.remove(liste.size()-1);
				d.addDest(o); // ajout au arraylist de dechargeur
				if(liste.size()==0) {
					System.out.println("Dechargement de l'objet "+o.getId()+" et chariot vide");	
					remplir= true;
					ajouter.signalAll();
				}else System.out.println("Dechargement de l'objet "+o.getId());
			}
		}finally {
			l.unlock();	
		}
	}
}
