package com.bjrara.practice.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengyizhou on 7/10/16.
 */
// p51
public class ZeorOrOneKnapsack {
    public static void main(String[] args) {
        int[] w = new int[]{2, 1, 3, 2};
        int[] v = new int[]{3, 2, 4, 2};
        System.out.println(solve(4, w, v, 5));
    }

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
}
