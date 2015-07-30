# Algorithms
My personal Java practises for some well-studied algorithms and problems commonly asked in technical interviews

## Implementations
* Given a number, return all possible strings that the number could represent if `0 -> {A, B, C}, 2 -> {D, E, F}`, and so on. (Modified from [*Original question*](http://www.glassdoor.com/Interview/phone-numbers-provided-a-phone-number-654-876-0987-return-all-possible-strings-that-the-phone-number-could-represent-QTN_361642.htm)) [**Solution**](AllPossibleStrings.java)
* You have a room-full of balances and weights. Each balance weighs ten pounds and is considered perfectly balanced when the sum of weights on its left and right sides are exactly the same. You have placed some weights on some of the balances, and you have placed some of the balances on other balances. Given a description of how the balances are arranged and how much additional weight is on each balance, determine how to add weight to the balances so that they are all perfectly balanced. ([*Original question*](http://www.careercup.com/question?id=12150672)) [**Solution**](Balance.java)
* [**Algorithms on binary trees**](BinaryTree.java). Comprises of the following algorithms:
 * Given a node value, finds the node in tree using dfs
 * Pre-order traversal
 * In-order traversal
 * Post-order traversal
 * Checks if tree is a BST
 * Gets the height of the BST
 * Checks if the tree is height balanced
 * Determines the Lowest Common Ancestor (LCA) of two nodes
 * Prints the tree level wise using BFS
 * Prints the average value for each level in tree
 * Determines the in-order successor for a node
* Suppose there are 5 types of coins: 50-cent, 25-cent, 10-cent, 5-cent, and 1-cent. Write a program to find the total number of different ways of making changes for any amount of money in cents. [**Solution**](CoinChange.java)
* Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible decodings of the given digit sequence. ([*Original question*](http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/)) [**Solution**](CountPossibleDecodings.java)
* Given a number, obtain the next larger permutation and next lower permutation by swapping around the digits in the number. [**Solution**](DigitPermutations.java)
* Implement Fibonacci in bottom-up dynamic programming fashion. [**Solution**](Fibonacci.java)
* Given n stairs, how many number of ways can you climb if u use either 1 or 2 at a time? ([*Original question*](http://www.careercup.com/question?id=3590768)) [**Solution**](FibonacciSteps.java)
* Implement Conway's game of life by predicting the next state of a matrix given its current state. [**Solution**](GameOfLife.java)
* [**Algorithms on Graphs**](Graph.java). Comprises of the following algorithms:
 * BFS
 * DFS
 * Kruskal (MST)
 * Prim (MST)
 * Modified Dijkstra (SSSP)
 * Bellman-Ford (SSSP)
 * Floyd-Warshall (APSP)
* You are given an M by N matrix containing only the characters 'P' and 'T'. You can flip the characters in any number of columns. I.e. 'P' becomes 'T' and 'T' becomes 'P' for all characters under that column. Your aim is to maximize the number of rows for which all the characters are the same. [**Solution**](HomogeneousRows.java)
* Implement integer division without using / or % in O(logN). [**Solution**](IntegerDivision.java)
* You are given a binary array with N elements: d[0], d[1], ... d[N - 1]. You can perform AT MOST one move on the array: choose any two integers and flip all the elements between (and including) them. What is the maximum number of '1'-bits which you can obtain in the final bit-string? ([*Original question*] (http://www.careercup.com/question?id=6262507668766720)) [**Solution**](KadaneBitFlip.java)
* Implement Karatsuba's multiplication algorithm. [**Solution**](Karatsuba.java)
* Implement Knuth shuffle. [**Solution**](KnuthShuffle.java)
* Given an array of numbers, return the Kth smallest number in the list. [**Solution**](KthSmallest.java)
* Given two strings, find the Longest Common Subsequence between them. [**Solution**](LongestCommonSubsequence.java)
* Given two strings, find the Longest Common Substring between them. [**Solution**](LongestCommonSubstring.java)
* Given an array of integers, find the Longest Increasing Subsequence of its items. [**Solution**](LongestIncreasingSubsequence.java)
* Given two strings, find the Longest Palindromic Subsequence between them. [**Solution**](LongestPalindromicSubsequence.java)
* Given two strings, find the Longest Palindromic Substring between them. [**Solution**](LongestPalindromicSubstring.java)
* Implement Kadane's algorithm, returning the maximum subarray sum as well as the start and end indexes representing the maximum subarray. [**Solution**](MaximumSubarray.java)
* Implement merge sort. [**Solution**](MergeSort.java)
* Given a sorted list of numbers, write a function to return a string representing the missing numbers from 0 to 99 inclusive. ([*Original question*](https://leetcode.com/problems/missing-ranges/)) [**Solution**](MissingRanges.java)
* Write a function strstr (char[] haystack, char[] needle) to return the first index occurrence of needle in haystack. If needle is not present in haystack, return -1. [**Solution**](NeedleHaystack.java)
* Implement numeric addition of two numbers in both decimal as well as binary. [**Solution**](NumericAddition.java)
* Given an array and a number n, can you obtain n using a 2 numbers in the array? What about 3 numbers?  [**Solution**](ObtainableSum.java)
* Implement a queue using 2 stacks. [**Solution**](QueueStack.java)
* Implement quick sort. [**Solution**](QuickSort.java)
* Reverse an array in-place. [**Solution**](ReverseArray.java)
* Given a matrix, rotate it clockwise by 90 degrees. [**Solution**](RotateMatrix.java)
* Given two list of numbers representing 2 sets, implement the set operations for intersection and union. [**Solution**](SetOperations.java)
* Given two sorted arrays and a number k, find the kth largest number in the union of the two arrays. Do it in place. [**Solution**](SortedArrayUnion.java)
* Implement square-root method wihtout using the Math library. [**Solution**](Sqrt.java)
* One steps through integer points of the straight line. The length of a step must be nonnegative and can be by one bigger than, equal to, or by one smaller than the length of the previous step. What is the minimum number of steps in order to get from x to y? The length of the first and the last step must be 1. ([*Original question*](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=787)) [**Solution**](Steps.java)
* Given a string "aaabbbcc", compress it, = "a3b3c2. [**Solution**](StringCompression.java)
* Given a string, print out all its permutations. [**Solution**](StringPermutations.java)
* Given an array of integers, return all possible subsets of length n. [**Solution**](SubsetsN.java)
* Implement a deep-copy method for the class Thing which has a list of Thing objects. [**Solution**](Thing.java)
* Given a string, write a function which prints all the subsets of the string.  Now make the function to return only unique solutions. [**Solution**](UniqueStringSubsets.java)
