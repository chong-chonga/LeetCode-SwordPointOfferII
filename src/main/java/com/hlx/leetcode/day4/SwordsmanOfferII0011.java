package com.hlx.leetcode.day4;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Lexin
 * @date 2022年02月25日 20:39
 */
public class SwordsmanOfferII0011 {
	/**
	 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
	 */
	static class Solution {
		public int findMaxLength(int[] nums) {
			int result = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0) {
					nums[i] = -1;
				}
			}
			int sum = 0;
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
				if (0 == sum) {
					result = Math.max(result, i + 1);
				}
				// 确保 sum - target = 0;
				Integer index = map.get(sum);
				if (index != null) {
					result = Math.max(result, i - index);
				}
				// map 存在对应的 sum, 那么对应的 i 越小, 所得到的 result 越大(因此不应当更新为较大的 i )
				map.putIfAbsent(sum, i);
			}
			return result;
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] arr = new int[]{0,0,1,0,0,0,1,1};
		s.findMaxLength(arr);
	}
}
