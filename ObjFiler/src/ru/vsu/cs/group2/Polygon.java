package ru.vsu.cs.group2;

import java.util.ArrayList;
import java.util.Objects;

public class Polygon {


    public final ArrayList<Integer> polygonVertexes = new ArrayList<>();
    public final ArrayList<Integer> polygonTextureVertexes = new ArrayList<>();
    public final ArrayList<Integer> polygonNormalVertexes = new ArrayList<>();

    private int maxVertex = 0;
    private int maxTextureVertex = 0;
    private int maxNormal = 0;


    public Polygon() {
    }

    public void addToPolygon (int indexV, int indexVt,  int indexVn) {
        if (indexVt > 0) {
            if (polygonTextureVertexes.size() == 0 && polygonVertexes.size() != 0) {
                throw new PolygonException("TextureVertexes are specified only for a part of polygons.", indexVt);
            }
            polygonTextureVertexes.add(indexVt);
            if (indexVt > maxTextureVertex) {
                maxTextureVertex = indexVt;
            }
        } else {
            if (polygonTextureVertexes.size() != 0 && polygonVertexes.size() != 0) {
                throw new PolygonException("TextureVertexes are specified only for a part of polygons.", indexVt);
            }
        }
        if (indexVn > 0) {
            if (polygonNormalVertexes.size() == 0 && polygonVertexes.size() != 0) {
                throw new PolygonException("NormalVertexes are specified only for a part of polygons.", indexVn);
            }
            polygonNormalVertexes.add(indexVn);
            if (indexVn > maxNormal) {
                maxNormal = indexVt;
            }
        } else {
            if (polygonNormalVertexes.size() != 0 && polygonVertexes.size() != 0) {
                throw new PolygonException("NormalVertexes are specified only for a part of polygons.", indexVn);
            }
        }
        polygonVertexes.add(indexV);
        if (indexV > maxVertex) {
            maxVertex = indexV;
        }
    }

    public int getMaxVertex() {
        return maxVertex;
    }

    public int getMaxTextureVertex() {
        return maxTextureVertex;
    }

    public int getMaxNormal() {
        return maxNormal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return Objects.equals(((Polygon) o).polygonVertexes, polygon.polygonVertexes)
                && Objects.equals(((Polygon) o).polygonTextureVertexes, polygon.polygonTextureVertexes)
                && Objects.equals(((Polygon) o).polygonNormalVertexes, polygon.polygonNormalVertexes);
    }

}
