package flyweight;

class ConcreteTreeType implements TreeType {
    private String name;
    private String color;
    private String texture;

    public ConcreteTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing a tree of type " + name + " with color " + color + " and texture " + texture + " at (" + x + ", " + y + ")");
    }
}
