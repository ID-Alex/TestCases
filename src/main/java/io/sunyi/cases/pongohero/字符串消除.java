package io.sunyi.cases.pongohero;

/*
 * 给定一个字符串，仅由a,b,c 3种小写字母组成。当出现连续两个不同的字母时，你可以用另外一个字母替换它，
 * 如 有ab或ba连续出现，你把它们替换为字母c； 有ac或ca连续出现时，你可以把它们替换为字母b； 
 * 有bc或cb 连续出现时，你可以把它们替换为字母a。 你可以不断反复按照这个规则进行替换，
 * 你的目标是使得最终结果所得到的字符串尽可能短，求最终结果的最短长度。 
 * 输入：字符串。长度不超过200，仅由abc三种小写字母组成。 
 * 输出： 按照上述规则不断消除替换，所得到的字符串最短的长度。 
 * 例如：输入cab，输出2。因为我们可以把它变为bb或者变为cc。输入bcab，输出1。
 * 尽管我们可以把它变为aab -> ac -> b，也可以把它变为bbb，但因为前者长度更短，所以输出1。
 */
public class 字符串消除 {

	public static int minLength(String s) {
		return forAll(s).length();
	}

	public static String forAll(String str) {

		if (str == null || str == "") {
			return "";
		} else if (str.length() == 1) {
			return str;
		} else if (str.length() == 2) {
			if (str.charAt(0) == str.charAt(1)) {
				return str;
			} else {
				return String.valueOf(converted(str.charAt(0), str.charAt(1)));
			}
		} else if (str.length() == 3) {
			str = String.valueOf(for3(str.charAt(0), str.charAt(1), str.charAt(2)));
			if (str.length() == 3) {
				return str;
			}
			return forAll(str);
		} else if (str.length() == 4) {
			str = String.valueOf(for4(str.charAt(0), str.charAt(1), str.charAt(2), str.charAt(3)));
			if (str.length() == 4) {
				return str;
			}
			return forAll(str);
		}

		String result = str;

		for (int i = 0, j = 4; j <= str.length(); i++, j++) {
			String sub = str.substring(i, j);
			String subr = String.valueOf(for4(sub.charAt(0), sub.charAt(1), sub.charAt(2), sub.charAt(3)));
			if (subr.length() == 4) {
				continue;
			} else {
				result = str.substring(0, i) + subr + str.substring(j);
				break;
			}
		}

		if (str.equals(result)) {
			return str;
		}

		return forAll(result);

	}

	static char[] for4(char c1, char c2, char c3, char c4) {

		if (c1 == c2 && c2 == c3 && c3 == c4) {
			// AAAA
			return new char[] { c1, c2, c3, c4 };
		} else if (c1 == c2 && c2 == c3) {
			// AAA?
			return new char[] { c1, c2, converted(c3, c4) };
		} else if (c2 == c3 && c3 == c4) {
			// ?AAA
			return new char[] { converted(c1, c2), c3, c4 };
		} else if (c1 == c2 || c3 == c4) {
			// AA?? or ??AA
			return new char[] { c1, converted(c2, c3), c4 };
		} else {
			// ?AA? or ????
			return new char[] { converted(c1, c2), c3, c4 };
		}

	}

	static char[] for3(char c1, char c2, char c3) {
		if (c1 == c2 && c2 == c3) {
			// AAA
			return new char[] { c1, c2, c3 };
		} else if (c1 == c2) {
			// AA?
			c2 = converted(c2, c3);
			return new char[] { c1, c2 };
		} else {
			// ?AA or ???
			c2 = converted(c1, c2);
			return new char[] { c2, c3 };
		}
	}

	static char converted(char c1, char c2) {
		String s = String.valueOf(c1) + String.valueOf(c2);

		if (s.equals("ab") || s.equals("ba")) {
			return 'c';
		}

		if (s.equals("ac") || s.equals("ca")) {
			return 'b';
		}

		if (s.equals("bc") || s.equals("cb")) {
			return 'a';
		}
		throw new IllegalArgumentException();
	}

	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		String s = "bbbb";
		System.out.println(s.length());
		System.out.println(forAll(s));
		System.out.println(System.currentTimeMillis() - l);
	}
}
