package com.hlx.leetcode.day3;

/**
 * @author Huang Lexin
 * @date 2022年02月24日 11:41
 */
public class SwordsmanOfferII009 {
	static class Solution {
		public int numSubarrayProductLessThanK(int[] nums, int k) {
			int result = 0;
			int product = 1;
			int left = 0;
			int right = 0;
			while (right < nums.length) {
				// 如果乘积满足条件, ++res
				if (product * nums[right] < k) {
					product *= nums[right];
					result += (right - left + 1);
					++right;
				}
				else if (left == right) {
					// 乘积 >= k, 且边界重合, 说明 nums[right] >= k, 因此左右指针同时右移
					product = 1;
					++right;
					left = right;
				}
				else {
					// 乘积 >= k, 则简单的移动左边界, 降低 product 乘积
					product /= nums[left++];
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
