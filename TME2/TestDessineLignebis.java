import graphic.Window;
import java.awt.Point;
import java.lang.String;
public class TestDessineLignebis{
	public static void main(String[] args){
		Window w = new Window(600,600,"test");
		new Thread(new DessineLigne(new Point(1,1),new Point(500,500),w)).start();
		new Thread(new DessineLigne(new Point(500,500),new Point(50,500),w)).start();
		new Thread(new DessineLigne(new Point(50,500),new Point(1,1),w)).start();
	}
}
