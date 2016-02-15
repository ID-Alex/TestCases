package io.sunyi.cases.pongohero;

public class 字符串消除_cjb {
	public static int minLength(String s) {
//		  System.out.println(s);
		  int length = s.length();
		  int minLength = 1000;
		  for(int i = 0; i < length -1; i++){
			  char s1 = s.charAt(i);
			  char s2 = s.charAt(i+1);
			  int l = 0;
//			  System.out.println(s1 + "" + s2);
			  if(s1 != s2){
				  char r;
				  if((s1 == 'a' && s2 == 'b') || (s1 == 'b' && s2 == 'a')){
					  r = 'c';
				  } else if((s1 == 'a' && s2 == 'c') || (s1 == 'c' && s2 == 'a')){
					  r = 'b';
				  } else {
					  r = 'a';
				  }
//				  System.out.println(s.substring(0, i) + r + s.substring(i + 2));
				  l = minLength(s.substring(0, i) + r + s.substring(i + 2));
				  if(l == 1){
					  return 1;
				  }
				  if(l < minLength){
					  minLength = l;
				  }
			  }
		  }
		  if(s.indexOf("a") == -1 && s.indexOf("b") == -1){
			  minLength = s.length();
		  }
		  if(s.indexOf("a") == -1 && s.indexOf("c") == -1){
			  minLength = s.length();
		  }
		  if(s.indexOf("c") == -1 && s.indexOf("b") == -1){
			  minLength = s.length();
		  }
		  return minLength;
	  }
	
	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		String s = "bbbbcacbbabccabbbcabccaccbccaccbaaaabcbabaaacbbbbccabaccbcbccabacaaaccaaabbaaabcabacbabbbabababaabbabcbcbbcabbcccccbbbacbbaacbbabcaacaabbacabbabcccabaaccbbcacacbaccbcbaaabbbbcbcccaacbabccccccbbbacbbbb";
		System.out.println(s.length());
		System.out.println(minLength(s));
		System.out.println(System.currentTimeMillis() - l);
	}

}
