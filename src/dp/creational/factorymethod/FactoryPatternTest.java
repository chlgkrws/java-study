package creational.factorymethod;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Client, Creator ºÎºÐ
 * @author cgw981
 *
 */
public class FactoryPatternTest {
	
	public static void main(String args[]) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Shape circle = shapeFactory.getShape("CIRCLE");
		circle.draw();
		
		Shape rectangle = shapeFactory.getShape("RECTANGLE");
		rectangle.draw();
		
		Shape square = shapeFactory.getShape("SQUARE");
		square.draw();
	}
}



//HashSet<String> hs = new HashSet<String>();
//Iterator<String> it = hs.iterator();
