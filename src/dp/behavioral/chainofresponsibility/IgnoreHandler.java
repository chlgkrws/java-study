package behavioral.chainofresponsibility;


/**
 * ��û ó���� ������ �κ� ���.
 * @author cgw981
 *
 */
public class IgnoreHandler implements Handler{

	@Override
	public boolean handleRequest(int importance) {
		System.out.println("not important to handle");
		return false;
	}
	
}
