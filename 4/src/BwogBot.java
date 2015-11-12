import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BwogBot {

  SeparateChainingMap<String, Integer> map;

  public BwogBot() {
    map = new SeparateChainingMap<>();
  }

  public void readFile(String fileName) throws IOException {
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNext()) {
      String word = scanner.next();
      if (map.get(word) == null)
        map.put(word, 1);
      else
        map.put(word, map.get(word) + 1);
    }

  }

  public int getCount(String word) {
    return map.get(word);
  }

  public List<String> getNMostPopularWords(int n) {
    return null;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
//    System.out.println(bot.getNMostPopularWords(100));
    System.out.println(bot.getCount("to"));
  }
}
