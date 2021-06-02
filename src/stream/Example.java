package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example {
	
	public static void main(String[] args) {
		
		//임의의 수
		IntStream intStream = new Random().ints();				//매개변수로 유한 스트림을 만들 수 있음.
		intStream.limit(5).forEach(System.out::println);
		
		
		
		
		//람다식 - iterate(), generate()
		Stream.iterate(0, n -> n+2);			//.forEach(System.out::println);
		Stream.generate(() -> 1);				//.forEach(System.out::println);
		
		

		
		//List<V> to Map<K, V>
		//List -> Map으로 변환 : 특정 오브젝트 타입의 리스트를 오브젝트의 한 필드를 키로하는 Map으로 변환
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("짱구", 23, "010-2020-0202"));
		personList.add(new Person("유리", 24, "010-3214-3232"));
		personList.add(new Person("철수", 25, "010-4242-1234"));
		personList.add(new Person("철수", 26, "010-3232-2131"));
		personList.add(new Person("맹구", 26, null));
		
		Map<String, Person> personMap = personList.stream()
					.collect(Collectors.toMap(Person::getName,
											  Function.identity(),		//Function.identity는 t->t, 항상 입력된 인자를 반환한다.
											  (oldKey, newKey) -> oldKey
							));
		System.out.println(personMap);
				
		
		
		
		//상단 코드 풀어 사용하기
		personMap = personList.stream()
						.collect(Collectors.toMap(
								new Function<Person, String>() {
									@Override
									public String apply(Person person) {
										return person.getName();
									}
								},
								new Function<Person, Person>() {
									@Override
									public Person apply(Person person) {
										return person;
									}
								},
								new BinaryOperator<Person>() {
									@Override
									public Person apply(Person person1, Person person2) {
										System.out.println(person1.getPhoneNumber());
										System.out.println(person2.getPhoneNumber());
										return person1;
									}
								}
								));
		System.out.println(personMap);
		
		
		
		
		//중복 키(duplicatekey)를 허용하면서 value를 리스트로 반환
		Map<String, List<Person>> duplicateMap = personList.stream()
															.collect(Collectors.groupingBy(Person::getName));	//그룹핑할 기준
		System.out.println(duplicateMap);
		
		
		
		
		//스트림에서 null 제외하기
		List<String> filterList = personList.stream()
											.map(Person::getPhoneNumber)
											.filter(Objects::nonNull)
											.collect(Collectors.toList());
		System.out.println(filterList);
		
		
		
		
		//조건에 일치한 요소 찾기
		Optional<Person> findPerson = personList.stream()
								.filter(p -> p.getAge() > 10)
								.findFirst();
		System.out.println(findPerson.orElse(null).getName());
		
		
		
		//순서 상관 없이 조건에 충족한 요소를 찾고 싶을 때
		Person person = personList.parallelStream()
								.filter(p -> p.getAge() > 10)
								.findAny().get();
		System.out.println(person.getName());
		
		
		
		//스트림 정렬하기
		personList.stream()
			.sorted(Comparator.comparing(Person::getAge).reversed())
			.forEach(p -> System.out.println(p.getName()));
		
		
		
		//reduce로 결과 구하기
		//스트림을 하나의 결과로 만들 수 있다.
		List<Integer> list = List.of(5, 4, 2, 1, 4, 5, 6, 2, 3);
		
		list.stream()
			.reduce((a, b) -> a + b)
			.ifPresent(System.out::print);
		
		//박싱 비용 줄이기
		list.stream()
			.mapToInt(Integer::intValue).sum();
		
		
		
		
		//Swift라는 문자열보다 길고 리스트 중에서 가장 긴 문자열
		List<String> testList = List.of("Java", "C++", "Python", "Ruby");
		
		testList.stream()
			.filter(s -> s.length() > "Swift".length())
			.reduce((a, b) -> a.length() > b.length() ? a : b)
			.ifPresent(System.out::print);
		
		
		
		
		//단일 컬렉션 만들기
		//flatmap으로 중첩구조 제거
		String[][] names = new String[][] {
			{"A", "B"}, {"C","D"}
		};
		
		List<String> flatmapList = Arrays.stream(names)
				.flatMap(arr -> Arrays.stream(arr))
				.collect(Collectors.toList());
		
		System.out.println(flatmapList);
		
		//1차원 배열로
		String[] flattedNames = Arrays.stream(names)
				.flatMap(Stream::of)
				.toArray(String[]::new);
		System.out.println(flattedNames[0]);
		
	}
}
