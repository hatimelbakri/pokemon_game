import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Pokemon> pokemons;

    public Player(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void addPokemon(Pokemon p) {
        pokemons.add(p);
    }

    public void removePokemon(Pokemon p) {
        pokemons.remove(p);
    }

    public boolean hasPokemon(Pokemon p) {
        return pokemons.contains(p);
    }

    public boolean isMewtwoDefeated() {
        for (Pokemon p : pokemons) {
            if (p.getNumPokedex() == 150 && p.getHP() > 0) {
                return false;
            }
        }
        return true;
    }
}