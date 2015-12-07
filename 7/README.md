# Homework 7

COMS W3134 - Data Structures in Java
* 100pts total (+20 extra credit)
* Due:  December 14th, 11:59pm

Please be aware of the [late homework and academic honesty policy on the course website](http://www.cs.columbia.edu/~bauer/cs3134/homework.html).

Make sure to check that you submitted all your files to Github. In general, late submissions will not be accepted.

#Part 1 - Theory (42 pts)

## Problem 1 (21 pts)

The object of the Kevin Bacon Game is to link a movie actor to Kevin
Bacon via shared movie roles. The minimum number of links is an
actor’s Bacon number. For instance, Tom Hanks has a Bacon number of 1;
he was in Apollo 13 with Kevin Bacon. Sally Field has a Bacon number
of 2, because she was in Forrest Gump with Tom Hanks, who was in
Apollo 13 with Kevin Bacon. Almost all well-known actors have a Bacon
number of 1 or 2. Assume that you have a comprehensive list of of (Actor, Movie) pairs.

  - (a) Explain how to find an actor’s Bacon number.
  - (b) Explain how to find the actor with the highest Bacon number.
  - (c) Explain how to find the minimum number of links between two arbitrary actors.


# Problem 2 (21 pts)

Consider the recursive algorithm below for finding the
shortest weighted path in an *acyclic graph*, from *s* to *t*.

```
Distance shortest(s, t) {
    Distance dt, tmp; 
    if (s == t)
        return 0;
    dt = ∞;
    for each Vertex v adjacent to s {
        tmp = shortest(v,t); 
        if(cost(s,v) + tmp < dt)
            dt = cost(s,v) + tmp; 
    }   
    return dt; 
}
```

  - (a) Why does this algorithm not work for general graphs? 
  - (b) Explain why this algorithm is guaranteed to terminate for acyclic graphs.  
  - (c) What is the worst-case running time of the algorithm?



#Part 2 - Programming: Super Soda (58 pts)

We'll use dynamic programming to do something really cool. Let's say that you love soda (or some other beverage that comes in cans that is within your legal parameters). You walk into a store, and find that there are sodas available in the following case sizes:

- 1 single can for $0.80 total
- 6 cans for $4 total
- 12 cans for $7.50 total
- 25 cans for $14 total
- 36 cans for $20 total

You are a college student, so you love penny pinching. You decide to come up with an algorithm to solve two problems:

1. Given `n`, the number of cans of soda you want, what is the minimum cost required?
2. Given `x`, the amount of money you have, what is the maximum number of cans of soda you can afford?

You realize that a greedy strategy you learned in 1004 may not work that well. The greedy strategy goes like this: say I want 100 cans. I start from the largest combination and work downwards.

- I can buy 100/36 = 2 packs of 36 cans, remainder = 28.
- I can buy 28/25 = 1 pack of 25 cans, remainder = 3
- I can buy 3/1 = 3 packs of 1 can, remainder = 0.

Total cost = 2 * 20 + 1 * 14 + 3 * 0.8 = 56.4

However, the actual minimum is 25*4 = 56

Instead, your goal is to come up with a **dynamic programming** algorithm to solve the two problems. 

### Minimum Soda Cost (29 pts)

In the class `SuperSoda`, write the method `public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n)`. 

`int[] sodaSizes` is an array of `int`s representing the different case sizes of sodas. For the problem above, it would be `int[] sodaSizes = new int[] {1, 6, 12, 25, 36}`. 

`double[] costs` is an array of `double`s representing the cost of each of those case sizes. For the problem above, it would be `double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 }`. 

`n` is the number of cans of soda you want. You can assume that `sodaSizes.length == costs.length >= 1.
The method should return the minimum cost required to purchase *n* sodas. 

**You can also assume that the smallest case size of `sodaSizes` is 1**, i.e. you can always buy an individual soda (without this limitation it would be possible that there are some amounts `n` of sodas that you cannot buy using a certain combination of case sizes).

Your final algorithm should employ dynamic programming. If you don't use dynamic programming, you will be heavily penalized. In fact, if you don't use dynamic programming (and solve this using either a bruter-force or a recursive divide-and-conquer algorithm), your runs will almost definitely time out since we will be testing with large values of n (at least `n = 100`).

**Hint**: Come up with suitable simpler subproblems and devise a divide-and conquer solution, then design a way to memoize the solution of these subproblems.


This method should return the minimum cost required as a `double`. Note that operations involving `double`s in Java can be slightly imprecise. Jarvis will take account of that and instead check doubles using a small margin of error (say 10^-6). Read [http://stackoverflow.com/questions/7180952/is-checking-a-double-for-equality-ever-safe](http://stackoverflow.com/questions/7180952/is-checking-a-double-for-equality-ever-safe)

What is the runtime for your method? 

### Maximum Soda Number (29 pts)

Write the method `public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost)`. The parameters are the same as the previous question, just that instead of `int n` you have `double cost`. This should return the maximum number of sodas you can buy with the given cost. Again, your algorithm should employ dynamic programming and code that fails to comply will be penalized severely.

What is the runtime for your method? 

## Extra Credit 

### Minimal Soda Cost Combination (20pts)

Write the method `public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n)` that returns an `int[]` representing the combination of sodas instead of just the number of sodas. This is the combination that minimizes the cost given the number of cans required. That is, say

```Java
int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
System.out.println(minimalSodaCost(sodaSizes, costs, 105)); // prints 59.6
System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105))); // prints [2, 1, 0, 1, 2]
```

since `[2, 1, 0, 1, 2]` is the number of 1 packs, 6 packs, 12 packs, 25 packs, 36 packs respectively that resulted in the cost of 59.6.

You can make all the same assumptions as the Minimum Soda Cost portion of this problem. This again should be dynamic programming, and the penalization applies.
