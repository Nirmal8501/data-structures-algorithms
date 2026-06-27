class Solution {
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        boolean answer = false;

        List<List<Integer>> adjlist = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adjlist.add(new ArrayList<>());
        }

        // Queue<Node> queue = new LinkedList<>();
        int[] visited = new int[V];
        
        // taking input into adjacency list, simple...
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            adjlist.get(u).add(v);
            adjlist.get(v).add(u);
        }
        
        // we will need to run bfs for each unvisited node as it can have multiple components.
        
        for(int i=0; i<V; i++){
            if(visited[i] != 1){
                visited[i] = 1;
                if(dfs(i, -1, visited, adjlist) == true) return true;
            }
        }
        
        return answer;
    }
    
    public boolean dfs(int node, int parent, int[] visited, List<List<Integer>> adjlist){
        visited[node] = 1;
        for(Integer neighbour: adjlist.get(node)){
            if(visited[neighbour] != 1){
                if (dfs(neighbour, node, visited, adjlist) == true) return true;
            }
            else if(visited[neighbour] == 1 && neighbour != parent){
                return true;
            }
        }
        
        return false;
    }
    
    class Node{
        
        int vertex;
        int parentVertex;
        
        public Node(int vertex, int parentVertex){
            this.vertex = vertex;
            this.parentVertex = parentVertex;
        }
    }
}