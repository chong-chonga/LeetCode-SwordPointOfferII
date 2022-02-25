# 剑指 Offer II 009. 乘积小于 K 的子数组



## 解法1: 枚举

最直观的解决方法就是枚举每一个子数组左边界和右边界， 并查看该子数组的乘积是否小于 k。

设子数组的乘积为 product,

设子数组的左边界下标为 i (0 <= i < nums.length), 右边界下标为 j; 开始时 i = j, 也就是该子数组只有一个元素的时候;  此时, product = nums[j]。

- 当 product < k 时, 说明存在一种子数组, 使得乘积小于 k; ++result
- 否则, 由于 nums[i] >= 1, 说明往后枚举的子数组乘积是非递减的, 因此停止这次枚举

![image-20220224205032119](C:\Users\悠一木碧\AppData\Roaming\Typora\typora-user-images\image-20220224205032119.png)

具体实现代码如下

```java
 class Solution {
		public int numSubarrayProductLessThanK(int[] nums, int k) {
			int result = 0;
			int product = 1;
			for (int i = 0; i < nums.length; i++) {
				for (int j = i; j < nums.length; j++) {
					product *= nums[j];
					if (product >= k) {
						break;
					}
					++result;
				}
                // 将子数组乘积重置
				product = 1;
			}
			return result;

		}
	}
```



### 复杂度分析

- 时间复杂度：O(n^2), 在最差的情况下, 对于长度为 n 的数组, 接近于嵌套的 for 循环。
- 空间复杂度：O(1)。



## 解法二: 滑动窗口

设 left, right 分别作为子数组的两个边界, 乘积 product;

假定长度为 n 的连续子数组满足乘积 < k, 那么 nums[left] ~ nums[right] 的任意子数组都满足乘积 < k。

此时， 子数组个数有: **1 + 2 + 3 + ··· + n-1 + n = (n + 1)*n/2** 个。

那么当子数组 right 指针增长时, 只会有两种情况

1. product *= nums[right] < k; 

   数组长度变化: **n -> n+1**

   因此有 (n+2)*(n+1)/2 种子数组组合, 相比于长度 n 时, 增加了 **(3n + 2 - n) / 2 = n + 1;**

   由此我们可以知道, 当 right 指针右移时, 子数组答案种数增加了 (n + 1)

   n + 1 = right - left + 1 (right, left 为 当前的指针值)

   故

   ```java
   res += (right - left + 1);
   ```

   这里需要注意: res 加上了 nums[left] ~ nums[right] 区间内**新增的**所有的可能

2. product *= nums[right] >= k;

   我们需要对子数组区间进行缩小, 通过 left++, 将乘积 product 缩小

   ```java
   product /= nums[left++];
   ```

   如果 left == right, 说明的单个元素都大于 k, 因此还需要 right++, 同时 product = 1;



### 代码实现

在代码实现上, 我们先将 left, right 指针重叠, 初始的乘积为 1(确保单个元素的时候, 乘积为本身)

```java
 class Solution {
		public int numSubarrayProductLessThanK(int[] nums, int k) {
			int result = 0;
			int product = 1;
			int left = 0;
			int right = 0;
			while (right < nums.length) {
				if (product * nums[right] < k) {
					product *= nums[right];
					result += (right - left + 1);
					++right;
				} else if (left == right) {
					product = 1;
					++right;
					left = right;
				} else {
					product /= nums[left];
				}
			}
			return result;

		}
	}
```



### 复杂度分析

- 时间复杂度: O(n), 其中 n 为数组的长度, 只需一次遍历即可得到答案。
- 空间复杂度: O(1)。

