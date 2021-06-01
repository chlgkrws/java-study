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
		
		
		
		
		//filter : Predicate<T> �������̽��� test �߻�޼��� ����
		list = List.of("Spring", "Security");
		
		result = list.stream()
				.filter(s -> s.contains("S"))
				.collect(Collectors.toList());
		
		System.out.println(result);				//[Spring, Security]
	
		
		
		
		//map : �޼��带 ����Ͽ� ��Ʈ�� �� ��Ҹ� ���ϴ� ���·� ��ȯ
		list = List.of("Spring", "Security");
		
		result = list.stream()
				.map(s -> s.concat("TEST"))
				.collect(Collectors.toList());
			
		System.out.println(result);				//[SpringTEST, SecurityTEST]
		
		
		
		
		//�⺻ Ÿ�� ��Ʈ�� ��ȯ : Boxing�� ���� �� �ֵ��� �⺻ ������ Ÿ�Կ� Ưȭ�� ��Ʈ������ ��ȯ
		IntStream intStream = List.of("10", "20", "30")
								.stream()
								.mapToInt(Integer::parseInt);
		
		intStream.forEach(System.out::println);
		
		
		
		
		//flatmap : ���� ��Ʈ������ ��ȯ
		List<String> list1 = List.of("Spring", "Security");
		List<String> list2 = List.of("JWT", "OAUTH");
		List<List<String>> combinedList = List.of(list1, list2);
		
		List<String> streamByList  = combinedList.stream()
									.flatMap(e -> e.stream())
									.collect(Collectors.toList());
		
		System.out.println(streamByList);			//[Spring, Security, JWT, OAUTH]
		
		
		
		
		//distinct : ����� �ߺ� ����, ��ü�� ��� Object.equals �޼���� ��
		IntStream stream = Arrays.stream(new int[] {1,1,2,2,2,3,4,5,6});
		
		stream.distinct()
			.forEach(System.out::println);			//1,2,3,4,5,6
		
		
		
		
		//sorted : ��Ʈ�� �� ��Ҹ� ����
		List.of(5,4,3,1,2,5,10).stream()
			.sorted()
		  //.sorted(Comparator.reverseOrder()) �������� ����
			.forEach(System.out::print);
		System.out.println();
		//IntStream, DoubleStream, LongStream �⺻�� Ưȭ ��Ʈ���� sorted �޼��忡 ���ڸ� �ѱ� �� �����Ƿ�
		//boxed �޼��带 �̿��� ��ü ��Ʈ������ ��ȯ �� ���
		IntStream.range(0, 10)
			.boxed()
			.sorted()
			.forEach(System.out::print);
		
		
		
		
		//limit : ��Ʈ�� ���� ��� ������ ������ �� ����
		list.stream()
			.limit(1).collect(Collectors.toList())
			.forEach(System.out::println);			//Spring
		
		
		
		
		//skip : ��Ʈ�� ���� ù ��° ��Һ��� ���ڷ� ���޵� ���� ��ŭ�� ��Ҹ� ������ ������ ��ҷ� ������ ���ο� ��Ʈ���� ����
		//��, [1,2,3] �迭���� skip(1)�� �տ��� 1���� �����Ѵٴ� ���� ����
		list = List.of(new String[] {"Spring", "Java", "Stack"});
		list.stream()
			.skip(2)
			.collect(Collectors.toList())
			.forEach(System.out::println);			//Stack
		
	}
}
