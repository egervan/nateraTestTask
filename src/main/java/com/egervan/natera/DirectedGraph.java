package com.egervan.natera;

import com.egervan.natera.model.Edge;
import com.egervan.natera.model.Vertex;

public class DirectedGraph extends AbstractGraph {
    @Override
    boolean isLeadingVertex(Edge edge, Vertex vertex) {
        return edge.getFirstVertex().equals(vertex);
    }

    @Override
    boolean isDependentVertex(Edge edge, Vertex vertex) {
        return edge.getSecondVertex().equals(vertex);
    }

    @Override
    Vertex getDependentVertex(Edge edge, Vertex vertex) {
        return edge.getSecondVertex();
    }
}
