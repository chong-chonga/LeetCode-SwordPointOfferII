package com.hlx.leetcode.day1;

/**
 * @author Huang Lexin
 * @date 2022年02月21日 16:36
 */
public class TheNumberOf1SInTheFirstNDigitsInBinary {
	class Solution {
		public int[] countBits(int n) {
			if (n == 0) {
				return new int[]{0};
			}
			if (n == 1) {
				return new int[]{0, 1};
			}
			int[] result = new int[n + 1];
			result[0] = 0;
			result[1] = 1;
			int count = 2;
			for (int i = 2; i <= n; i++) {
				if (count == 0) {
					count = i;
				}
				result[i] = 1 + result[i - count];
				--count;
			}
			return result;
		}
	}
}
