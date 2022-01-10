package ru.vsu.cs.group2.filesWorking.objWriter;

import ru.vsu.cs.group2.Model;
import ru.vsu.cs.group2.Polygon;
import ru.vsu.cs.group2.Vector2f;
import ru.vsu.cs.group2.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ObjWriter {


    public static void modelToFile(Model model, String filename) throws FileNotFoundException {
        if (!filename.toLowerCase().endsWith(".obj")) {
            filename += ".obj";
        }
        File file = new File(filename);
        PrintWriter out = new PrintWriter(file);


        verticesToFile(out, model.OBJ_VERTEX_TOKEN, model.vertices);
        out.println();

        textureVerticesToFile(out, model.OBJ_TEXTURE_TOKEN, model.textureVertices);
        out.println();

        normalsToFile(out, model.OBJ_NORMAL_TOKEN, model.normals);
        out.println();

        polygonsToFile(out, model.OBJ_POLYGON_TOKEN, model.polygons);
    }

    private static void verticesToFile(PrintWriter out, String token, List<Vector3f> vertices) {
        for (Vector3f v : vertices) {
            StringBuilder sb = new StringBuilder();
            sb.append(token + " ");
            sb.append(String.format("%.4f %.4f %.4f", v.x, v.y, v.z));
            out.println(sb);
        }
    }

    private static void textureVerticesToFile(PrintWriter out, String token, List<Vector2f> textureVertices) {
        for (Vector2f v : textureVertices) {
            StringBuilder sb = new StringBuilder();
            sb.append(token + " ");
            sb.append(String.format("%.4f %.4f", v.x, v.y));
            out.println(sb);
        }
    }

    private static void normalsToFile(PrintWriter out, String token, List<Vector3f> normals) {
        StringBuilder res = new StringBuilder();
        for (Vector3f v : normals) {
            StringBuilder sb = new StringBuilder();
            sb.append(token + " ");
            sb.append(String.format("%.4f %.4f %.4f", v.x, v.y, v.z));
            out.println(sb);
        }
    }

    private static void polygonsToFile(PrintWriter out, String token, List<Polygon> polygons) {
        for (Polygon p : polygons) {
            StringBuilder sb = new StringBuilder();
            sb.append(token + " ");
            for (int i = 0; i < p.polygonVertexes.size(); i++) {
                sb.append(p.polygonVertexes.get(i));
                if (p.polygonTextureVertexes.size() > 0) {
                    sb.append("/" + p.polygonTextureVertexes.get(i));
                } else if (p.polygonNormalVertexes.size() > 0) {
                    sb.append("/");
                }
                if (p.polygonNormalVertexes.size() > 0) {
                    sb.append("/" + p.polygonNormalVertexes.get(i));
                }
                sb.append(" ");
            }
            out.println(sb);
        }
    }

}
