package structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 컴포지트에 해당.
 * @author cgw981
 *
 */
public class Composite implements Component{
	
	//Collection of child Components
	private List<Component> childComponents = new ArrayList<>();

	
	//Print the Component
	@Override
	public void print() {
		for(Component component : childComponents) {
			component.print();
		}
	}
	
	//Add the component to the composition
	public void add(Component component) {
		childComponents.add(component);
	}
	
	//Remove the component from the composition
	public void remove(Component component) {
		childComponents.remove(component);
	}
}
