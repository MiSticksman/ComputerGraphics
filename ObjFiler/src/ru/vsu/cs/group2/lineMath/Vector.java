package ru.vsu.cs.group2.lineMath;

public interface Vector {

     Vector add(Vector vector) throws Exception;

     Vector multiply(double a);

     Vector deduct(Vector vector) throws Exception;

     default Vector divide(double a) throws Exception {
         return multiply(1 / a);
     }

    double scalarMultiply(Vector vector) throws Exception;

     default double length() throws Exception {
         return (Math.sqrt(scalarMultiply(this)));
     }

    default Vector normalize() throws Exception {
         return divide(length());
    }
}
