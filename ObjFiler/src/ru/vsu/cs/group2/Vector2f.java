package ru.vsu.cs.group2;

public class Vector2f {

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public final double x, y;

    @Override
    public boolean equals(Object o) {
        double eps = 1e-7f;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector2f other = (Vector2f) o;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps;
    }
}
