package behavioral.chainofresponsibility;

public class Leader implements Handler{
	private Handler successor;
	
	public Leader(Handler successor) {
		this.successor = successor;
	}
	
	@Override
	public boolean handleRequest(int importance) {
		if(importance < 2) {
			System.out.println("leader handle the request!");
			return true;
		}else {
			return successor.handleRequest(importance);
		}
	}

}
