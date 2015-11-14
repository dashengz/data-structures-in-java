# Homework 4

COMS W3134 - Data Structures in Java
* 100pts toal (+ max 10 pts extra credit)
* Due: November 11, 23:59pm

Please be aware of the [late homework and academic honesty policy on the course website](http://www.cs.columbia.edu/~bauer/cs3134/homework.html).

Make sure to check that you submitted all your files to Github. In general, late submissions will not be accepted.

#Part 1 - Theory (24 pts)
Answer the questions for Part 1 in a separate .txt file, .pdf file, or [Github Markdown .md file](https://help.github.com/articles/markdown-basics/). Only these file formats will be graded. .rtf, .doc, or .docx submissions will receive no credit. Please make sure the size of your PDF documents doesn't exceed 500kB. 

## Problem 1 (12 pts): 
Consider the following array of integers: *a = [3, 9, 7, 8, 2, 5, 1]*

   * a) Show the result of inserting the integers in *a* one by one into an initially empty min-heap. Then show the result of calling deleteMin on the heap 3 times. After each insertion and deleteMin step, show the array and the tree structure it represents.
   * b) Sort the array using in-place using Heap Sort. First show the result of turning a into a max heap using the linear time algorithm to build a heap (submit only the final array). Then show the array after each deleteMax step. The last step should be an array sorted in increasing order.

## Problem 2 (12 pts): 
We discussed that a complete binary tree of *N* elements uses array positions 1 to *N*. Suppose we try to use an array representation of a binary tree that is not complete. We use the same layout for the array as for a complete tree, but only store entries for nodes that are actually in the binary tree, leaving the other entries set to null. For example: 
```
              A  
            /
           B
         /  \
        C    D
       /
      E

array representation: [AB-CD--E-------]
```

Give a big-O bound for the space required to store each of the following trees:

   * a) A complete binary tree with *N* nodes that, in addition, has two extra levels (that is, it is very slightly unbalanced).
   * b) a binary tree that has a deepest node at depth *2 log N*.
   * c) a binary tree that has a deepest node at depth *4.1 log N*.
   * d) the worst case binary tree.

#Part 2 - Programming (76 pts)

## Problem 3: AvlMap (28 pts)

In this problem, you will write a class `AvlMap.java` that implements the given `Map.java` interface. Skeleton code has been provided.

Specifically, your map should hold keys (denoted by the generic `K`) and values (denoted by the generic `V`). Since it is usually convenient to wrap the two items in a single class, we provided the `Pair.java` class as a "capsule" for one single key and a single value. Please do read through the code for `Pair.java` -- it is fairly easy to understand if not completely trivial.

Your `AvlMap` should contain an `AvlTree` as an instance variable. `AvlTree.java` contains the AVL tree implementation from the Weiss textbook, so you won't have to implement rotations from scratch. You should, however, read through this code in its entirety and understand what it is doing.  Feel free to modify `AvlTree.java` as you see fit. 

You are also allowed (and encouraged) to modify `AvlTree.java`.

Feel free to add any variables / private methods to `AvlMap` you need.
We will test your code on the following methods:

### a) (15 pts)

Write a method `public void put(K key, V value)` that inserts a pair into the `AvlTree`. This should be done in *O(log N)* time where `N` is the number of elements in the map. Any algorithm that goes slower than this will be heavily penalized.

