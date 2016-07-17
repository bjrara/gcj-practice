package com.bjrara.practice.greedy;

/**
 * Created by mengyizhou on 7/2/16.
 */
// p47
// POJ 3253
public class FenceRepair {
    public static void main(String[] args) {
        int N = 15;
        int[] L = new int[]{3, 4, 5, 1, 2};
        System.out.println(solve(L));
    }

    private static int solve(int[] L) {
        if (L.length == 0) return 0;
        if (L.length == 1) return L[0];

        quickSort(L, 0, L.length - 1);

        int sum = 0;
        int tmp = L[0] + L[1];
        sum += tmp;

        int i = 2;
        while (i + 1 < L.length) {
            if (L[i] < tmp && L[i + 1] < tmp) {
                sum += (L[i] + L[i + 1]);
                i += 2;
            } else {
                tmp += L[i];
                sum += tmp;
                i += 1;
            }
        }
        return sum;
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int mid = low + (high - low) / 2;
        int pivot = arr[mid];

        int i = low;
        int j = high;
        while (i <= j) {
            while (i <= high && arr[i] < pivot) i++;
            while (j >= low && arr[j] > pivot) j--;
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        if (low < j) quickSort(arr, low, j);
        if (high > i) quickSort(arr, i, high);
    }

}
