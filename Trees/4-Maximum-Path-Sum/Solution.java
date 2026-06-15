/*
Definition for Node
class Node{
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    int findMaxSum(Node root) {
        // code here
        if(root == null) return 0;
    int[] maxSum = new int[1];
    maxSum[0] = Integer.MIN_VALUE;
    findSum(root, maxSum);
    return maxSum[0];
    }
    
    private int findSum(Node node, int[] maxSum){
        if(node == null) return 0;
        
        int leftSum = Math.max(0, findSum(node.left, maxSum));  // because path can end at any node and not necessarily at leaf, so if leftSum is negative, we will not take it, we will take 0 instead.
        int rightSum = Math.max(0, findSum(node.right, maxSum)); // because path can end at any node, so if rightSum is negative, we will not take it, we will take 0 instead.
        
        int sumViaMe = node.data + leftSum + rightSum;
        if(sumViaMe > maxSum[0]) maxSum[0] = sumViaMe;
        
        return node.data + Math.max(leftSum,rightSum); // we will return max of left or right sum to our parent, because path can only go through one child, not both.
        
    }
}