package Trees.3-Diameter-Of-Tree;

// OPtimal, Compute something else and store something


/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = right = null;
    }
} */

class Solution {
    public int diameter(Node root) {
        // code here
        int[] diam = new int[1];  // or take an AtomicReference as integers cant be passed by reference in java;
        height(root, diam);
        return diam[0];
    }
    
    private int height(Node node, int[] diameter){
        if(node == null) return 0;
        
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        
        // int diamViaMe = lh + rh;
        diameter[0] = Math.max(diameter[0], lh+rh);
        int ht = 1 + Math.max(lh, rh);
        return ht;
    }
}
