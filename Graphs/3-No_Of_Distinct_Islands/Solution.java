// User function Template for Java

class Solution {

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        Set<List<Node>> uniqueIslands = new HashSet<>();
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[][] visited = new int[rows][cols];
        
        for(int i=0; i<rows; i++){
            for(int j=0; j< cols; j++){
                if(visited[i][j] != 1 && grid[i][j] == 1){
                    Node node = new Node(i,j);
                    Node baseNode = new Node(i,j);  // Since this is the start point, node and base node will be same;
                    List<Node> coords = new ArrayList<>();
                    dfs(node, baseNode, coords, visited, grid);
                    uniqueIslands.add(coords);
                }
            }
        }
        
        return uniqueIslands.size();
        
    }
    
    // to visit, i will follow, up, right, down, left order for all
    void dfs(Node node, Node baseNode, List<Node> coords, int[][] visited, int[][] grid){
        int maxRowLength = grid.length;
        int maxColLength = grid[0].length;
        
        visited[node.row][node.col] = 1;
        Node adjustedNode = node.subtractBase(baseNode);
        coords.add(new Node(adjustedNode.row, adjustedNode.col));
        
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaCol = {0, +1, 0, -1};
        
        for(int i=0; i<4; i++){
            int nrow = node.row + deltaRow[i];
            int ncol = node.col + deltaCol[i];
            
            if(nrow >= 0 && nrow < maxRowLength && ncol >=0 && ncol < maxColLength){
                if(visited[nrow][ncol] == 0 && grid[nrow][ncol] == 1){
                    Node currNode = new Node(nrow, ncol);
                    dfs(currNode, baseNode, coords, visited, grid);
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
        
        public Node subtractBase(Node baseNode){
            return new Node(row - baseNode.row, col - baseNode.col);
        }
        
         @Override
        public boolean equals(Object obj){
        
            if(this == obj){
                return true;
            }
        
            if(obj == null || getClass() != obj.getClass()){
                return false;
            }
        
            Node other = (Node) obj;
        
            return this.row == other.row &&
                   this.col == other.col;
        }
        
        @Override
        public int hashCode(){
        
            return Objects.hash(row, col);
        }
    }
}
