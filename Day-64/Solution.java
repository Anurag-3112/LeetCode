import java.util.*;
class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, Integer[]> mp = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if(!mp.containsKey(curr)) {
                mp.put(curr, new Integer[] {i, -1, -1});
            } else {
                Integer[] val = mp.get(curr);
                if(val[1] == -1)    val[1] = i;
                else  {
                    if(val[2] == -1)
                        val[2] = i;
                    else {
                        val[0] = val[1];
                        val[1] = val[2];
                        val[2] = i;
                    }
                    ans = Math.min(ans, calc(val));
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
    private int calc(Integer[] val) {
        return val[1] - val[0] + val[2] - val[1] + val[2] - val[0];
    }
}