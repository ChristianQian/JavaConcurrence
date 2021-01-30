
public class Chargeur implements Runnable{
	private Chariot c;
	private AleaStock s;
	
	public Chargeur(Chariot c, AleaStock s){
		this.c= c;
		this.s= s;
	}

	public void run(){
		while(!s.stockVide()){//while(true){  
			try {
				c.remplir(s);
			}catch(InterruptedException e) {
				System.out.println("Problem thread chargeur");
			}
		}
		c.notifDecharg();
	}
	
}
