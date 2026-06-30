import java.util.ArrayList;

public class DisjointSet {
    // parent array and rank array
    private ArrayList<Integer> parent = new ArrayList<>();
    private ArrayList<Integer> rank = new ArrayList<>();
    
    // initialize
    public DisjointSet(int n){
        for(int i=0; i<=n; i++){ //in case of 1 based indexing, kept it equal to n
            parent.add(i);
            rank.add(0);
        }
    }
    
    public int findUltParent(int node){
        if(parent.get(node) == node) return node;
        
        int ultParent = findUltParent(parent.get(node)); 
        parent.set(node, ultParent); // Path Compression
        return ultParent;
    }
    
    public void unionByRank(int u, int v){
        // I have to perform union of u and v
        // Algotithm: 1) Find Ultimate Par of u and v
        // 2) If their rank is same and attach u to v (or v to u) and increase rank of u by 1 and assign parent of v as u.
        // 3) Else if their rank is not same, then attach lower to higher without increasing rank and only update parent. 
        
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
            parent.set(uUltPar, uUltPar);
        }
    }

    // Just for testing purpose writing a main method
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        if(ds.findUltParent(3) == ds.findUltParent(7)){
            System.out.println("Same");
        }
        else{
            System.out.println("Not Same");
        }

        ds.unionByRank(3, 7);

        if(ds.findUltParent(3) == ds.findUltParent(7)){
            System.out.println("Same");
        }
        else{
            System.out.println("Not Same");
        }
    }
}