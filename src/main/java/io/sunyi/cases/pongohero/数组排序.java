package io.sunyi.cases.pongohero;

/**
 * 本题来自caopengcs，只要你有兴趣，每个人都可以出题（出题入口在主页右侧边栏“贡献题目”内），以下是题目详情：
 * 给定一个包含1-n的数列，我们通过交换任意两个元素给数列重新排序。求最少需要多少次交换，能把数组排成按1-n递增的顺序，其中，数组长度不超过100。
 * 例如： 原数组是3,2,1， 我们只需要交换1和3就行了，交换次数为1，所以输出1。
 * 原数组是2,3,1，我们需要交换2和1，变成1,3,2，再交换3和2，变为1，2，3，总共需要的交换次数为2，所以输出2。
 * 
 */
public class 数组排序 {

	public static int run(int[] a) {
		if (a == null || a.length < 2) {
			return 0;
		}

		
		int val = 0,index = 0,count =0;
		for (int i = 0; i < a.length; i++) {
			val = a[i];
			index = i;
			for (int j = i; j < a.length; j++) {
				if (a[j] < val) {
					val = a[j];
					index = j;
				}
			}
			
			if(val < a[i]){
				int cache = a[i];
				a[i] = a[index];
				a[index] = cache;
				count ++;
			}
		}
		return count;
	}

	// start 提示：自动阅卷起始唯一标识，请勿删除或增加。
	public static void main(String args[]) {
		int[] a = new int[]{9,8,7,6,5,4,3,2,1};
		System.out.println(run(a));
	}
	// end //提示：自动阅卷结束唯一标识，请勿删除或增加。
}
