package ru.vsu.cs.group2;

import ru.vsu.cs.group2.filesWorking.objReader.ObjReaderException;

import java.util.ArrayList;


public class Model {

    public static final String OBJ_VERTEX_TOKEN = "v";
    public static final String OBJ_TEXTURE_TOKEN = "vt";
    public static final String OBJ_NORMAL_TOKEN = "vn";
    public static final String OBJ_POLYGON_TOKEN = "f";

    public final ArrayList<Vector3f> vertices = new ArrayList<>();
    public final ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public final ArrayList<Vector3f> normals = new ArrayList<>();

    public final ArrayList<Polygon> polygons = new ArrayList<>();

    public Model() {
    }

    public int getCountOfVertices() {
        return vertices.size();
    }

    public int getCountOfTextureVertices() {
        return  textureVertices.size();
    }

    public int getCountOfNormals() {
        return normals.size();
    }

    public void addVertices(Vector3f vector3f) {
        vertices.add(vector3f);
    }

    public void addTextureVertices(Vector2f vector2f) {
        textureVertices.add(vector2f);
    }

    public void addNormals(Vector3f vector3f) {
        normals.add(vector3f);
    }

    public void addPolygon(Polygon polygon) {
        if (getCountOfVertices() < polygon.getMaxVertex()) {
            throw new ObjReaderException("Max index of vertexes: ", getCountOfVertices());
        }
        if (getCountOfTextureVertices() < polygon.getMaxTextureVertex()) {
            throw new ObjReaderException("Max index of textureVertexes: ", getCountOfTextureVertices());
        }
        if (getCountOfNormals() < polygon.getMaxNormal()) {
            throw new ObjReaderException("Max index of normals: ", getCountOfNormals());
        }
        polygons.add(polygon);
    }
}
