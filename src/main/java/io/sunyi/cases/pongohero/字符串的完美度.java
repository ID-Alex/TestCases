package io.sunyi.cases.pongohero;

import java.util.Arrays;

/**
 * 题目详情 我们要给每个字母配一个1-26之间的整数，具体怎么分配由你决定，但不同字母的完美度不同，
 * 而一个字符串的完美度等于它里面所有字母的完美度之和，且不在乎字母大小写，也就是说字母F和f的完美度是一样的。
 * 
 * 现在给定一个字符串，输出它的最大可能的完美度。 例如：dad，你可以将26分配给d，25分配给a，这样整个字符串最大可能的完美度为77。
 * 
 * 函数头部 C int perfect(const char *s); C++ int perfect(const string &s); java
 * public static int perfect(String s);
 * 
 */
public class 字符串的完美度 {

	public static int perfect(String s) {
		s = s.toUpperCase();

		short[] arr = new short[26];

		for (char c : s.toCharArray()) {
			int i = (int) c - 65;
			arr[i] += 1;
		}

		Arrays.sort(arr);

		int result = 0;
		for (int i = arr.length - 1; i >= 0 && arr[i] > 0; i--) {
			result += arr[i] * (i + 1);
		}
		return result;
	}

	public static void main(String[] args) {
	}
}
