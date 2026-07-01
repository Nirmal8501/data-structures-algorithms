class Solution {
    static int kruskalsMST(int V, int[][] edges) {
        // code here
        
        // Sort the edges.
        Arrays.sort(edges, (a,b) -> Integer.compare(a[2], b[2]));
        
        // define a disjoint set. and if the parent is same then ignore the edge as it is already part of MST
        // else take the edge as part of MST and apply union on them.
        //keep taking the edge out and keep constructing.
        DisjointSet ds = new DisjointSet(V);
        int wt = 0;
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            int upar = ds.findUltParent(u);
            int vpar = ds.findUltParent(v);
            
            if(upar != vpar){
                wt += w;
                ds.unionBySize(u,v);
            }
        }
        
        return wt;
    }
}

class DisjointSet {
    // parent array and rank array
    private ArrayList<Integer> parent = new ArrayList<>();
    private ArrayList<Integer> rank = new ArrayList<>();
    private ArrayList<Integer> size = new ArrayList<>();
    
    // initialize
    public DisjointSet(int n){
        for(int i=0; i<=n; i++){ //in case of 1 based indexing, kept it equal to n
            parent.add(i);
            rank.add(0);
            size.add(1); // Initialize size of each component as 1
        }
    }
    
    public int findUltParent(int node){
        if(parent.get(node) == node) return node;
        
        int ultParent = findUltParent(parent.get(node)); 
        parent.set(node, ultParent); // Path Compression
        return ultParent;
    }
    
    public void unionByRank(int u, int v){
        
        int uUltPar = findUltParent(u);
        int vUltPar = findUltParent(v);
        
        if(uUltPar == vUltPar) return; // no need to do anything
        
        if(rank.get(uUltPar) == rank.get(vUltPar)){
            int urank = rank.get(uUltPar);
            rank.set(uUltPar, urank + 1);
            parent.set(vUltPar, uUltPar);
        }
        else if(rank.get(uUltPar) > rank.get(vUltPar)){
            parent.set(vUltPar, uUltPar);
        }
        else{ // rank vpar > rank upar
            parent.set(uUltPar, vUltPar);
        }
    }

        public void unionBySize(int u, int v){
        int ultPar_u = findUltParent(u);
        int ultPar_v = findUltParent(v);
        
        if(ultPar_u == ultPar_v) return; // no need to do anything

        if(size.get(ultPar_v)>= size.get(ultPar_u)){ 
            int sizeV = size.get(ultPar_v);
            size.set(ultPar_v, size.get(ultPar_u) + sizeV);
            parent.set(ultPar_u, ultPar_v);
        }
        else{
            int sizeU = size.get(ultPar_u);
            size.set(ultPar_u, sizeU + size.get(ultPar_v));
            parent.set(ultPar_v, ultPar_u);
        }
    }
}
