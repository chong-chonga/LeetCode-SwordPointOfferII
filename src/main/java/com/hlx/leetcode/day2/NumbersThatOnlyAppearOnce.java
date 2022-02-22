package com.hlx.leetcode.day2;

import java.util.Arrays;

/**
 * @author Huang Lexin
 * @date 2022年02月22日 10:45
 */
public class NumbersThatOnlyAppearOnce {
	class Solution {
		public int singleNumber(int[] nums) {
			Arrays.sort(nums);
			for (int i = 0; i < nums.length; i+=3) {
				if (i + 2 < nums.length && nums[i] == nums[i + 2]) {
					continue;
				}
				return nums[i];
			}
			return 0;
		}
	}

}
