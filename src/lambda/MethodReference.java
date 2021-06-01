package lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
	
	public static void main(String[] args) {
		Function<String, Integer> fs = s -> Integer.parseInt(s);
		
		Function<String, Integer> f = Integer::parseInt;
		
		
		//equals 람다식, 메서드 참조
		//String str = new String("나는 참조변수");
	
		//Function<String, Boolean> equals = s -> str.equals(s);	//람다식
		
		//Function<String, Boolean> equals2 = str::equals;	//메서드 참조
		
		//객체 생성 
		//Supplier<String> s = () -> new String();		//람다식
		
		//Supplier<String> s2 = String::new;		//메서드 참조
		
		//매개변수 지정
		Function<String, String> s = str -> new String();	//람다식
		
		Function<String, String> s2 = String::new;		//메서드 참조
		
		//진수 변환 
		BiFunction<String, Integer, Integer> parseInt = (str, num) -> Integer.parseInt(str, num);	//람다식
				
		BiFunction<String, Integer, Integer> parseInt2 = Integer::parseInt;		//메서드 참조
		
		
		//배열 생성
		Function<Integer, int[]> arr = x -> new int[x];		//람다식
		
		Function<Integer, int[]> arr2 = int[]::new;			//메서드 참조
	}
	
	public Integer parseInt(String s) {
		return Integer.parseInt(s);
	}
}
