
public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = new Properties(args[0]);
		Thread thread = new Thread(new Mover());
		thread.start();
		Board.init(properties);
	}
}
