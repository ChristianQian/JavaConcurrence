import java.util.ArrayList;
public class AleaStock{
	private int taille;
	private ArrayList<AleaObjet> liste;

	public AleaStock(int taille){
		this.taille= taille;
		liste= new ArrayList<>();
	}
	
	public void ajout(AleaObjet o) {
		liste.add(o);
	}
	
	public AleaObjet recup() {
		return liste.remove(liste.size()-1);
	}
	
	public boolean stockVide() {
		return liste.size() == 0;
	}
}
