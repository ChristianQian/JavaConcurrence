import graphic.Window;
import java.awt.Point;
import java.lang.String;
public class DessineLigne extends Thread{
	private Point p1;
	private Point p2;
	private Window w;
	public DessineLigne(Point p1, Point p2, Window w){
		this.p1=p1;
		this.p2=p2;
		this.w=w;
	}
	public void run(){
		w.plotLine(p1,p2);
	}
}
	
