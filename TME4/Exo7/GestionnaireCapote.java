import java.nio.channels.ClosedByInterruptException;
import java.util.Random;
public class GestionnaireCapote implements Runnable{
	private boolean requete= false;
	private MoteurVitre m1, m2;
	private Thread tg,td;
	Random r = new Random();

	public GestionnaireCapote(){
		m1= new MoteurVitre(Cote.GAUCHE);
		m2= new MoteurVitre(Cote.DROITE);
	}

	public void run() {
		Thread tg= new Thread(m1);
		Thread td= new Thread(m2);
		tg.start();
		td.start();
		
		m1.demander(Operation.DESCENDRE);
		m2.demander(Operation.DESCENDRE);
		while(m1.getPosition()!=Position.BASSE && m2.getPosition()!=Position.BASSE) {
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){}
		}
		
		tg.interrupt();
		td.interrupt();
		
		int d= r.nextInt(6) +15; //random de 15 a 20
		System.out.println("Les 2 vitres sont baissees, la capote se plie en "+d+"sec");
		
		
		System.out.println("On replie la capote et retablie la position initiale des vitres");
		
		tg=new Thread(m1);
		td=new Thread(m2);
		tg.start();
		td.start();

		m1.retablir();
		m2.retablir();
		while(m1.getPosition()!=m1.getPositionInit() && m2.getPosition()!=m2.getPositionInit()) {
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){}
		}
		
		tg.interrupt();
		td.interrupt();
	}
}
