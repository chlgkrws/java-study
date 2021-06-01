package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntermediateOperations {
	
	public static void main(String[] args) {
		
		//filter, map
		List<String> list = List.of("Invail", "Cause", "Spring", "Security");
		
		List<String> result = list.stream()
				.filter(s -> s.length() < 6)
				.map(s -> s + "TEST")
				.collect(Collectors.toList());
		
		System.out.println(result);				//[CauseTEST]
		
		
		
		
		//filter : Predicate<T> 인터페이스의 test 추상메서드 정의
		list = List.of("Spring", "Security");
		
		result = list.stream()
				.filter(s -> s.contains("S"))
				.collect(Collectors.toList());
		
		System.out.println(result);				//[Spring, Security]
	
		
		
		
		//map : 메서드를 사용하여 스트림 내 요소를 원하는 형태로 변환
		list = List.of("Spring", "Security");
		
		result = list.stream()
				.map(s -> s.concat("TEST"))
				.collect(Collectors.toList());
			
		System.out.println(result);				//[SpringTEST, SecurityTEST]
		
		
		
		
		//기본 타입 스트림 변환 : Boxing을 피할 수 있도록 기본 데이터 타입에 특화된 스트림으로 변환
		IntStream intStream = List.of("10", "20", "30")
								.stream()
								.mapToInt(Integer::parseInt);
		
		intStream.forEach(System.out::println);
		
		
		
		
		//flatmap : 단일 스트림으로 변환
		List<String> list1 = List.of("Spring", "Security");
		List<String> list2 = List.of("JWT", "OAUTH");
		List<List<String>> combinedList = List.of(list1, list2);
		
		List<String> streamByList  = combinedList.stream()
									.flatMap(e -> e.stream())
									.collect(Collectors.toList());
		
		System.out.println(streamByList);			//[Spring, Security, JWT, OAUTH]
		
		
		
		
		//distinct : 결과값 중복 제거, 객체인 경우 Object.equals 메서드로 비교
		IntStream stream = Arrays.stream(new int[] {1,1,2,2,2,3,4,5,6});
		
		stream.distinct()
			.forEach(System.out::println);			//1,2,3,4,5,6
		
		
		
		
		//sorted : 스트림 내 요소를 정렬
		List.of(5,4,3,1,2,5,10).stream()
			.sorted()
		  //.sorted(Comparator.reverseOrder()) 내림차순 정렬
			.forEach(System.out::print);
		System.out.println();
		//IntStream, DoubleStream, LongStream 기본형 특화 스트림은 sorted 메서드에 인자를 넘길 수 없으므로
		//boxed 메서드를 이용해 객체 스트림으로 변환 후 사용
		IntStream.range(0, 10)
			.boxed()
			.sorted()
			.forEach(System.out::print);
		
		
		
		
		//limit : 스트림 내의 요소 개수를 제한할 수 있음
		list.stream()
			.limit(1).collect(Collectors.toList())
			.forEach(System.out::println);			//Spring
		
		
		
		
		//skip : 스트림 내의 첫 번째 요소부터 인자로 전달된 개수 만큼의 요소를 제외한 나머지 요소로 구성된 새로운 스트림을 리턴
		//즉, [1,2,3] 배열에서 skip(1)은 앞에서 1개를 제외한다는 말과 같음
		list = List.of(new String[] {"Spring", "Java", "Stack"});
		list.stream()
			.skip(2)
			.collect(Collectors.toList())
			.forEach(System.out::println);			//Stack
		
	}
}
