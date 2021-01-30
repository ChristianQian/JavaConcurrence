import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EnsembleDonnees {
	public ArrayList<Integer> donnees;
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
	
	public EnsembleDonnees() {
		donnees = new ArrayList<>();		
	}
	
	public void ajoutDonnee(int donnee) {
		rwl.writeLock().lock();
		donnees.add(donnee);
		rwl.writeLock().unlock();
	}
	
	/* PREMIERE VERSION (avant l'alternative qui fonctionne)
	public void suppDonnee(int donnee) throws NoElementException{
		rwl.readLock().lock();
		try{
		int i = donnees.lastIndexOf(donnee);

		if(i==-1) {
			throw new NoElementException("Pas d'occurence de "+donnee+" a supprimer");
		}
	
		}finally{ rwl.readLock().unlock();}	
		
		rwl.writeLock().lock();
		try{
			donnees.remove(i);
		}finally{	rwl.writeLock().unlock();}
	}*/
	
	public void suppDonnee(int donnee) throws NoElementException{
		rwl.writeLock().lock();
		try{
		int i = donnees.lastIndexOf(donnee);
		if(i==-1) {
			throw new NoElementException("Pas d'occurence de "+donnee+" a supprimer");
		}
		donnees.remove(i);
		
		}finally{
			rwl.writeLock().unlock();
		}
	}

	
	public String toString() {
		String s="";
		for(int i=0; i<donnees.size(); i++) {
			s+=donnees.get(i)+" ";
		}
		return s;
	}
	
}

