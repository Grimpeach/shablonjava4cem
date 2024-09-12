package practice8.visitor;

interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}