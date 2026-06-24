package Graphs.7-Min_Multiplication_To_Reach_End;

public class Solution {
    public int minSteps(int[] arr, int start, int end) {
        // code here
        // if(start > end) return -1; Mistake, as start can get modded later and return a smaller value
        
        Queue<State> q = new LinkedList<>();
        boolean[] visited = new boolean[1001]; // Max value can be 1e3, we need visited, 
        // because if the number is 1, then it will keep multiplying and give TLE. 
        // And we have already proved that the first occurance of the end shall be with minimum multiplications, 
        // as it is a BFS with equal weights.
        q.offer(new State(0, start));
        visited[start] = true;

        while(!q.isEmpty()){
            State state = q.poll();
            int node = state.node;
            int noOfMultiplicationsTillNow = state.noOfMultiplications;
            
            if(node == end) return noOfMultiplicationsTillNow;
            // traverse for all neighbours
            for(int i = 0; i<arr.length; i++){
                int neighbourNode = (node * arr[i]) % (int)(1e3);
                if(!visited[neighbourNode]){
                    visited[neighbourNode] = true;
                    q.offer(new State(noOfMultiplicationsTillNow + 1, neighbourNode));
                }
            }
        }
        
        return -1;
    }
    
    class State {
        int noOfMultiplications;
        int node;
        
        public State(int noOfMultiplications, int node){
            this.noOfMultiplications = noOfMultiplications;
            this.node = node;
        }
    }
} {
    
}
