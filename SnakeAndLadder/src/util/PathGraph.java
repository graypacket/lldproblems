package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathGraph {
    Map<Integer, List<Integer>> graph;

    public PathGraph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(int start, int end) {
        if(!this.graph.containsKey(start)) this.graph.put(start, new ArrayList<>());
        this.graph.get(start).add(end);
    }

    private boolean detectCycle(int node, Set<Integer> parents) {
        parents.add(node);
        if(graph.containsKey(node))
            for(int child : graph.get(node)) {
                if(parents.contains(child)) return true;
                if(detectCycle(child, parents)) return true;
            }
        parents.remove(node);
        return false;
    }

    public boolean isCyclic() {
        Set<Integer> visited = new HashSet<>();
        
        for(Map.Entry<Integer, List<Integer>> edge : graph.entrySet()) {
            int currNode = edge.getKey();
            if(!visited.contains(currNode)) {
                if (detectCycle(currNode, new HashSet<>())) return true;
                visited.add(currNode);
            }
        }
        
        return false;
    }
}
