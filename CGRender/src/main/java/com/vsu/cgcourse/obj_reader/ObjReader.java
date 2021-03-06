package com.vsu.cgcourse.obj_reader;

import com.vsu.cgcourse.math.Vector2;
import com.vsu.cgcourse.math.Vector3;
import com.vsu.cgcourse.model.Mesh;
import com.vsu.cgcourse.model.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ObjReader {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";
    private int count;

    public static Mesh read(String fileContent) {
        Mesh result = new Mesh();

        int lineInd = 0;
        Scanner scanner = new Scanner(fileContent);
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            ArrayList<String> wordsInLine = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
            if (wordsInLine.isEmpty())
                continue;

            final String token = wordsInLine.get(0);
            wordsInLine.remove(0);

            lineInd++;
            switch (token) {
                case OBJ_VERTEX_TOKEN -> result.getVertices().add(parseVertex(wordsInLine, lineInd));
                case OBJ_TEXTURE_TOKEN -> result.getTextureVertices().add(parseTextureVertex(wordsInLine, lineInd));
                case OBJ_NORMAL_TOKEN -> result.getNormals().add(parseNormal(wordsInLine, lineInd));
                case OBJ_FACE_TOKEN -> parseFace(wordsInLine, lineInd, result);
                default -> {
                }
            }
        }
        scanner.close();
        result.recheckModel();
        return result;
    }

    protected static Vector3 parseVertex(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            if (wordsInLineWithoutToken.size() > 3) {
                throw new ObjReaderException("Too many vertex arguments.", lineInd);
            }
            return new Vector3(new float[]{
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1)),
                    Float.parseFloat(wordsInLineWithoutToken.get(2))});
        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few vertex arguments.", lineInd);
        }
    }

    protected static Vector2 parseTextureVertex(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            if (wordsInLineWithoutToken.size() > 2) {
                if (Math.abs(Float.parseFloat(wordsInLineWithoutToken.get(2))) - 0.00000f > 1e-6) {
                    throw new ObjReaderException("Too many texture vertex arguments.", lineInd);
                }
            }
            return new Vector2(new float[]{
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1))});

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few texture vertex arguments.", lineInd);
        }
    }

    protected static Vector3 parseNormal(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            if (wordsInLineWithoutToken.size() > 3) {
                throw new ObjReaderException("Too many normal arguments.", lineInd);
            }
            return new Vector3(new float[]{
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1)),
                    Float.parseFloat(wordsInLineWithoutToken.get(2))});

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few normal arguments.", lineInd);
        }
    }

    protected static void parseFace(
            final ArrayList<String> wordsInLineWithoutToken,
            int lineInd, Mesh mesh) {
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<Integer>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<Integer>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<Integer>();

        for (String s : wordsInLineWithoutToken) {
            parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices, onePolygonNormalIndices, lineInd);
        }

        mesh.getPolygons().addAll(triangulate(onePolygonVertexIndices, onePolygonTextureVertexIndices, onePolygonNormalIndices));

    }

    protected static void parseFaceWord(
            String wordInLine,
            ArrayList<Integer> onePolygonVertexIndices,
            ArrayList<Integer> onePolygonTextureVertexIndices,
            ArrayList<Integer> onePolygonNormalIndices,
            int lineInd) {
        try {
            String[] wordIndices = wordInLine.split("/");
            switch (wordIndices.length) {
                case 1 -> {
                    onePolygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
                }
                case 2 -> {
                    onePolygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
                    onePolygonTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
                }
                case 3 -> {
                    onePolygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
                    onePolygonNormalIndices.add(Integer.parseInt(wordIndices[2]) - 1);
                    if (!wordIndices[1].equals("")) {
                        onePolygonTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
                    }
                }
                default -> {
                    throw new ObjReaderException("Invalid element size.", lineInd);
                }
            }

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse int value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few arguments.", lineInd);
        }
    }

    protected static ArrayList<Polygon> triangulate(ArrayList<Integer> onePolygonVertexIndices,
                                                    ArrayList<Integer> onePolygonTextureVertexIndices,
                                                    ArrayList<Integer> onePolygonNormalIndices) {
        ArrayList<Polygon> triangulatedPolygons = new ArrayList<>();
        for (int i = 1; i < onePolygonTextureVertexIndices.size() - 1; i++) {
            Polygon polygon = new Polygon();

            if (onePolygonVertexIndices.size() > 0) {
                polygon.getPolygonVertexIndices().add(onePolygonVertexIndices.get(0));
                polygon.getPolygonVertexIndices().add(onePolygonVertexIndices.get(i));
                polygon.getPolygonVertexIndices().add(onePolygonVertexIndices.get(i + 1));
            }

            if (onePolygonTextureVertexIndices.size() > 0) {
                polygon.getPolygonTextureVertexIndices().add(onePolygonTextureVertexIndices.get(0));
                polygon.getPolygonTextureVertexIndices().add(onePolygonTextureVertexIndices.get(i));
                polygon.getPolygonTextureVertexIndices().add(onePolygonTextureVertexIndices.get(i + 1));
            }

            if (onePolygonNormalIndices.size() > 0) {
                polygon.getPolygonNormalIndices().add(onePolygonNormalIndices.get(0));
                polygon.getPolygonNormalIndices().add(onePolygonNormalIndices.get(i));
                polygon.getPolygonNormalIndices().add(onePolygonNormalIndices.get(i + 1));
            }

            triangulatedPolygons.add(polygon);
        }
        return triangulatedPolygons;
    }

}