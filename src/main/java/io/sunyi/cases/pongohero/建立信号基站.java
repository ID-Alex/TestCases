package io.sunyi.cases.pongohero;

/**
 * 题目详情 要建立一个信号基站服务n个村庄，这n个村庄用平面上的n个点表示。假设基站建立的位置在(X,Y)，则它对某个村庄(x,y)的距离为max{|X –
 * x|, |Y – y|}， 其中| |表示绝对值，我们的目标是让所有村庄到信号基站的距离和最小。 基站可以建立在任何实数坐标位置上，也可以与某村庄重合。
 * 
 * 
 * 输入： 给定每个村庄的位置x[],y[]，x,y都是整数，满足： -1000000000 < x,y < 1000000000
 * 村庄个数大于1，小于101。 输出： 所有村庄到信号基站的距离和的最小值。 关于精度：
 * 因为输出是double。我们这样判断对错，如果标准答案是A,你的答案是a，如果|Ａ – a| < 1e-3 我们认为是正确的，否则认为是错误的。
 * 
 * 样例： 假设有4个村庄位置分别为 （1，4） （2，3） （0，1） （1，1） 我们的结果是5。因为我们可以选择（1.5，2.5）来建立信号基站。
 * bestDistance = max(|1.5-1|, |2.5-4|) + max(|1.5-2|,|2.5-3|) +
 * max(|1.5-0|,|2.5-1|) + max(|1.5-1|,|2.5-1|) = max(0.5, 1.5) + max(0.5,0.5) +
 * max(1.5,1.5) + max(0.5,1.5) = 1.5 + 0.5 + 1.5 + 1.5 = 5
 * 
 * 函数头部: C/C++ double bestDistance(int n, cons int *x, const int *y); Java class
 * Main() { public static double bestDistance(int [] x,int [] y); }
 * 
 * 
 */
public class 建立信号基站 {

	public static double bestDistance(int[] x, int[] y) {

		double xp = x[0];
		double yp = y[0];

		for (int i = 1; i < x.length; i++) {
			if (Math.abs(xp - x[i]) >= Math.abs(yp - y[i])) {

			}
		}

		xp = 2;
		yp = 3;

		System.out.println(xp + "\t" + yp);

		double result = 0d;

		for (int i = 0; i < x.length; i++) {
			double xpx = Math.abs(xp - x[i]);
			double ypy = Math.abs(yp - y[i]);
			System.out.println(xpx + "\t" + ypy);
			result += Math.max(xpx, ypy);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] x = new int[] { 1, 2, 0, 1 };
		int[] y = new int[] { 4, 3, 1, 1 };
		System.out.println(bestDistance(x, y));
	}

}
