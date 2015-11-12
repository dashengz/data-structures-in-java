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
    this(INITIAL_TABLE_SIZE);
  }

  public SeparateChainingMap(int newTableSize) {
    tableSize = newTableSize;
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
      size++;
      double factor = (double) size/tableSize;
      if (factor > MAX_LOAD_FACTOR){
        arrayList.get(position).add(pair);
        upsize();
      }
      else
        arrayList.get(position).add(pair);
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
    SeparateChainingMap<K, V> newMap = new SeparateChainingMap<>(tableSize * SCALE_FACTOR);

    for (int i = 0; i < this.arrayList.size(); i++) {
      for (int j = 0; j < this.arrayList.get(i).size(); j++) {
        if (this.arrayList.get(i) != null)
          newMap.put(this.arrayList.get(i).get(j).key, this.arrayList.get(i).get(j).value);
      }
    }

    this.arrayList = newMap.arrayList;
    this.tableSize = newMap.arrayList.size();
  }

  public static void main(String[] args) {
    SeparateChainingMap<String, Integer> map = new SeparateChainingMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);
    map.put("D", 2);
    map.put("E", 2);
    map.put("F", 2);
    map.put("G", 2);
    map.put("H", 6);
    map.put("I", 3);
    map.put("I", 5);
    map.put("J", 1);
    map.put("K", 1);
    map.put("L", 1);
    map.put("M", 931);
    map.put("N", 1);
    map.put("O", 1);
    map.put("P", 1);
    map.put("Q", 1);
    System.out.println(map.arrayList);
    System.out.println(map.get("A"));
    System.out.println(map.get("G"));
    System.out.println(map.get("M"));
    System.out.println(map.getTableSize());
    System.out.println(map.getSize());
  }
}
