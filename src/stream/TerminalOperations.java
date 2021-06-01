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
		
		
		
		
		//결과 모으기 (collect)
		//스트림을 List, Set, Map과 같은 형태의 결과로 변환 해주는 기능
		class Food{
			private String name;
			private int cal;
			
			public Food(String name, int cal) {
				this.name = name;
				this.cal = cal;
			}
			@Override
			public String toString() {
				return String.format("name : %s, cal : %s", name, cal);
			}
			public String getName() {
				return name;
			}
			public int getCal() {
				return cal;
			}
			
		}
		
		List<Food> foods = new ArrayList<>();
		foods.add(new Food("burger", 520));
		foods.add(new Food("chips", 220));
		foods.add(new Food("coke", 240));
		foods.add(new Food("soda", 310));
		
		//작업 결과 리스트로 반환
		List<String> nameList = foods.stream()
									.map(Food::getName)				//참조 변수가 없으므로 클래스::메서드로 메서드 참조를 한다.
									.collect(Collectors.toList());	
		
		System.out.println(nameList);
		
		
		
		
		//숫자값의 합, 평균 등 구하기
		
		//name들의 길이의 합 구하
		Integer summingName = foods.stream()
								.collect(Collectors.summingInt(s -> s.getName().length()));
		
		//mapToInt 메서드로 칼로리 합 구하기
		int sumCal = foods.stream()
						.mapToInt(Food::getCal).sum();
		System.out.println(sumCal);
		
		//평균 구하기
		Double averageInt = foods.stream()
								.collect(Collectors.averagingInt(Food::getCal));
		System.out.println(averageInt);
		
		//summarizingInt와 같은 통계를 얻을 수 있는 메서드를 이용하면 한번에 그 정보를 담을 수 있음
		IntSummaryStatistics summaryStatistics = foods.stream()
													.collect(Collectors.summarizingInt(Food::getCal));
		
		System.out.println(summaryStatistics.getAverage());
		System.out.println(summaryStatistics.getCount());
		System.out.println(summaryStatistics.getMax());
		System.out.println(summaryStatistics.getMin());
		System.out.println(summaryStatistics.getSum());
	}
}
