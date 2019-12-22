package com.egervan.natera;

import com.egervan.natera.model.Edge;
import com.egervan.natera.model.Vertex;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class DirectedGraphTest {
    @Test
    public void getPath() {
        final DirectedGraph directedGraph = new DirectedGraph();
        final Vertex vertex1 = directedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = directedGraph.addVertex(new Vertex<>(2));
        final Vertex vertex3 = directedGraph.addVertex(new Vertex<>(3));
        final Vertex vertex4 = directedGraph.addVertex(new Vertex<>(4));
        final Vertex vertex5 = directedGraph.addVertex(new Vertex<>(5));
        final Vertex vertex6 = directedGraph.addVertex(new Vertex<>(6));

        directedGraph.addEdge(new Edge(vertex1, vertex3));
        directedGraph.addEdge(new Edge(vertex3, vertex4));
        directedGraph.addEdge(new Edge(vertex4, vertex5));
        directedGraph.addEdge(new Edge(vertex5, vertex2));
        directedGraph.addEdge(new Edge(vertex3, vertex6));

        final List<Edge> result1 = directedGraph.getPath(vertex1, vertex2);
        final List<Edge> expected1 = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex4), new Edge(vertex4, vertex5), new Edge(vertex5, vertex2));

        assertEquals(expected1, result1);

        final List<Edge> result2 = directedGraph.getPath(vertex1, vertex6);
        final List<Edge> expected2 = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex6));

        assertEquals(expected2, result2);

    }

    @Test
    public void getPath_customVertexValue() {
        final DirectedGraph directedGraph = new DirectedGraph();
        final Vertex vertex1 = directedGraph.addVertex(new Vertex<>(new CustomType("first")));
        final Vertex vertex2 = directedGraph.addVertex(new Vertex<>(new CustomType("second")));
        final Vertex vertex3 = directedGraph.addVertex(new Vertex<>(new CustomType("third")));
        final Vertex vertex4 = directedGraph.addVertex(new Vertex<>(new CustomType("fourth")));
        final Vertex vertex5 = directedGraph.addVertex(new Vertex<>(new CustomType("fifth")));
        final Vertex vertex6 = directedGraph.addVertex(new Vertex<>(new CustomType("sixth")));

        directedGraph.addEdge(new Edge(vertex1, vertex3));
        directedGraph.addEdge(new Edge(vertex3, vertex4));
        directedGraph.addEdge(new Edge(vertex4, vertex5));
        directedGraph.addEdge(new Edge(vertex5, vertex2));
        directedGraph.addEdge(new Edge(vertex3, vertex6));

        final List<Edge> result = directedGraph.getPath(vertex1, vertex2);
        final List<Edge> expected = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex4), new Edge(vertex4, vertex5), new Edge(vertex5, vertex2));

        assertEquals(expected, result);

        final List<Edge> result2 = directedGraph.getPath(vertex1, vertex6);
        final List<Edge> expected2 = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex6));

        assertEquals(expected2, result2);
    }

    @Test
    public void isLeadingVertex() {
        final DirectedGraph directedGraph = new DirectedGraph();
        final Vertex vertex1 = directedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = directedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex1);

        assertTrue(directedGraph.isLeadingVertex(edge, vertex1));
        assertFalse(directedGraph.isLeadingVertex(edge, vertex2));
    }

    @Test
    public void isDependentVertex() {
        final DirectedGraph directedGraph = new DirectedGraph();
        final Vertex vertex1 = directedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = directedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex1);

        assertTrue(directedGraph.isLeadingVertex(edge, vertex1));
        assertFalse(directedGraph.isLeadingVertex(edge, vertex2));
    }

    @Test
    public void getDependentVertex() {
        final DirectedGraph directedGraph = new DirectedGraph();
        final Vertex vertex1 = directedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = directedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex2);

        assertSame(vertex2, directedGraph.getDependentVertex(edge, vertex1));
        assertSame(vertex2, directedGraph.getDependentVertex(edge, vertex2));
    }

    @ToString
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    private static class CustomType {
        private final String customValue;
    }
}