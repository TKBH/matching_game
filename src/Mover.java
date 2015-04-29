
public class Mover implements Runnable{

	@Override
	public void run() {
		while(true){
			
			if(Board.initialized()){
				Board.doMove();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
