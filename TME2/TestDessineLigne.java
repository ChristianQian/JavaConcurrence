import graphic.Window;
import java.awt.Point;
import java.lang.String;
public class TestDessineLigne{
	public static void main(String[] args){
		Window w = new Window(600,600,"test");
		Thread d1 = new DessineLigne(new Point(1,1),new Point(500,500),w);
		Thread d2 = new DessineLigne(new Point(500,500),new Point(50,500),w);
		Thread d3 = new DessineLigne(new Point(50,500),new Point(1,1),w);
		d1.start();
		d2.start();
		d3.start();
	}		
}
