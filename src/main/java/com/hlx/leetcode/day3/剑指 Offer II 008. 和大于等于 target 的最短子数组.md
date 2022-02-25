# 剑指 Offer II 008. 和大于等于 target 的最短子数组



## 解题思路

连续子数组长度最短, 且和满足 大于/小于/等于条件等问题, 大多是使用滑动窗口的思路解题。

此题属于:  **子数组和大于等于** 类型。



设索引 left, right 分别表示子数组的左右边界

那么子数组的长度表达式为:

```java
int len = right-left+1;
```

设子数组的和为 sum, 我们递增 right, 表示子数组的边界扩大, 并将 nums[right] 加入到 sum; 

```java
sum += nums[right];
```

由于题目保证了 nums[i] >= 1, 那么在遍历过程中, sum 的值必定是在递增；当递增到 sum >= target 的时候, 我们就开始缩短子数组的长度, 同时更新 len。

```java
...
while(sum >= target) {
    len = Math.min(len, right - left + 1);
    sum -= nums[left++];
}
...
```



## 代码实现

```java
	class Solution {
		public int minSubArrayLen(int target, int[] nums) {

			int res = Integer.MAX_VALUE;
			int sum = 0;
			int left = 0;
			int right = 0;
			while (true) {
				sum += nums[right];
				if (sum >= target) {
					while (sum >= target) {
						res = Math.min(res, right - left + 1);
						sum -= nums[left];
						++left;
					}
				}
				if (right == nums.length - 1) {
					break;
				}
				++right;
			}
			return res == Integer.MAX_VALUE ? 0 : res;
		}
	}
```



## 其他细节

target >= 1, 当数组长度为 0 时, sum = 0, 此时 sum >= target, 保证了 left 不会越界。



## 复杂度分析

- 时间复杂度：O(n), 只需一次遍历就可以得到答案。
- 空间复杂度：O(1), 使用了常数个变量。









