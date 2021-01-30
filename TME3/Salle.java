public class Salle{
	private int nbRangs, nbPlacesParRang;
	private boolean [][] placesLibres;
	
	public Salle(int r, int nbpr){
		nbRangs= r;
		nbPlacesParRang= nbpr;
		placesLibres= new boolean [r][nbpr];
		for(int i =0; i< r; i++){
			for(int j =0; j< nbpr; j++){
				placesLibres[i][j]= true;
			}
		}
	}

	public boolean capaciteOK(int n){
		int cpt=0;
		for(int i=0;i<nbRangs;i++){
			for(int j=0;j<nbPlacesParRang;j++){
				if(placesLibres[i][j]) cpt++;
			}
		}
		return cpt>=n;
	}
	
	public int nContiguesAuRangI(int n, int i){
		int j=0, cpt=0, jdebut=0;
		while(cpt<n && j<nbPlacesParRang) {
			if(placesLibres[i][j] && cpt==0) {cpt++; jdebut=j;}
			else if(placesLibres[i][j]) cpt++;
			else cpt=0;
			j++;
		}
		if (cpt==n) return jdebut;
		else return -1;
	}
	public boolean reserverContigues(Groupe p){
		int a=nContiguesAuRangI(p.getnb(),0), i=1;
		while(a == -1 && i<nbRangs) {
			a=nContiguesAuRangI(p.getnb(),i);
			i++;
		}
		
		if(a != -1) {
			int k=0;
			for(int j=a; j<a+p.getnb(); j++) {
				placesLibres[i-1][j]=false;
				p.chOcc(k,0,i-1);
				p.chOcc(k,1,j);
				k++;
			}
			return true;
		}
		return false;
	}

	public synchronized boolean reserver(Groupe p){
		boolean b= reserverContigues(p);
		int cpt= 0;
		if(capaciteOK(p.getnb()) && !b){  //si non contigues 
			b=true;
			cpt= 0;
			int k=0; //pour le tableau de personne
			for(int i=0; i< nbRangs; i++){
				for(int j=0; j< nbPlacesParRang & cpt<p.getnb(); j++){
					if(placesLibres[i][j]){
						p.chOcc(k,0,i);
						p.chOcc(k,1,j);
						k++;
						placesLibres[i][j]= false;
						cpt++;
					}
				}
			}
		}
		
		return b;
	}

	public synchronized boolean annuT(Groupe p){
		boolean b=false; 
		
		if(!p.getannu()){ //si déjà annulé
			p.doannu();
			
			b= true;
			for(int i=0; i< p.getnb(); i++)
				placesLibres[(p.getOcc())[i][0]][(p.getOcc())[i][1]]= true;
		}
		return b;
	}

	public synchronized boolean annuP(Groupe p,int n){
		boolean b=false;
		if(!p.getannu()){ //si déjà annulé
			if(!(n > p.getnb())){ // si n dépasse le nb de personne
				b= true;
				for(int i=0; i<n; i++)
					placesLibres[(p.getOcc())[i][0]][(p.getOcc())[i][1]]= true;
			}
		}
		return b;
	}
		
}
		
