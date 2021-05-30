package lambda;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class shapeOfLambda {
	
	public static void main(String[] args) {
		IntSupplier s = () -> (int)(Math.random() * 100) + 1;
		IntConsumer c = i -> System.out.print(i + " ");
		IntPredicate p = i -> i % 2 == 0;
		IntUnaryOperator op = i -> i*10;
		
		int[] arr = new int[10];
		
		makeRandomList(s, arr);
		IntStream.of(arr).forEach(c);
		
		System.out.println();
		printEvenNum(p, c, arr);
		System.out.println();
		
		int[] test = doSomething(op, arr);
		IntStream.of(test).forEach(c);
	}
	
	static void makeRandomList(IntSupplier s, int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = s.getAsInt();
		}
	}
	
	static void printEvenNum(IntPredicate p, IntConsumer c, int[] arr) {
		for(int i : arr) {
			if(p.test(i)) {
				c.accept(i);
			}
		}
	}
	
	static int[] doSomething(IntUnaryOperator op, int[] arr) {
		int[] newArr = new int[arr.length];
		
		for(int i = 0; i < newArr.length; i++) {
			newArr[i] = op.applyAsInt(arr[i]);
		}
		
		return newArr;
	}
	
	
}
