import java.util.List;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

  protected BinaryNode<T> root;

  public BinarySearchTree() {
    root = null;
  }

  // implement your methods here
  // feel free (and you probably should) add helper private methods
  // problem 3a
  public boolean isBst() {
    return isBst(root, findMin(), findMax());
  }

  private boolean isBst(BinaryNode<T> n, T less, T more) {
    if (n == null) {
      return true;
    }
    if (n.data.compareTo(less) <= 0 || n.data.compareTo(more) >= 0) {
      return false;
    }
    return isBst(n.left, less, n.data) && isBst(n.right, n.data, more);
  }

  // problem 3b
  public List<T> getInterval(T min, T max) {
    return null;
  }

  // problem 3c
  @Override
  public Iterator<T> iterator() {
    return null;
  }

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
    String s = "sdljfnzxvk234";
    for (int j = 0; j < s.length(); j++) {
      st.insert(s.substring(j, j + 1));
    }
    st.remove("f");
    System.out.println("x " + st.contains("x"));
    System.out.println("max " + st.findMax());
    System.out.println("min " + st.findMin());
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

}
