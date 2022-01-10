package ru.vsu.cs.vadim;

import ru.vsu.cs.group2.lineMath.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineMathTest {

    @Test
    public void notEquals() {
        Assertions.assertNotEquals(new Vector3(1, 2, 3), new Vector3(3, 2, 1));
    }

    @Test
    public void checkLength() throws Exception {
        Assertions.assertEquals(new Vector3(5, 5, 5).normalize().length(), 1);
    }

    @Test
    public void checkNormalize() throws Exception {
        Assertions.assertEquals(new Vector2(0, 2).normalize(), new Vector2(0, 1));
        System.out.println(new Vector2(0, 2).normalize());
    }

    @Test
    public void addOfVerctors() throws Exception {
        Vector3 vector1 = new Vector3(4, 6, 3);
        Vector3 vector2 = new  Vector3(1, 2, 1);
        System.out.println("vector1" + vector1);
        System.out.println("vector2" + vector2);
        Vector3 vector3 = vector2.add(new Vector3(3, 4, 2));
        Assertions.assertEquals(vector1, vector3);
        System.out.println("vector2 after add: " + vector3);
    }

    @Test
    public void checkMultiply() throws Exception {
        Assertions.assertEquals(new Vector4(6, 6, 3, 9), new Vector4(2, 2, 1, 3).multiply(3));
    }

    @Test
    public void checkScalarMultiply() throws Exception {
        Assertions.assertEquals(new Vector3(2, 3, 1).scalarMultiply(new Vector3(4, 1, 1)), 12);
    }

    @Test
    public void testDivide() throws Exception {
        Assertions.assertEquals(new Vector3(5, 10, 6).divide(2), new Vector3(2.5, 5, 3));
    }

    @Test
    public void testTransposition()  {
        Matrix3 matrix = new Matrix3(new Vector3(7, 2, 1),
                new Vector3(5, 3, 8),
                new Vector3(2, 4, 6));
        System.out.println(matrix);
        Matrix3 matrix2 = new Matrix3(new Vector3(7, 5, 2),
                new Vector3(2, 3, 4),
                new Vector3(1, 8, 6));
        System.out.println(matrix2);
        Assertions.assertEquals(matrix.transposition(), matrix2);
        System.out.println(matrix);

    }

    @Test
    public void testAddMatrix() throws Exception {
        Matrix3 matrix = new Matrix3(new Vector3(5, 1, 7),
                new Vector3(6, 3, 1),
                new Vector3(8, 5, 2));
        Matrix3 matrix2 = new Matrix3(new Vector3(2, 1, 7),
                new Vector3(3, 2, 1),
                new Vector3(1, 5, 3));
        Matrix3 matrix3 = new Matrix3(new Vector3(7, 2, 14),
                new Vector3(9, 5, 2),
                new Vector3(9, 10, 5));
        System.out.println(matrix.add(matrix2));
        System.out.println(matrix3);
        Assertions.assertEquals(matrix.add(matrix2), matrix3);
    }

    @Test
    public void testVectorMultiply() throws Exception { //fall data
        Assertions.assertEquals(new Vector3(4, 2, 1).vectorMultiply(new Vector3(2, 1, 2)), new Vector3(3, -6, 0));
    }

    @Test
    public void testMatrixDeduct() throws Exception {
        Matrix4 matrix = new Matrix4(new Vector4(5, 1, 7, 2),
                new Vector4(6, 3, 1, 2),
                new Vector4(8, 5, 2, 6),
                new Vector4(1, 4, 8, 4));
        Matrix4 matrix2 = new Matrix4(new Vector4(4, 0, 6, 1),
                new Vector4(5, 2, 0, 1),
                new Vector4(7, 4, 1, 5),
                new Vector4(0, 3, 7, 3));
        Matrix4 matrix3 = new Matrix4(new Vector4(1, 1, 1, 1),
                new Vector4(1, 1, 1, 1),
                new Vector4(1, 1, 1, 1),
                new Vector4(1, 1, 1, 1));
        System.out.println(matrix3);
        Assertions.assertEquals(matrix.deduct(matrix2), matrix3);
    }

    @Test
    public void testMultiplyOnVector() throws Exception {
        //falled
        Matrix3 matrix = new Matrix3(new Vector3(5, 1, 7),
                new Vector3(6, 3, 1),
                new Vector3(8, 5, 2));
        Vector3 vector3 = new Vector3(25, 20, 30); // специальная ошибка, (х = 26);
        Assertions.assertEquals(matrix.multiplyOnVector(new Vector3(2, 2, 2)), vector3);
    }


}
