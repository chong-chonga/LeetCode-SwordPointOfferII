package com.hlx.leetcode.day5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huang Lexin
 * @date 2022年02月27日 11:19
 */
public class SwordsmanOfferII0015 {
	/**
	 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
	 * 变位词 指字母相同，但排列不同的字符串。
	 *
	 * 1 <= s.length, p.length <= 3 * 104
	 * s 和 p 仅包含小写字母
	 */
	class Solution {
		public List<Integer> findAnagrams(String s, String p) {
			List<Integer> res = new ArrayList<>();
			int[] pcharsMap = new int[26];
			char[] chars = p.toCharArray();
			for (char c : chars) {
				++pcharsMap[c - 'a'];
			}
			char[] schars = s.toCharArray();
			int left = 0;
			int right = 0;
			final int boundaryDifference = p.length() - 1;
			while (right < s.length()) {
				int index = schars[right] - 'a';
				if (pcharsMap[index] == 0) {
					while (left < right) {
						++pcharsMap[schars[left] - 'a'];
						++left;
						if (schars[left - 1] == schars[right]) {
							break;
						}
					}
					if (pcharsMap[index] == 0) {
						++right;
						left = right;
						continue;
					}
				}
				--pcharsMap[index];
				if (boundaryDifference == (right - left)) {
					if (isSubString(pcharsMap)) {
						res.add(left);
					}
					++pcharsMap[schars[left] - 'a'];
					++left;
				}
				++right;
			}

			return res;
		}

		private boolean isSubString(int[] charsMap) {
			for (int i : charsMap) {
				if (i != 0) {
					return false;
				}
			}
			return true;
		}
	}
}
