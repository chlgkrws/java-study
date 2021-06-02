package stream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class TerminalOperations {
	
	public static void main(String[] args) {
		
		//순회(iterate) : forEach 메서드를 사용하여 스트림을 순회
		List<Integer> list = List.of(3,2,1,5,7);
		list.stream()
			.forEach(System.out::print);
		
		System.out.println();
		
		//병렬스트림을 사용했을 땐 순서를 보장할 수 없음. 순서대로 순회하고 싶은경우 forEachOrdered 사용
		list.parallelStream()
			.forEach(System.out::print);
		
		System.out.println();
		
		list.parallelStream()
			.forEachOrdered(System.out::print);	
		
		
		
		
		//결과 합치기(reduce) : 모든 스트림 요소를 처리하여 결과를 구할 수 있음
		
		//1. BinaryOperator을 인자로 받는 reduce
		list = List.of(1,2,3);
		Optional<Integer> opt = list.stream()
									.reduce((a, b) -> a + b);
		System.out.println(opt.orElse(-1));						//6
		
		
		//2. 항등값(초기값), BinaryOperator을 인자로 받는 reduce
		Integer sum = list.stream()
							.reduce(10, (a, b) -> a + b);
		System.out.println(sum);								//16
		
		
		//3. 항등값(초기값), BiFunction, BinaryOperator을 인자로 받는 reduce
		//BiFunction은 항등값과 요소 간 연산처리, BinaryOperator은 병렬처리하는 역할을 한다.
		list = List.of(3, 7, 9);
		Integer result = list.parallelStream()
							.reduce(100, Integer::sum, (a, b) -> {
								System.out.println(a +" - "+ b +" : "+ (a - b));
								return a - b;
							});
		System.out.println(result);
		
		
		
		
		// 계산 : 최솟값, 총합, 평균 등
		OptionalDouble min = DoubleStream.of(4.1, 3.4, -1.3, 2.2, 5.5).min();
		min.ifPresent(System.out::println);
		
		long count = IntStream.of(2, 4, 1, 2).count();
		System.out.println(count);
		
		//기본형에 특화된 IntStream, DoubleStream, LongStream에서는 요소 총합, 평균을 구할 수 있음
		double dSum = DoubleStream.of(1.2, 4.2, 21.2).sum();
		
		OptionalDouble avg = IntStream.of(3, 2, 1).average();
		
		avg.ifPresent(System.out::print);
		

	}
}
