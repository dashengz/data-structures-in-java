import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BwogBot {
  /*
  * Rationale of using SeparateChainingMap instead of AVLMap:
  * The AVLMap uses an AVL Tree, whose put() and get() methods run in O(Log(N))
  * while those of a SeparateChainingMap hash table
  * run in O(1) (with a small load factor since it has upsize() that rehashes the table)
  * In this problem we have a huge data set that a lot of put() and get() calls are required
  * So using the SCM is a better choice.
  */
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

//    words.sort(new Comparator<Pair<String, Integer>>() {
//      @Override
//      public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
//        if (o1.value.equals(o2.value))
//          return 0;
//        if (o1.value.compareTo(o2.value) < 0)
//          return 1;
//        else return -1;
//      }
//    });

    // use lambda expressions
    words.sort(
            (o1, o2) -> {
              if (o1.value.equals(o2.value))
                return 0;
              if (o1.value.compareTo(o2.value) < 0)
                return 1;
              else return -1;
            }
    );

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

    /*
    * Top 100 Popular Words:
    * hodor, the, to, a, is, i, of, you, and, that, this, it, in, s, nt, for, are, not, on, do, was, have, they, be, with,
    * so, what, about, all, at, we, as, if, like, people, your, columbia, who, just, bwog, my, no, but, there, an, how, one,
    * or, can, get, from, re, why, has, m, me, does, their, out, by, think, he, she, know, would, students, did, because,
    * were, should, rape, really, more, up, when, these, will, go, campus, some, been, good, even, time, also, her, actually,
    * being, than, only, here, school, going, any, someone, barnard, them, student, year, way
    */

    /*
    * From the list we can't really see a distinct pattern of the bwog language style:
    * The top of the list, hodor, comes from a single spam comment.
    * Most of the words are a. / pron. / adv. etc. instead of nouns or adjectives which have concrete meanings and personal opinions.
    * Maybe a way to handle this is to filter the list by setting a limit for each word (more than three letters) */
  }
}
