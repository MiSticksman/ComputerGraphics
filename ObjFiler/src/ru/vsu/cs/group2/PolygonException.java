package ru.vsu.cs.group2;

 class PolygonException  extends  RuntimeException {
     public PolygonException(String errorMessage, int lineInd) {
         super("Error parsing OBJ file on line: " + lineInd + ". " + errorMessage);
     }
    
} 