Hint: Consider why `Pair.java` stipulates that `K` needs to be `Comparable` (that's what the line `K extends Comparable<? super K>` means.) and its implications for the `AvlTree` you are using. Then, decide if `AvlTree` should contain `K`, or `V`, or a `Pair` of `K` and `V`.

### b) (13 pts)

Write a method `public V get(K key)` that gets the value associated with a key. Feel free to error handle when the key doesn't exist in a way that you deem suitable. This should also run in `O(logN)` where `N` is the number of elements in the map. Any algorithm that goes slower than this will be heavily penalized.

## Problem 4 - SeparateChainingMap (28 pts)

Now that you're done implementing `Map.java` using `AvlMap.java`, it is time to try out another implementation -- `SeparateChainingMap.java`. This should be a map using separate chaining.

For the choice of list, you can use anything that implements the `java.util.List` interface. We recommend `java.util.LinkedList`.

Your table should be initially of length `INITIAL_TABLE_SIZE` (set to `8`).

Your implementation should access the `hashCode()` and `equals()` method provided by Java objects. All java classes ultimately inherit from the mother class `Object`, which provides default `equals()` and `hashCode()` methods (among others). 
`hashCode()` simple returns an integer (not necessarily positive). It does not guarantee that this integer addresses an entry in your hash map so you need to compute the actual index yourself (using modulo and taking care of negative values).

**Important: Do not modify any of the `public static final` variables in the SeparateChainingMap class**


You will be graded on the following sections:

### a) (6 pts)

Write a method `public void put(K key, V value)` that inserts a pair into the `SeparateChainingMap`. Again, you will find `hashCode()` and `equals()` useful if not essential. This should run in, on average, `O(1)` with a small load factor <=1.

### b) (6 pts)

Write a method `public V get(K key)` that retrieves the value associated with a key. This should run in, on average, `O(1)` with a small load factor.

### c) (6 pts)

Write the methods `public int getSize()` that returns the number of pairs in the map, and `public int getTableSize()` that returns the size of the table (not the size of the lists in the table) used by the map.

### d) (10 pts)

Write the method `public void upsize()` that increases the table size by `SCALE_FACTOR` (set to `2`) and rehashes all the elements into the new table. This should decrease the load factor associated with the map.

Then, modify your `put(K key, V value)` method to check if `upsize()` is necessary every time its called. `upsize()` is necessary if the load factor of the map is greater than `MAX_LOAD_FACTOR` (set to `1.0`).

## Problem 5: BwogBot (20 pts)

Now it's time to use your Map (and practice some Java File Input/Output)! Over midterms week, Linan was bored so he wrote a scraper to crawl [bwog](http://bwog.com) and collect ten years worth of comments (along with their likes and dislikes). The code (and data) is available here: [https://github.com/linanqiu/bwog-scraper](https://github.com/linanqiu/bwog-scraper)

We took the most recent 10,000 comments from that dataset, cleaned them up (removed punctuation, made all text lower case, tokenized etc.) and provide them here as `comments.txt` in your repository. The text file is specified as such:

- Words are separated by one or more spaces
- A single line represents a single comment

You will be building `BwogBot.java` that reads through the `comments.txt` and does a basic word count. After all, wouldn't it be fun to find out what people are saying on Bwog over the last year?

The skeleton code for `BwogBot.java` is provided.

### a) (10 pts)

Write the method `public void readFile(String fileName)` that reads the `comments.txt` and stores the data in a map with the counts of words. That is, the map should have a word as its key and the number of times the word appeared in `comments.txt` as its value. For example, if `hamdel` appears `3` times in the entire document (and it does), then your map should contain (among many other words and counts) a key of `hamdel` and a corresponding value of `3`.

For this entire program, you can use either `AvlTree.java` or `SeparateChainingMap.java`. **However, justify in the comments above this method why you chose either one. We will grade you based on your justification**. In particular, if you used inefficient `put` or `get` (or didn't do `resize` properly in `SeparateChainingMap.java`, you will find that this step takes ages). If you did this correct though, it shouldn't take more than half a second. After all the file only contains 10,000 lines (the full data contains 180,000).

### b) (3 pts)

Write the method `getMap()` that returns the map constructed in `readFile(String fileName)`. Notice that you are required to return an object implementing the `Map` interface, so either `AvlTree` or `SeparateChainingMap` is fine.

### c) (7 pts)

Write the method `getCount(String word)` that returns the count of a word in `comments.txt`. This returns the count of the word.

### Extra Credit: (10 pts)

Write the method `getNMostPopularWords(int n)` that returns the top `n` most popular words from the comments. **Then, based on what you see by reading the top 100 most common words, write a short comment on why this method is a good / bad way of analyzing texts, and what can we do to make this more effective**. Hint: are the top words very indicative of [bwog](http://bwog.com) or just plain English? If so, does it tell us anything special about [bwog](http://bwog.com)? This is a common issue in Natural Language Processing.
