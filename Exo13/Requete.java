public class Requete{
	private Client cl;
	private int num, type;
	
	public Requete(Client c, int n, int t){
		cl= c;
		num= n;
		type= t;
	}
	
	public Client getClient(){ return cl; }
	
	public int getNum(){ return num; }
	
	public int getType(){ return type; }
}
