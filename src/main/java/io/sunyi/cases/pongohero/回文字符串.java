package io.sunyi.cases.pongohero;

/**
 * 题目详情 回文字符串是指从左到右和从右到左相同的字符串，现给定一个仅由小写字母组成的字符串，你可以把它的字母重新排列，以形成不同的回文字符串。
 * 
 * 输入：非空仅由小写字母组成的字符串，长度不超过100； 输出：能组成的所有回文串的个数（因为结果可能非常大，输出对1000000007取余数的结果）。
 * 
 * 例如：输入"aabb" 输出为2（因为“aabb”对应的所有回文字符串有2个：abba和baab）
 * 
 * 函数头部 c: int palindrome(const char *s); c++ int palindrome(const string &s);
 * java public static int palindrome(String s) ;
 * 
 */
public class 回文字符串 {

	public static int palindrome(String s) {
		s = s.toLowerCase();
		short[] arr = new short[26];
		for (char c : s.toCharArray()) {
			int i = (int) c - 97;
			arr[i] += 1;
		}

		boolean hasOnce = false;
		long l = 1;
		for (short sh : arr) {
			if (sh % 2 != 0) {
				if (!hasOnce) {
					hasOnce = true;
				} else {
					return 0;
				}
			}
			if (sh > 1) {
				l *= factorial(sh / 2);
			}
		}
		return (int) ((factorial(s.length() / 2) / l) % 1000000007);
	}

	public static int factorial(int i) {
		int r = 1;
		for (int j = 2; j <= i; j++) {
			r *= j;
		}
		return r;
	}

	public static void main(String[] args) {
		// System.out.println((int)'a');
		// System.out.println(factorial(4));
		System.out.println(palindrome("hqaymehhrsfuqrpahrimsxftuxqrpsejouuehaqtsryxjhearxmogmi"));

	}
}
