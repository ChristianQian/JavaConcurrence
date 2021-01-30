import java.util.Random;
import java.util.concurrent.Callable;
public class Servant implements Callable<ReponseRequete>{
	Random gen= new Random();
	private Requete r;
	public Servant(Requete r){
		this.r=r;
	}
	
	public ReponseRequete call(){
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
		r.getClient().requeteTraitee();
		return new ReponseRequete(r.getClient(),r.getNum(),r.getType());
	}
}
