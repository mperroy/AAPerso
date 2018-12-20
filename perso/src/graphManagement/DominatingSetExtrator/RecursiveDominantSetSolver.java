package graphManagement.DominatingSetExtrator;

import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;
import graphManagement.Vertex;

import java.util.Vector;

public class RecursiveDominantSetSolver extends ExactDominantSetSolver {
    public RecursiveDominantSetSolver(Graph<Vertex, Edge> g) {
        super(g);
    }

    public boolean hasDominatingSet(int maxSize)
    {
        getJSONInstanceOrDie();
        System.out.println("Beginning recursive solving");


        Vector<Vertex> currentSet = new Vector<>();
        return this.hasDominatingSetRecursive(maxSize, currentSet);
    }


    protected boolean hasDominatingSetRecursive(int size, Vector<Vertex> currentSet) {
        if (size <= 0)
            return false;

        Vector<Vertex> currentlyDominated = new Vector<>();
        for (Vertex chosen : currentSet) {
            Vector<Vertex> tmp = new Vector<>();

            for (Object e : g.edgesOf(chosen)) {
                Vertex t = (Vertex) ((Edge) e).getTarget();
                Vertex s = (Vertex) ((Edge) e).getSource();
                if(!tmp.contains(t))
                    tmp.add(t);
                if(!tmp.contains(s))
                    tmp.add(s);
            }

            for (Vertex tmpV : tmp) {
                if (!currentlyDominated.contains(tmpV))
                    currentlyDominated.add(tmpV);
            }

            if (!currentlyDominated.contains(chosen))
                currentlyDominated.add(chosen);
        }

        if (currentlyDominated.size() == g.vertexSet().size() && BasicGraphBuilder.allIntersected(currentSet)) {
            dominatingSet.addAll(currentSet);
            return true;
        }


        for (Vertex v : verticesSet) {
                currentSet.add(v);
                if (hasDominatingSetRecursive(size - 1, currentSet))
                    return true;
                currentSet.remove(v);

        }

        return false;
    }

}
