class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int[] distance = new int[V];

        for(int i = 0; i<V; i++){
            distance[i] = (int)(1e8);
        }
        
        distance[src] = 0;
        
        // perform relaxation n-1 times.
        for(int j = 0; j < V-1; j++){
            for(int i = 0; i<edges.length; i++){
                int u = edges[i][0];
                int v = edges[i][1];
                int w = edges[i][2];
                
                if(distance[u] != (int)(1e8) && (distance[u] + w < distance[v])){
                    distance[v] = distance[u] + w;
                }
            }
        }
        
        // perform nth relaxation, if still distance gets updated then -ve cycle
        for(int i = 0; i<edges.length; i++){
                int u = edges[i][0];
                int v = edges[i][1];
                int w = edges[i][2];
                
                if(distance[u] != (int)(1e8) && (distance[u] + w < distance[v])){
                    return new int[]{-1};
                }
            }
            
            return distance;
    }
    
    class Edge{
        int to;
        int weight;
        
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}
