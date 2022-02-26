package com.hlx.leetcode.day4;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Lexin
 * @date 2022年02月25日 16:06
 */
public class SwordsmanOfferII0010 {
	/**
	 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
	 * 1 <= nums.length <= 2 * 104
	 * -1000 <= nums[i] <= 1000
	 * -107 <= k <= 107
	 */
	class Solution {
		public int subarraySum(int[] nums, int k) {
			int res = 0;
			int sum = 0;
			Map<Integer, Integer> sums = new HashMap<>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
				// 查找前缀和中是否存在一个 target, 使得 sum - target = k
				if (sum == k) {
					++res;
				}
				int target = sum - k;
				Integer count = sums.get(target);
				if (count != null) {
					res += count;
				}
				// 增加前缀和为 sum 的个数
				sums.merge(sum, 1, Integer::sum);
			}
			return res;
		}
	}
}
