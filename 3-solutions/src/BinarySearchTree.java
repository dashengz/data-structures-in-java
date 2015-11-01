import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

  protected BinaryNode<T> root;

  public BinarySearchTree() {
    root = null;
  }

  // 3a
  
  public boolean isBst() {
    if (root == null) {
      return true;
    }
    T max = findMax();
    T min = findMin();
    return isBst(this.root, min, max);
  }

  private boolean isBst(BinaryNode<T> n, T min, T max) {
    if (n == null) {
      return true;
    }
    if (n.data.compareTo(min) < 0 || n.data.compareTo(max) > 0) {
      return false;
    }

    if (n.left != null && n.left.data.compareTo(n.data) > 0 || n.right != null && n.right.data.compareTo(n.data) < 0) {
      return false;
    }
    return isBst(n.left, min, n.data) && isBst(n.right, n.data, max);
  }

  /* The following is an alternative version that runs in O(N). 
   * The algorithm is based on the idea that the in-order traversal of a BST
   * must be in increasing order. We could compute this sequence explicitly, 
   * store it in a list and check that the list is in increasing order, but 
   * this would require an additional O(N) space. The solution below uses O(1)
   * extra space. 
   
  private static class BstException extends RuntimeException {
  }

  private boolean isBst() {
    if (root == null) {
        return true;
    }
    try {
        T max = isBst(root, null);
    } catch (BstException e) {
        return false;
    }
    return true;
  }

  private T isBst(BinaryNode<T> n, T prev) {
    if ((prev != null) && (prev.compareTo(n.data) > 0)) {
        throw new BstException();    
    }

    if (n.left != null) {
      if (isBst(n.left, prev).compareTo(n.data) > 0) {
        throw new BstException();    
      }
    }

    if (n.right != null) {
      return isBst(n.right,n.data);
    }

    return n.data;
  }
  */

  // 3b
  public List<T> getInterval(T min, T max) {
    List<T> list = new LinkedList<>();
    getInterval(this.root, min, max, list);
    return list;
  }

  private void getInterval(BinaryNode<T> n, T min, T max, List<T> list) {
    if (n == null) {
      return;
    }
    if (n.data.compareTo(min) < 0) {
      getInterval(n.right, min, max, list);
      return;
    }
    if (n.data.compareTo(max) > 0) {
      getInterval(n.left, min, max, list);
      return;
    }
    getInterval(n.left, min, max, list);
    list.add(n.data);
    getInterval(n.right, min, max, list);
  }

  // 3c
  public Iterator<T> iterator() {
    // Solution 1: Traversal list iterator
    return postorderTraversal(root).iterator();

    // Solution 2: Lazy traversal iterator
  }

  private List<T> inorderTraversal(BinaryNode<T> n) {
    if (n == null) {
      return new LinkedList<T>();
    }
    List<T> traversal = new LinkedList<T>();
    for (T data : inorderTraversal(n.left)) {
      traversal.add(data);
    }
    traversal.add(n.data);
    for (T data : inorderTraversal(n.right)) {
      traversal.add(data);
    }
    return traversal;
  }

  private List<T> postorderTraversal(BinaryNode<T> n) {
    if (n == null) {
      return new LinkedList<T>();
    }
    List<T> traversal = new LinkedList<T>();
    for (T data : postorderTraversal(n.left)) {
      traversal.add(data);
    }
    for (T data : postorderTraversal(n.right)) {
      traversal.add(data);
    }
    traversal.add(n.data);
    return traversal;
  }

  /* END HOMEWORK 3, PROBLEM 3 */

  public void insert(T x) {
    root = insert(x, root);
  }

  public void remove(T x) {
    root = remove(x, root);
  }

  public T findMin() {
    if (isEmpty())
      throw new IllegalStateException();
    return findMin(root).data;
  }

  public T findMax() {
    if (isEmpty())
      throw new IllegalStateException();
    return findMax(root).data;
  }

  public boolean contains(T x) {
    return contains(x, root);
  }

  public void makeEmpty() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void printTree() {
    if (isEmpty())
      System.out.println("Empty tree");
    else
      printTree(root);
  }

  private BinaryNode<T> insert(T x, BinaryNode<T> t) {
    if (t == null)
      return new BinaryNode<>(x, null, null);

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      t.left = insert(x, t.left);
    else if (compareResult > 0)
      t.right = insert(x, t.right);
    else {
      // duplicate, do nothing
    }

    return t;
  }

  private BinaryNode<T> remove(T x, BinaryNode<T> t) {
    if (t == null)
      return t; // Item not found; do nothing

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      t.left = remove(x, t.left);
    else if (compareResult > 0)
      t.right = remove(x, t.right);
    else if (t.left != null && t.right != null) {
      // 2 children
      t.data = findMin(t.right).data;
      t.right = remove(t.data, t.right);
    } else
      // 1 or 0 children
      t = (t.left != null) ? t.left : t.right;
    return t;
  }

  private BinaryNode<T> findMin(BinaryNode<T> t) {
    if (t == null)
      return null;
    else if (t.left == null)
      return t;
    return findMin(t.left);
  }

  private BinaryNode<T> findMax(BinaryNode<T> t) {
    if (t != null)
      while (t.right != null)
        t = t.right;

    return t;
  }

  private boolean contains(T x, BinaryNode<T> t) {
    if (t == null)
      return false;

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      return contains(x, t.left);
    else if (compareResult > 0)
      return contains(x, t.right);
    else
      return true; // Match
  }

  private void printTree(BinaryNode<T> t) {
    if (t != null) {
      printTree(t.left);
      System.out.println(t.data);
      printTree(t.right);
    }
  }

  private int height(BinaryNode<T> t) {
    if (t == null)
      return -1;
    else
      return 1 + Math.max(height(t.left), height(t.right));
  }

  // Test program
  public static void main(String[] args) throws Exception {
    BinarySearchTree<Integer> t = new BinarySearchTree<>();
    final int NUMS = 100;// 4000;
    final int GAP = 37;

    System.out.println("Checking... (no more output means success)");

    for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
      t.insert(i);

    t.printTree();

    for (int i = 1; i < NUMS; i += 2)
      t.remove(i);

    t.printTree();

    if (NUMS < 40)
      t.printTree();
    if (t.findMin() != 2 || t.findMax() != NUMS - 2)
      System.out.println("FindMin or FindMax error!");

    for (int i = 2; i < NUMS; i += 2)
      if (!t.contains(i))
        System.out.println("Find error1!");

    for (int i = 1; i < NUMS; i += 2) {
      if (t.contains(i))
        System.out.println("Find error2!");
    }
    BinarySearchTree<String> st = new BinarySearchTree<String>();
    String s = "159438270";
    for (int j = 0; j < s.length(); j++) {
      st.insert(s.substring(j, j + 1));
    }
    st.remove("f");
    System.out.println("x " + st.contains("x"));
    System.out.println("max " + st.findMax());
    System.out.println("min " + st.findMin());
    System.out.println("interval " + st.getInterval("1", "5"));

    for (String element : st) {
      System.out.println(element);
    }

    BinarySearchTree<Integer> integerST = new BinarySearchTree<>();
    for (int i = 2; i < 9; i++) {
      integerST.insert(i);
    }
    System.out.println(integerST.postorderTraversal(integerST.root));
  }

  private class BinaryNode<U> {

    public U data; // The data in the node
    public BinaryNode<U> left; // Left child
    public BinaryNode<U> right; // Right child

    public BinaryNode(U data) {
      this(data, null, null);
    }

    public BinaryNode(U data, BinaryNode<U> left, BinaryNode<U> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public class BinarySearchTreeIterator<V> implements Iterator<V> {

    private Stack<BinaryNode<V>> stack;
    private BinaryNode<V> current;

    public BinarySearchTreeIterator(BinaryNode<V> root) {
      this.stack = new Stack<BinaryNode<V>>();
      this.current = root;
    }

    public boolean hasNext() {
      return this.current != null || !this.stack.empty();
    }

    public V next() {
      while (current != null) {
        this.stack.push(this.current);
        this.current = this.current.left;
      }
      if (!this.stack.empty()) {
        BinaryNode<V> next = this.stack.pop();
        this.current = next.right;
        return next.data;
      }
      return null;
    }

    public void remove() {
      return;
    }
  }
}
