package com.hlx.leetcode.day4;

/**
 * @author Huang Lexin
 * @date 2022年02月26日 16:56
 */
public class SwordsmanOfferII0012 {

	/**
	 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
	 *
	 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
	 *
	 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
	 *
	 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
	 * 1 <= nums.length <= 104
	 * -1000 <= nums[i] <= 1000
	 */
	class Solution {
		public int pivotIndex(int[] nums) {
			int len = nums.length;
			int[] prefixSums = new int[len];
			prefixSums[0] = nums[0];
			for (int i = 1; i < len; i++) {
				prefixSums[i] = prefixSums[i - 1] + nums[i];
			}
			int sum = prefixSums[len - 1];
			// 中心下标为数组左边界时
			if (0 == sum - prefixSums[0]) {
				return 0;
			}
			// 通常情况
			for (int i = 1; i < len - 1; i++) {
				if (sum - prefixSums[i] == prefixSums[i-1]) {
					return i;
				}
			}
			// 中心下标为数组右边界时
			if (0 == prefixSums[len - 2]) {
				return len - 1;
			}
			// 说明没有中心下标
			return -1;

		}
	}
}
