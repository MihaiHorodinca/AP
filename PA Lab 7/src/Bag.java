import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> bag = new ArrayList<Tile>();
    private final int[] noLetters = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    private final int[] pointsLetters = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < 10; i++)
                bag.add(new Tile(c, (int) (Math.random() * 10)));
        }
    }

    public synchronized List<Tile> extractTiles (int howMany) {
        List<Tile> extracted = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < howMany; i++) {
            if (bag.isEmpty()) {
                break;
            }
            int randomIndex = rand.nextInt(bag.size());
            extracted.add(bag.get(randomIndex));
            bag.remove(randomIndex);
        }

        return extracted;
    }
}
