package castingExample;

public class MainApp {
    public static void main(String[] args) {
        Animal animal = new Mammel("Elephant", 5);

        animal.eat();
        ((Mammel)animal).walk();
    }
}
