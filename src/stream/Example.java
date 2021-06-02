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
		
		//������ ��
		IntStream intStream = new Random().ints();				//�Ű������� ���� ��Ʈ���� ���� �� ����.
		intStream.limit(5).forEach(System.out::println);
		
		
		
		
		//���ٽ� - iterate(), generate()
		Stream.iterate(0, n -> n+2);			//.forEach(System.out::println);
		Stream.generate(() -> 1);				//.forEach(System.out::println);
		
		

		
		//List<V> to Map<K, V>
		//List -> Map���� ��ȯ : Ư�� ������Ʈ Ÿ���� ����Ʈ�� ������Ʈ�� �� �ʵ带 Ű���ϴ� Map���� ��ȯ
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("¯��", 23, "010-2020-0202"));
		personList.add(new Person("����", 24, "010-3214-3232"));
		personList.add(new Person("ö��", 25, "010-4242-1234"));
		personList.add(new Person("ö��", 26, "010-3232-2131"));
		personList.add(new Person("�ͱ�", 26, null));
		
		Map<String, Person> personMap = personList.stream()
					.collect(Collectors.toMap(Person::getName,
											  Function.identity(),		//Function.identity�� t->t, �׻� �Էµ� ���ڸ� ��ȯ�Ѵ�.
											  (oldKey, newKey) -> oldKey
							));
		System.out.println(personMap);
				
		
		
		
		//��� �ڵ� Ǯ�� ����ϱ�
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
		
		
		
		
		//�ߺ� Ű(duplicatekey)�� ����ϸ鼭 value�� ����Ʈ�� ��ȯ
		Map<String, List<Person>> duplicateMap = personList.stream()
															.collect(Collectors.groupingBy(Person::getName));	//�׷����� ����
		System.out.println(duplicateMap);
		
		
		
		
		//��Ʈ������ null �����ϱ�
		List<String> filterList = personList.stream()
											.map(Person::getPhoneNumber)
											.filter(Objects::nonNull)
											.collect(Collectors.toList());
		System.out.println(filterList);
		
		
		
		
		//���ǿ� ��ġ�� ��� ã��
		Optional<Person> findPerson = personList.stream()
								.filter(p -> p.getAge() > 10)
								.findFirst();
		System.out.println(findPerson.orElse(null).getName());
		
		
		
		//���� ��� ���� ���ǿ� ������ ��Ҹ� ã�� ���� ��
		Person person = personList.parallelStream()
								.filter(p -> p.getAge() > 10)
								.findAny().get();
		System.out.println(person.getName());
		
		
		
		//��Ʈ�� �����ϱ�
		personList.stream()
			.sorted(Comparator.comparing(Person::getAge).reversed())
			.forEach(p -> System.out.println(p.getName()));
		
		
		
		//reduce�� ��� ���ϱ�
		//��Ʈ���� �ϳ��� ����� ���� �� �ִ�.
		List<Integer> list = List.of(5, 4, 2, 1, 4, 5, 6, 2, 3);
		
		list.stream()
			.reduce((a, b) -> a + b)
			.ifPresent(System.out::print);
		
		//�ڽ� ��� ���̱�
		list.stream()
			.mapToInt(Integer::intValue).sum();
		
		
		
		
		//Swift��� ���ڿ����� ��� ����Ʈ �߿��� ���� �� ���ڿ�
		List<String> testList = List.of("Java", "C++", "Python", "Ruby");
		
		testList.stream()
			.filter(s -> s.length() > "Swift".length())
			.reduce((a, b) -> a.length() > b.length() ? a : b)
			.ifPresent(System.out::print);
		
		
		
		
		//���� �÷��� �����
		//flatmap���� ��ø���� ����
		String[][] names = new String[][] {
			{"A", "B"}, {"C","D"}
		};
		
		List<String> flatmapList = Arrays.stream(names)
				.flatMap(arr -> Arrays.stream(arr))
				.collect(Collectors.toList());
		
		System.out.println(flatmapList);
		
		//1���� �迭��
		String[] flattedNames = Arrays.stream(names)
				.flatMap(Stream::of)
				.toArray(String[]::new);
		System.out.println(flattedNames[0]);
		
	}
}
