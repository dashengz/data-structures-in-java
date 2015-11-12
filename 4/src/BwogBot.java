import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Pair<String, Integer>> words = new ArrayList<>();
    for (int i = 0; i < map.getTable().size(); i++) {
      for (int j = 0; j < map.getTable().get(i).size(); j++) {
        if (map.getTable().get(i) != null)
          words.add(map.getTable().get(i).get(j));
      }
    }

    words.sort(new Comparator<Pair<String, Integer>>() {
      @Override
      public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        if (o1.value.equals(o2.value))
          return 0;
        if (o1.value.compareTo(o2.value) < 0)
          return 1;
        else return -1;
      }
    });

    for (int i = 0; i < n; i++) {
      list.add(words.get(i).key);
    }

    return list;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
    System.out.println(bot.getNMostPopularWords(100));
//    System.out.println(bot.getCount("to"));
  }
}
