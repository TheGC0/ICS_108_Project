package org.ics.flying_stars.game.geometry;


public class Polygon {
    private final Line[] edges;
    private final Point[] vertices;

    public Polygon(Point[] points) {
        if (points.length < 3) {
            throw new UnsupportedOperationException("Polygon cannot have less than 3 points");
        }
        vertices = points;
        edges = new Line[points.length - 1];

        // Create edges
        createEdges();
    }

    private void createEdges() {
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
