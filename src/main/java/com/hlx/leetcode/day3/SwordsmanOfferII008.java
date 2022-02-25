package com.hlx.leetcode.day3;

/**
 * @author Huang Lexin
 * @date 2022年02月23日 14:31
 */
public class SwordsmanOfferII008 {
	class Solution {
		public int minSubArrayLen(int target, int[] nums) {

			int res = 100000;
			int sum = 0;
			int left = 0;
			int right = 0;
			while (right < nums.length) {
				sum += nums[right];
				if (sum >= target) {
					while (sum >= target) {
						res = Math.min(res, right - left + 1);
						sum -= nums[left++];
					}
				}
				++right;
			}
			return res == 100000 ? 0 : res;
		}
	}
}
