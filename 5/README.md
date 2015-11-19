# Homework 5

COMS W3134 - Data Structures in Java
* 100pts toal (+ max 10 pts extra credit)
* Due: November 23, 23:59pm

Please be aware of the [late homework and academic honesty policy on the course website](http://www.cs.columbia.edu/~bauer/cs3134/homework.html).

Make sure to check that you submitted all your files to Github. In general, late submissions will not be accepted.

#Part 1 - Theory (40 pts)


## Problem 1: Sorting (15 pts)
Sort the following array of (base-10) integers using
   * (a) Insertion Sort (for each entry that needs to be moved, show the array after it has been moved to its final position).
   * (b) Quick Sort with median of three pivot (show the pivot in each step and the subarray after partitioning according to the pivot). You do not have to do an in-place sort, i.e. you don't have to use swaps to partition the array.
   * (c) Radix Sort.
   
[314, 512, 004, 999, 023, 042, 613, 109, 001, 123, 666]

## Problem 2: Sorting Stability (10 pts)

   * (a) Some sorting algorithms are not stable. Suggest a way to make any sorting algorithm stable by adding extra information to the keys. 
   * (b) Demonstrate, by providing a small example, that Quick Sort is not stable.

## Problem 3: Parallel MergeSort (15 pts)
In Merge Sort and Quick Sort, the individual recursive calls for each of the two partitions *left* and *right* of a subarray are independent of each other. You could therefore, in theory, run both recursive calls in parallel, for instance on a multicore CPU. Assuming *left* has *N₁* elements and *right* has *N₂* elements, the time needed to recursively sort both partitions is only *max(T(N₁),T(N₂))*. Now, assume you have a machine with an infinite number of cores, so you can run as many method calls in parallel as you want. What is the total running time to sort an array of length *N* using Merge Sort in this scenario? Provide a tight big-O bound and justify your answer.

#Part 2 - Programming (60 pts)

# Problem 4: Finding the *k*-largest values in a stream (20 pts)
Assume you are given an `Iterator<T> s` that provides a new element each time `s.next()` is called. 
We do not know how many elements `s` contains. In fact, there could be inifinitely many.  This is called a stream. Streams occur frequently in real world data analysis problems. For instance, in a data set of daily maximum temperatures, a new value can be added every day. 

Implement the class `KBestCounter<T extends Comparable<T>>` that keeps track of the *k*-largest elements seen so far in a
stream. In the weather example, we might be interested in the *k* hottest days on record so far. 

The class should have two methods

   * `public void count(T x)`: consider the next element in the stream. This operation should run in *O(log k)* time.
   * `public List<T> kbest()`:  return a sorted (largest to smallest) array of the *O(k)*-largest elements. This should run in *O(k log k)* time. 
  
Use a PriorityQueue to implement this functionality (e.g. `java.util.PriorityQueue`, which implements a heap). You should not use more than *O(k)* space.

The following example illustrates how `KBestcounter` could be used.

```
int k = 10;
KBestCounter <Integer> counter = new KBestCounter<>( k );

for ( int i = 0; i < 100; i ++) 
  counter.count(stream.next());

// print k largest after 200 elements
List<Integer> kbest = counter.kbest();
System.out.println(Arrays.toString(kbest.toArray()));

for ( int i = 0; i < 10; i ++) 
  counter.count(stream.next());
        
// print k largest after 110 elements
List<Integer> kbest = counter.kbest();
System.out.println(Arrays.toString(kbest.toArray()));
```


## Problem 5: Iterative Bottom-up Merge Sort (20 pts)

In Problem 5 and 6, we will examine different implementations of Merge Sort and their properties. Add your solutions to the class `MergeSort`.  We will only consider arrays of Integers.

The Merge Sort implementation discussed in class uses recursion. In this problem, you will write a non-recursive (iterative) implementation of Merge Sort. Your algorithm should start with each pair of elements in the array
and then sort larger and larger subarrays bottom-up. 
For instance, to sort [1,9,4,2,8,7,3,5,0] you do the following steps: 

   * size 2: [1,9] [2,4] [7,8] [3,5] [0]
   * size 4: [1,2,4,9] [3,5,7,8] [0]
   * size 8: [1,2,3,4,5,7,8,9] [0]
   * final: [0,1,2,3,4,5,7,8,9]

Note how these steps are different from the recursive merge sort.

You can use *O(N)* space (in addition to the input array) and your algorithm should have the same running time as recursive merge sort. Write the method `public static void mergeSortB(Integer[] inputArray)` that sorts the array `inputArray` in place. You can use the the existing original `merge` method. 

## Problem 6: Merge Sort for Lists, Without Side Effects (20 pts)
  
The Merge Sort implementation discussed in class sorts the array in place. This implementation is typical in languages like C and Java, in which objects, or pointers to objects, can be passed to other methods that can then modify the content of the object (here add/remove/move elements of the array). As an alternative, we might want to implement a version of Merge Sort in which the *return value* of each recursive call is a sorted list. A new list is created when the sorted sublists (return values of each recursive call) are merged. The original list is not modified during sorting. In general, no data outside of the method, not even the state of the parameters, should be changed. This is also referred to as a *side-effect free* function -- an important concept in functional programming.

First, write the method `public static List<Integer> mergeLists(List<Integer> left, List<Integer> right)`, that takes two sorted lists as a parameter and returns a new list containing the values of `left` and `right` in increasing order. The method should run in *O(left.size() + right.size())*, assuming that `left` and `right` are linked lists. Hint: use iterators.

Next, write the method `public static List<Integer> sortList(List<Integer> inputList)` that uses `mergeLists` to sort a list of integers *without modifying it* and returns a new sorted list. You might find it useful to take a look at the [java.util.List subList method](http://docs.oracle.com/javase/7/docs/api/java/util/List.html#subList%28int,%20int%29).

Finally, estimate (as a tight big-O bound) how much space your method requires, in addition to the input array of size N. 

## Problem 7: Extra Credit (Theory, 10pts)
For a fixed sequence of *N* integers, you can use a heap to find the median in *O(N log N)* time. 

Assume that, instead of a fixed sequence of integers, you have a stream (implemented as an iterator, as described in in problem 4). Instead of the *k*-largest integers, you want to to keep track of the median of the integers in the stream seen so far (not the mean!). Describe a way to keep track of the median in the stream, so that you can return the current median at any time in *O(1)*. When we encounter a new number in the stream, updating the median should only take *O(log N)* time, where *N* is the number of integers seen in the stream so far, including the latest one.

Hint: Use more than one heap.


