package org.ics.flying_stars.game.engine.geometry;


public class Polygon {
    protected final Line[] edges;
    protected final Point[] vertices;

    public Polygon(Point[] points) {
        if (points.length < 3) {
            throw new UnsupportedOperationException("Polygon cannot have less than 3 points");
        }
        vertices = points;
        edges = new Line[points.length];

        // Create edges
        createEdges();
    }

    protected void createEdges() {
        Point startPoint = vertices[0];
        Point endPoint = vertices[vertices.length - 1];
        // Create edge that connects first and last points
        edges[0] = new Line(startPoint, endPoint);

        // Create remaining edges
        for (int i=1; i < vertices.length; i++) {
            endPoint = vertices[i];
            edges[i] = new Line(startPoint, endPoint);
            startPoint = endPoint;
        }

    }

    public Line[] getEdges() {
        return edges;
    }

    public Point[] getVertices() {
        return vertices;
    }
}
