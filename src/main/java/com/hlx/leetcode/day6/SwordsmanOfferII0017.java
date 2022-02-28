package com.hlx.leetcode.day6;

/**
 * @author Huang Lexin
 * @date 2022年02月28日 8:08
 */
public class SwordsmanOfferII0017 {

	/**
	 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
	 *
	 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
	 *
	 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
	 * 1 <= s.length, t.length <= 105
	 * s 和 t 由英文字母组成
	 */
	class Solution {
		boolean hasAns = false;
		int resLeft = 0;
		int resRight = 100000;
		int len = 100000;
		public String minWindow(String s, String t) {
			char[] tchars = t.toCharArray();
			int left = 0;
			int right = 0;
			int targetDiff = t.length() - 1;

			int[] charsMap = new int[58];
			for (char tchar : tchars) {
				++charsMap[tchar - 'A'];
			}
			char[] schars = s.toCharArray();
			int nextIndex = 26;
			while (right < schars.length) {
				--charsMap[schars[right]-'A'];
				if (right - left >= targetDiff && charsMap[nextIndex] <= 0) {
					nextIndex = getIndexOfInvalidChar(charsMap);
					// 说明当前子串满足条件
					if (nextIndex == 26) {
						hasAns = true;
						int i = schars[left] - 'A';
						while (charsMap[i] < 0 && left < right) {
							++charsMap[i];
							++left;
							i = schars[left] - 'A';
						}
						if (right - left < len) {
							len = right - left;
							resLeft = left;
							resRight = right;
						}
					}
				}
				++right;
			}

			return !hasAns ? "" : s.substring(resLeft, resRight + 1);
		}

		/**
		 * @return 26 当所有值都不大于 0 时; 否则返回对应的下标
		 */
		public int getIndexOfInvalidChar(int[] charsMap) {
			for (int i = 0; i < charsMap.length; i++) {
				if (charsMap[i] > 0) {
					return i;
				}
			}
			// 'A' + 26 = '['
			// '[' 字符数必定等于 0
			return 26;
		}

	}

	public static void main(String[] args) {
		int c = 'z' - 'A';
		System.out.println(c);
	}
}
