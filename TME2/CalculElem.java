public class CalculElem implements Runnable{
	private int i,j;
	private MatriceEntiere m, m2, m3;
	public CalculElem(MatriceEntiere m, int i, int j, MatriceEntiere m2, MatriceEntiere m3){
		this.i= i;
		this.j= j;
		this.m= m;
		this.m2= m2;
		this.m3= m3;
	}
	public void run(){
		try{
			m.setElem(i, j, MatriceEntiere.produitLigneColonne(m2, i, m3, j));
		}catch(TaillesNonConcordantesException e){
			System.out.println(e);
		}
	}
}
