class Solution {
    public int countPaths(int V, int[][] edges) {
        int mod = (int) (1e9 + 7);
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.distanceTillNow, b.distanceTillNow));
        List<List<Edge>> adjList = new ArrayList<>();
        long[] distance = new long[V];
        int[] ways = new int[V];

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList());
            distance[i] = Long.MAX_VALUE;
        }

        // take input into the graph
        for (int i = 0; i < edges.length; i++) {
            int edgeFrom = edges[i][0];
            int edgeTo = edges[i][1];
            int edgeWeight = edges[i][2];

            adjList.get(edgeFrom).add(new Edge(edgeTo, edgeWeight));
            adjList.get(edgeTo).add(new Edge(edgeFrom, edgeWeight));
        }

        // initial state
        pq.offer(new State(0, 0));
        distance[0] = 0;
        ways[0] = 1;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            long distanceTillNow = state.distanceTillNow;
            int vertex = state.vertex;

            for (Edge edge : adjList.get(vertex)) {
                long potentialBetterDistance = (distanceTillNow + edge.weight);
                // This means we have got a better distance for the first time
                if (potentialBetterDistance < distance[edge.to]) {
                    distance[edge.to] = potentialBetterDistance;
                    ways[edge.to] = ways[vertex] % mod;
                    pq.offer(new State(potentialBetterDistance, edge.to));
                }
                // means we reached the node not for the first time, but with same distance, so add more ways to it.
                else if (potentialBetterDistance == distance[edge.to]) {
                    ways[edge.to] = (ways[edge.to] + ways[vertex]) % mod;
                }
            }
        }

        return ways[V - 1] % mod;
    }

    class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    class State {
        long distanceTillNow;
        int vertex;

        public State(long distanceTillNow, int vertex) {
            this.distanceTillNow = distanceTillNow;
            this.vertex = vertex;
        }
    }
}