package com.hlx.leetcode.day11;

import java.util.*;

/**
 * @author Huang Lexin
 * @date 2022年03月07日 15:12
 */
public class SwordsmanOfferII0033 {

	/**
	 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
	 *
	 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
	 * 1 <= strs.length <= 104
	 * 0 <= strs[i].length <= 100
	 * strs[i] 仅包含小写字母
	 *
	 */
	class Solution {
		public List<List<String>> groupAnagrams(String[] strs) {

			Map<String, List<String>> map = new HashMap<>();
			for (String str : strs) {
				char[] chars = str.toCharArray();
				Arrays.sort(chars);
				String temp = new String(chars);
				List<String> strings = map.get(temp);
				if (strings == null) {
					strings = new ArrayList<>();
				}
				strings.add(str);
				map.put(str, strings);
			}
			Set<Map.Entry<String, List<String>>> entries = map.entrySet();
			List<List<String>> result = new ArrayList<>();
			for (Map.Entry<String, List<String>> entry : entries) {
				result.add(entry.getValue());
			}
			return result;


		}
	}
}
