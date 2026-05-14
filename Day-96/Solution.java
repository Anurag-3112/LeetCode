public class Solution {

    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - 1 - i];
            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;

            diff[2] += 2;
            diff[low] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[high + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;
        int curr = 0;

        for (int sum = 2; sum <= 2 * limit; sum++) {
            curr += diff[sum];
            ans = Math.min(ans, curr);
        }

        return ans;
    }

}