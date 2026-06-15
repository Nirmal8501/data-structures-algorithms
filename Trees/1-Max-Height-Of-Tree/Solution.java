package Trees.1-Max-Height-Of-Tree;

public interface Solution {
    /*
Definition for Node
class Node {
    int data;
    Node left, right;

    Node(int val)
    {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public int height(Node root) {
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
}
