package com.hlx.leetcode.day2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Lexin
 * @date 2022年02月22日 20:54
 */
public class MaximumProductOfWordLengths {

	class Solution {
		public int maxProduct(String[] words) {
			Map<Integer, Integer> map = new HashMap<>();
			for (String word : words) {
				int stringBitPattern = 0;
				for (char c : word.toCharArray()) {
					// 每种字符对应于一个位
					stringBitPattern |= (1 << (c - 'a'));
				}
				Integer lastLen = map.getOrDefault(stringBitPattern, 0);
				map.put(stringBitPattern, Math.max(lastLen, word.length()));
			}

			int ans = 0;
			for (Map.Entry<Integer, Integer> entry1 : map.entrySet()) {
				for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
					// 当两个字符串之间没有相同的字符的时候, 就说明这两个字符串的相同位撒花姑娘, 一个位为1, 另一个位为0
					if ((entry1.getKey() & entry2.getKey()) == 0) {
						ans = Math.max(ans, entry1.getValue()*entry2.getValue());
					}
				}
			}
			return ans;
		}
	}

}
