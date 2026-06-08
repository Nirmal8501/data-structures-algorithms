// User function Template for Java

class Solution {
    public boolean isPossible(int N, int P, int[][] prerequisites) {
        // Your Code goes here
        // Store the input as a graph in AdjList, because clearly it is a topo-sort problem.
        // Something before something
        List<Integer> topoSort = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[N];
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i = 0; i<N; i++){
            adjList.add(new ArrayList<>());
        }
        
        for(int i = 0; i<P; i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            
            adjList.get(u).add(v);
            indegree[v]++; // indegree.set(v, indegree.get(v) + 1); if using list instead of primitive.
        }
        
        // now perform Topological Sort (I will do it by Kahn's Algorithm)
        for(int i = 0; i<N; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
         
        while(!q.isEmpty()){
            int node = q.poll();
            topoSort.add(node);
            
            for(Integer neighbourNode : adjList.get(node)){
                indegree[neighbourNode]--;
                if(indegree[neighbourNode] == 0){
                    q.offer(neighbourNode);
                }
            }
        }
        
        return topoSort.size() == N;
    }
}