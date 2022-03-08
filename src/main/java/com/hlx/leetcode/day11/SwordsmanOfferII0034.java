package com.hlx.leetcode.day11;

/**
 * @author Huang Lexin
 * @date 2022年03月07日 20:19
 */
public class SwordsmanOfferII0034 {

	/**
	 *
	 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
	 *
	 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，
	 * 只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
	 *
	 * 1 <= words.length <= 100
	 * 1 <= words[i].length <= 20
	 * order.length == 26
	 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
	 *
	 */
	class Solution {

		public boolean isAlienSorted(String[] words, String order) {
			if (words.length > 1) {
				int[] charOrder = new int[26];
				char[] chars = order.toCharArray();
				int len = chars.length;
				// 使用哈希表统计字符的对应的顺序
				for (int i = 0; i < len; i++) {
					charOrder[chars[i]-'a'] = i;
				}
				len = words.length;
				char[] s1 = words[0].toCharArray();
				char[] s2;
				for (int i = 1; i < len; i++) {
					s2 = words[i].toCharArray();
					int index1;
					int index2;
					boolean isOrder = false;
					int j;
					for (j = 0; j < s1.length && j < s2.length; j++) {
						index1 = s1[j]-'a';
						index2 = s2[j]-'a';
						if (charOrder[index1] > charOrder[index2]) {
							return false;
						} else if (charOrder[index1] < charOrder[index2]) {
							isOrder = true;
							break;
						}
					}

					// 如果两个字符串前缀相同, 但前一个串仍有字符没比较, 则返回 false
					if (!isOrder && j < s1.length) {
						return false;
					}
					s1 =s2;
				}
			}

			return true;
		}
	}
}
