package behavioral.chainofresponsibility;

public class Client {

	public static void main(String[] args) {
		Handler handler = new Leader(new Employee(new IgnoreHandler()));
		
		handler.handleRequest(1);
		handler.handleRequest(5);
		handler.handleRequest(15);
	}
}
