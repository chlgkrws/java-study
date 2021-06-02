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
		
		//��ȸ(iterate) : forEach �޼��带 ����Ͽ� ��Ʈ���� ��ȸ
		List<Integer> list = List.of(3,2,1,5,7);
		list.stream()
			.forEach(System.out::print);
		
		System.out.println();
		
		//���Ľ�Ʈ���� ������� �� ������ ������ �� ����. ������� ��ȸ�ϰ� ������� forEachOrdered ���
		list.parallelStream()
			.forEach(System.out::print);
		
		System.out.println();
		
		list.parallelStream()
			.forEachOrdered(System.out::print);	
		
		
		
		
		//��� ��ġ��(reduce) : ��� ��Ʈ�� ��Ҹ� ó���Ͽ� ����� ���� �� ����
		
		//1. BinaryOperator�� ���ڷ� �޴� reduce
		list = List.of(1,2,3);
		Optional<Integer> opt = list.stream()
									.reduce((a, b) -> a + b);
		System.out.println(opt.orElse(-1));						//6
		
		
		//2. �׵(�ʱⰪ), BinaryOperator�� ���ڷ� �޴� reduce
		Integer sum = list.stream()
							.reduce(10, (a, b) -> a + b);
		System.out.println(sum);								//16
		
		
		//3. �׵(�ʱⰪ), BiFunction, BinaryOperator�� ���ڷ� �޴� reduce
		//BiFunction�� �׵�� ��� �� ����ó��, BinaryOperator�� ����ó���ϴ� ������ �Ѵ�.
		list = List.of(3, 7, 9);
		Integer result = list.parallelStream()
							.reduce(100, Integer::sum, (a, b) -> {
								System.out.println(a +" - "+ b +" : "+ (a - b));
								return a - b;
							});
		System.out.println(result);
		
		
		
		
		// ��� : �ּڰ�, ����, ��� ��
		OptionalDouble min = DoubleStream.of(4.1, 3.4, -1.3, 2.2, 5.5).min();
		min.ifPresent(System.out::println);
		
		long count = IntStream.of(2, 4, 1, 2).count();
		System.out.println(count);
		
		//�⺻���� Ưȭ�� IntStream, DoubleStream, LongStream������ ��� ����, ����� ���� �� ����
		double dSum = DoubleStream.of(1.2, 4.2, 21.2).sum();
		
		OptionalDouble avg = IntStream.of(3, 2, 1).average();
		
		avg.ifPresent(System.out::print);
		

	}
}
