import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BwogBot {

  private SeparateChainingMap<String, Integer> counts;

  public BwogBot() {
    counts = new SeparateChainingMap<>((int) Math.pow(2, 16));
  }

  public void readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String line;

    while ((line = br.readLine()) != null) {
      for (String word : line.split("\\s+")) {
        if (counts.get(word) != null) {
          counts.put(word, counts.get(word) + 1);
        } else {
          counts.put(word, 1);
        }
      }
    }
    br.close();
  }

  public int getCount(String word) {
    return counts.get(word);
  }

  public List<String> getNMostPopularWords(int n) {
    List<String> words = counts.keyList();
    words.sort((String first, String second) -> Integer.compare(counts.get(second), counts.get(first)));
    return words.subList(0, n);
  }

  public Map<String, Integer> getMap() {
    return counts;
  }

  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    long endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);

    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
    System.out.println(bot.getCount("hodor"));
    System.out.println(bot.getNMostPopularWords(100));
  }
}
