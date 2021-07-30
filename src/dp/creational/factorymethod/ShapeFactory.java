package creational.factorymethod;

public class ShapeFactory {

	/**
	 *  팩토리 메소드 - 객체 생성 후 반환
	 * @param shapeType
	 * @return
	 */
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();

		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();

		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}

		return null;
	}
}
