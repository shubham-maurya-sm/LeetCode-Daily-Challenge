/*
1514. Path with Maximum Probability
Solved
Medium
Topics
Companies
Hint
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

 

Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 

Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.

*/


class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].add(new Pair<>(b, s));
            g[b].add(new Pair<>(a, s));
        }
        PriorityQueue<Pair<Double, Integer>> q
            = new PriorityQueue<>(Comparator.comparingDouble(Pair::getKey));
        double[] d = new double[n];
        d[start] = 1.0;
        q.offer(new Pair<>(-1.0, start));
        while (!q.isEmpty()) {
            Pair<Double, Integer> p = q.poll();
            double w = p.getKey();
            w *= -1;
            int u = p.getValue();
            for (Pair<Integer, Double> ne : g[u]) {
                int v = ne.getKey();
                double t = ne.getValue();
                if (d[v] < d[u] * t) {
                    d[v] = d[u] * t;
                    q.offer(new Pair<>(-d[v], v));
                }
            }
        }
        return d[end];
    }
}

/*
Output:
Case 1

n = 3
edges = [[0,1],[1,2],[0,2]]
succProb = [0.5,0.5,0.2]
start_node = 0
end_node =
2
1
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.2]
0
2
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.3]
0
2

Case 2

n =
3
edges = [[0,1],[1,2],[0,2]]
succProb = [0.5,0.5,0.3]
start_node = 0
end_node =
2
1
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.2]
0
2
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.3]
0
2

Case 3

n = 3
edges = [[0,1]]
succProb = [0.5]
start_node = 0
end_node =
2
1
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.2]
0
2
3
[[0,1],[1,2],[0,2]]
[0.5,0.5,0.3]
0
2





*/
