
public class Hangar {
	private int idL;  //id loco
	private static int cpt=1;
	private final int idH; //id hangar
	private final Object mutex = new Object();
	
	
	public Hangar() {
		idL=0;
		synchronized(mutex){
			idH = cpt++;
		}
	}
	
	public void entrer(int idL) {
		System.out.println("Loco "+idL+" entre hangar "+idH);
		this.idL= idL;
	}
	
	public boolean occup() { //car id de hangar commence de 1 a N
		return idL != 0;
	}
}
