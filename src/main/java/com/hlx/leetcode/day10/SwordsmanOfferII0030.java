package com.hlx.leetcode.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Huang Lexin
 * @date 2022年03月04日 12:45
 */
public class SwordsmanOfferII0030 {
	/**
	 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
	 * <p>
	 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
	 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
	 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
	 * <p>
	 * -2^31 <= val <= 2^31 - 1
	 * 最多进行 2 * 10^5 次 insert ， remove 和 getRandom 方法调用
	 * 当调用 getRandom 方法时，集合中至少有一个元素
	 */
	class RandomizedSet {

		HashMap<Integer, Integer> map;
		List<Integer> values;
		Random random;

		/**
		 * Initialize your data structure here.
		 */
		public RandomizedSet() {
			map = new HashMap<>();
			values = new ArrayList<>();
			random = new Random();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already contain the specified element.
		 */
		public boolean insert(int val) {
			if (map.containsKey(val)) {
				return false;
			}
			int index = values.size();
			map.put(val, index);
			values.add(val);
			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified element.
		 */
		public boolean remove(int val) {
			if (!map.containsKey(val)) {
				return false;
			}
			Integer index = map.get(val);
			int lastIndex = values.size() - 1;
			if (lastIndex != index) {
				int lastVal = values.get(lastIndex);
				values.set(index, lastVal);
				map.put(lastVal, index);
			}
			values.remove(lastIndex);
			map.remove(val);
			return true;
		}

		/**
		 * Get a random element from the set.
		 */
		public int getRandom() {
			int bound = values.size();
			return values.get(random.nextInt(bound));
		}
	}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
