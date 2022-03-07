# 剑指 Offer II 032. 有效的变位词



## 原题

给定两个字符串 `s` 和 `t` ，编写一个函数来判断它们是不是一组变位词（字母异位词）。

**注意：**若 `*s*` 和 `*t*` 中每个字符出现的次数都相同且**字符顺序不完全相同**，则称 `*s*` 和 `*t*` 互为变位词（字母异位词）。

- `1 <= s.length, t.length <= 5 * 104`
- `s` and `t` 仅包含小写字母

 

## 解题思路

这道题和之前的 [剑指 Offer II 014. 字符串中的变位词](https://leetcode-cn.com/problems/MPnaiL/) / [剑指 Offer II 015. 字符串中的所有变位词](https://leetcode-cn.com/problems/VabMRr/) 很类似。

多出的条件是判断 s 和 t 两个原字符串的字母**顺序不完全相同**, 而不是子串问题。

这道题指明了 `s` and `t` 仅包含小写字母, 所以我们还是可以用哈希表统计。

1. 判断两字符串长度是否相同。
2. 判断两个字符串是否 equal。
3. 使用哈希表统计所有字母的出现次数；判断各个字母出现次数是否相同。



## 完整代码

```java
	class Solution {
		public boolean isAnagram(String s, String t) {
			int len = s.length();
			if (len != t.length()) {
				return false;
			}
			char[] chars1 = s.toCharArray();
			char[] chars2 = t.toCharArray();
			
			// 以下三个循环可以使用并行优化性能
			boolean isEqual = true;
			for (int i = 0; i < len; i++) {
				if (chars1[i] != chars2[i]) {
					isEqual = false;
					break;
				}
			}
			if (isEqual) {
				return false;
			}
			int[] charsMap1 = new int[26];
			int[] charsMap2 = new int[26];
			
			for (char c : chars1) {
				++charsMap1[c - 'a'];
			}
			for (char c : chars2) {
				++charsMap2[c - 'a'];
			}
            // 当前循环与前面的循环有依赖关系
			for (int i = 0; i < 26; i++) {
				if (charsMap1[i] != charsMap2[i]) {
					return false;
				}
			}
			return true;

		}
	}

```



## 其他想说的

代码很简单， 但是代码确可以牵涉到很多性能优化点。

下面是一个更美观的代码。

```java
class Solution {
		public boolean isAnagram(String s, String t) {
			int len = s.length();
			if (len != t.length()) {
				return false;
			}
			char[] chars1 = s.toCharArray();
			char[] chars2 = t.toCharArray();

			// 以下的循环可以利用多核并行计算结果
			// GCC 环境下优化更好
			// 使用自己编写的 equal 方法优化 String 自带的方法 
			if (stringEqual(chars1, chars2)) {
				return false;
			}
			int[] charsMap1 = new int[26];
			int[] charsMap2 = new int[26];

			for (char c : chars1) {
				++charsMap1[c - 'a'];
			}
			for (char c : chars2) {
				++charsMap2[c - 'a'];
			}
			for (int i = 0; i < 26; i++) {
				if (charsMap1[i] != charsMap2[i]) {
					return false;
				}
			}
			return true;

		}
		
		boolean stringEqual(char[] chars1, char[] chars2) {
			int len = chars1.length;
			for (int i = 0; i < len; i++) {
				if (chars1[i] != chars2[i]) {
					return false;
				}
			}
			return true;
		}
	}
```

CSAPP(深入理解计算机系统)(第三版) 从数的编码开始, 再到汇编指令, 再到机器指令, 再到 CPU 逻辑、流水线原理...第五章开始讲到了**优化程序性能**

有这么几条知识可以用到: 

- 利用当前计算机的多核, 最大化程序的**并行执行**能力。这一点， 可以通过对上述的三个循环及进行并行处理，**因为它们的计算之间不会有依赖关系**。
- 减少转移指令对编译器的影响， 同时提高流水线执行效率。这一点， 可以通过对比第一份和第二份代码，两个代码做了同一件事情；**一个有方法调用， 一个顺序执行到底**（当然， 这个点可以忽略， 因为这个不是影响程序性能的关键），涉及到了流水线的指令损失和编译器的优化。
- 减少内存引用。这一点是关键, 虽然不知道 Java 会怎样优化以下的循环。

```java
			// 编译器会优化吗?
			for (int i = 0; i < charsMap1.length; i++) {
				if (charsMap1[i] != charsMap2[i]) {
					return false;
				}
			}
			...
            // len 应当是保存在一个寄存器中的
            // C/C++ 的汇编也是这么做的
            int len = charsMap1.length;
            for (int i = 0; i < len; i++) {
				if (charsMap1[i] != charsMap2[i]) {
					return false;
				}
			}    
```



