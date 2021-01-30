public class TestGestion{
	public static void main(String[] args){
		Thread th= new Thread(new GestionnaireCapote());
		th.start();
		th.interrupt();
	}
}
