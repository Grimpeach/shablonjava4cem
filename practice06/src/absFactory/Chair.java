package absFactory;

interface Chair {
    void sitOn();
}

interface Table {
    void putOn();
}

class ModernChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on a modern chair");
    }
}

class ModernTable implements Table {
    public void putOn() {
        System.out.println("Putting items on a modern table");
    }
}

class ClassicChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on a classic chair");
    }
}

class ClassicTable implements Table {
    public void putOn() {
        System.out.println("Putting items on a classic table");
    }
}

interface FurnitureFactory {
    Chair createChair();
    Table createTable();
}

class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new ModernChair();
    }
    public Table createTable() {
        return new ModernTable();
    }
}

class ClassicFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new ClassicChair();
    }
    public Table createTable() {
        return new ClassicTable();
    }
}
