package stream;

import java.util.Random;
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
		
		
		
	}
}
