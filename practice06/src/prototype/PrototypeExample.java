package prototype;

class Car implements Cloneable {
    public String model;
    public int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class PrototypeExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Car car1 = new Car("Tesla", 2022);
        Car car2 = (Car) car1.clone(); 

        System.out.println(car2.model); 
    }
}
