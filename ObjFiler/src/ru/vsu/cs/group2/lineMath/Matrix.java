package ru.vsu.cs.group2.lineMath;


public interface Matrix {

    Matrix add(Matrix matrix) throws Exception;

    Matrix deduct(Matrix matrix) throws Exception;

    Vector multiplyOnVector(Vector vector) throws Exception;

    Matrix transposition();

    Vector[] getArr();


}
