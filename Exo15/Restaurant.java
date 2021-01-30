
public class Restaurant {
	private int nbtable, placeParTable, tableDispo;
	private static int num= 1;
	
	public Restaurant(int nbt, int place) {
		this.nbtable= nbt;
		this.placeParTable= place;
		tableDispo= nbt;
	}
	
	public synchronized Integer reserver(int nbC) {
		int besoinT= nbC/placeParTable + ((nbC % placeParTable !=0)? 1 : 0);
		if(besoinT <= tableDispo) {
			tableDispo-= besoinT;
			return num;
		}
		return null;
	}
}
