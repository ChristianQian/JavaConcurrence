import java.util.Random;
public class Servant implements Runnable{
	Random gen= new Random();
	private Requete r;
	public Servant(Requete r){
		this.r=r;
	}
	
	public void run(){
		if(r.getType() == 1){
			try{
				Thread.sleep(gen.nextInt(1500)+500); //0.5 a 2 sec
			}catch(InterruptedException e){
				System.out.println("Servant interrompu");
			}
		}else{
			//while(true){
				try{
					Thread.sleep(300);
				}catch(InterruptedException e){
					System.out.println("Servant interrompu");
				}
			//}
		}
		r.getClient().requeteTraitee(new ReponseRequete(r.getClient(),r.getNum(),r.getType()));
	}
}