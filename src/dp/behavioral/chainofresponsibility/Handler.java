package behavioral.chainofresponsibility;

/**
 * 요청 처리 인터페이스
 * @author cgw981
 *
 */
public interface Handler {
	boolean handleRequest(int importance);
}
