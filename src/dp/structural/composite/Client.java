package structural.composite;

/**
 * 클라이언트 해당
 * @author cgw981
 *
 */
public class Client {
	public static void main(String[] args) {
		
		//Initialize component
		Leaf component1 = new Leaf();
		Leaf component2 = new Leaf();
		Leaf component3 = new Leaf();
		Leaf component4 = new Leaf();
		
		//Initialize composite
		Composite composite = new Composite();
		Composite composite1 = new Composite();
		Composite composite2 = new Composite();
		
		composite1.add(component1);		//children - leaf
		composite1.add(component2);
		composite1.add(component3);
		
		composite2.add(component4);		//children - leaf
		
		composite.add(composite1);		//children - composite
		composite.add(composite2);
		
		//Print the complete graphic
		System.out.println("composite start");
		composite.print();
		
		System.out.println("composite1 start");
		composite1.print();
		
		System.out.println("composite2 start");
		composite2.print();
	}
}
