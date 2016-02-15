package io.sunyi.cases.pongohero;

/*
 * 用n个不同的字符（编号1 - n），组成一个字符串，有如下2点要求：     
 * 1、对于编号为i 的字符，如果2 * i > n，则该字符可以作为最后一个字符，但如果该字符不是作为最后一个字符的话，则该字符后面可以接任意字符；     
 * 2、对于编号为i的字符，如果2 * i <= n，则该字符不可以作为最后一个字符，且该字符后面所紧接着的下一个字符的编号一定要 >= 2 * i。 
 * 问有多少长度为M且符合条件的字符串。 
 * 例如：N = 2，M = 3。则ABB,BAB,BBB是符合条件的字符串，剩下的均为不符合条件的字符串。 
 * 输入：n,m  (2<=n,m<=1000000000)； 
 * 输出：满足条件的字符串的个数，由于数据很大，输出该数Mod 10^9 + 7的结果。 
 * 函数头部 int validstring(int n,int m) { } 
 * 
 */
public class 合法字符串 {

	static int validstring(int n, int m) {

		int c = 0;

		int[] item = new int[m];
		for (int i = 0; i < m; i++) {
			item[i] = 1;
		}

		long allItemCount = (long) Math.pow(n, m);

		if (isOk(item, n)) {
			out(item);
			c++;
		}

		for (long l = 0; l < allItemCount - 1; l++) {
			increasing(item, n);
			if (isOk(item, n)) {
				out(item);
				c++;
			}
		}
		return c;
	}

	static boolean isOk(int[] a, int n) {
		for (int i = a.length - 1; i >= 0; i--) {
			if (i == a.length - 1) {
				if (2 * a[i] <= n) {
					return false;
				}
			} else if (i >= 0) {
				if (2 * a[i] <= n && a[i + 1] < 2 * a[i]) {
					return false;
				}
			}
		}
		return true;
	}

	static void increasing(int[] a, int radix) {
		int i = a.length - 1;
		while (i >= 0) {
			if (a[i] + 1 > radix) {
				a[i] = 1;
				i--;
			} else {
				a[i] = a[i] + 1;
				return;
			}
		}
		throw new RuntimeException("");
	}

	static void out(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	static long n2(int n) {
		if (n % 2 == 0) {
			if (n == 0)
				return 0;
			else {
				long result = 0;
				for (int i = n; i > 0; i -= 2) {
					result += 2 * i - 2;
				}
				return result;
			}
		} else {
			if (n == 1)
				return 1;
			else {
				long result = 0;
				for (int i = n; i > 0; i -= 2) {
					result += ((i / 2) * 4 + 1);
				}
				return result;
			}
		}
	}

	public static void main(String[] args) {
		int n = 6;
		int m = 8;
		System.out.println(validstring(n, m));

		// System.out.println(a(3));

	}

	static int a(int n) {
		if (n <= 0)
			return 0;
		return 2 * a(n - 1) + 3 * a(n - 2) + a(n - 3);
	}
}