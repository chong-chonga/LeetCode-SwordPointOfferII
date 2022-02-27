package com.hlx.leetcode.day5;

import java.util.Arrays;

/**
 * @author Huang Lexin
 * @date 2022年02月27日 10:06
 */
public class SwordsmanOfferII0014 {

	/**
	 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
	 * <p>
	 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
	 * 1 <= s1.length, s2.length <= 104
	 * s1 和 s2 仅包含小写字母
	 */
	class Solution {
		public boolean checkInclusion(String s1, String s2) {
			if (s2.length() < s1.length()) {
				return false;
			}
			// s2的某个子串有 s1 相同类型的字符, 且每种字符个数相同
			int[] charsMap = new int[26];
			char[] chars1 = s1.toCharArray();
			Arrays.sort(chars1);
			int count = 1;
			int end = chars1.length - 1;

			// 计算 s1 每种字符的个数
			for (int i = 0; i < chars1.length; ++i) {
				if (i < end && chars1[i + 1] == chars1[i]) {
					++count;
					continue;
				}
				charsMap[chars1[i] - 'a'] = count;
				count = 1;
			}
			// 使用双指针表示 s2 子串的左右边界
			int left = 0;
			int right = 0;
			char[] chars2 = s2.toCharArray();
			while (right < s2.length()) {
				int index = chars2[right] - 'a';
				if (charsMap[index] == 0) {
					// 这种字符所需数量 = 0, 说明需要移动子串位置来减少子串中该字符的数量
					while (left < right) {
						++charsMap[chars2[left] - 'a'];
						++left;
						// 如果左边界截去的字符是当前的字符, 则保证了这种字符数量没有溢出
						if (chars2[left - 1] == chars2[right]) {
							break;
						}
					}
					// 移动左边界后, 该字符所需数量仍然 = 0, 说明该字符不应该出现在子串中
					if (charsMap[index] == 0) {
						++right;
						left = right;
						continue;
					}
				}
				// 该种字符所需数量--
				--charsMap[index];

				// s2子串长度 = s1长度, 判断是否为子串
				if (right - left == end) {
					if (isASubstring(charsMap)) {
						return true;
					} else {
						// 不是子串, 左边界向前移动
						++charsMap[chars2[left] - 'a'];
						++left;
					}
				}
				++right;
			}
			return false;
		}

		/**
		 * 判断方法, 如果 s2 包含 s1, 每种字符数都等于 0
		 */
		boolean isASubstring(int[] charsMap) {
			for (int j : charsMap) {
				if (j != 0) {
					return false;
				}
			}
			return true;
		}
	}
}
