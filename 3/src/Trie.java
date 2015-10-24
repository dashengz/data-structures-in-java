import java.util.List;

public class Trie {

  private TrieNode root;
  public static final char NULL = '0';

  public Trie() {
    root = new TrieNode(NULL, false);
  }

  // implement your methods here
  // feel free (and you probably should) add helper private methods
  // problem 4a
  public void addWord(String word) {
    addWord(root, word);
  }

  private void addWord(TrieNode root, String word) {
    char[] letters = word.toCharArray();
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.children[letters[i] - 'a'] == null)
        node.children[letters[i] - 'a'] = new TrieNode(letters[i], false);
      node = node.children[letters[i] - 'a'];
    }
    node.endOfWord = true;
  }

  // problem 4b
  public boolean contains(String word) {
    return false;
  }

  // problem 4c
  public List<String> getStrings() {
    return null;
  }

  // problem 4d
  public List<String> getStartsWith(String prefix) {
    return null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb, 0);
    return sb.toString().trim();
  }
  
  private void buildString(TrieNode node, StringBuilder sb, int layer) {
    for (int i = 0; i < layer; i++) {  // print some indentation
      sb.append(" ");
    }
    sb.append(node);    // print the node itself
    sb.append("\n");
    for (TrieNode child : node.children) {  // recursively print each subtree
      if (child != null) {
        buildString(child, sb, layer + 1);
      }
    }
  }

  private class TrieNode {
    public char letter;
    public boolean endOfWord;
    public TrieNode[] children;

    public TrieNode(char letter, boolean endOfWord) {
      this.letter = letter;
      this.endOfWord = endOfWord;
      children = new TrieNode[26]; // number of letters in English alphabet
    }

    public String toString() {
      return endOfWord ? Character.toString(Character.toUpperCase(letter)) : Character.toString(letter);
    }
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.addWord("do");
    trie.addWord("dog");
    trie.addWord("doll");
    trie.addWord("dock");
    trie.addWord("doctor");
    System.out.println(trie);
//    System.out.println(trie.getStartsWith("hell"));
  }
}
