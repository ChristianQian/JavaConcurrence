public class Groupe implements Runnable{
	private int nb,id;
	private boolean annu;
	private static int cpt=0;
	private Salle s;
	private int [][] occ;
	public Groupe(int nb,Salle s){
		this.nb=nb;
		id=cpt++;
		this.s=s;
		occ= new int [nb][2];
		annu= false;
	}
	
	public void run(){
		if(s.reserver(this))
			System.out.println("La réservation a été effectuée ("+id+")"); 
		else
			System.out.println("La salle est pleine pour groupe ("+id+")"); 

		if(s.annuT(this))
			System.out.println("AnnulationT effectuée par groupe ("+id+")"); 
		else
			System.out.println("Annulation déjà effectuée ("+id+")"); 

		if(s.annuP(this,2))
			System.out.println("AnnulationP effectuée ("+id+")"); 
		else
			System.out.println("Annulation déjà effectuée ("+id+")"); 
	}		
	
	public boolean getannu(){
		return annu;
	}	

	public void doannu(){
		annu= true;
	}

	public void chOcc(int i,int j,int t){
		occ[i][j]= t;
	}

	public int [][] getOcc(){
		return occ;
	}
	
	public int getnb(){
		return nb;
	}
}

