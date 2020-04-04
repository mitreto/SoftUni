package aquarium.models;

import aquarium.models.aquariums.Aquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.decorations = new LinkedHashSet<>();
        this.fish = new LinkedHashSet<>();
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int calculateComfort() {

        return this.decorations
                .stream()
                .mapToInt(Decoration::getComfort)
                .sum();

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {

        int capacity = this.capacity;
        if (capacity - fish.getSize() < 0) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        this.fish.add(fish);

    }

    @Override
    public void removeFish(Fish fish) {
        Fish fishToRemove = null;
        for (Fish fish1 : this.fish) {
            if (fish1.getName().equals(fish.getName())) {
                fishToRemove = fish1;
            }
        }

        if (fishToRemove != null) {
            this.fish.remove(fishToRemove);
        }
    }

    @Override
    public void addDecoration(Decoration decoration) {

        this.decorations.add(decoration);
    }

    @Override
    public void feed() {

        for (Fish fish1 : this.fish) {
            fish1.eat();
        }
    }



    @Override
    public String getInfo() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator());

        if (this.fish.isEmpty()) {
            stringBuilder.append("none").append(System.lineSeparator());
        } else {
            stringBuilder.append("Fish: ");

            List<String> fishNames = new ArrayList<>();
            for (Fish fish1 : this.fish) {
                fishNames.add(fish1.getName());
            }

            stringBuilder.append(String.join(" ", fishNames));
            stringBuilder.append(System.lineSeparator());

        }

        stringBuilder.append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator());

        stringBuilder.append(String.format("Comfort: %d", this.calculateComfort()))
                .append(System.lineSeparator());

        return stringBuilder.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
