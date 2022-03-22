package routine_problems;

import java.util.HashMap;

/**
 * 链接：https://www.nowcoder.com/discuss/826182
 * 来源：牛客网
 * 寻找一个字符串中，每个字符之后比自己大的字符的个数，字符为大写字母、小写字母混合
 * 例如：
 * 输入：aosidg
 * 输出：[5, 1, 0, 0, 1, 0]（a之后的字串是osidg，每个字符的ascii值都比a大，所以是5）
 */
public class Problem_NumberOfBigerThanCurChar {

    //暴力方法
    //为了验证
    public static int[] fun1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i + 1; j < n; j++) {
                cur += str[i] < str[j] ? 1 : 0;
            }
            dp[i] = cur;
        }
        return dp;
    }

    //前提：len(s) > 0
    public static int[] fun2(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n];
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            //每到一个位置，先遍历一遍hashmap，构造当前位置的答案
            //然后把当前位置字符注册到hashmap中
            int cur = 0;
            for (char key : map.keySet()) {
                if (str[i] < key) cur += map.get(key);
            }
            dp[i] = cur;
            map.put(str[i], map.getOrDefault(str[i], 0) + 1);
        }
        return dp;
    }


    public static String generateString(int len) {
        char[] cand = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(cand[(int) (Math.random() * cand.length)]);
        }
        return sb.toString();
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1 == null & arr2 == null) return true;
        if (arr1 == null ^ arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int N = 50;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int cur = (int) (Math.random() * N);
            int len = cur == 0 ? 1 : cur;
            String s = generateString(len);
            int[] ans1 = fun1(s);
            int[] ans2 = fun2(s);
            /*System.out.println(s);
            print(ans1);
            print(ans2);*/
            if (!isEquals(ans1, ans2)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
