class Solution {
    public int numIslands(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        Queue<Node> q = new LinkedList<>();

        int ans = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1' && visited[i][j] != 1){
                    ans++;
                    bfs(grid, visited, q, new Node(i,j));
                }
            }
        }

        return ans;
    }

    private void bfs(char[][] grid, int[][] visited, Queue<Node> q, Node src){
        int rowSize = grid.length;
        int colSize = grid[0].length;
        q.offer(src);
        visited[src.row][src.col] = 1;

        while(!q.isEmpty()){
            Node node = q.poll();
            int row = node.row;
            int col = node.col;
            int[] deltaRow = new int[]{-1,0,1,0};
            int[] deltaCol = new int[]{0,1,0,-1};

            for(int i=0; i<4; i++){
                int nrow = row + deltaRow[i];
                int ncol = col + deltaCol[i];

                if(nrow>=0 && nrow<rowSize && ncol>=0 && ncol <colSize && grid[nrow][ncol]=='1' && visited[nrow][ncol]==0){
                    visited[nrow][ncol] = 1;
                    q.offer(new Node(nrow, ncol));
                }
            }
        }
    }
}

class Node{
    int row;
    int col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}