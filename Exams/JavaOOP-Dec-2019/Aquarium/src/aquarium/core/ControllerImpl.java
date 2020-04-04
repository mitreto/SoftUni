package aquarium.core;

import aquarium.models.*;
import aquarium.models.aquariums.Aquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;
import aquarium.repositories.DecorationRepository;

import static aquarium.common.ExceptionMessages.*;
import static aquarium.common.ConstantMessages.*;

import java.util.Collection;
import java.util.LinkedHashSet;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashSet<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;

        if (aquariumType.equals("FreshwaterAquarium")) {
            aquarium = new FreshwaterAquarium(aquariumName, 50);
            this.aquariums.add(aquarium);
        } else if (aquariumType.equals("SaltwaterAquarium")){
            aquarium = new SaltwaterAquarium(aquariumName, 25);
            this.aquariums.add(aquarium);
        } else {
            throw new IllegalArgumentException(INVALID_AQUARIUM_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;

        if (type.equals("Ornament")){
            decoration = new Ornament(1, 5);
            this.decorations.add(decoration);
        } else if (type.equals("Plant")){
            decoration = new Plant(5, 10);
            this.decorations.add(decoration);
        } else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }


        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration decoration = this.decorations.findByType(decorationType);

        if (decoration != null){
            for (Aquarium aquarium : this.aquariums) {
                if (aquarium.getName().equals(aquariumName)){
                    aquarium.addDecoration(decoration);
                    this.decorations.remove(decoration);
                }
            }
        } else {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        return String.format(
                SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM,
                decorationType,
                aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Fish fish;
        String outputMessage = "";
        String requiredAquariumForFish = "";

        if (fishType.equals("FreshwaterFish")){
            fish = new FreshwaterFish(fishName, fishSpecies, price);
            requiredAquariumForFish = "FreshwaterAquarium";
        } else if (fishType.equals("SaltwaterFish")){
            fish = new SaltwaterFish(fishName, fishSpecies, price);
            requiredAquariumForFish = "SaltwaterAquarium";
        } else {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }


        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                if (aquarium.getCapacity() - fish.getSize() < 0){
                    outputMessage = NOT_ENOUGH_CAPACITY;
                } else if (!requiredAquariumForFish.equals(aquarium.getClass().getSimpleName())){
                    outputMessage = WATER_NOT_SUITABLE;
                } else {
                    aquarium.addFish(fish);
                    outputMessage = String.format(
                            SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM,
                            fishType,
                            aquariumName);
                }
            }
        }

        return outputMessage;
    }

    @Override
    public String feedFish(String aquariumName) {

        int fishes = 0;
        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                aquarium.feed();
                fishes = aquarium.getFish().size();
            }
        }

        return String.format(FISH_FED, fishes);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double fishesPrice = 0.0;
        double decorationsPrice = 0.0;


        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                for (Fish fish : aquarium.getFish()) {
                    fishesPrice += fish.getPrice();
                }

                for (Decoration decoration : aquarium.getDecorations()) {
                    decorationsPrice += decoration.getPrice();
                }
            }
        }

        double total = fishesPrice + decorationsPrice;

        return String.format(VALUE_AQUARIUM, aquariumName, total);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        for (Aquarium aquarium : this.aquariums) {
           sb.append(aquarium.getInfo());
        }
        return sb.toString();
    }
}
