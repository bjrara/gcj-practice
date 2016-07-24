package com.bjrara.practice.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengyizhou on 7/10/16.
 */
public class ZeorOrOneKnapsack {
    public static void main(String[] args) {
        int[] w = new int[]{2, 1, 3, 2};
        int[] v = new int[]{3, 2, 4, 2};
        System.out.println(solve(4, w, v, 5));
        System.out.println(solve1(4, w, v, 5));
    }

    // p51
    private static int solve(int n, int[] w, int[] v, int W) {
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        int total_v = solve(n, w, v, W, 0, cache);
        for (int i = 1; i < n; i++) {
            total_v = max(total_v, solve(n, w, v, W, i, cache));
        }
        return total_v;
    }

    private static int solve(int n, int[] w, int[] v, int W, int i, Map<Integer, Integer> cache) {
        if (i >= n) return 0;
        if (w[i] > W) return solve(n, w, v, W, i + 1, cache); // f(i, W) = f(i + 1, W)

        Integer total_v = cache.get(i);
        if (total_v != null) return total_v;

        total_v = 0;
        int a, b;
        // to choose or not to choose
        a = solve(n, w, v, W - w[i], i + 1, cache) + v[i]; // f(i, W) = f(i + 1, W - w[i]) + v[i]
        b = solve(n, w, v, W, i + 1, cache); // f(i, W) = f(i + 1, W)
        total_v += max(a, b); // total_v = f(i, W) = max(f(i + 1, W - w[i]) + v[i], f(i + 1, W))

        cache.put(i, total_v);
        return total_v;
    }

    private static int max(int a, int b) {
        return a >= b ? a : b;
    }

    // p60
    private static int solve1(int n, int[] w, int[] v, int W) {
        Arrays.sort(v);
        Arrays.sort(w);
        int V = v[n - 1] * (W / w[0]);
        int[][] matrix = new int[n + 1][V + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= V; j++) {
                if (j < v[i]) {
                    matrix[i][j] = matrix[i + 1][j];
                } else {
                    int a = matrix[i + 1][j];
                    int b = matrix[i + 1][j - v[i]] + w[i];
                    matrix[i][j] = a > b ? a : b;
                }
            }
        }
        int r = 0;
        for (int i = 0; i < V + 1; i++) {
            if (matrix[0][i] <= W) r = i;
        }
        return r;
    }
}
