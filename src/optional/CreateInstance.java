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
		 * 객체 값 가져오기
		 * opt.get()				- 저장된 값 가져오기. Null이면 NoSuchElementException 발생
		 * opt.orElse(T)			- 저장된 값 가져오기. Null이면 T타입 변수로 대체
		 * opt.orElseGet(lambda)	- 저장된 값 가져오기. Null이면 lambda가 행하는 값 반환 
		 * opt.orElseThrow()		- 저장된 값 가져오기. Null이면 예외 발생
		 * opt.orElseThrow(lambda)  - 저장된 값 가져오기. Null이면 커스텀 예외 발생
		 * opt.ifPresent(lambda)	- 저장된 값이 있으면, 람다식 실행
		 * opt.ifPresentOrElse(lambda, Runnable) - 저장된 값이 있으면, 람다식 실행. 없으면 Runnable.run() 실행
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
