package class_2021_12_3_week;

//测试链接 : https://www.nowcoder.com/test/33701596/summary
//本题目为第2题
//有一个二进制字符串num，可以选择该串中的任意一段区间进行取反(可以进行一次或不进行)，取反指将0变为1，将1变为0。
//那么取反之后的可能的最大的字典序是多少呢。如有num=1000，将区间[2,4]取反变为1111是字典序最大的。
//输入例子1:
//"1000"
//输出例子1:
//"1111"
//例子说明1:
//如题意描述。
//输入例子2:
//"1001"
//输出例子2:
//"1111"
//例子说明2:
//对区间[2,3]取反能使得字典序最大。
public class Code02_BinaryNegate {

	public static String maxLexicographical(String num) {
		char[] arr = num.toCharArray();
		int i = 0;
		//找到第一个0的位置
		while (i < arr.length) {
			if (arr[i] == '0') {
				break;
			}
			i++;
		}
		//找到第一个1的位置（连续0的位置全变1）
		while(i < arr.length) {
			if(arr[i] == '1') {
				break;
			}
			arr[i++] = '1';
		}
		return String.valueOf(arr);
	}

}
