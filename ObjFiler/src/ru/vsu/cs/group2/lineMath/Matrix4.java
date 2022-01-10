package ru.vsu.cs.group2.lineMath;

import java.util.Arrays;

public class Matrix4 implements Matrix {

    private Vector4[] vector4s = new Vector4[4];

    public Matrix4(Vector4 vector1, Vector4 vector2, Vector4 vector3, Vector4 vector4) {
        vector4s[0] = vector1;
        vector4s[1] = vector2;
        vector4s[2] = vector3;
        vector4s[3] = vector4;
    }

    public Matrix4() {
        Vector4 vector4 = new Vector4(0, 0, 0, 0);
        Arrays.fill(vector4s, vector4);
    }

    public Matrix4 unitMatrix() {
        Vector4 vector1 = new Vector4(1, 0, 0, 0);
        Vector4 vector2 = new Vector4(0, 1, 0, 0);
        Vector4 vector3 = new Vector4(0, 0, 1, 0);
        Vector4 vector4 = new Vector4(0, 0, 0, 1);
        return new Matrix4(vector1, vector2, vector3, vector4);
    }

    @Override
    public Matrix4 add(Matrix matrix) throws Exception {
        Vector4[] vectors = ((Matrix4) matrix).getArr();
        return new Matrix4(
                vector4s[0].add(vectors[0]),
                vector4s[1].add(vectors[1]),
                vector4s[2].add(vectors[2]),
                vector4s[3].add(vectors[3]));
    }

    @Override
    public Matrix4 deduct(Matrix matrix) throws Exception {
        Matrix4 matrix4 = (Matrix4) matrix;
        return new Matrix4(
                vector4s[0].deduct(matrix4.vector4s[0]),
                vector4s[1].deduct(matrix4.vector4s[1]),
                vector4s[2].deduct(matrix4.vector4s[2]),
                vector4s[3].deduct(matrix4.vector4s[3]));
    }

    @Override
    public Vector4 multiplyOnVector(final Vector vector) throws Exception {
        Vector4 vector4 = (Vector4) vector;
        return new Vector4(
                vector4s[0].scalarMultiply(vector4),
                vector4s[1].scalarMultiply(vector4),
                vector4s[2].scalarMultiply(vector4),
                vector4s[3].scalarMultiply(vector4));
    }

    @Override
    public Matrix4 transposition() {
        return new Matrix4(
                new Vector4(vector4s[0].x, vector4s[1].x, vector4s[2].x, vector4s[3].x),
                new Vector4(vector4s[0].y, vector4s[1].y, vector4s[2].y, vector4s[3].y),
                new Vector4(vector4s[0].z, vector4s[1].z, vector4s[2].z, vector4s[3].z),
                new Vector4(vector4s[0].g, vector4s[1].g, vector4s[2].g, vector4s[3].g) );
    }

    @Override
    public Vector4[] getArr() {
        return vector4s;
    }

    @Override
    public String toString() {
        return "Matrix4 {" + "\n"
                + vector4s[0].x + " " + vector4s[0].y + " " + vector4s[0].z + " " + vector4s[0].g + "\n"
                + vector4s[1].x + " " + vector4s[1].y + " " + vector4s[1].z + " " + vector4s[1].g + "\n"
                + vector4s[2].x + " " + vector4s[2].y + " " + vector4s[2].z + " " + vector4s[2].g +  "\n"
                + vector4s[3].x + " " + vector4s[3].y + " " + vector4s[3].z + " " + vector4s[3].g +  "\n"
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix4 matrix4 = (Matrix4) o;
        return Arrays.equals(vector4s, matrix4.vector4s);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector4s);
    }
}

