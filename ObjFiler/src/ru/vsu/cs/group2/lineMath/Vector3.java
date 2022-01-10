package ru.vsu.cs.group2.lineMath;

import java.util.Objects;

public class Vector3 implements Vector {

    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public Vector3 add(Vector vector) throws Exception {
        Vector3 vector3 = (Vector3) vector;
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        return new Vector3(this.x + vector3.x, this.y + vector3.y, this.z + vector3.z);
    }

    @Override
    public Vector3 multiply(double a) {
        return new Vector3(x * a, y * a, z * a);
    }

    @Override
    public Vector3 deduct(Vector vector) throws Exception {
        return add(vector.multiply(-1));
    }

    @Override
    public Vector3 divide(double a) throws Exception {
        return multiply(1 / a);
    }

    @Override
    public double scalarMultiply(Vector vector) throws Exception {
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        Vector3 vector3 = (Vector3) vector;
        return this.x * vector3.x + this.y * vector3.y + this.z * vector3.z;
    }

    public Vector3 vectorMultiply(Vector3 v) {
        return new Vector3(y * v.z - z * v.y, (-1) * (x * v.z - z * v.x), x * v.y - y * v.x);
    }

    @Override
    public String toString() {
        return "Vector3 {" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Double.compare(vector3.x, x) == 0 && Double.compare(vector3.y, y) == 0 && Double.compare(vector3.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
