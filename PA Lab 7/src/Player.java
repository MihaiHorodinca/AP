import java.util.List;
import java.util.concurrent.TimeUnit;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;

    public Player(String name) { this.name = name; }

    public void setGame(Game game) { this.game = game; }

    public String getName() { return this.name; }

    private boolean submitWord() throws InterruptedException {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }
        String word = new String();

        for (int i = 0; i < 7; i++){
            word += extracted.get(i).getLetter();
        }

        game.getBoard().addWord(this, word);

        TimeUnit.MILLISECONDS.sleep(50);

        return true;
    }

    public void run (){
        while(running){
            try {
                running = submitWord();
            }
            catch (Exception e){
            }
        }
    }
}