package castingExample;

public class Animal {

    String name;

    public Animal(String name){
        this.name = name;
    }
    
    public void eat(){
        System.out.println(name + " eats...");
    }
}
