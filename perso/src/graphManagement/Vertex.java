package graphManagement;

import IOManager.InputJSON;

import java.awt.geom.Point2D;

public class Vertex {
	public Point2D.Double location;
	private VertexType type;
	private boolean isOnIntersection = false;
	private int degree;

	public Vertex(double x, double y) {
		this.location = new Point2D.Double(x, y);
		this.type = VertexType.DEFENDER;
		this.isOnIntersection = false;
		this.degree = 0;
	}

	public Vertex(Point2D.Double p) {
		this(p.getX(), p.getY());
	}

	public Vertex(Point2D.Double p, VertexType type) {
		this(p);
		this.type = type;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public VertexType getType() {
		return type;
	}

	public void setType(VertexType type) {
		this.type = type;
	}

	public void setOnIntersection(boolean isOnIntersection) {
		this.isOnIntersection = isOnIntersection;
	}

	public boolean isDefender() {
		return (this.getType() == VertexType.DEFENDER);
	}

	public boolean isOpponent() {
		return (this.getType() == VertexType.OPPONENT);
	}

	public void setDefender() {
		type = VertexType.DEFENDER;
	}

	public void setOpponent() {
		this.type = VertexType.OPPONENT;
	}

	public boolean isOnIntersection() {
		return isOnIntersection;
	}

	public void intersectAShoot() {
		isOnIntersection = true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Type : ").append(type).append("\n");
		sb.append("Location : ").append(location).append("\n");

		return sb.toString();
	}

	public boolean hasSameLocation(Vertex other)
	{
		double dist = Math.pow(location.getX()-other.location.getX(), 2) + Math.pow(location.getY()-other.location.getY(),2);
		dist = Math.sqrt(dist);
		double minDist = InputJSON.getInstance().getRobotRadius()*2;
		return dist < minDist;
	}

}
