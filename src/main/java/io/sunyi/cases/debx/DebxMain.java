package io.sunyi.cases.debx;

/**
 * @author sunyi
 */
public class DebxMain {


	public static void main(String args[]) {
		//[贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1]


		double orgAmount = 10000;
		// 月利率
		double monthRate = 0.093 / 12;

		double totalPhase = 36;
		/**
		 * A. 每月等额本息(本金+收益) = [本金×月利率×(1+月利率)^还款月数]÷((1+月利率)^还款月数-1) <br>
		 */
		double amountAndEarningDouble = (orgAmount * monthRate * Math.pow(1 + monthRate, totalPhase))
				/ (Math.pow(1 + monthRate, totalPhase) - 1);


		System.out.println(amountAndEarningDouble);
		System.out.println(amountAndEarningDouble * totalPhase);
	}
}
