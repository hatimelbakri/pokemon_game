import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PokedexLoader {
    private static class Stats {
        int hp, attack, defense, speed;
        String t1,t2;

        Stats(int hp, int attack, int defense, int speed, String type1, String type2) {
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;
            this.t1 = type1;
            this.t2 = type2;
        }
    }

    private static Map<Integer, Stats> statsMap = new HashMap<>();

    static {
        try (BufferedReader br = new BufferedReader(new FileReader("data/pokedex_gen1.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int num = Integer.parseInt(parts[0].trim());
                int hp = Integer.parseInt(parts[4].trim());
                int attack = Integer.parseInt(parts[5].trim());
                int defense = Integer.parseInt(parts[6].trim());
                int speed = Integer.parseInt(parts[7].trim());
                String t1 = parts[2].trim();
                String t2 = (parts.length > 3 && !parts[3].trim().isEmpty()) ? parts[3].trim() : "SANS";

                statsMap.put(num, new Stats(hp, attack, defense, speed, t1, t2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHP(int num) {
        return statsMap.getOrDefault(num, new Stats(100, 10, 10, 10,"NORMAL","SANS")).hp;
    }

    public static int getAttack(int num) {
        return statsMap.get(num).attack;
    }

    public static int getDefense(int num) {
        return statsMap.get(num).defense;
    }

    public static int getSpeed(int num) {
        return statsMap.get(num).speed;
    }

    public static int getType1(int num) {
        return Type.getIndiceType(statsMap.get(num).t1);
    }
    
    public static int getType2(int num) {
        return Type.getIndiceType(statsMap.get(num).t2);
    }    
}
