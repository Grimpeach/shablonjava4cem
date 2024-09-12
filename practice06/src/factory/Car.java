package factory;

abstract class Car {
    public abstract void drive();
}

class SportsCar extends Car {
    public void drive() {
        System.out.println("Driving a sports car!");
    }
}

class SUV extends Car {
    public void drive() {
        System.out.println("Driving an SUV!");
    }
}

abstract class CarFactory {
    public abstract Car createCar();
}

class SportsCarFactory extends CarFactory {
    public Car createCar() {
        return new SportsCar();
    }
}

class SUVFactory extends CarFactory {
    public Car createCar() {
        return new SUV();
    }
}
