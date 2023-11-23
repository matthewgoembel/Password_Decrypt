/**
 * Goal: Optimize recursion using stacks, run in < O(26^n)?
 *
 * Approach 1: Use a modified alphabet that contains more frequently used characters at the start.
 * static final String letters = "abcdefghijklmnopqrstuvwxyz";  // In normal order
 * static final String letters = "eariotnslcudpbfghjkmqvwxyz";  // In order of frequency
 *
 * Approach 2: Modify stack method to be more efficient.
 * Because the program is a brute force way, it can only run so fast
 * Implementing linkedList could be more efficient in managing password and length

