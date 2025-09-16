import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pokemon {
    private int numPokedex, row, col, playerId;
    private int hp, attack, defense, speed;
    private int type1, type2;
    private BufferedImage image;

    public Pokemon(int numPokedex, int row, int col, int playerId) {
        this.numPokedex = numPokedex;
        this.row = row;
        this.col = col;
        this.playerId = playerId;
        this.hp = PokedexLoader.getHP(numPokedex);
        this.attack = PokedexLoader.getAttack(numPokedex);
        this.defense = PokedexLoader.getDefense(numPokedex);
        this.speed = PokedexLoader.getSpeed(numPokedex);
        this.type1 = PokedexLoader.getType1(numPokedex);
        this.type2 = PokedexLoader.getType2(numPokedex);

        try {
            this.image = ImageIO.read(new File("images/" + numPokedex + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getNumPokedex() {
        return this.numPokedex;  // assuming you have a field called numPokedex
    }
    public int getHP() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getType1() { return type1; }
    public int getType2() { return type2; }
    public BufferedImage getImage() { return image; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getPlayerId() { return playerId; }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isValidMove(int newRow, int newCol) {
        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);
    
        // Allow movement to any adjacent cell (8 directions)
        return (rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0);
    }
    

    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        // Apply type effectiveness using both types of the defender
        double eff1 = Type.getEfficacite(attacker.getType1(), defender.getType1());
        double eff2 = Type.getEfficacite(attacker.getType1(), defender.getType2());
        double eff3 = Type.getEfficacite(attacker.getType2(), defender.getType1());
        double eff4 = Type.getEfficacite(attacker.getType2(), defender.getType2());
        double[] effects = {eff1, eff2, eff3, eff4};
        double effectiveness = 1.0;

        for (double eff : effects) {
            if (eff != 1.0) {
                effectiveness = eff;
                break;
            }
        }
        int baseDamage = attacker.getAttack() - defender.getDefense();
        baseDamage = Math.max(1, baseDamage); // at least 1
        return (int) (baseDamage * effectiveness);
    } 
    
    public void attack(Pokemon target) {
        // Ensure the Pokémon are from different players
        if (this.playerId == target.getPlayerId()) {
            System.out.println("A player cannot attack their own Pokémon!");
            return;  // Prevent the attack if the Pokémon are from the same player
        }

        Pokemon first, second;
    
        if (this.speed >= target.speed) {
            first = this;
            second = target;
        } else {
            first = target;
            second = this;
        }
    
        int damage1 = calculateDamage(first, second);
        second.hp -= damage1;
        if (second.hp < 0) second.hp = 0;
    
        if (second.hp > 0) {
            int damage2 = calculateDamage(second, first);
            first.hp -= damage2;
            if (first.hp < 0) first.hp = 0;
        }
    }
}