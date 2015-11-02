import java.io.IOException;
import java.util.List;

public class BwogBot {

  public BwogBot() {

  }

  public void readFile(String fileName) throws IOException {

  }

  public int getCount(String word) {
    return 0;
  }

  public List<String> getNMostPopularWords(int n) {
    return null;
  }

  public Map<String, Integer> getMap() {
    return null;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
    System.out.println(bot.getNMostPopularWords(100));
  }
}
