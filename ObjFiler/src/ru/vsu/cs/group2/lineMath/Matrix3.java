package ru.vsu.cs.group2.lineMath;


import java.util.Arrays;

public class Matrix3 implements Matrix {

    Vector3[] vector3s = new Vector3[3];

    public Matrix3(Vector3 vector1, Vector3 vector2, Vector3 vector3) {
        vector3s[0] = vector1;
        vector3s[1] = vector2;
        vector3s[2] = vector3;
    }

    public Matrix3() {
        Vector3 vector3 = new Vector3(0, 0, 0);
        Arrays.fill(vector3s, vector3);
    }

    public Matrix3 unitMatrix() {
        Vector3 vector1 = new Vector3(1, 0, 0);
        Vector3 vector2 = new Vector3(0, 1, 0);
        Vector3 vector3 = new Vector3(0, 0, 1);
        return new Matrix3(vector1, vector2, vector3);
    }

    @Override
    public Matrix3 add(Matrix matrix) throws Exception {
        Vector3[] vectors = ((Matrix3) matrix).getArr();
        return new Matrix3(
                vector3s[0].add(vectors[0]),
                vector3s[1].add(vectors[1]),
                vector3s[2].add(vectors[2]));
    }

    @Override
    public Matrix3 deduct(final Matrix matrix) throws Exception {
        Matrix3 matrix3 = (Matrix3) matrix;
        return new Matrix3(
                vector3s[0].deduct(matrix3.vector3s[0]),
                vector3s[1].deduct(matrix3.vector3s[1]),
                vector3s[2].deduct(matrix3.vector3s[2]));
    }

    @Override
    public Vector3 multiplyOnVector(final Vector vector) throws Exception {
        Vector3 vector3 = (Vector3) vector;
        return new Vector3(
                vector3s[0].scalarMultiply(vector3),
                vector3s[1].scalarMultiply(vector3),
                vector3s[2].scalarMultiply(vector3));
    }

    @Override
    public Matrix3 transposition() {
        return new Matrix3(
                new Vector3(vector3s[0].x, vector3s[1].x, vector3s[2].x),
                new Vector3(vector3s[0].y, vector3s[1].y, vector3s[2].y),
                new Vector3(vector3s[0].z, vector3s[1].z, vector3s[2].z));
    }

    @Override
    public Vector3[] getArr() {
        return vector3s;
    }

    @Override
    public String toString() {
        return "Matrix3 {" + "\n"
                + vector3s[0].x + " " + vector3s[0].y + " " + vector3s[0].z + "\n"
                + vector3s[1].x + " " + vector3s[1].y + " " + vector3s[1].z + "\n"
                + vector3s[2].x + " " + vector3s[2].y + " " + vector3s[2].z + "\n"
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix3 matrix3 = (Matrix3) o;
        return Arrays.equals(vector3s, matrix3.vector3s);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector3s);
    }
}
