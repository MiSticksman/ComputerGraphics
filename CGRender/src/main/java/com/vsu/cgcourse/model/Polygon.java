package com.vsu.cgcourse.model;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<Integer> polygonVertexIndices;
    private ArrayList<Integer> polygonTextureVertexIndices;
    private ArrayList<Integer> polygonNormalIndices;

    public Polygon() {
        polygonVertexIndices = new ArrayList<>();
        polygonTextureVertexIndices = new ArrayList<>();
        polygonNormalIndices = new ArrayList<>();
    }

    public ArrayList<Integer> getPolygonVertexIndices() {
        return polygonVertexIndices;
    }

    public ArrayList<Integer> getPolygonTextureVertexIndices() {
        return polygonTextureVertexIndices;
    }

    public ArrayList<Integer> getPolygonNormalIndices() {
        return polygonNormalIndices;
    }

//    public void recheckOnCorrect(int index) {
//        checkOnSize(index);
//        checkOnCorrectPolygonFill(index);
//        /*if (!polygonTextureVertexIndices.get(index).isEmpty() && !polygonNormalIndices.get(index).isEmpty()) {
//            if (polygonTextureVertexIndices.get(index).size() != polygonNormalIndices.get(index).size()) {
//                throw new ObjReaderException("Different size between TextureVertexIndices and NormalIndices");
//            }
//        }*/
//    }

    public void triangulation() {

    }

//    private void checkOnCorrectPolygonFill(int index) {
//        if (index != 0) {
//            if (polygonTextureVertexIndices.get(0).isEmpty() && !polygonTextureVertexIndices.get(index).isEmpty()) {
//                throw new ObjReaderException("Unexpected TextureVertexIndices");
//            }
//            if (!polygonTextureVertexIndices.get(0).isEmpty() && polygonTextureVertexIndices.get(index).isEmpty()) {
//                throw new ObjReaderException("Can't find TextureVertexIndices");
//            }
//            if (polygonNormalIndices.get(0).isEmpty() && !polygonNormalIndices.get(index).isEmpty()) {
//                throw new ObjReaderException("Unexpected NormalIndices");
//            }
//            if (!polygonNormalIndices.get(0).isEmpty() && polygonNormalIndices.get(index).isEmpty()) {
//                throw new ObjReaderException("Can't find NormalIndices");
//            }
//        }
//    }
}
