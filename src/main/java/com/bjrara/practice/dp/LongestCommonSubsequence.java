package com.bjrara.practice.dp;

/**
 * Created by mengyizhou on 7/17/16.
 */
// p56
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        int n = 4, m = 4;
        String s = "abcd";
        String t = "becd";
        System.out.println(solve(s.toCharArray(), t.toCharArray(), n, m));
    }

    private static int solve(char[] s, char[] t, int n, int m) {
        int[][] matrix = new int[n + 1][m + 1];
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                matrix[i][j] = 0;
            }
        }
        for (int i = n; i > 0; i--) {
            for (int j = m; j > 0; j--) {
                if (s[i - 1] == t[j - 1]) {
                    matrix[i - 1][j - 1] = matrix[i][j] + 1;
                } else {
                    // try s proceed or t proceed
                    matrix[i - 1][j - 1] = matrix[i][j - 1] > matrix[i - 1][j] ? matrix[i][j - 1] : matrix[i - 1][j];
                }
            }
        }
        return matrix[0][0];
    }
}
