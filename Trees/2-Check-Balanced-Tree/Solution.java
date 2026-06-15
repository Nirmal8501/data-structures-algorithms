/*
Definition for Node
class Node {
    int data;
    Node left, right;

    Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    public boolean isBalanced(Node root) {
        // code here
        int ht = height(root);
        if(ht == -1) return false;
        return true;
    }
    
    public int height(Node node){
        if(node == null) return 0;
        
        int lh = height(node.left);
        int rh = height(node.right);
        
        if(Math.abs(lh - rh) > 1) return -1;
        if(lh == -1 || rh == -1) return -1;
        
        return  1 + Math.max(lh, rh);
    }
}


// Brute-Force 
    // public boolean check(Node node){
    //     if(node == null) return true;
        
    //     int lh = findHeight(node.left);
    //     int rh = findHeight(node.right);
        
    //     if(Math.abs(lh - rh) > 1) return false;
        
    //     bool leftBalanced = check(node.left);
    //     bool rightBalanced = check(node.right);
    //     if(leftBalanced == false || rightBalanced == false) return false;
        
    //     return true;
    // }