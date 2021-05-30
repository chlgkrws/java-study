package lambda;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionAndPredicate {
	
	public static void main(String[] args) {
		Function<String, Integer> f = str -> Integer.parseInt(str, 16);
		Function<Integer, String> g = num -> Integer.toBinaryString(num);
		Function<String, String> h = f.andThen(g);
		
		//System.out.println(h.apply("10"));
		
		Function<Integer, String> f2 = num -> Integer.toBinaryString(num);
		Function<String, Integer> g2 = str -> Integer.parseInt(str, 16);
		Function<Integer, Integer> h2 = g2.compose(f2);
		
		//System.out.println(h2.apply(2));
		
		Predicate<Integer> p = i -> i < 100;
		Predicate<Integer> q = i -> i < 200;
		Predicate<Integer> r = i -> i % 2 == 0;
		Predicate<Integer> notP = p.negate();
		
		//100 <= i %% (i < 200 || i % 2 == 0)
		Predicate<Integer> all = notP.and(q.or(r));
		System.out.println(all.test(150));
		
		Predicate<Integer> all2 = notP.and(i -> i < 200).or(i -> i % 2 == 0);
		System.out.println(all2.test(150));
		
		Predicate<String> equal = Predicate.isEqual("테스트 문자열");
		System.out.println(equal.test("테스트 문자열"));
		
		System.out.println(Predicate.isEqual("테스트 문자열").test("테스트 문자열"));
	}
}	
