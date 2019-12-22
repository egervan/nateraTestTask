package com.egervan.natera.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Edge {
    @NonNull
    private final Vertex firstVertex;
    @NonNull
    private final Vertex secondVertex;
}
