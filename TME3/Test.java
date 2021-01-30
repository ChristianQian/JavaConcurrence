public class Test{
	public static void main(String [] args){
		Salle s= new Salle(3,4);
		Thread [] th= new Thread [5];
		for(int i=0; i<5; i++){
			th[i]= new Thread(new Groupe(3,s));
			th[i].start();
		}
		

	}
}
		
		
