package Trees.3-Diameter-Of-Tree;
// BruteForce


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
        
        // stand at node and ask what info do we need from my child.
        // I need their height, so lh and rh and +1 to compute my height.
        // but how will I know if my height is max ? I need something to compare right, like a global max.
        // or I will return maxDiam instead to my ancestor
        // So I need height From mychildren and their own Diameter.
        
        Integer currentDiamter = 0;
        int d = diam(root);
        
        return d;
    }
    
    private int diam(Node root){
        if(root == null) return 0;
        
        int lh = height(root.left);
        int rh = height(root.right);
        
        int diameterViaMe = lh + rh;
        
        int leftDiam = diam(root.left);
        int rightDiam = diam(root.right);
        
        return Math.max(diameterViaMe, Math.max(leftDiam, rightDiam));
        
        
        // return Math.max(lDiam, rDiam);
        
        // return currentDiameter;
    }
    
        private int height(Node root) {
        // code here
        // think of smallest possible tree, with 0 node, what is height ? 0 right, so basecase defined.
        if(root == null) return 0;
        
        // stand at node and ask what do I need from my children ?
        // I need left and right height to compute my own height.
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
