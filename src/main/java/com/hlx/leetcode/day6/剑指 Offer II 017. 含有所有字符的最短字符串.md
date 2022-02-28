# 剑指 Offer II 017. 含有所有字符的最短字符串



在完成 **剑指 Offer II 014 ~ 剑指 Offer II 016** 题后, 对

```tex
一个字符串 s1 包含另一个字符串 s2 的所有字符的XX子串
```

这种题型有了一个比较实用的解法。

那就是使用哈希表统计 s2 的字符种类和对应的个数; 然后使用滑动窗口, 逐次减少哈希表中的值 (相当于 s1 的子串字符数 - s2 的字符数), 使得所有哈希表的值 <= 0 (保证了 s1 的子串含有 s2 的所有字符)



## 解题思路

根据上面的表述, 我们需要明确以下几点:

1. 统计字符串 t 字符对应的个数, 存放至 chasMap 中
2. 使用滑动窗口, 根据当前字符, 减少 charsMap 中对应的值
3. s 的子串的长度至少大于等于 t 的长度时才会出现满足条件的子串 (可优化判断条件)
4. 子串满足条件时,   需要尽量缩短子串长度, 使得答案保证为最短。



### 统计字符数

根据上面的表述, 我们使用 charsMap 统计 t 中的每种字符出现的次数

```tex
s 和 t 由英文字母组成
```

和前面题目不一样的是, 这次是英文字母(包含小写和大写)

在 ASCII 编码中, 'A' ~ 'z' 的范围是 58, 因此我们能写出以下代码 (以排序最小的字符为偏移量)

```java
int[] charsMap = new int[58]
char[] tchars = t.toCharArray();
for (char c : tchars) {
    ++charsMap[c - 'A'];
}
```



### 滑动窗口

在下面的表述中, 我们还是使用 left, right 指针分别表示子串的左右边界。

根据第 2、3点， 我们移动 right 指针, 并减少 charsMap 中对应的值 (子串的字符数 - t 的字符数)

子串长度 = right - left + 1

只有当子串长度 >= t的长度时, 才会出现答案;

我们在这里再使用一个方法去优化; 当出现长度满足时, 我们使用该方法去查找 charsMap 中仍然不满足条件的下标, 用于下次判断

```java
		/**
		 * @return 26 当所有值都不大于 0 时; 否则返回对应的下标
		 */
		public int getIndexOfInvalidChar(int[] charsMap) {
			for (int i = 0; i < charsMap.length; i++) {
				if (charsMap[i] > 0) {
					return i;
				}
			}
			// 'A' + 26 = '['
			// '[' 字符数必定等于 0
			return 26;
		}
```

结合起来, 就是这样的效果

```java
char[] schars = s.toCharArray();
int nextIndex = 26;
while (right < schars.length) {
	--charsMap[schars[right]-'A'];
	if (right - left >= targetDiff && charsMap[nextIndex] <= 0) {
		nextIndex = getIndexOfInvalidChar(charsMap);
		// 说明当前子串满足条件
		if (nextIndex == 26) {
			...
			int i = schars[left] - 'A';
            // 试着缩短左边界, 同时保持条件满足
			while (charsMap[i] < 0 && left < right) {
					++charsMap[i];
					++left;
					i = schars[left] - 'A';
			}
            // 更新答案
			if (right - left < len) {
				...
                ...
			}
		}
	}
	++right;
}
```



## 完整代码

```java
class Solution {
		boolean hasAns = false;
		int resLeft = 0;
		int resRight = 100000;
		int len = 100000;
		public String minWindow(String s, String t) {
			char[] tchars = t.toCharArray();
			int left = 0;
			int right = 0;
			int targetDiff = t.length() - 1;

			int[] charsMap = new int[58];
			for (char tchar : tchars) {
				++charsMap[tchar - 'A'];
			}
			char[] schars = s.toCharArray();
			int nextIndex = 26;
			while (right < schars.length) {
				--charsMap[schars[right]-'A'];
				if (right - left >= targetDiff && charsMap[nextIndex] <= 0) {
					nextIndex = getIndexOfInvalidChar(charsMap);
					// 说明当前子串满足条件
					if (nextIndex == 26) {
						hasAns = true;
						int i = schars[left] - 'A';
						while (charsMap[i] < 0 && left < right) {
							++charsMap[i];
							++left;
							i = schars[left] - 'A';
						}
						if (right - left < len) {
							len = right - left;
							resLeft = left;
							resRight = right;
						}
					}
				}
				++right;
			}

			return !hasAns ? "" : s.substring(resLeft, resRight + 1);
		}

		/**
		 * @return 26 当所有值都不大于 0 时; 否则返回对应的下标
		 */
		public int getIndexOfInvalidChar(int[] charsMap) {
			for (int i = 0; i < charsMap.length; i++) {
				if (charsMap[i] > 0) {
					return i;
				}
			}
			// 'A' + 26 = '['
			// '[' 字符数必定等于 0
			return 26;
		}

	}
```





