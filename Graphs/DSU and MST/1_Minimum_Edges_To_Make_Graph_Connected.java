public class 1_Minimum_Edges_To_Make_Graph_Connected {
    
}

// Question:
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/

// There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

// You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

// Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.





//  Answer:

class Solution {
    public int makeConnected(int V, int[][] edges) {
        int noOfEdges = edges.length;
        // lets find no of connected components.
        int noOfComponents = 0;
        DisjointSet ds = new DisjointSet(V);
        
        for(int[] edge : edges){
            ds.unionBySize(edge[0], edge[1]); 
            // another hack
            // you can use here if-else. if the parent of u n v is equal then it is redundant edge so increase count
            // and else perform union. because in case of equal parent union anyways doesnt do anything.
        }
        
        for(int i=0; i<V; i++){
            int pi = ds.getUParent(i);
            
            if(i == pi) noOfComponents++;
        }
        
        // Observation -> if no of components = nc, then min edges required to connect is nc - 1.
        // so there shd be atleast nc - 1 extra edges in the graph. 
        // so to connect V vertices in a graph, min edges required is V - 1. 
        // So total edges - (v - 1) shd be greater than or equal to nc - 1;
        
        if(noOfEdges >= (V - 1)) return noOfComponents - 1; // if possible then this will always be the answer.
        
        return -1;
    }
}

class DisjointSet {
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();
    
    public DisjointSet(int n){
        for(int i = 0; i<=n; i++){
            size.add(1);
            parent.add(i);
        }
    }
    
    public int getUParent(int node){
        if(parent.get(node) == node) return node;
        
        int ultParent = getUParent(parent.get(node));
        parent.set(node, ultParent); // Path Compression
        return ultParent;
    }
    
    public void unionBySize(int u, int v){
        int pu = getUParent(u);
        int pv = getUParent(v);
        
        if(pv == pu) return;
        
        if(size.get(pu) >= size.get(pv)){
            int puSize = size.get(pu);
            size.set(pu, size.get(pv) + puSize);
            parent.set(pv, pu);
        }
        else{
            int pvSize = size.get(pv);
            size.set(pv, size.get(pu) + pvSize);
            parent.set(pu, pv);
        }
    }
}