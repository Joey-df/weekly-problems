package routine_problems;

import java.util.*;

//给一个数组 一组下标 i, j, k 如果它们的值互不相等，则为合格的一个三元组。这个的三元组总共多少个
public class Problem_TupleThreeNums {

    // 1 2 3 4 5 6
    public static int fun1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int n : arr) set.add(n);
        if (set.size() < 3) return 0;
        return (int) c(set.size(), 3);
    }

    public static long c(int a, int b) {
        if (a == b) {
            return 1;
        }
        long x = 1; // 分子
        long y = 1; // 分母
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    // 调用的时候，请保证初次调用时，a和b都不为0
    // a,b 要是正数，不能有任何一个等于0
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 3, 4, 5};
        System.out.println(fun1(arr));
    }
}
