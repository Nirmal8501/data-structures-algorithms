import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgo {
    
}


class Solution {
    public int spanningTree(int V, int[][] edges) {
        // code here
        // using prims;
        int wt = 0;
        List<List<Edge>> adjList = new ArrayList<>();
        List<MSTEdge> mst = new ArrayList<>();
        PriorityQueue<State> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        int[] visited = new int[V];
        
        for(int i = 0; i<V; i++){
            adjList.add(new ArrayList<>());
        }
        
        for(int[] edgeInfo : edges){
            int u = edgeInfo[0];
            int v = edgeInfo[1];
            int w = edgeInfo[2];
            
            adjList.get(u).add(new Edge(v, w));
            adjList.get(v).add(new Edge(u, w));
        }
        
        pq.offer(new State(0, 0, -1)); // no initial weight, 0 node and no parent;
        // will not mark it visited. because visited means part of MST.
        
        while(!pq.isEmpty()){
            State state = pq.poll();
            int weight = state.weight;
            int node = state.node;
            int parent = state.parent;
            
            if(visited[node] == 1) continue;
            
            visited[node] = 1;
            wt += weight;
            
            if(parent != -1){
                // include to MST.
                mst.add(new MSTEdge(node, parent, weight));
            }
            
            for(Edge edge : adjList.get(node)){
                if(visited[edge.to] == 0){
                    pq.offer(new State(edge.weight, edge.to, node));
                }
            }
        }
        
        return wt;
    }
    
    class Edge {
        public int to;
        public int weight;
        
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    class State {
        int weight;
        int node;
        int parent;
        
        public State(int weight, int node, int parent){
            this.weight = weight;
            this.node = node;
            this.parent = parent;
        }
    }

    class MSTEdge{
    int u;
    int v;
    int wt;

    MSTEdge(int u,int v,int wt){
        this.u=u;
        this.v=v;
        this.wt=wt;
    }
}
}
