package com.hlx.leetcode.day5;

/**
 * @author Huang Lexin
 * @date 2022年02月27日 11:35
 */
public class SwordsmanOfferII0016 {

	/**
	 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
	 *
	 * 0 <= s.length <= 5 * 104
	 * s 由英文字母、数字、符号和空格组成
	 */
	class Solution {
		public int lengthOfLongestSubstring(String s) {
			if (s.isEmpty()) {
				return 0;
			}
			int result = 0;
			int[] charMap = new int[1 << 7];
			char[] chars = s.toCharArray();
			int left = 0;
			int right = 0;
			while (right < chars.length) {
				if (charMap[chars[right]] != 0) {
					// 当前 left, right 为边界的子串出现了重复字符, 则更新连续子字符串的长度
					result = Math.max(result, right - left);
					while (left < right) {
						--charMap[chars[left]];
						++left;
						if (chars[left - 1] == chars[right]) {
							break;
						}
					}
					// left <= right, 当 left == right 时, 必定不会有重复字符
				}
				++charMap[chars[right]];
				++right;
			}
			result = Math.max(result, right - left);

			return result;
		}
	}
}
