package com.hlx.leetcode.day11;

import java.util.*;

/**
 * @author Huang Lexin
 * @date 2022年03月07日 21:13
 */
public class SwordsmanOfferII0035 {

	/**
	 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
	 * 2 <= timePoints <= 2 * 104
	 * timePoints[i] 格式为 "HH:MM"
	 * 输入：timePoints = ["23:59","00:00"]
	 * 输出：1
	 */
	/**
	 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
	 * 2 <= timePoints <= 2 * 104
	 * timePoints[i] 格式为 "HH:MM"
	 * 输入：timePoints = ["23:59","00:00"]
	 * 输出：1
	 */
	class Solution {
		public int findMinDifference(List<String> timePoints) {
			// 计算每个时间的分钟表示, 并按从小到大排序
			int[] arr = new int[timePoints.size()];
			for (int i = 0; i < timePoints.size(); i++) {
				String[] split = timePoints.get(i).split(":");
				int hour = Integer.parseInt(split[0]);
				int minute = Integer.parseInt(split[1]);
				arr[i] = 60 * hour + minute;
			}
			Arrays.sort(arr);

			// 要获取最小的时间差, 要么取当天的时间差, 要么取隔夜的时间差
			int result = Integer.MAX_VALUE;
			int length = arr.length;
			for (int i = 1; i < length; i++) {
				result = Math.min(Math.min(arr[i] - arr[i-1], Math.abs(arr[i]- arr[i-1]-1440)), result);
			}
			result = Math.min(result, Math.abs(arr[length - 1] - arr[0]-1440));
			return result;
		}
	}

	public static void main(String[] args) {
		System.out.println(1);
	}
}
