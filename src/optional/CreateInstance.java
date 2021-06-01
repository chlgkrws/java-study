package optional;

import java.util.Optional;

public class CreateInstance {

	public static void main(String[] args) {
		String str = "abc";
		Optional<String> optVal1 = Optional.of(str);
		Optional<String> optVal2 = Optional.ofNullable(str);
		Optional<String> optVal3 = Optional.empty();
		System.out.println(optVal3);
		
		optVal3 = Optional.ofNullable(str);
		//NPE
		//System.out.println(Optional.of(null));
		
		/**
		 * ��ü �� ��������
		 * opt.get()				- ����� �� ��������. Null�̸� NoSuchElementException �߻�
		 * opt.orElse(T)			- ����� �� ��������. Null�̸� TŸ�� ������ ��ü
		 * opt.orElseGet(lambda)	- ����� �� ��������. Null�̸� lambda�� ���ϴ� �� ��ȯ 
		 * opt.orElseThrow()		- ����� �� ��������. Null�̸� ���� �߻�
		 * opt.orElseThrow(lambda)  - ����� �� ��������. Null�̸� Ŀ���� ���� �߻�
		 * opt.ifPresent(lambda)	- ����� ���� ������, ���ٽ� ����
		 * opt.ifPresentOrElse(lambda, Runnable) - ����� ���� ������, ���ٽ� ����. ������ Runnable.run() ����
		 */
		Optional<String> optStr = Optional.ofNullable("str");
		Optional<Integer> optInt = Optional.ofNullable(1);
		Optional<Integer> optNull = Optional.ofNullable(null);
		
		System.out.println(optNull.get());				//NSE
		System.out.println(optNull.orElse(1234));			//1234
		System.out.println(optNull.orElseGet(() -> 5));		//5
		System.out.println(optNull.orElseThrow());			//NSE(NoSuchElementException)
		System.out.println(optNull.orElseThrow(NullPointerException::new));			//NPE
		optStr.ifPresent(System.out::println);					//str
		optNull.ifPresentOrElse(System.out::print, new Runnable() {			//?
			@Override
			public void run() {
				System.out.println("?");
			}
		});
		
		
		if(str != null) {
			System.out.println(str);
		}
		
		optStr.ifPresent(System.out::println);
	}
}
