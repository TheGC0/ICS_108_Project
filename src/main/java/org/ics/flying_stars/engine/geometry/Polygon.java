package org.ics.flying_stars.engine.geometry;


public class Polygon {
    protected final Line[] edges;
    protected final Vector2D[] vertices;

    public Polygon(Vector2D[] points) {
        if (points.length < 3) {
            throw new UnsupportedOperationException("Polygon cannot have less than 3 points");
        }
        vertices = points;
        edges = new Line[points.length];

        // Create edges
        createEdges();
    }

    protected void createEdges() {
        Vector2D startPoint = vertices[0];
        Vector2D endPoint = vertices[vertices.length - 1];
        // Create edge that connects first and last points
        edges[0] = new Line(startPoint, endPoint);

        // Create remaining edges
        for (int i=1; i < vertices.length; i++) {
            endPoint = vertices[i];
            edges[i] = new Line(startPoint, endPoint);
            startPoint = endPoint;
        }

    }

    public void rotate(double radians) {
        for (Vector2D vertex: vertices) {
            vertex.rotate(radians);
        }
    }

    public Line[] getEdges() {
        return edges;
    }

    public Vector2D[] getVertices() {
        return vertices;
    }
}
