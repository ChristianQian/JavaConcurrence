import graphic.Window;
import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Window f;
	private ExecutorService exec;
	public VonKochMono (Window f, Point a, Point b, Point c) {
		this.f = f;
		exec = Executors.newCachedThreadPool();
		exec.execute(new Cote(f, b, a, exec));
		exec.execute(new Cote(f, a, c, exec));
		exec.execute(new Cote(f, c, b, exec));
		/*new Thread(new Cote(f, b, a)).start();
		new Thread(new Cote(f, a, c)).start();
		new Thread(new Cote(f, c, b)).start();*/
	}
}
