package com.CK;

public class Main {

    public static void main(String[] args) {
        new Solution().findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);

    }
}

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int max = 0;
        for (int num : nums) {
            max += num;
        }
        int min = -max;
        if (S > max || S < min)
            return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][2 * max + 1];

        for (int i = 1; i < n + 1; i++) {
            if (i == 1){
                dp[i][nums[0] + max] ++;
                dp[i][-nums[0] + max] ++;
                continue;
            }
            for (int k = 0; k < (2 * max + 1); k++) {
                int j = k - max;
                int plus = j - nums[i - 1] < min ? 0 : dp[i - 1][k - nums[i - 1]];
                int minus = j + nums[i - 1] > max ? 0 : dp[i - 1][k + nums[i - 1]];
                dp[i][k] = plus + minus;
            }
        }
        return dp[n][S + max];
    }
}

class Solution2 {
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (s > sum || s < -sum) return 0;
        int[] dp = new int[2 * sum + 1];
        dp[0 + sum] = 1;
        for (int i = 0; i < nums.length; i++) {
            int[] next = new int[2 * sum + 1];
            for (int k = 0; k < 2 * sum + 1; k++) {
                if (dp[k] != 0) {
                    next[k + nums[i]] += dp[k];
                    next[k - nums[i]] += dp[k];
                }
            }
            dp = next;
        }
        return dp[sum + s];
    }
}