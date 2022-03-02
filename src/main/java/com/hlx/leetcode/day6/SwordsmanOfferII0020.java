package com.hlx.leetcode.day6;

/**
 * @author Huang Lexin
 * @date 2022年03月01日 20:32
 */
public class SwordsmanOfferII0020 {

	/**
	 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
	 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
	 *
	 * 1 <= s.length <= 1000
	 * s 由小写英文字母组成
	 */
	static class Solution {
		public int countSubstrings(String s) {
			char[] chars = s.toCharArray();
			int len = s.length();
			int res = 0;
			int left = 0;
			int right = 0;
			int count = 0;
			// 既然是回文字符串, 那么肯定有一个中心点, 从这个点向左右两边的字符都相同
			// 回文串的长度可能是奇数, 也可能是偶数, 所以中心点可能是一个, 也可能是两个
			// 从左向右枚举中心点, 关键是中心点的迭代规律
			while (right < len) {
				while (left >= 0 && right < len && chars[left] == chars[right]) {
					++res;
					--left;
					++right;
				}
				++count;
				left = count / 2;
				right = (count + 1) / 2;
			}
			return res;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.countSubstrings("abc");
	}
}
