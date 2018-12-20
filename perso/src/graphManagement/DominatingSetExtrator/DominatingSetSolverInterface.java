package graphManagement.DominatingSetExtrator;

import java.util.Vector;

import graphManagement.Vertex;

public interface DominatingSetSolverInterface {

    boolean hasDominatingSet(int maxSize);
    Vector<Vertex> getDominatingSet();
    Vector<Vertex> getVertices();
}
