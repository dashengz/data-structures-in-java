public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {
  AvlTree<Pair<K, V>> tree;
  public AvlMap() {
    tree = new AvlTree<>();
  }

  @Override
  public void put(K key, V value) {
    Pair<K, V> pair = new Pair<>(key, value);
    tree.insert(pair);
  }

  @Override
  public V get(K key) {
    V value = null;
    Pair<K, V> pair = new Pair<>(key, value);
    if (tree == null)
      return null;
    if (tree.get(pair) == null)
      return null;
    if (key.compareTo(tree.get(pair).key) == 0) {
      value = tree.get(pair).value;
    }
    return value;
  }

  public static void main(String[] args) {
    AvlMap<String, Integer> map = new AvlMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);
    map.put("D", 4);
    map.put("C", 5);

    System.out.println(map.get("C"));
    System.out.println(map.get("E"));
  }
}
