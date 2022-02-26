package com.hlx.leetcode.day4;

/**
 * @author Huang Lexin
 * @date 2022年02月26日 17:26
 */
public class SwordsmanOfferII0013 {
	class NumMatrix {

		private final int[][] prefixSums;
		/**
		 * 给定一个二维矩阵 matrix，以下类型的多个请求：
		 *
		 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
		 * 实现 NumMatrix 类：
		 *
		 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
		 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。
		 * m == matrix.length
		 * n == matrix[i].length
		 * 1 <= m, n <= 200
		 * -105 <= matrix[i][j] <= 105
		 * 0 <= row1 <= row2 < m
		 * 0 <= col1 <= col2 < n
		 */
		public NumMatrix(int[][] matrix) {
			prefixSums = new int[matrix.length][matrix[0].length];
			for (int row = 0; row < matrix.length; row++) {
				prefixSums[row][0] = matrix[row][0];
			}
			for (int row = 0; row < matrix.length; row++) {
				for (int col = 1; col < matrix[0].length; col++) {
					prefixSums[row][col] = prefixSums[row][col - 1] + matrix[row][col];
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			int sum = 0;
			for (int r = row1; r <= row2; r++) {
				if (col1 == 0) {
					sum += prefixSums[r][col2];
				} else {
					sum += (prefixSums[r][col2] - prefixSums[r][col1 - 1]);
				}
			}
			return sum;
		}
	}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
}
