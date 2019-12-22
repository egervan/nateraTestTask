package com.egervan.natera;

import com.egervan.natera.model.Edge;
import com.egervan.natera.model.Vertex;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class UnDirectedGraphTest {
    @Test
    public void getPath() {
        final UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
        final Vertex vertex1 = unDirectedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = unDirectedGraph.addVertex(new Vertex<>(2));
        final Vertex vertex3 = unDirectedGraph.addVertex(new Vertex<>(3));
        final Vertex vertex4 = unDirectedGraph.addVertex(new Vertex<>(4));
        final Vertex vertex5 = unDirectedGraph.addVertex(new Vertex<>(5));
        final Vertex vertex6 = unDirectedGraph.addVertex(new Vertex<>(6));

        unDirectedGraph.addEdge(new Edge(vertex1, vertex3));
        unDirectedGraph.addEdge(new Edge(vertex3, vertex4));
        unDirectedGraph.addEdge(new Edge(vertex4, vertex5));
        unDirectedGraph.addEdge(new Edge(vertex5, vertex2));
        unDirectedGraph.addEdge(new Edge(vertex3, vertex6));

        //from vertex1 to vertex2. Forward direction
        final List<Edge> result1 = unDirectedGraph.getPath(vertex1, vertex2);
        final List<Edge> expected1 = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex4), new Edge(vertex4, vertex5), new Edge(vertex5, vertex2));

        assertEquals(expected1, result1);

        //from vertex2 to vertex1. Reverse direction
        final List<Edge> result2 = unDirectedGraph.getPath(vertex2, vertex1);
        final List<Edge> expected2 = asList(new Edge(vertex5, vertex2), new Edge(vertex4, vertex5), new Edge(vertex3, vertex4), new Edge(vertex1, vertex3));

        assertEquals(expected2, result2);

        //from vertex1 to vertex6. Forward direction
        final List<Edge> result3 = unDirectedGraph.getPath(vertex1, vertex6);
        final List<Edge> expected3 = asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex6));

        assertEquals(expected3, result3);

        //from vertex6 to vertex1. Reverse direction
        final List<Edge> result4 = unDirectedGraph.getPath(vertex6, vertex1);
        final List<Edge> expected4 = asList(new Edge(vertex3, vertex6), new Edge(vertex1, vertex3));

        assertEquals(expected4, result4);
    }

    @Test
    public void isLeadingVertex() {
        final DirectedGraph unDirectedGraph = new UnDirectedGraph();
        final Vertex vertex1 = unDirectedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = unDirectedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex2);

        assertTrue(unDirectedGraph.isLeadingVertex(edge, vertex1));
        assertTrue(unDirectedGraph.isLeadingVertex(edge, vertex2));
    }

    @Test
    public void isDependentVertex() {
        final UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
        final Vertex vertex1 = unDirectedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = unDirectedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex2);

        assertTrue(unDirectedGraph.isLeadingVertex(edge, vertex1));
        assertTrue(unDirectedGraph.isLeadingVertex(edge, vertex2));
    }

    @Test
    public void getDependentVertex() {
        final UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
        final Vertex vertex1 = unDirectedGraph.addVertex(new Vertex<>(1));
        final Vertex vertex2 = unDirectedGraph.addVertex(new Vertex<>(2));
        final Edge edge = new Edge(vertex1, vertex2);

        assertSame(vertex2, unDirectedGraph.getDependentVertex(edge, vertex1));
        assertSame(vertex1, unDirectedGraph.getDependentVertex(edge, vertex2));
    }
}
