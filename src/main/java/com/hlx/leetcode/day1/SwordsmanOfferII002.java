package com.hlx.leetcode.day1;

/**
 * @author Huang Lexin
 * @date 2022年02月21日 16:12
 */
public class SwordsmanOfferII002 {
	static class Solution {

		public String addBinary(String a, String b) {
			char[] achars = a.toCharArray();
			char[] bchars = b.toCharArray();
			StringBuilder res = new StringBuilder();
			int index1 = achars.length - 1;
			int index2 = bchars.length - 1;
			int  C = 0;
			char S;
			while(index1 >= 0 || index2 >= 0 || C == 1) {
				int i1 = index1 >= 0 ? achars[index1] - '0' : 0;
				int i2 = index2 >= 0 ? bchars[index2] - '0' : 0;
				int temp = i1 ^ i2;
				S = (temp ^ C) == 0 ? '0' : '1';
				res.append(S);
				C = (i1 & i2) | (temp & C);
				--index1;
				--index2;
			}
			return res.reverse().toString();

		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.addBinary("1", "1");
	}
}
