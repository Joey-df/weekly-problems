package class_2022_03_2_week;

import java.util.Arrays;

// 来自字节飞书团队
// 小歪每次会给你两个字符串：
// 笔记s1和关键词s2，请你写一个函数
// 判断s2的排列之一是否是s1的子串
// 如果是，返回true
// 否则，返回false

// leetcode567题原题
// 滑动窗口：欠账表+还款模型
public class Code02_StringCheck {

    //为了验证的暴力方法
    public static boolean check1(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] str2 = s2.toCharArray();
        Arrays.sort(str2);
        s2 = String.valueOf(str2);
        for (int L = 0; L < s1.length(); L++) {
            for (int R = L; R < s1.length(); R++) {
                char[] cur = s1.substring(L, R + 1).toCharArray();
                Arrays.sort(cur);
                String curSort = String.valueOf(cur);
                if (curSort.equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check2(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] str2 = s2.toCharArray();
        int[] count = new int[256];
        for (char c : str2) {
            count[c]++;
        }
        int M = str2.length;
        char[] str1 = s1.toCharArray();
        int inValidTimes = 0;
        int R = 0;
        for (; R < M; R++) {
            if (count[str1[R]]-- <= 0) {
                inValidTimes++;
            }
        }
        for (; R < str1.length; R++) {
            if (inValidTimes == 0) {
                return true;
            }
            if (count[str1[R]]-- <= 0) {
                inValidTimes++;
            }
            if (count[str1[R - M]]++ < 0) {
                inValidTimes--;
            }
        }
        return inValidTimes == 0;
    }

    public static boolean check3(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char c : str2) {
            count[c - 'a']++;
        }
        int M = str2.length;
        char[] str1 = s1.toCharArray();
        int all = M;
        int R = 0;
        for (; R < M; R++) {
            //减之前>0，all--
            if (count[str1[R] - 'a']-- > 0) {
                all--;
            }
        }
        for (; R < str1.length; R++) {
            if (all == 0) {
                return true;
            }
            if (count[str1[R] - 'a']-- > 0) { //右边进一个
                all--;
            }
            //加之前>=0，all才++
            if (count[str1[R - M] - 'a']++ >= 0) { //左边吐一个
                all++;
            }
        }
        return all == 0;
    }

    //随机生成长度为len的小写字母组成的字符串
    public static String generateStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) ((int) (Math.random() * 26) + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String str1 = generateStr(15);
            String str2 = generateStr(4);
            boolean ans1 = check1(str1, str2);
            boolean ans2 = check2(str1, str2);
            boolean ans3 = check3(str1, str2);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("出错了！");
                System.out.println(str1);
                System.out.println(str2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
