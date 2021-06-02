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
		
		//��� ������ (collect)
		//��Ʈ���� List, Set, Map�� ���� ������ ����� ��ȯ ���ִ� ���
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
		
		//�۾� ��� ����Ʈ�� ��ȯ
		List<String> nameList = foods.stream()
									.map(Food::getName)				//���� ������ �����Ƿ� Ŭ����::�޼���� �޼��� ������ �Ѵ�.
									.collect(Collectors.toList());	
		
		System.out.println(nameList);
		
		
		
		
		//���ڰ��� ��, ��� �� ���ϱ�
		
		//name���� ������ �� ����
		Integer summingName = foods.stream()
								.collect(Collectors.summingInt(s -> s.getName().length()));
		
		//mapToInt �޼���� Į�θ� �� ���ϱ�
		int sumCal = foods.stream()
						.mapToInt(Food::getCal).sum();
		System.out.println(sumCal);
		
		//��� ���ϱ�
		Double averageInt = foods.stream()
								.collect(Collectors.averagingInt(Food::getCal));
		System.out.println(averageInt);
		
		//summarizingInt�� ���� ��踦 ���� �� �ִ� �޼��带 �̿��ϸ� �ѹ��� �� ������ ���� �� ����
		IntSummaryStatistics summaryStatistics = foods.stream()
													.collect(Collectors.summarizingInt(Food::getCal));
		
		System.out.println(summaryStatistics.getAverage());
		System.out.println(summaryStatistics.getCount());
		System.out.println(summaryStatistics.getMax());
		System.out.println(summaryStatistics.getMin());
		System.out.println(summaryStatistics.getSum());
		
		
		//���� ����� �ϳ��� ���ڿ��� �����
		String defaultJoining = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining());
		System.out.println(defaultJoining);
		
		//������(delimiter)�� ���ڷ� �޾� ���ڿ� �����
		String delimiterJoining = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining(","));
		System.out.println(delimiterJoining);
		
		//������(delimiter, prefix, suffix)�� ���ڷ� �޾� ���ڿ� �����
		String combineJoning = foods.stream()
									.map(Food::getName)
									.collect(Collectors.joining(",", "[", "]"));
		System.out.println(combineJoning);
		
		
		//Ư�� �������� �׷� ���� : Ư�� ���ǿ� ���߾� �׷����� �� �� �ִ�.
		Map<Integer, List<Food>> calMap = foods.stream()
											.collect(Collectors.groupingBy(Food::getCal));
		System.out.println(calMap);
		
		
		//��, �������� �׷�����
		//partitioningBy�� ���ڷ� Predicate �Լ��� �������̽��� �ް�, boolean ������ �׷����Ѵ�.
		Map<Boolean, List<Food>> partitionMap = foods.stream()
													.collect(Collectors.partitioningBy(s -> s.getCal() > 300));
		System.out.println(partitionMap);
		
		
		//Map���� ��� ������
		//Į�θ��� �̸����� Map �����
		Map<Integer, String> map = foods.stream()
										.collect(Collectors.toMap(
												obj -> obj.getCal(), 
												obj -> obj.getName(),
												(oldV, newV) -> newV)		//key ���� ��ĥ �� ���ο� ���� �־��ֱ� (���� ó��)
												);
		System.out.println(map);
		
		
		//collect �Ŀ� ���� �߰�
		//collectingAndThen �޼��带 �̿��ϸ� Ư�� Ÿ���� ���·� ������ ���� �߰������� ������ �� �ִ�.
		Food food = foods.stream()
						.collect(Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Food::getCal)), 
								(Optional<Food> o) -> o.orElse(null)));
		
		System.out.println(food);


		//���� Collector �����
		Collector<Food, StringJoiner, String> foodNameCollector = Collector.of(
																() -> new StringJoiner(" | " ),
																(a, b) -> a.add(b.getName()),
																(a, b) -> a.merge(b),
																StringJoiner::toString);
		//���� �÷��͸� ��Ʈ���� ����
		String foodNames = foods.stream().collect(foodNameCollector);
		
		System.out.println(foodNames);
		
		
		
		
		//���� üũ
		//Predicate ���ǽ��� ���ڷ� �޾Ƽ� �ش� ������ �����ϴ� ��Ұ� �ִ��� üũ
		//anyMatch, allMatch, noneMatch	
		boolean anyMatch = foods.stream()						
							.anyMatch(obj -> obj.getCal() > 300);		//anyMatch : �ϳ��� ������ �����ϴ°�
		System.out.println(anyMatch);
		
		boolean allMatch = foods.stream()
							.allMatch(obj -> obj.getCal() > 100);		//allMatch : ��� ������ �����ϴ°�
		System.out.println(allMatch);
		
		boolean noneMatch = foods.stream()
								.noneMatch(obj -> obj.getCal() > 1000);	//noneMatch : ��� ������ �������� �ʴ°�
		System.out.println(noneMatch);
	}
}
