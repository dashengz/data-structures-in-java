import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;

public class KBestCounter<T extends Comparable<T>> {
   
    PriorityQueue<T> heap;
    private int kBest;

    public KBestCounter(int k) {
        heap = new PriorityQueue<>();
        kBest = k;
    }

    public void count(T x) {
        heap.add(x);
    }

    public List<T> kbest() {
        LinkedList<T> kBestList = new LinkedList<>();
        for (int i = 0; i < kBest; i++) {
            kBestList.add(heap.poll());
        }
        return kBestList;
    }
}
