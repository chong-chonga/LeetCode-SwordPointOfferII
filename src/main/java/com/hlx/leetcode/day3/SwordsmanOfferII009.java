package com.hlx.leetcode.day3;

/**
 * @author Huang Lexin
 * @date 2022年02月24日 11:41
 */
public class SwordsmanOfferII009 {
	static class Solution {
		public int numSubarrayProductLessThanK(int[] nums, int k) {
			int result = 0;
			int left = 0;
			int right = 0;
			int product = 1;

			while (right < nums.length) {
				if (product * nums[right] < k) {
					product *= nums[right];
					result += (right - left + 1);
					++right;
				} else if (left == right) {
					product = 1;
					++right;
					left = right;
				} else {
					product /= nums[left];
				}
			}
			return result;

		}
	}

	public static void main(String[] args) {
		int[] arr = new int[]{10, 5, 2, 6};
		Solution solution = new Solution();
		solution.numSubarrayProductLessThanK(arr, 100);
	}

}
