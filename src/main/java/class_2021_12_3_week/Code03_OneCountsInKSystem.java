package class_2021_12_3_week;

// 测试链接 : https://www.nowcoder.com/test/33701596/summary
// 本题目为第3题
// 核心方法，在大厂刷题班19节，第3题

// K进制下 1～m 中数字 1 出现的次数，记作F(m,k)。例如F(5,3)=5，因为三进制1~5为{1,2,10,11,12}，数字1出现了5次。
// 牛牛现在给你k和n，他想知道F(m,k)>=n，最小的m是多少呢。请你返回的值。
//输入例子1:
//5,3
//输出例子1:
//5
//例子说明1:
//F(m,3) >= 5，最小的 m为 5。
//输入例子2:
//10,10
//输出例子2:
//17
//例子说明2:
//十进制下1~9只有一个1,10,11,12,13,14,15,16,17，一共 10个 1。所以最小的 m=17。

//算法原型：大厂刷题班 19节第三题
public class Code03_OneCountsInKSystem {


	public static long minM(int n, int k) {
		int len = bits(n, k);
		long l = 1;
		long r = power(k, len + 1);
		long ans = r;
		// 二分答案法（发现单调性，找到上限，进行二分）
		while (l <= r) {
			long m = l + ((r - l) >> 1);
			if (ones(m, k) >= n) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	public static int bits(long num, int k) {
		int len = 0;
		while (num != 0) {
			len++;
			num /= k;
		}
		return len;
	}

	//快速幂
	public static long power(long base, int power) {
		long ans = 1;
		while (power != 0) {
			if ((power & 1) != 0) {
				ans *= base;
			}
			base *= base;
			power >>= 1;
		}
		return ans;
	}

	public static long ones(long num, int k) {
		int len = bits(num, k);
		if (len <= 1) {
			return len;
		}
		long offset = power(k, len - 1);
		long first = num / offset;
		long curOne = first == 1 ? (num % offset) + 1 : offset;
		long restOne = first * (len - 1) * (offset / k);
		return curOne + restOne + ones(num % offset, k);
	}

}
