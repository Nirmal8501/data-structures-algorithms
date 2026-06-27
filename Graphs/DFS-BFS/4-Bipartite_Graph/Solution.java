class Solution {
    public boolean isBipartite(int V, int[][] edges) {
        // Code here
        // we need to store the graph
        int noOfEdges = edges.length;
        List<List<Integer>> adjList = new ArrayList<>();
        int[] visited = new int[V]; // it will store 0 for not visited, 1 and 2 as colors
        
        // initialization
        for(int i = 0; i< V; i++){
            adjList.add(new ArrayList<>());
        }
        
        // push edges to adjList.
        for(int i = 0; i < noOfEdges; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        // return dfs(0, 1, adjList, visited); // 0 = first node, 1 = first color, but graph can be in multiple component.
        
        for(int i = 0; i<V; i++){
            if(visited[i] == 0){
                 if(dfs(i, 1, adjList, visited) == false) return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(int node, int color, List<List<Integer>> adjList, int[] visited){
        visited[node] = color;
        
        for(int neighbourNode : adjList.get(node)){
            if(visited[neighbourNode] == 0){
                int nextColor = getNextColor(color);
                if(dfs(neighbourNode, nextColor, adjList, visited) == false) return false; // this shd return true or false
            }
            else if(visited[neighbourNode] == color) return false;
        }
        
        return true; // returning false after exploring all adj node dfs calls and still not finding a cycle.
    }
    
    private int getNextColor(int color){
        if(color == 1) return 2;
        else if (color == 2) return 1;
        
        throw new RuntimeException("Invalid color passed");
    }
}