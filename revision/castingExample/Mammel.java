package castingExample;

public class Mammel extends Animal {

    int numOfLegs;

    public Mammel(String name, int legs) {
        super(name);
        this.numOfLegs = legs;
        super.eat();
    }

    public void walk() {
        System.out.println(name + "walks with " + this.numOfLegs + " legs.");
    }
    
}
