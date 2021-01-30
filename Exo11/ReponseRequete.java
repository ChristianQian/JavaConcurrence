public class ReponseRequete{
	private int num, res;
	private Client c;
	
	public ReponseRequete(Client c, int n, int r){
		num= n;
		res= r;
		this.c= c;
	}

	public String toString(){
		return c.getidC()+" | Requete "+num+" | Val:"+res;
	}

}
	
