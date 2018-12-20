package graphManagement;

import org.jgrapht.graph.SimpleGraph;

import java.util.Vector;

public class Graph<V, E> extends SimpleGraph<Object, Object> {

	private Vector<Vertex> opponentsVertices;
	private Vector<Vertex> defendersVertices;

	public Graph(Class edgeClass) {
		super(edgeClass);
		opponentsVertices = new Vector<Vertex>();
		defendersVertices = new Vector<Vertex>();
	}

	public boolean addVertex(Object v) {
		if (!v.getClass().equals(Vertex.class))
			return false;
		Vertex vertex = (Vertex) v;
		if (vertex.isDefender())
			defendersVertices.add(vertex);
		else if (vertex.isOpponent())
			opponentsVertices.add(vertex);

		return super.addVertex(v);
	}

	public boolean removeVertex(Vertex v) {
		if (v.getType().equals(VertexType.OPPONENT))
			opponentsVertices.remove(v);
		if (v.getType().equals(VertexType.DEFENDER))
			defendersVertices.remove(v);
		return super.removeVertex(v);
	}

	public Vector<Vertex> getOpponentVertices() {
		return opponentsVertices;
	}
	
	public Vertex getOpponentVertice(Vertex v) {
		return opponentsVertices.get(opponentsVertices.indexOf(v));
	}

	public Vector<Vertex> getDefendersVertices() {
		return defendersVertices;
	}
	
	public Vertex getDefenderVertice(Vertex v) {
		return defendersVertices.get(defendersVertices.indexOf(v));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("defenders").append(defendersVertices).append("\n");
		sb.append("opponents").append(opponentsVertices).append("\n");

		return sb.toString();
	}
}
