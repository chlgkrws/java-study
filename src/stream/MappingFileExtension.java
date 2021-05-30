package stream;

import java.io.File;
import java.util.stream.Stream;

public class MappingFileExtension {
	
	public static void main(String[] args) {
		
		File[] fileArr = {
			new File("EX1.java"),
			new File("EX2.txt"),
			new File("EX3.js"),
			new File("EX4.few"),
			new File("EX5.pw"),
		};
		
		Stream<File> fileStream = Stream.of(fileArr);
		
		//map()���� Stream<File>�� Stream<String>���� ��ȯ
		Stream<String> filesNameStream = fileStream.map(File::getName);
		filesNameStream.forEach(System.out::println);		//��� ������ �̸� ���
		
		fileStream = Stream.of(fileArr);					//��Ʈ�� �����
		
		fileStream.map(File::getName)						
			.filter(s -> s.indexOf('.') != -1)				//Ȯ���ڰ� ���� ���� ����
			.map(s -> s.substring(s.indexOf('.') +1))		//Ȯ���ڸ� ����
			.map(String::toUpperCase)						//�빮�ڷ� ��ȯ
			.distinct()										//�ߺ� ����
			.forEach(System.out::println);					//���
		
		
	}
}
