import java.io.IOException;
import java.util.LinkedList;

public class BenchmarkBwogBot {
  public static void main(String[] args) throws IOException {

    LinkedList<Long> scTimes = new LinkedList<>();
    LinkedList<Long> avlTimes = new LinkedList<>();
    LinkedList<Long> hashTimes = new LinkedList<>();

    System.out.println(Benchmarking SeparateChainingMap);
    for (int i = 0; i < 10; i++) {
      System.out.println(Run  + i);
      BwogBot sc = new BwogBot();
      long startTime = System.currentTimeMillis();
      sc.readFile(comments.txt);
      long endTime = System.currentTimeMillis();
      scTimes.add(endTime - startTime);
    }

    System.out.println(Benchmarking AvlMap);
    for (int i = 0; i < 10; i++) {
      System.out.println(Run  + i);
      BwogBotAvl avl = new BwogBotAvl();
      long startTime = System.currentTimeMillis();
      avl.readFile(comments.txt);
      long endTime = System.currentTimeMillis();
      avlTimes.add(endTime - startTime);
    }

    System.out.println(Benchmarking HashMap);
    for (int i = 0; i < 10; i++) {
      System.out.println(Run  + i);
      BwogBotJava hash = new BwogBotJava();
      long startTime = System.currentTimeMillis();
      hash.readFile(comments.txt);
      long endTime = System.currentTimeMillis();
      hashTimes.add(endTime - startTime);
    }

    System.out.println(Separate Chaining:  + scTimes.stream().mapToLong(x -> x).summaryStatistics());
    System.out.println(AVL:  + avlTimes.stream().mapToLong(x -> x).summaryStatistics());
    System.out.println(HashMap:  + hashTimes.stream().mapToLong(x -> x).summaryStatistics());

    /*
    Results:
    Separate Chaining: LongSummaryStatistics{count=10, sum=528, min=35, average=52.800000, max=146}
    AVL: LongSummaryStatistics{count=10, sum=1206, min=114, average=120.600000, max=133}
    HashMap: LongSummaryStatistics{count=10, sum=409, min=33, average=40.900000, max=50}
    */
  }
}
