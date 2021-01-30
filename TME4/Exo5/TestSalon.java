
public class TestSalon {
	public static void main(String[] args) throws InterruptedException{
		Salon s = new Salon(2);
		Thread barbier = new Thread(new Barbier(s));
		barbier.start();
		Thread tab[] = new Thread[20];
		for(int i=0; i<20; i++) {
			tab[i] = new Thread(new Client(s));
			tab[i].start();
		}
		for(int j=0; j<20; j++) {
			try {
				tab[j].join();
			}catch(InterruptedException e) {
				System.err.println(e);
			}
		}
		barbier.interrupt();
		System.out.println("Le barbier sort du salon.");
	}
}
