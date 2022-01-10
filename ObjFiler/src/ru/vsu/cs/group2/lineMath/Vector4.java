package ru.vsu.cs.group2.lineMath;

import java.util.Objects;

public class Vector4 implements Vector {

    public double x;
    public double y;
    public double z;
    public double g;

    public Vector4(double x, double y, double z, double g) {
        this.x = x;
        this.y = y;
        this.g = g;
    }
    @Override
    public Vector4 add(Vector vector) throws Exception {
        Vector4 vector4 = (Vector4) vector;
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        return new Vector4(this.x + vector4.x, this.y + vector4.y, this.z + vector4.z, this.g + vector4.g);
    }

    @Override
    public Vector4 multiply(double a)  {
        return new Vector4(x * a, y * a, z * a, g * a);
    }

    @Override
    public Vector4 deduct(Vector vector) throws Exception {
        return add(vector.multiply(-1));
    }

    @Override
    public Vector4 divide(double a) throws Exception {
        return multiply(1 / a);
    }

    @Override
    public double scalarMultiply(Vector vector) throws Exception {
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        Vector4 vector4 = (Vector4) vector;
        return this.x * vector4.x + this.y * vector4.y + this.z * vector4.z + this.g * vector4.g;
    }

    @Override
    public String toString() {
        return "Vector4 {" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", g=" + g +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector4 vector4 = (Vector4) o;
        return Double.compare(vector4.x, x) == 0 && Double.compare(vector4.y, y) == 0 && Double.compare(vector4.z, z) == 0 && Double.compare(vector4.g, g) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, g);
    }
}
