package stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StaticMethodOfComparator {

	public static void main(String[] args) {
	
		Student[] students = {
				new Student("�ڹ�1", 1, 100),
				new Student("�ڹ�2", 1, 150),
				new Student("�ڹ�3", 2, 140),
				new Student("�ڹ�4", 2, 120),
				new Student("�ڹ�5", 3, 200),
				new Student("�ڹ�6", 3, 300),
				new Student("�ڹ�7", 3, 1200)
		};
		
		Stream<Student> studentStream = Stream.of(students);
		

		//������ �ݺ� ��������,������ �������� ����
		studentStream.sorted(Comparator.comparing(Student::getBan)
				.thenComparing(Comparator.naturalOrder()))
				.forEach(System.out::println);
		
		
		Stream<Student> studentStream2 = Stream.of(students);
		
		//�� ��� �� �̸��� ��������
		studentStream2.sorted(Comparator.comparing(Student::getBan)
				.thenComparing(Comparator.comparing(Student::getTotalScore).reversed()))
				.forEach(s -> System.out.println(s.name));
	}
}
	
class Student implements Comparable<Student>{
	
	String name;
	int ban;
	int totalScore;
	
	public Student(String name, int i, int totalScore) {
		super();
		this.name = name;
		this.ban = i;
		this.totalScore = totalScore;
	}

	@Override
	public int compareTo(Student o) {
		return o.totalScore - this.totalScore;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBan() {
		return ban;
	}
	public void setBan(int ban) {
		this.ban = ban;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return String.format("[%s, %d, %d]", name, ban, totalScore);
	}
	
}
