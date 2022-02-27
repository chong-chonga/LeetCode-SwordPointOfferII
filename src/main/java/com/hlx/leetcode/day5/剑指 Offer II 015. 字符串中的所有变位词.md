# 剑指 Offer II 015. 字符串中的所有变位词



**本题和 [剑指 Offer II 014. 字符串中的变位词](https://leetcode-cn.com/problems/MPnaiL/)** 是同一题的变换, 只是要求的答案改变了而已。

大致的解题思路和 [剑指 Offer II 014. 字符串中的变位词题解](https://leetcode-cn.com/problems/MPnaiL/solution/gei-ding-zi-yuan-ren-yi-zu-he-de-zi-chua-ayqd/) 一样



## 题目本意

给定两个字符串 `s` 和 `p`，找到 `s` 中所有 `p` 的 **变位词** 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

**变位词** 指字母相同，但排列不同的字符串。



本题答案与上一题相比, 将 **子串的存在 (bool) 改为了子串的开始索引 (集合)**



## 代码

```java
	class Solution {
		public List<Integer> findAnagrams(String s, String p) {
			List<Integer> res = new ArrayList<>();
			int[] pcharsMap = new int[26];
			char[] chars = p.toCharArray();
			for (char c : chars) {
				++pcharsMap[c - 'a'];
			}
			char[] schars = s.toCharArray();
			int left = 0;
			int right = 0;
			final int boundaryDifference = p.length() - 1;
			while (right < s.length()) {
				int index = schars[right] - 'a';
				if (pcharsMap[index] == 0) {
					while (left < right) {
						++pcharsMap[schars[left] - 'a'];
						++left;
						if (schars[left - 1] == schars[right]) {
							break;
						}
					}
					if (pcharsMap[index] == 0) {
						++right;
						left = right;
						continue;
					}
				}
				--pcharsMap[index];
				if (boundaryDifference == (right - left)) {
					if (isSubString(pcharsMap)) {
                        // 唯一修改的地方, 只是简单地将起始索引加入到 res 中
						res.add(left);
					}
					++pcharsMap[schars[left] - 'a'];
					++left;
				}
				++right;
			}

			return res;
		}

		private boolean isSubString(int[] charsMap) {
			for (int i : charsMap) {
				if (i != 0) {
					return false;
				}
			}
			return true;
		}
	}
```

