# 剑指 Offer II 007. 数组中和为 0 的三个数



这也是一道**双指针**类型的题目; 特殊的是, 这道题目要求的是三个数, 经常遇到, 也经常不知道怎么做, 所以这道题还是值得好好记下。



## 解题思路

要把双指针的思路应用到三元组上, 就必须将问题化为双指针类型。

设数组元素 **a,b,c, a + b + c = 0;**

则 **-a = b + c;**

先针对一个元素 a, 从数组中找到两个元素 b、c 使之 b + c = -a;

这样一来, 问题就化简为: **找到数组中两个元素和为 -a**。

题目变成了两个元素XXX, 那么我们就好使用双指针来解决这道问题了。

我们先对数组进行排序, 来保证双指针的有序查找。

```java
Arrays.sort(nums);
```

我们枚举每一个不重复的 a, 来保证已经出现的 (b + c) 组合不再重复。

为了达到这个效果, 我们可以先确定一个**旧值 lastNum**, 将当前值与旧值比较; 如果不相同, 则开始搜索满足条件的三元组。

```java
int lastNum = Integer.MAX_VALUE;
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == lastNum) {
					continue;
				}
                ...
                ...
                ...
            }
```

至于剩下的逻辑， 我们可以当成， **数组中 b + c 的值为 -a 的不重复集合**



## 代码实现

```java
	class Solution {
		public List<List<Integer>> threeSum(int[] nums) {
			if (null == nums || 3 > nums.length) {
				return new ArrayList<>();
			}
			Arrays.sort(nums);
			int lastNum = Integer.MAX_VALUE;
			List<List<Integer>> result = new ArrayList<>();
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == lastNum) {
					continue;
				}
				lastNum = nums[i];
				int left = i + 1;
				int right = nums.length - 1;
				int target = -nums[i];
				int sum;
				while (left < right) {
					sum = nums[left] + nums[right];
					if (sum == target) {
						result.add(Arrays.asList(lastNum, nums[left], nums[right]));
						++left;
						while(left < right && nums[left] == nums[left-1]){
							++left;
						}
						--right;
						while (left < right && nums[right] == nums[right + 1]) {
							--right;
						}
					} else if (sum > target) {
						--right;
					} else {
						++left;
					}
				}
			}
			return result;
		}
	}
```



## 总结

**双指针找两个数组元素的和， 绝大部分要使用快排让数组有序来提速。**

**双指针绝大部分一个确立左边界， 一个确立右边界**















