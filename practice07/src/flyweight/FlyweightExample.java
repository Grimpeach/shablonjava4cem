package flyweight;

public class FlyweightExample {
    public static void main(String[] args) {
        TreeType oakType = TreeFactory.getTreeType("Oak", "Green", "Rough");
        TreeType pineType = TreeFactory.getTreeType("Pine", "Dark Green", "Smooth");

        Tree tree1 = new Tree(10, 20, oakType);
        Tree tree2 = new Tree(15, 25, oakType);
        Tree tree3 = new Tree(20, 30, pineType);

        tree1.draw();
        tree2.draw();
        tree3.draw();
    }
}
