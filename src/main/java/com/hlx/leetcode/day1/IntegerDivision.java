package com.hlx.leetcode.day1;


/**
 * @author Huang Lexin
 * @date 2022年02月20日 21:08
 */
public class IntegerDivision {

	class Solution {

		public int divide(int a, int b) {
			// 本题中，如果除法结果溢出，则返回 2^31 − 1 (Integer.MIN_VALUE / 1 未溢出, 所以不考虑, 其他情况不会溢出)
			// 常规结果仍然是最小值, 因为最小值的非就是本身
			// 0x80000000 + 0x80000000 = 0x00000000(32位截断后的值) --- 参照 CSAPP(第三版)-P66
			if (a == Integer.MIN_VALUE && b == -1) {
				return Integer.MAX_VALUE;
			}
			// 计算结果的正负
			boolean isResNegative = (a > 0) ^ (b > 0);
			// 这里如果对 Integer.MIN_VALUE 取绝对值仍然会得到本身, 参照如上
			// 取绝对值后,  0(b!=0) <= a、b <= Integer.MAX_VALUE 或 a、b == Integer.MIN_VALUE
			a = Math.abs(a);
			b = Math.abs(b);
			int result = 0;
			//这里把 a,b 当成无符号数处理, 因此强制逻辑右移( Java只支持有符号数,但是我们可以把底层位模式看成无符号数)
			for (int i = 31; i >= 0; --i) {
				// 考虑边界值:
				// a = Integer.MAX_VALUE, b = 1                  res = Integer.MAX_VALUE
				// a = Integer.MIN_VALUE, b = 1                  res = Integer.MIN_VALUE
				// a = Integer.MIN_VALUE, b = Integer.MIN_VALUE  res = 1
				if ((a >>> i) - b >= 0) {
					a -= (b << i);
					result += (1 << i);
				}

			}
			return isResNegative ? -result : result;
		}
	}


	public static void main(String[] args) {
		//Solution solution = new Solution();
		//solution.divide(-2147483648, 1);
	}
}
