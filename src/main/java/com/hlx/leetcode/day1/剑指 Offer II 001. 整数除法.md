# 剑指 Offer II 001. 整数除法

**题意**: 给定两个整数 a 和 b ，求它们的除法的商 a/b.

```tex
要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。除法结果应截去小数部分
```
此题拿到手有点懵, 分析题意就知道这道题目具体要考查的内容

```tex
 1. 思考除法的底层实现
 2. 除法? a / b = k····c, c为余数, 舍去
```



## 解题思路

锁定表达式中的: **k**

k是整数, 因此可以用二进制表示

由乘法反推 a 的等式:

```tex
b*k + c = a;
```

此题和[剑指 Offer 16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)相似---通过 k 的位模式累加至 a。

**为了简便, 在下面的描述中， 我们把 a,b 看成无符号**。

由于计算机底层在处理有符号数和无符号数的加减法时, **在位模式上的行为是一致的**, 只是对相同的位模式看作不同的值 ; **因此我们可以将其看成无符号数的减法来处理**-----CSAPP-第三版-P74#关于整数运算的最后思考。

```tex
补充: 有符号数的最高位的权重为 -2^31; 无符号数的最高位权重为 2^31; 其他位权重一致。
```

那我们可以通过逐次减 b 来推得 k 的位模式吗?



### 推演

从 k 的最高位开始推算(权重 **2^31**)。

k 的最高位只能有两种情况: 0 或 1

```tex
1) **最高位为 1 时, 说明 2^31*b <= a;
2) **最高位为 0 时, 说明 2^31*b > a;
```

k 的剩余位和最高位同样处理, 就不再赘述

...

按照这个逻辑得到 k 的值后, 我们就得到了无符号数 **a**,**b** 的商 **k**

由于是无符号数的推算, 因此在转换成结果时, 需要进行符号化。



## 其他细节

题目要求截去小数部分, 我们在处理 k 的位模式的时候, 只有 0或1, 再看 **a = b*k + c**

其中 c < b, 当计算 k 的最低位时, 由于 1*b > c, 故最低位必定被设置为 0, 从而实现了截断

```tex
补充: 题目要求的是向 0 舍入 ---CSAPP-第三版-P73
```



## 代码实现

本题中，如果除法结果溢出，则返回 2^31 − 1 (Integer.MIN_VALUE / 1 未溢出, 所以不考虑, 其他情况更不会溢出)。

```java
	class Solution {

		public int divide(int a, int b) {
			// 本题中，如果除法结果溢出，则返回 2^31 − 1 (Integer.MIN_VALUE / 1 未溢出, 所以不考虑, 其他情况不会溢出)
			// 常规结果仍然是最小值, 因为最小值的非就是本身
			// 0x80000000 + 0x80000000 = 0x00000000(32位截断后的值) --- 参照 CSAPP(第三版)-P66
			if (a == Integer.MIN_VALUE && b == -1) {
				return Integer.MAX_VALUE;
			}
			// 计算结果的正负
			boolean isResNegative = (a > 0) ^ (b > 0);
			// 这里如果对 Integer.MIN_VALUE 取绝对值仍然会得到本身, 参照如上
			// 取绝对值后,  0(b!=0) <= a、b <= Integer.MAX_VALUE 或 a、b == Integer.MIN_VALUE
			a = Math.abs(a);
			b = Math.abs(b);
			int result = 0;
			//这里把 a,b 当成无符号数处理, 因此强制逻辑右移( Java只支持有符号数,但是我们可以把底层位模式看成无符号数)
			for (int i = 31; i >= 0; --i) {
				// 考虑边界值:
				// a = Integer.MAX_VALUE, b = 1                  res = Integer.MAX_VALUE
				// a = Integer.MIN_VALUE, b = 1                  res = Integer.MIN_VALUE
				// a = Integer.MIN_VALUE, b = Integer.MIN_VALUE  res = 1
				if ((a >>> i) - b >= 0) {
					a -= (b << i);
					result += (1 << i);
				}

			}
			return isResNegative ? -result : result;
		}
	}
```

