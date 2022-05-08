# 工程简介



# 第一轮

```
NateNiu 16:18
Are String str="i" and  String str=new String(“i”) same? 

heap 

stack 
NateNiu 16:24
Write a function to swap two integers without temporary variable? 
NateNiu 16:31
Write a function that returns an array with each index being the product of all the other numbers in the input array except the one with that index.

So, given the array: [1,2,3,4,5]
Index 0 should exclude 1 and be 2*3*4*5 = 120
Index 1 should exclude 2 and be 1*3*4*5 = 60
Index 2 should exclude 3 and be 1*2*4*5 = 40
Index 3 should exclude 4 and be 1*2*3*5 = 30
Index 4 should exclude 5 and be 1*2*3*4 = 24
Final Answer: [120, 60, 40, 30, 24] 

```

```
https://hr.gs/IBJavaDeveloper ，这个是笔试链接，您这边可以在五一假期期间抽空做一下。
```



------



## 2022050801



Given an array of integers, calculate the ratios of its elements that are *positive*, *negative*, and *zero*. Print the decimal value of each fraction on a new line with places after the decimal.

**Note:** This challenge introduces precision problems. The test cases are scaled to six decimal places, though answers with absolute error of up to are acceptable.

**Example**

There are elements, two positive, two negative and one zero. Their ratios are , and . Results are printed as:

```
0.400000
0.400000
0.200000
```

**Function Description**

Complete the *plusMinus* function in the editor below.

plusMinus has the following parameter(s):

- *int arr[n]*: an array of integers

**Print**
Print the ratios of positive, negative and zero values in the array. Each value should be printed on a separate line with digits after the decimal. The function should not return a value.

**Input Format**

The first line contains an integer, , the size of the array.
The second line contains space-separated integers that describe .

**Constraints**



**Output Format**

**Print** the following lines, each to decimals:

1. proportion of positive values
2. proportion of negative values
3. proportion of zeros

**Sample Input**

```
STDIN           Function
-----           --------
6               arr[] size n = 6
-4 3 -9 0 4 1   arr = [-4, 3, -9, 0, 4, 1]
```

**Sample Output**

```
0.500000
0.333333
0.166667
```

**Explanation**

There are 3 positive numbers, 2 negative numbers, and 1 zero in the array.
The proportions of occurrence are positive: 3/6=0.500000, negative 2/6=0.333333: and zeros: 1/6=0.166667.



------



## 2022050802



Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.

**Example**

The minimum sum is and the maximum sum is . The function prints

```
16 24
```

**Function Description**

Complete the *miniMaxSum* function in the editor below.

miniMaxSum has the following parameter(s):

- *arr*: an array of integers

**Print**

Print two space-separated integers on one line: the minimum sum and the maximum sum of of elements.

**Input Format**

A single line of five space-separated integers.

**Constraints**



**Output Format**

Print two space-separated long integers denoting the respective minimum and maximum values that can be calculated by summing exactly *four* of the five integers. (The output can be greater than a 32 bit integer.)

**Sample Input**

```
1 2 3 4 5
```

**Sample Output**

```
10 14
```

**Explanation**

The numbers are 1,2, 3,4, and 5,. Calculate the following sums using four of the five integers:

1. Sum everything except , the sum is 2 + 3 + 4 + 5 = 14.
2. Sum everything except , the sum is 1+ 3 + 4 + 5 = 13.
3. Sum everything except , the sum is 1+ 2 + 4 + 5 = 12.
4. Sum everything except , the sum is 1+ 2 + 3 + 5 = 11.
5. Sum everything except , the sum is 1+ 2 + 3 + 4 = 10.

**Hints:** Beware of integer overflow! Use 64-bit Integer.

------

Need help to get started? Try the [Solve Me First](https://www.hackerrank.com/challenges/solve-me-first) problem
