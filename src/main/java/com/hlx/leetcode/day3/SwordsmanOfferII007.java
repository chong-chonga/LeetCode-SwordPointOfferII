package com.hlx.leetcode.day3;

import java.util.*;

/**
 * @author Huang Lexin
 * @date 2022年02月23日 14:11
 */
public class SwordsmanOfferII007 {
	class Solution {
		public List<List<Integer>> threeSum(int[] nums) {
			if (null == nums || 3 > nums.length) {
				return new ArrayList<>();
			}
			Arrays.sort(nums);
			int lastNum = Integer.MAX_VALUE;
			List<List<Integer>> result = new ArrayList<>();
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == lastNum) {
					continue;
				}
				lastNum = nums[i];
				int left = i + 1;
				int right = nums.length - 1;
				int target = -nums[i];
				int sum;
				while (left < right) {
					sum = nums[left] + nums[right];
					if (sum == target) {
						result.add(Arrays.asList(lastNum, nums[left], nums[right]));
						++left;
						while(left < right && nums[left] == nums[left-1]){
							++left;
						}
						--right;
						while (left < right && nums[right] == nums[right + 1]) {
							--right;
						}
					} else if (sum > target) {
						--right;
					} else {
						++left;
					}
				}
			}
			return result;
		}
	}
}
