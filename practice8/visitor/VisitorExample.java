package practice8.visitor;

public class VisitorExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(2, 3);

        Visitor areaCalculator = new AreaCalculator();
        Visitor shapeDrawer = new ShapeDrawer();

        circle.accept(areaCalculator);
        rectangle.accept(areaCalculator);

        circle.accept(shapeDrawer);
        rectangle.accept(shapeDrawer);
    }
}
