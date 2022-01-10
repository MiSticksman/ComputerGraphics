package ru.vsu.cs.group2.lineMath;

import java.util.Objects;

public class Vector2 implements Vector {

    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vector2 add(Vector vector) throws Exception {
        Vector2 vector2 = (Vector2) vector;
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        return new Vector2(this.x + vector2.x, this.y + vector2.y);
    }

    @Override
    public Vector2 multiply(double a)  {
        return new Vector2(x * a,  y * a);
    }

    @Override
    public Vector2 deduct(Vector vector) throws Exception {
        return add(vector.multiply(-1));
    }

    @Override
    public Vector2 divide(double a) throws Exception {
        return multiply(1 / a);
    }

    @Override
    public double scalarMultiply(Vector vector) throws Exception {
        if (vector.getClass() != getClass()) {
            throw  new Exception("invalid vector");
        }
        Vector2 vector2 = (Vector2) vector;
        return this.x * vector2.x + this.y * vector2.y;
    }

    @Override
    public String toString() {
        return "Vector2 {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
