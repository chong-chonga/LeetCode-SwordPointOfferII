package com.hlx.leetcode.day6;

/**
 * @author Huang Lexin
 * @date 2022年02月28日 8:08
 */
public class SwordsmanOfferII0019 {

	/**
	 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
	 * 1 <= s.length <= 105
	 * s 由小写英文字母组成
	 */
	class Solution {
		public boolean validPalindrome(String s) {
			char[] chars = s.toCharArray();
			int left = 0;
			int right = chars.length - 1;
			return isPalindrome(chars, left, right, true);
		}

		boolean isPalindrome(char[] chars, int left, int right, boolean canDelete) {
			while (left < right) {
				if (chars[left] == chars[right]) {
					++left;
					--right;
					continue;
				}
				if (canDelete) {
					return isPalindrome(chars, left, right - 1, false) || isPalindrome(chars, left + 1, right, false);
				} else {
					return false;
				}
			}
			return true;
		}
	}
}
