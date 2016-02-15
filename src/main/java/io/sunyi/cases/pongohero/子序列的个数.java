package io.sunyi.cases.pongohero;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目详情 本题同样来自caopengcs，只要你有兴趣，每个人都可以出题（出题入口在主页右侧边栏“贡献题目”->“我要发布”内），以下是题目详情：
 * 子序列的定义：对于一个序列a=a[1],a[2],......a[n]，则非空序列a
 * '=a[p1],a[p2]......a[pm]为a的一个子序列，其中1<=p1<p2<.....<pm<=n。
 * 例如：4,14,2,3和14,1,2,3都为4,13,14,1,2,3的子序列。
 * 对于给出序列a，有些子序列可能是相同的，这里只算做1个，要求输出a的不同子序列的数量。
 * 
 * 
 * 输入： 长度为n的数组1<=n<=100,数组元素0<=a[i]<=110 输出：子序列
 * 的个数对1000000007取余数的结果（由于答案比较大，输出Mod 1000000007的结果即可）。
 * 
 * 函数头部： C/C++: int run(cons int *a,int n); java public static int run(int []
 * a);
 * 
 */
public class 子序列的个数 {

	public static int run(int[] a) {

		V v = new V();

		boolean[] ea = buildExistArray(0, a);
		for (int i = 0; i < a.length; i++) {
			if (!ea[i]) {
				v.add();
				count(i, v, a);
			}
		}
		return (int) (v.getValue() % 1000000007);
	}

	public static void count(int i, V v, int[] a) {

		boolean[] ea = buildExistArray(i + 1, a);
		for (int j = i + 1; j < a.length; j++) {
			if (!ea[j - i - 1]) {
				v.add();
				count(j, v, a);
			}
		}
	}

	public static boolean[] buildExistArray(int i, int[] a) {
		Set<Integer> set = new HashSet<Integer>();
		// exist array
		boolean[] ea = new boolean[a.length - i];
		for (int j = i; j < a.length; j++) {
			ea[j - i] = !set.add(a[j]);
		}
		set.clear();
		return ea;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 4,1,1,4,3,3,4,2,2,3,4,541,11,1,1,2,3,3,2,1,123,123,1231,23,4,5,12,2, };
		System.out.println(run(a));
	}

	private static class V {
		private long i = 0;

		public void add() {
			i++;
		}

		public long getValue() {
			return i;
		};
	}
}
