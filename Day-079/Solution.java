class Solution {

    private static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public boolean containsCycle(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] &&
                        isCycle(i, j, -1, -1, grid, visit, grid[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCycle(int x, int y,
            int px, int py,
            char[][] grid,
            boolean[][] visit,
            char ch) {

        visit[x][y] = true;

        for (int[] d : dir) {
            int x1 = x + d[0];
            int y1 = y + d[1];

            if (x1 >= 0 && y1 >= 0 &&
                x1 < grid.length && y1 < grid[0].length) {

                if (grid[x1][y1] != ch) {
                    continue;
                }

                if (visit[x1][y1]) {
                    if (!(x1 == px && y1 == py)) {
                        return true;
                    }
                } else {
                    if (isCycle(x1, y1, x, y, grid, visit, ch)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}