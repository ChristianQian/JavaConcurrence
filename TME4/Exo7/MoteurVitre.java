import java.util.Random;
public class MoteurVitre implements Runnable {
	private Cote c;
	private Position p,init;
	private Operation o;
	Random r = new Random();
	private boolean requete;
	
	public MoteurVitre(Cote c) {
		this.c= c;
		p= Position.HAUTE;
		init=p;
		o= Operation.NIL;
		requete=false;
	}
	
	public Position getPosition() {
		return p;
	}

	public Position getPositionInit() {
		return init;
	}

	private synchronized void executer(){
		switch(o){
			case MONTER: 
				if(p==Position.BASSE){
					p=Position.HAUTE;
					int d= r.nextInt(3) +2; //random de 2 a 4
					System.out.println("Vitre cote "+c+" montee en "+d+"sec");
				}else
					System.out.println("Vitre cote "+c+" deja montee");	 
				break;
			case DESCENDRE: 
				if(p==Position.HAUTE){
					p=Position.BASSE;
					int d= r.nextInt(3) +2;
					System.out.println("Vitre cote "+c+" baissee en "+d+"sec");
				}else
					System.out.println("Vitre cote "+c+" deja baissee");
				break;
			case NIL:
				System.out.println("Vitre cote "+c+" ne fait rien");
				break;
		}
		requete= false;
	}
	
	public synchronized void demander(Operation o) {
		this.o=o;
		requete= true;
		notifyAll();
	}

	public synchronized void retablir(){
		switch(p){
			case HAUTE:
				if(init==Position.BASSE) demander(Operation.DESCENDRE);
				break;
			case BASSE:
				if(init==Position.HAUTE) demander(Operation.MONTER);
				break;
		}
	}
	
	public void run() {
		while(true) {
			attente();
			executer();
		}
	}
	
	public synchronized void attente() {
		while(!requete) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Moteur "+c+" fermee");
			}
		}
	}
}
