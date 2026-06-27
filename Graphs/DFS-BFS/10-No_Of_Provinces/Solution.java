class Solution {
    public int findCircleNum(int[][] isConnected) {
        // simple, no of different components in a graph are no. of provinces. 
        // we know if a graph is fully connected then one single dfs/bfs call from any source will cover everyone.
        // so basically the no. of calls needed in total will be the no. of components and hence our answer.

        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < isConnected.length; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i< isConnected.length; i++){
            for(int j = 0; j< isConnected[0].length; j++){
                if(isConnected[i][j] == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        // Perform BFS. Then convert it to multipart BFS.
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[isConnected.length];

        for(int i = 0; i< isConnected.length; i++){
            if(visited[i]!=1){
                bfs(q, adjList, visited, i);
                ans++;
            }
        }
        return ans;
    }

    public void bfs(Queue<Integer> q, List<List<Integer>> adjList, int[] visited, int src){
        q.offer(src);
        visited[src] = 1;
        while(!q.isEmpty()){
                int node = (int) q.poll();
                for(int neighbour : adjList.get(node)){
                    if(visited[neighbour] != 1){
                        visited[neighbour] = 1;
                        q.offer(neighbour);
                    }
                }
            }
    }
}