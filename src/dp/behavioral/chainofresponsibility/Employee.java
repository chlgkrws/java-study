package behavioral.chainofresponsibility;

public class Employee implements Handler{
	private Handler successor;
	
	public Employee(Handler successor) {
		this.successor = successor;
	}
	
	@Override
	public boolean handleRequest(int importance) {
		if(importance < 10) {
			System.out.println("employee handle request");
			return true;
		}else {
			return successor.handleRequest(importance);
		}
	}
	
}
