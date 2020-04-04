package aquarium.repositories;

import aquarium.models.decorations.Decoration;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class DecorationRepository implements Repository {

    private Collection<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new LinkedHashSet<>();
    }

    @Override
    public void add(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        boolean isPresent = false;
        Decoration decorationToRemove = null;

        for (Decoration decoration1 : this.decorations) {
            if (decoration1.equals(decoration)) {
                decorationToRemove = decoration1;
                isPresent = true;
                break;
            }
        }

        if (isPresent){
            this.decorations.remove(decorationToRemove);
        }

        return isPresent;
    }

    @Override
    public Decoration findByType(String type) {
        Decoration searchedDecoration = null;

        for (Decoration decoration : this.decorations) {
            if (decoration.getClass().getSimpleName().equals(type)){
                searchedDecoration = decoration;
            }
        }

        return searchedDecoration;
    }
}
