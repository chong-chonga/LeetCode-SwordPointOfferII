package com.hlx.leetcode.day6;

/**
 * @author Huang Lexin
 * @date 2022年02月28日 8:08
 */
public class SwordsmanOfferII0018 {

	/**
	 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
	 *
	 * 本题中，将空字符串定义为有效的 回文串 。
	 * 1 <= s.length <= 2 * 105
	 * 字符串 s 由 ASCII 字符组成
	 */
	class Solution {
		public boolean isPalindrome(String s) {
			char[] chars = s.toCharArray();
			int left = 0;
			int right = chars.length - 1;
			while (left < right) {
				while (left < right && notTargetChar(chars[left])) {
					++left;
				}
				while (left < right && notTargetChar(chars[right])) {
					--right;
				}
				if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
					return false;
				}
				++left;
				--right;
			}
			return true;
		}

		private boolean notTargetChar(char c) {
			return !((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
		}
	}
}
