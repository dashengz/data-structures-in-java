import java.util.*;

public class KBestCounter<T extends Comparable<T>> {
   
    PriorityQueue<T> heap;
    private int kBest;

    public KBestCounter(int k) {
        kBest = k;
        heap = new PriorityQueue<>(kBest, Collections.reverseOrder());
    }

    public void count(T x) {
        heap.add(x);
    }

    public List<T> kbest() {
        LinkedList<T> kBestList = new LinkedList<>();
        for (int i = 0; i < kBest; i++) {
            if (heap.peek() != null) {
                kBestList.add(heap.poll());
            }
        }

        for (T item : kBestList) {
            heap.add(item);
        }

        return kBestList;
    }

    public static void main(String[] args) {
//        int k = 5;
//        List<Integer> numbers = Arrays.asList(new Integer[] { 1, 2, 3 });
//        KBestCounter<Integer> counter = new KBestCounter<>(k);
//        numbers.stream().forEach(x -> {
//            counter.count(x);
//        });
//        List<Integer> kbestStudent = counter.kbest();
//        System.out.println(Arrays.toString(kbestStudent.toArray()));
//        for (int i = 4; i < 100; i++) {
//            counter.count(i);
//        }
//        kbestStudent = counter.kbest();
//        System.out.println(Arrays.toString(kbestStudent.toArray()));
//        counter.count(101);
//        counter.count(102);
//        counter.count(100);
//        kbestStudent = counter.kbest();
//        System.out.println(Arrays.toString(kbestStudent.toArray()));
    }
}
