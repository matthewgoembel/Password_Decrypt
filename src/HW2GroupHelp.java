/**
 * Goal: Optimize recursion using stacks, run in < O(26^n)?
 *
 * Approach 1: Use a modified alphabet that contains more frequently used characters at the start.
 * static final String letters = "abcdefghijklmnopqrstuvwxyz";  // In normal order
 * static final String letters = "eariotnslcudpbfghjkmqvwxyz";  // In order of frequency

