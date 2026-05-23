import java.util.ArrayList;

public class RottenOranges {

}


class Solution {
    public int orangesRotting(int[][] grid) {
        int ans = 0;

        int[][] visited = new int[grid.length][grid[0].length];
        Queue<Cell> queue = new LinkedList<>();

        // handle edge cases, if no fresh oranges at start then return 0 - EDGE 1
        // and if any single orange is left event after BFS then return -1; - EDGE 2 or if the no of rotten oranges at start is 0 (This will automatically be covered in bfs as nothing will get added to queue)

        // EDGE 1.
        int noOfFreshOranges = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] == 1) {
                 noOfFreshOranges++;
                }
            }
        }

        if(noOfFreshOranges == 0) return 0;



// Initial config to mark visited and store the node in queue along with its level (extra state).
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] == 2) {
                    visited[i][j] = 2;
                    queue.offer(new Cell(i,j,0));
                }
                if(grid[i][j] != 2) visited[i][j] = grid[i][j];
            }
        }

        while(!queue.isEmpty()){
            Cell cell = queue.peek();
            if(cell.time > ans) ans = cell.time;
            queue.poll();
            visitNeighboursOfCell(cell, queue, visited);
        }


        // If still any unrotten left then return -1;
        for(int i = 0; i<visited.length; i++){
            for(int j = 0; j<visited[0].length; j++){
                if(visited[i][j] == 1) {
                 return -1;
                }
            }
        }

        return ans;
    }

    public void visitNeighboursOfCell(Cell cell, Queue<Cell> queue, int[][] visited){
        int row = cell.row;
        int col = cell.col;
        int time = cell.time;
        // visitUp
        if(row != 0){
            if(visited[row-1][col] == 1){
                visited[row-1][col] = 2;
                queue.offer(new Cell(row-1, col, time+1));
            }
        }

        // visitDown
        if(row != visited.length-1){
            if(visited[row+1][col] == 1){
                visited[row+1][col] = 2;
                queue.offer(new Cell(row+1, col, time+1));
            }
        }

        // visitLeft
        if(col != 0){
            if(visited[row][col-1] == 1){
                visited[row][col-1] = 2;
                queue.offer(new Cell(row, col-1, time+1));
            }
        }

    // visitRight
        if(col != visited[0].length - 1){
            if(visited[row][col+1] == 1){
                visited[row][col+1] = 2;
                queue.offer(new Cell(row, col+1, time+1));
            }
        }
    }

    class Cell {
        public int row;
        public int col;
        public int time;

        public Cell(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}