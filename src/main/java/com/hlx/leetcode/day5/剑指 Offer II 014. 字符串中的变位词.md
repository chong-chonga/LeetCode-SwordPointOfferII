# 剑指 Offer II 014. 字符串中的变位词



原题:

给定两个字符串 `s1` 和 `s2`，写一个函数来判断 `s2` 是否包含 `s1` 的某个变位词。

换句话说，第一个字符串的排列之一是第二个字符串的 **子串** 。



## 题意分析

题目要求的是从 s2 中找子串, 要求该子串是可以由 s1 **重组排列**而成。

由于是重组排列, 那么字符的种类和个数必须和 s1 相同。

举个不太恰当的例子: 有一幅画(s1), 现在给你一些种类且数量不一的颜料(s2), 你能不能在这幅画(s1)中找到部分画面(子串), 用这些颜料(s2)模仿出来, 怎么画都可以。



## 解题思路

要复刻一模一样的画, 所需的颜色, 上色的量都得一样(这里不考虑颜色混合产生新的颜色);

回到问题上来, 也就是我们需要从 s2 中寻找与 **s1长度相等** 且 **字符种类/个数和 s1 相同** 的子串。

那么我们先应该从 s1 入手, 统计其每种字符对应的数量

由于 `s1` 和 `s2` 仅包含小写字母 ('a' ~ 'z'), 因此我们可以直接使用长度为 **26** 的数组来实现**哈希表**

```java
	int[] charsMap = new int[26];
	char[] chars1 = s1.toCharArray();		
	// 计算 s1 每种字符的个数
	for (char c : chars1) {
		++charsMap[c-'a'];
	}
```

这道题目也是子串问题， 和子数组一样， 我们可以使用**双指针**

设 left, right 分别是子串的左右边界, chars2 是 s2 的字符数组表示

我们移动 right 指针, 表示子串中的 chars[right] 字符增加, 同时减少对应字符所需数量

```java
char[] chars2 = s2.toCharArray();
while (right < s2.length()) {
    int index = chars2[right] - 'a';
    ...
    ...
    --charsMap[index];
    ...
    ...
    ++right;
}
```

**在减少对应字符所需数量前, 我们还需要判断对应 charsMap[index] 是否等于0**

**如果等于 0, 说明子串中不能再出现该字符了**

这时, 我们必须移动 left 指针, 减少子串中该字符的数量。

```java
if (charsMap[index] == 0) {
    while (left < right) {
        ++charsMap[chars2[left]-'a'];
        ++left;
        if (chars2[left-1] == chars2[right]) {
            break;
        }
    }
}
```

**如果当前子串中没有该字符，也就是说明该字符不应当出现在子串中** (s1 的charsMap 决定的)

这时, 我们应当将子串的左右边界都置为 right + 1

```java
if (charsMap[index] == 0) {
    ++right;
    left = right;
    continue;
}
```

上面的逻辑都清楚了, 现在只需要使得子串长度和 s1 相等了

子串长度:  len = right - left + 1;

```java
if (len == s1.length()) {
    // charsMap[i] 都等于 0 时, 说明存在这样的子串
    ...
}
```



## 完整代码

```java
	class Solution {
		public boolean checkInclusion(String s1, String s2) {
			if (s2.length() < s1.length()) {
				return false;
			}
			// s2的某个子串有 s1 相同类型的字符, 且每种字符个数相同
			int[] charsMap = new int[26];
			char[] chars1 = s1.toCharArray();
			int end = chars1.length - 1;

			// 计算 s1 每种字符的个数
			for (char c : chars1) {
				++charsMap[c-'a'];
			}
			// 使用双指针表示 s2 子串的左右边界
			int left = 0;
			int right = 0;
			char[] chars2 = s2.toCharArray();
			while (right < s2.length()) {
				int index = chars2[right] - 'a';
				if (charsMap[index] == 0) {
					// 这种字符所需数量 = 0, 说明需要移动子串位置来减少子串中该字符的数量
					while (left < right) {
						++charsMap[chars2[left] - 'a'];
						++left;
						// 如果左边界截去的字符是当前的字符, 则保证了这种字符数量没有溢出
						if (chars2[left - 1] == chars2[right]) {
							break;
						}
					}
					// 移动左边界后, 该字符所需数量仍然 = 0, 说明该字符不应该出现在子串中
					if (charsMap[index] == 0) {
						++right;
						left = right;
						continue;
					}
				}
				// 该种字符所需数量--
				--charsMap[index];

				// s2子串长度 = s1长度, 判断是否为子串
				if (right - left == end) {
					if (isASubstring(charsMap)) {
						return true;
					} else {
						// 不是子串, 左边界向前移动
						++charsMap[chars2[left] - 'a'];
						++left;
					}
				}
				++right;
			}
			return false;
		}

		/**
		 * 判断方法, 如果 s2 包含 s1, 每种字符数都等于 0
		 */
		boolean isASubstring(int[] charsMap) {
			for (int j : charsMap) {
				if (j != 0) {
					return false;
				}
			}
			return true;
		}
	}
```



## 后记

做完本题后, 接着练习 [剑指 Offer II 015. 字符串中的所有变位词](https://leetcode-cn.com/problems/VabMRr/) 更有效。

