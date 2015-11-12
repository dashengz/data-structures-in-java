import java.util.ArrayList;
import java.util.LinkedList;

public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  public static final int SCALE_FACTOR = 2;
  public static final int INITIAL_TABLE_SIZE = 8;
  public static final double MAX_LOAD_FACTOR = 1.0;

  private int tableSize;
  private int size;
  private ArrayList<LinkedList<Pair<K, V>>> arrayList;

  public SeparateChainingMap() {
    tableSize = INITIAL_TABLE_SIZE;
    size = 0;
    arrayList = new ArrayList<>();
    for (int i = 0; i < tableSize; i++)
      arrayList.add(new LinkedList<>());
  }

  public int getSize() {
    // returns number of pairs in map
    return size;
  }

  public int getTableSize() {
    // returns size of table
    return tableSize;
  }

  public void put(K key, V value) {
    Pair<K, V> pair = new Pair<>(key, value);
    int hash = key.hashCode();
    int position = hash % tableSize;
    LinkedList<Pair<K, V>> linkedList = arrayList.get(position);
    boolean found = false;
    for (Pair<K, V> pair1 : linkedList) {
      if (pair1.key.equals(key)) {
        pair1.value = value;
        found = true;
      }
    }
    if (!found) {
      arrayList.get(position).add(pair);
      size++;
    }
  }

  public V get(K key) {
    V value = null;
    Pair<K, V> pair = new Pair<>(key, value);
    int hash = key.hashCode();
    int position = hash % tableSize;
    LinkedList<Pair<K, V>> linkedList = arrayList.get(position);
    for (Pair<K, V> pair1 : linkedList) {
      if (pair1.compareTo(pair) == 0) {
        value = pair1.value;
        break;
      }
    }
    return value;
  }

  public void upsize() {

  }

  public static void main(String[] args) {
    SeparateChainingMap<String, Integer> map = new SeparateChainingMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);
    map.put("A", 2);
    map.put("E", 6);
    map.put("B", 3);
    map.put("D", 1);
    System.out.println(map.arrayList);
    System.out.println(map.get("E"));
    System.out.println(map.get("A"));
    System.out.println(map.get("F"));
    System.out.println(map.getTableSize());
    System.out.println(map.getSize());
  }
}
