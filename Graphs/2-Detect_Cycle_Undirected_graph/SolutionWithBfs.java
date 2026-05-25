class Solution {
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        boolean answer = false;

        List<List<Integer>> adjlist = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adjlist.add(new ArrayList<>());
        }

        Queue<Node> queue = new LinkedList<>();
        
        // List<Node> visited[] = new ArrayList(V);
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
                
                queue.offer(new Node(i, -1)); // the first node has no parent, it came from nowhere;
                
                // iterate for its neighbours, while Queue is not empty, and if not visited then push them into the queue.
                while(!queue.isEmpty()){
                    
                    Node node = queue.peek();
                    queue.poll();
                    
                    int element = node.vertex;
                    int parent = node.parentVertex;
                    
                    for(Integer neighbour : adjlist.get(element)){
                        
                        if(visited[neighbour] != 1){
                            
                            visited[neighbour] = 1;
                            
                            queue.offer(new Node(neighbour, element));
                        }
                        else if(visited[neighbour] == 1 && neighbour != parent){
                            return true;
                        }
                    }
                }
            }
        }
        
        return answer;
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