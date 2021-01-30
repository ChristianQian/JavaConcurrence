
public class TestTransport {
	public static void main(String [] args) {
		AleaStock s= new AleaStock(4);
		for(int i = 0; i <4 ; i++)
			s.ajout(new AleaObjet(1,1));
		Chariot c= new Chariot(2,2);
		Thread tc= new Thread(new Chargeur(c,s));
		Thread td= new Thread(new Dechargeur(c,s));
		tc.start();
		td.start();
	}
}
