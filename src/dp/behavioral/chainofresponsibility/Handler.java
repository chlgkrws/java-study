package behavioral.chainofresponsibility;

/**
 * ��û ó�� �������̽�
 * @author cgw981
 *
 */
public interface Handler {
	boolean handleRequest(int importance);
}
