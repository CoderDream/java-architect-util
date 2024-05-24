// File: subset_sum_i.go
// Created Time: 2023-06-24
// Author: Reanon (793584285@qq.com)

package chapter_backtracking

import "sort"

/* 回溯算法：子集和 I */
func backtrackSubsetSumI(start, target int, state, choices *[]int, res *[][]int) {
	// 子集和等于 target 时，记录解
	if target == 0 {
		newState := append([]int{}, *state...)
		*res = append(*res, newState)
		return
	}
	// 遍历所有选择
	// 剪枝二：从 start 开始遍历，避免生成重复子集
	for i := start; i < len(*choices); i++ {
		// 剪枝一：若子集和超过 target ，则直接结束循环
		// 这是因为数组已排序，后边元素更大，子集和一定超过 target
		if target-(*choices)[i] < 0 {
			break
		}
		// 尝试：做出选择，更新 target, start
		*state = append(*state, (*choices)[i])
		// 进行下一轮选择
		backtrackSubsetSumI(i, target-(*choices)[i], state, choices, res)
		// 回退：撤销选择，恢复到之前的状态
		*state = (*state)[:len(*state)-1]
	}
}

/* 求解子集和 I */
func subsetSumI(nums []int, target int) [][]int {
	state := make([]int, 0) // 状态（子集）
	sort.Ints(nums)         // 对 nums 进行排序
	start := 0              // 遍历起始点
	res := make([][]int, 0) // 结果列表（子集列表）
	backtrackSubsetSumI(start, target, &state, &nums, &res)
	return res
}
