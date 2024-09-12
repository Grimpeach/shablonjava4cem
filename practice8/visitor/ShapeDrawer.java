package practice8.visitor;

class ShapeDrawer implements Visitor {
    @Override
    public void visit(Circle circle) {
        System.out.println("Drawing a circle with radius " + circle.radius);
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Drawing a rectangle with width " + rectangle.width + " and height " + rectangle.height);
    }
}
