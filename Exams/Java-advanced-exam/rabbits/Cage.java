package rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public List<Rabbit> data() {
        return this.data;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void add(Rabbit rabbit) {
        if (data.size() < getCapacity()) {
            data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        for (Rabbit rabbit : data) {
            if (rabbit.getName().equalsIgnoreCase(name)) {
                data.remove(rabbit);
                return true;
            }
        }
        return false;
    }

    public void removeSpecies(String species) {
        List<Rabbit> rabbitsToRemove = this.data.stream()
                .filter(rabbit -> rabbit.getSpecies().equals(species))
                .collect(Collectors.toList());
        this.data.removeAll(rabbitsToRemove);
    }

    public Rabbit sellRabbit(String name) {
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            String rabbit = data.get(i).getName();
            if (rabbit.equalsIgnoreCase(name)) {
                data.get(i).setAvailable();
                index = i;
                break;
            }
        }
        return data.get(index);
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> sold = new ArrayList<>();
        for (Rabbit rabbit : this.data) {
            if (rabbit.getSpecies().equalsIgnoreCase(species)) {
                sold.add(rabbit);
                this.data.remove(rabbit);
            }
        }
        return sold;
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Rabbits available at %s:", getName()))
                .append(System.lineSeparator());
        for (Rabbit rabbit : data) {
            report.append(rabbit).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}

