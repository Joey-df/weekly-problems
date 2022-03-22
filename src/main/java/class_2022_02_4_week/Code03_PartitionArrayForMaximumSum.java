package class_2022_02_4_week;

import java.util.Arrays;

/**
 * 1043. 分隔数组以得到最大和
 * 给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。
 * 分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 	* 返回将数组分隔变换后能够得到的元素最大和。
 * 注意，原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。
 *
 * 示例 1：
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：
 * 因为 k=3 可以分隔成 [1,15,7] [9] [2,5,10]，结果为 [15,15,15,9,10,10,10]，和为 84，是该数组所有分隔变换后元素总和最大的。
 * 若是分隔成 [1] [15,7,9] [2,5,10]，结果就是 [1, 15, 15, 15, 10, 10, 10] 但这种分隔方式的元素总和（76）小于上一种。
 * 示例 2：
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * 输入：arr = [1], k = 1
 * 输出：1
 *
 * 提示：
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 */
// 测试链接 : https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
public class Code03_PartitionArrayForMaximumSum {

	public static int maxSumAfterPartitioning1(int[] arr, int k) {
		int[] dp = new int[arr.length];
		Arrays.fill(dp, -1);
		return process1(arr, k, arr.length - 1, dp);
	}

	// 永远不变的固定参数 : arr, k
	// 可变参数 : index
	// 缓存 : dp
	// process含义 : arr[0...index]最优划分搞出的最大和是多少，返回
	public static int process1(int[] arr, int k, int index, int[] dp) {
		if (index == -1) {
			return 0;
		}
		if (dp[index] != -1) {
			return dp[index];
		}
		int max = Integer.MIN_VALUE;
		int ans = Integer.MIN_VALUE;
		for (int i = index, j = 1; i >= 0 && j <= k; i--, j++) {
			max = Math.max(max, arr[i]);
			ans = Math.max(ans, process1(arr, k, i - 1, dp) + (index - i + 1) * max);
		}
		dp[index] = ans;
		return ans;
	}

	public static int maxSumAfterPartitioning2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[] dp = new int[n];
		dp[0] = arr[0];
		for (int i = 1; i < n; i++) {
			dp[i] = arr[i] + dp[i - 1];
			int max = arr[i];
			for (int j = i - 1; j >= 0 && (i - j + 1) <= k; j--) {
				max = Math.max(max, arr[j]);
				dp[i] = Math.max(dp[i], max * (i - j + 1) + (j - 1 >= 0 ? dp[j - 1] : 0));
			}
		}
		return dp[n - 1];
	}

}