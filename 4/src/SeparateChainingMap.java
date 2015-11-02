public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  public static final int SCALE_FACTOR = 2;
  public static final int INITIAL_TABLE_SIZE = 8;
  public static final double MAX_LOAD_FACTOR = 1.0;

  public SeparateChainingMap() {

  }

  public int getSize() {
    // returns number of pairs in map
    return 0;
  }

  public int getTableSize() {
    // returns size of table
    return 0;
  }

  public void put(K key, V value) {

  }

  public V get(K key) {
    return null;
  }

  public void upsize() {

  }
}
