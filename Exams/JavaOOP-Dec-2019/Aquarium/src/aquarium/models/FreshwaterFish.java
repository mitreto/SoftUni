package aquarium.models;

public class FreshwaterFish extends BaseFish {
    // Has 3 initial size
    // Can only live in FreshwaterAquarium!

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(3);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize() + 3);
    }
}
