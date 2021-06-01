package lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
	
	public static void main(String[] args) {
		Function<String, Integer> fs = s -> Integer.parseInt(s);
		
		Function<String, Integer> f = Integer::parseInt;
		
		
		//equals ���ٽ�, �޼��� ����
		//String str = new String("���� ��������");
	
		//Function<String, Boolean> equals = s -> str.equals(s);	//���ٽ�
		
		//Function<String, Boolean> equals2 = str::equals;	//�޼��� ����
		
		//��ü ���� 
		//Supplier<String> s = () -> new String();		//���ٽ�
		
		//Supplier<String> s2 = String::new;		//�޼��� ����
		
		//�Ű����� ����
		Function<String, String> s = str -> new String();	//���ٽ�
		
		Function<String, String> s2 = String::new;		//�޼��� ����
		
		//���� ��ȯ 
		BiFunction<String, Integer, Integer> parseInt = (str, num) -> Integer.parseInt(str, num);	//���ٽ�
				
		BiFunction<String, Integer, Integer> parseInt2 = Integer::parseInt;		//�޼��� ����
		
		
		//�迭 ����
		Function<Integer, int[]> arr = x -> new int[x];		//���ٽ�
		
		Function<Integer, int[]> arr2 = int[]::new;			//�޼��� ����
	}
	
	public Integer parseInt(String s) {
		return Integer.parseInt(s);
	}
}
