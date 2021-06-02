package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TerminalOperations2 {
	public static void main(String[] args) {
		
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
		foods.add(new Food("coke", 220));
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
		
		
		//연산 결과를 하나의 문자열로 만들기
		String defaultJoining = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining());
		System.out.println(defaultJoining);
		
		//구분자(delimiter)을 인자로 받아 문자열 만들기
		String delimiterJoining = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining(","));
		System.out.println(delimiterJoining);
		
		//구분자(delimiter, prefix, suffix)를 인자로 받아 문자열 만들기
		String combineJoning = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining(",", "[", "]"));
		System.out.println(combineJoning);
		
		
		//특정 조건으로 그룹 짓기 : 특정 조건에 맞추어 그룹핑을 할 수 있다.
		Map<Integer, List<Food>> calMap = foods.stream()
											.collect(Collectors.groupingBy(Food::getCal));
		System.out.println(calMap);
		
		
		//참, 거짓으로 그룹짓기
		//partitioningBy는 인자로 Predicate 함수형 인터페이스를 받고, boolean 값으로 그룹핑한다.
		Map<Boolean, List<Food>> partitionMap = foods.stream()
													.collect(Collectors.partitioningBy(s -> s.getCal() > 300));
		System.out.println(partitionMap);
		
		
		//Map으로 결과 모으기
		//칼로리와 이름으로 Map 만들기
		Map<Integer, String> map = foods.stream()
										.collect(Collectors.toMap(
												obj -> obj.getCal(), 
												obj -> obj.getName(),
												(oldV, newV) -> newV)		//key 값이 겹칠 때 새로운 값을 넣어주기 (예외 처리)
												);
		System.out.println(map);
		
		
		//collect 후에 연산 추가
		//collectingAndThen 메서드를 이용하면 특정 타입의 형태로 수집한 이후 추가연산을 진행할 수 있다.
		Food food = foods.stream()
						.collect(Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Food::getCal)), 
								(Optional<Food> o) -> o.orElse(null)));
		
		System.out.println(food);


		//직접 Collector 만들기
		Collector<Food, StringJoiner, String> foodNameCollector = Collector.of(
																() -> new StringJoiner(" | " ),
																(a, b) -> a.add(b.getName()),
																(a, b) -> a.merge(b),
																StringJoiner::toString);
		//만든 컬렉터를 스트림에 적용
		String foodNames = foods.stream().collect(foodNameCollector);
		
		System.out.println(foodNames);
		
		
		
		
		//조건 체크
		//Predicate 조건식을 인자로 받아서 해당 조건을 만족하는 요소가 있는지 체크
		//anyMatch, allMatch, noneMatch	
		boolean anyMatch = foods.stream()						
							.anyMatch(obj -> obj.getCal() > 300);		//anyMatch : 하나라도 조건을 만족하는가
		System.out.println(anyMatch);
		
		boolean allMatch = foods.stream()
							.allMatch(obj -> obj.getCal() > 100);		//allMatch : 모두 조건을 만족하는가
		System.out.println(allMatch);
		
		boolean noneMatch = foods.stream()
								.noneMatch(obj -> obj.getCal() > 1000);	//noneMatch : 모두 조건을 만족하지 않는가
		System.out.println(noneMatch);
	}
}
