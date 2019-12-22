package com.egervan.natera;

import com.egervan.natera.model.Edge;
import com.egervan.natera.model.Vertex;

public class UnDirectedGraph extends DirectedGraph {
    @Override
    boolean isLeadingVertex(Edge edge, Vertex vertex) {
        return vertex.equals(edge.getFirstVertex()) || vertex.equals(edge.getSecondVertex());
    }

    @Override
    boolean isDependentVertex(Edge edge, Vertex vertex) {
        return vertex.equals(edge.getFirstVertex()) || vertex.equals(edge.getSecondVertex());
    }

    @Override
    Vertex getDependentVertex(Edge edge, Vertex vertex) {
        final Vertex firstVertex = edge.getFirstVertex();
        final Vertex secondVertex = edge.getSecondVertex();

        if (vertex.equals(firstVertex)) {
            return secondVertex;
        } else if (vertex.equals(secondVertex)) {
            return firstVertex;
        }

        return null;
    }
}
