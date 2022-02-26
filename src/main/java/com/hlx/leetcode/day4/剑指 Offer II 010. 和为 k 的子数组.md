# 剑指 Offer II 010. 和为 k 的子数组



## 题目要求

给定一个整数数组和一个整数 `k` **，**请找到该数组中和为 `k` 的连续子数组的个数。

- `1 <= nums.length <= 2 * 104`
- `-1000 <= nums[i] <= 1000`
- `-107 <= k <= 107`



## 解题思路

题目要求的是 "**连续子数组**", 因此此题和**排序**没关系；题目还要求的是子数组的**个数**。

数组有两个属性, 左边界和右边界, 我们设其分别为 **i, j**(j >= i)

假设 nums[i] ~ nums[j] 的子数组满足条件: 和为 k

设**前缀和 sum[i]** 表示 nums[0] ~ nums[i] 的和, 上述条件可以表述为

![image-20220226184951129](C:\Users\悠一木碧\AppData\Roaming\Typora\typora-user-images\image-20220226184951129.png)

这样一来, 我们可以遍历数组, 依次计算前缀和

```java
for (int i = 0; i < nums.length; i++) {
    sum += nums[i];
    // i == 0 的情况
    if (sum == k) {
        ++res;
    }
    ...
}
```

我们可以使用 map 来存储已经出现的前缀和, 其中 key 为 sum[i], value 为相同前缀和的个数

```java
			Map<Integer, Integer> sums = new HashMap<>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				...
				// 增加前缀和为 sum 的个数
				sums.merge(sum, 1, Integer::sum);
			}
```

还差最后一步, 我们只考虑了 i == 0 的情况, 由于我们将计算的前缀和放入了 map 中; 因此我们可以查找 map 中满足 

sum[j] - sum[i - 1] = k 的个数, 并更新 res 即可。



## 代码实现

```java
	class Solution {
		public int subarraySum(int[] nums, int k) {
			int res = 0;
			int sum = 0;
			Map<Integer, Integer> sums = new HashMap<>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
				// 查找前缀和中是否存在一个 target, 使得 sum - target = k
				if (sum == k) {
					++res;
				}
				int target = sum - k;
				Integer count = sums.get(target);
				if (count != null) {
					res += count;
				}
				// 增加前缀和为 sum 的个数
				sums.merge(sum, 1, Integer::sum);
			}
			return res;
		}
	}
```



## 笔记

求连续子数组的和为 XXXX 的题目类型, 可以使用 **前缀和y - 前缀和x = 子数组和** 的思想来解决