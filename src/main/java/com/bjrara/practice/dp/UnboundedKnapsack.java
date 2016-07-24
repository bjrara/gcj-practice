package com.bjrara.practice.dp;

/**
 * Created by mengyizhou on 7/19/16.
 */
public class UnboundedKnapsack {
    /**
     * dp[i][W] = max(dp[i+1][W-n*w[i]]+n*v[i])
     * where n=(0, W/w[i])
     * when n=0, dp[i][W] = dp[i+1][W]
     */
    public static void main(String[] args) {
        int c = 3;
        int[] w = new int[]{3, 4, 2};
        int[] v = new int[]{4, 5, 3};
        int W = 7;
        System.out.println(solve0(c, w, v, W));
        System.out.println(solve1(c, w, v, W));
        System.out.println(solve2(c, w, v, W));
    }

    // p57
    private static int solve0(int c, int[] w, int[] v, int W) {
        int[][] matrix = new int[c + 1][W + 1];
        for (int i = c - 1; i >= 0; i--) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    matrix[i][j] = matrix[i + 1][j];
                } else {
                    int r = 0;
                    for (int k = 0; k <= j / w[i]; k++) {
                        if (k == 0) {
                            r = r > matrix[i + 1][j] ? r : matrix[i + 1][j];
                        } else {
                            int tmp = matrix[i + 1][j - k * w[i]] + k * v[i];
                            r = r > tmp ? r : tmp;
                        }
                    }
                    matrix[i][j] = r;
                }
            }
        }
        return matrix[0][W];
    }

    private static int solve1(int c, int[] w, int[] v, int W) {
        int[][] matrix = new int[c + 1][W + 1];
        for (int i = c - 1; i >= 0; i--) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    matrix[i][j] = matrix[i + 1][j];
                } else {
                    for (int k = 0; k <= j / w[i]; k++) {
                        int a = matrix[i][j];
                        int b = matrix[i + 1][j - k * w[i]] + k * v[i];
                        matrix[i][j] = a > b ? a : b;
                    }

                }
            }
        }
        return matrix[0][W];
    }

    private static int solve2(int c, int[] w, int[] v, int W) {
        int[][] matrix = new int[c + 1][W + 1];
        for (int i = c - 1; i >= 0; i--) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    matrix[i][j] = matrix[i + 1][j];
                } else {
                    int a = matrix[i + 1][j];
                    int b = matrix[i][j - w[i]] + v[i];
                    matrix[i][j] = a > b ? a : b;
                }
            }
        }
        return matrix[0][W];
    }
}
