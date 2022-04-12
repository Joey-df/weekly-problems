package class_2021_12_5_week;

import java.util.ArrayList;

// 测试链接 : https://leetcode.com/problems/loud-and-rich/
// 拓扑排序
public class Code01_LoudAndRich {

	// richer[i] = {a, b} a比b更有钱  a -> b
	// quiet[i] = k, i这个人安静值是k
	public static int[] loudAndRich(int[][] richer, int[] quiet) {
		int N = quiet.length;
		// a -> b
		// a -> c
		// b -> c
		// a : b c
		// b : c
		// nexts[0] = {5,7,3}
		// 0 : 5 7 3  -> 0 往外指向 5 7 3
		// 5最没钱的，
		// -> nexts[5] = { }
		ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			// 0 : {}
			// 1 : {}
			// n-1 : {}
			nexts.add(new ArrayList<>());
		}
		// 入度
		// 0 : 0
		// 1 : 2
		int[] degree = new int[N];
		for (int[] r : richer) {
			// [a,b]  a -> b   a比b更有钱
			nexts.get(r[0]).add(r[1]); // 根据richer二维数组，构建向下的指向关系
			degree[r[1]]++; // 入度信息
		}
		// 所有入度为0的点，入队列
		int[] zeroQueue = new int[N]; // 使用数组模拟的队列！！！
		int l = 0;
		int r = 0; // l、r初始都为0，表示队列为空
		// r 表示下一个元素要入队列，该放的位置
		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) {
				zeroQueue[r++] = i; // 入度为0的节点入队列
			}
		}
		// ans[i] = j : 比i有钱的所有人里，j最安静
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			ans[i] = i; // 初始：比我有钱的人中，我自己最安静，然后在后续拓扑排序中，把每个位置的值更新对
		}
		while (l < r) { // 如果队列不空 （l==r时，队列就没数了）
			// 弹出一个入度为0的点
			int cur = zeroQueue[l++];
			// 1) 消除当前cur的影响！
			for (int next : nexts.get(cur)) { // cur的所有下级节点
				// cur : 比cur有钱，最安静的人是谁呢！是 ans[cur]
				if (quiet[ans[next]] > quiet[ans[cur]]) {
					ans[next] = ans[cur];
				}
				if (--degree[next] == 0) {
					zeroQueue[r++] = next;
				}
			}
		}
		return ans;
	}

}