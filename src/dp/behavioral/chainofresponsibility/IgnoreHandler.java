package behavioral.chainofresponsibility;


/**
 * 요청 처리의 마지막 부분 담당.
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
