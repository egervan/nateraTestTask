package com.egervan.natera;

import com.egervan.natera.model.Edge;
import com.egervan.natera.model.Vertex;

import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.singleton;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

public abstract class AbstractGraph {
    private final Set<Vertex> vertexSet = new HashSet<>();
    private final Set<Edge> edgeSet = new HashSet<>();

    public Vertex addVertex(Vertex vertex) {
        vertexSet.add(vertex);

        return vertex;
    }

    public Edge addEdge(Edge edge) {
        checkVertexAvailability(edge.getFirstVertex(), edge.getSecondVertex());
        edgeSet.add(edge);

        return edge;
    }

    public List<Edge> getPath(Vertex fromVertex, Vertex toVertex) {
        if (!vertexSet.contains(fromVertex) || !vertexSet.contains(toVertex)) {
            return null;
        }
        if (fromVertex.equals(toVertex)) {
            return new LinkedList<>();
        }

        final List<Edge> vertexEdges = getVertexEdges(fromVertex);
        final List<Edge> visitedEdges = new ArrayList<>();
        return getPathsForVertex(fromVertex, toVertex, vertexEdges, visitedEdges);
    }

    private List<Edge> getVertexEdges(Vertex vertex) {
        return edgeSet.stream()
                .filter(edge -> isLeadingVertex(edge, vertex))
                .collect(toList());
    }

    private List<Edge> getPathsForVertex(Vertex vertexFrom, Vertex vertexTo, List<Edge> edges, List<Edge> visitedEdges) {
        for (Edge edge : edges) {
            if (visitedEdges.contains(edge)) {
                continue;
            }
            visitedEdges.add(edge);

            final Vertex dependentVertex = getDependentVertex(edge, vertexFrom);
            if (isDependentVertex(edge, vertexTo)) {
                return new LinkedList<>(singleton(edge));
            } else {
                final List<Edge> neighborEdges = getVertexEdges(dependentVertex);
                final List<Edge> pathToVertex = getPathsForVertex(dependentVertex, vertexTo, neighborEdges, visitedEdges);

                if (nonNull(pathToVertex)) {
                    pathToVertex.add(0, edge);
                    return pathToVertex;
                }
            }
        }

        return null;
    }

    private void checkVertexAvailability(Vertex... vertices) {
        Arrays.stream(vertices)
                .filter(vertex -> !vertexSet.contains(vertex))
                .findAny()
                .ifPresent(vertex -> {
                    throw new IllegalArgumentException(format("Graph doesn't contains vertex: %s", vertex));
                });
    }

    abstract boolean isLeadingVertex(Edge edge, Vertex vertex);
    abstract boolean isDependentVertex(Edge edge, Vertex vertex);
    abstract Vertex getDependentVertex(Edge edge, Vertex vertex);
}