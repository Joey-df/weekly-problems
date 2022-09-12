package routine_problems;

import java.util.ArrayList;
import java.util.List;

// 微软考题
public class Problem_ComputeAllAnswer {

    // 给定正数数组arr
    // [l,r]范围上任意两个数之间，可以添加 + - * /
    // 括号表示计算的优先级，括号可以任意添加
    // 不能改变数组元素的顺序
    // 返回所有可能的结果，如果有重复的结果，也要保留
    public static List<Double> fun(int[] arr, int l, int r) {
        List<Double> ans = new ArrayList<>();
        if (l == r) { //范围上只有一个数
            ans.add((double)arr[l]);
        } else {
            // 枚举每一个分界线
            for (int m = l; m < r; m++) {
                List<Double> left = fun(arr, l, m);
                List<Double> right = fun(arr, m + 1, r);
                // 填 + - * /
                for (double a: left) {
                    for (double b: right) {
                        ans.add(a+b);
                        ans.add(a-b);
                        ans.add(a*b);
                        ans.add(a/b);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,1};
        List<Double> ans = fun(arr, 0, arr.length - 1);
        System.out.println(ans);
    }
}
