package com.hlx.leetcode.day10;

/**
 * @author Huang Lexin
 * @date 2022年03月07日 14:16
 */
public class SwordsmanOfferII0032 {

	/**
	 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
	 *
	 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）
	 *
	 * 1 <= s.length, t.length <= 5 * 104
	 * s and t 仅包含小写字母
	 */
	class Solution {
		public boolean isAnagram(String s, String t) {
			int len = s.length();
			if (len != t.length()) {
				return false;
			}
			char[] chars1 = s.toCharArray();
			char[] chars2 = t.toCharArray();

			// 优化1: 以下的循环可以利用多核并行计算结果
			// 优化2: 使用自己编写的 equal 方法优化 String 自带的方法
			// 优化3: 减少方法调用
			// 由于方法调用产生的转移指令和流水线分支预测带来的损失+影响编译器的因素+栈帧的释放带来的影响
			// 这里使用 C++ 类似的 inline 函数写法 (Java 没有, 那就一码到底)
			// 参照 CSAPP-第五章-优化程序性能
			if (stringEqual(chars1, chars2)) {
				return false;
			}
			int[] charsMap1 = new int[26];
			int[] charsMap2 = new int[26];

			for (char c : chars1) {
				++charsMap1[c - 'a'];
			}
			for (char c : chars2) {
				++charsMap2[c - 'a'];
			}
			for (int i = 0; i < 26; i++) {
				if (charsMap1[i] != charsMap2[i]) {
					return false;
				}
			}
			return true;

		}

		boolean stringEqual(char[] chars1, char[] chars2) {
			int len = chars1.length;
			for (int i = 0; i < len; i++) {
				if (chars1[i] != chars2[i]) {
					return false;
				}
			}
			return true;
		}
	}
}
