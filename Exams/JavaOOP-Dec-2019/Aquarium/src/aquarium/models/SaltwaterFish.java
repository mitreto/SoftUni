package aquarium.models;

public class SaltwaterFish extends BaseFish {
    // Has 5 initial size.
    //Can only live in SaltwaterAquarium!

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(5);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize() + 2);
    }
}
