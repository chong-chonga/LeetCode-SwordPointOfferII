# 剑指 Offer II 035. 最小时间差



## 原题

给定一个 24 小时制（小时:分钟 **"HH:MM"**）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

 **提示：**

- `2 <= timePoints <= 2 * 104`
- `timePoints[i]` 格式为 **"HH:MM"**

 

## 解题思路

先分析下给定两个时间, 如何找到这两个时间的最小时间差。

设时间 **t1**、 t2， 时间差 **diff**； (以同一天为标准, **t1 < t2**)。

在下面的表述中, 我们使用分钟表示。

- 如果计算当天的 t1, t2 之间的时间差，则 `diff1  = t2 - t1;`

- 如果如果计算隔天 t1、t2 的时间差，由于 t2 > t1，如果 t2 在第二天，t1 在第一天，则 diff2 = diff1 + 1440；这个比第一种情况的差值要大。因此，我们只考虑 t1 在第一天， t2 在第二天，则 `diff2 = abs(diff1 - 1440);`

| 时间差 | 同一天  |      相隔一天       |
| :----: | :-----: | :-----------------: |
|  diff  | t2 - t1 | abs(t2 - t1 - 1440) |

对上述计算出的 diff1、diff2， 我们取最小值。由此，我们得到最小时间差： `res = min(diff1, diff2);`

假设存在多个时间，**t1 < t2 < t3 < t4... < tn**, 我们只考虑**相邻的时间, 和差值最大的两个时间** (**t1** 和 **tn**), 因为只有这些情况中, 才会出现最小值。



## 完整代码

```java
class Solution {
		public int findMinDifference(List<String> timePoints) {
            int len = timePoints.size();
			int[] arr = new int[len];
			for (int i = 0; i < len; i++) {
				String[] split = timePoints.get(i).split(":");
				int hour = Integer.parseInt(split[0]);
				int minute = Integer.parseInt(split[1]);
				arr[i] = 60 * hour + minute;
			}
			Arrays.sort(arr);
			int result = Integer.MAX_VALUE;
            // 求相邻的两个时间的最小时间差
			for (int i = 1; i < len; i++) {
				result = Math.min(Math.min(arr[i] - arr[i-1], Math.abs(arr[i]- arr[i-1]-1440)), result);
                if (result == 0) {
                    return 0;
                }
			}
            // 如果求当天的时间差, 则相邻的时间会更短
			result = Math.min(result, Math.abs(arr[len - 1] - arr[0]-1440));
			return result;
		}
	}
```

## 其他

这道题目给出的不一样的地方就在于, 我们可以横跨一个周期，这里的周期是 24小时；求差值最小，可以把一个时间当成同一周期，也可以当成第二周期，关键是找到两者的最小值。



