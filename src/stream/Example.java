package stream;

import java.util.Random;
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
		
		
		
	}
}
