package com.hlx.leetcode.day2;

/**
 * @author Huang Lexin
 * @date 2022年02月23日 14:04
 */
public class SwordsmanOfferII006 {
	class Solution {
		public int[] twoSum(int[] numbers, int target) {
			int index1 = 0;
			int index2 = numbers.length - 1;
			int sum;

			while (true) {
				if ((sum = (numbers[index1] + numbers[index2])) > target) {
					--index2;
				} else if (sum < target) {
					++index1;
				} else {
					break;
				}
			}
			return new int[]{index1, index2};
		}
	}
}
