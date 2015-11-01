# Homework 3
COMS W3134 - Data Structures in Java
* 100pts toal
* Due: Wednesday 1021/ 11:59pm


Please be aware of the [late homework and academic honesty policy on the course website](http://www.cs.columbia.edu/~bauer/cs3134/homework.html).


Make sure to check that you submitted all your files to Github. In general, late submissions will not be accepted.


#Part 1 - Theory - SOLUTIONS

## Problem 1
**(a)** Assume  you  start  with  an  empty Binary Search Tree. Show  the  result  of  inserting  3,7,2,8,9,4,5 into the tree (in this sequence). Is the tree balanced? If not, where is the balance condition violated?

```
        3
      /   \
    2       7
          /   \
        4       8
          \       \
            5       9
```

The tree is **not balanced**. The balance condition is violated at the **root**.

**(b)** Show the result of deleting the root of the tree from part (a) (using the convention for the deletion operation made in class).

```
        4
      /   \
    2       7
          /   \
        5       8
                  \
                    9
```


## Problem 2
Show the result of inserting 3,7,2,8,9,4,5 (in this sequence) into an initially empty AVL tree.  You do not need to draw all intermediate trees, but please pecify which rotations are needed (if any) and around which node.

#### Rotation 1: Inserting 9 (single rotation)
```
        3
      /   \
    2       7
              \
                8
```

```
        3
      /   \
    2       8
          /   \
        7       9
```

#### Rotation 2: Inserting 4 (double rotation)
```
Step 1
        3
      /   \
    2       8
          /   \
        7       9
          \
            4
```
```
Step 2
        3
      /   \
    2       7
          /   \
        4       8
                  \
                    9
```
```
Balanced
        7
      /   \
    3       8
  /   \       \
2       4       9
```

#### Final Tree
```
            7
        /     \
    3           8
  /   \           \
2       4           9
          \
            5
```

#Part 2 - Programming

## Problem 3: Binary Search Trees  (?? pts)
In this problem, you will add functionality to a Binary Search Tree implementation.

### a) (?? pts)
Write a method `public boolean isBst()` that returns true if the tree is a Binary Search Tree. You may want to use the public method / private method pattern, where a non-recursive public method calls a private recursive method. An example can be found here [Recitation Week 5 Friday Tree Sample Code](https://github.com/cs3134/recitations/blob/master/week5-fri/basic-tree/src/Tree.java)

### b) (?? pts)
Write a method `public List<T> kMin(int k)` that returns the *k* smallest elements from the Binary Search Tree. Assuming you Binary Tree is balanced, your method should run in *O(k + log N)* time (i.e. you cannot just call `getMin` N times).

### c) (??? pts)
Write a method `public List<T> getInterval(T min, T max)` that returns all elements elements *x* from the Binary Search Tree, such that *min <= x <= max*. Your method should run in *O(k + log N)* where *k* is the number of elements in the resulting list.

## Problem 4: Trie

A trie is a compact representation of a set of words in a tree. Every node in the tree corresponds to the prefix of a word. The children of a node represent all different strings that share the same prefix but differ in the last letter. The root node represents the empty prefix.

The following prefix tree represents the words `[do, dog, doll, dock, doctor ]`. Note that some nodes (displayed in bold) are specially marked as end-of-word nodes. You can read off a word by starting at the root and following a path down until you reach a bold node.

![./trie.pdf](./trie.pdf)

You're given the class `Trie.java` with a inner class `TrieNode.java` that represents the skeleton code of a trie and its node. In the code, the root node has the letter `'0'` (shown in the line `public static final char NULL = '0';`).

### a) (?? pts)

Implement the method `public void addWord(String word)` that adds a word to the trie. You'll only get **lowercase input**.

You will find the public private method pattern useful here again. In particular, you'll want to adopt a recursive pattern. You can also do an iterative version instead of the recursive version. Both ways are very accepted.

You'll also find it useful to know that a `char` in Java is essentially an integer. For example, lower case `a` is basically `141`. Then, `'b' - 'a' = 1`. To check out the integers behind chars, check out [https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html](https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html) or just type `man ascii` in your terminal.

### b) (?? pts)

Implement the method `public boolean contains(String word)` that checks if a word is present in the trie. Remember to use the `endOfWord` flag to check if a word is present in the tree. For example, in the diagram above, even though `doc` exists in the tree, `c` isn't an end of word, so `doc` isn't a word in the trie (which makes sense since it wasn't added).

### c) (?? pts)

Implement the method `public List<String> getStrings()` that gives a list of all the words in the trie. For example, the trie above should return a list containing `[do, dog, doll, dock, doctor ]`.

### d) (?? pts)

Implement the method `public List<String> getStartsWith(String prefix)` that returns a list of words that starts with the prefix. For example, calling this method on the trie above with prefix `doc` should produce a list containing `[doctor, dock]`. This is also a very useful cheat for scrabble if you haven't noticed the potential of tries in the game.

This entire program should use `TrieNode`s. **If you use a list to store words in the Trie itself (beyond using it as a return value collector in `getStrings` and `getStartsWith`), you will be heavily penalized for this project. Think about the runtime for such a program.**

Beyond a cool scrabble hack, tries are used very frequently to speed up word retrieval and partial word searches. They are also very common interview questions. `;)`
