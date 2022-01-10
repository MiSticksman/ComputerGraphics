package ru.vsu.cs.group2.filesWorking.objReader;
import ru.vsu.cs.group2.Model;
import ru.vsu.cs.group2.Polygon;
import ru.vsu.cs.group2.Vector2f;
import ru.vsu.cs.group2.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjReader {

    public static Model read(String filename) throws FileNotFoundException {
        Model result = new Model();

        int lineInd = 0;

        if (!filename.toLowerCase().endsWith(".obj")) {
            filename += ".obj";
        }

        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            ArrayList<String> wordsInLine = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
            if (wordsInLine.isEmpty())
                continue;

            final String token = wordsInLine.get(0);
            wordsInLine.remove(0);

            ++lineInd;
            switch (token) {
                // Обратите внимание!
                // Для структур типа вершин методы написаны так, чтобы ничего не знать о внешней среде.
                // Они принимают только то, что им нужно для работы, а возвращают только то, что могут создать.
                // Исключение - индекс строки. Он прокидывается, чтобы выводить сообщение об ошибке.
                // Могло быть иначе. Например, метод parseVertex мог вместо возвращения вершины принимать вектор вершин
                // модели или сам класс модели, работать с ним.
                // Но такой подход может привести к большему количеству ошибок в коде. Например, в нем что-то может
                // тайно сделаться с классом модели.
                // А еще это портит читаемость
                // И не стоит забывать про тесты. Чем проще вам задать данные для теста, проверить, что метод рабочий,
                // тем лучше.
                case Model.OBJ_VERTEX_TOKEN -> result.addVertices(parseVertex(wordsInLine, lineInd));
                case Model.OBJ_TEXTURE_TOKEN -> result.addTextureVertices(parseTextureVertex(wordsInLine, lineInd));
                case Model.OBJ_NORMAL_TOKEN -> result.addNormals(parseNormal(wordsInLine, lineInd));
                case Model.OBJ_POLYGON_TOKEN -> result.addPolygon(parsePolygon(wordsInLine, lineInd));
                default -> {}
            }
        }
        return result;
    }
    // Всем методам кроме основного я поставил модификатор доступа protected, чтобы обращаться к ним в тестах
    public static Vector3f parseVertex(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            return new Vector3f(
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1)),
                    Float.parseFloat(wordsInLineWithoutToken.get(2)));

        } catch(NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch(IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few vertex arguments.", lineInd);
        }
    }

    protected static Vector2f parseTextureVertex(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            return new Vector2f(
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1)));

        } catch(NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch(IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few texture vertex arguments.", lineInd);
        }
    }

    protected static Vector3f parseNormal(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        try {
            return new Vector3f(
                    Float.parseFloat(wordsInLineWithoutToken.get(0)),
                    Float.parseFloat(wordsInLineWithoutToken.get(1)),
                    Float.parseFloat(wordsInLineWithoutToken.get(2)));

        } catch(NumberFormatException e) {
            throw new ObjReaderException("Failed to parse float value.", lineInd);

        } catch(IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few normal arguments.", lineInd);
        }
    }

    public static Polygon parsePolygon(final ArrayList<String> wordsInLineWithoutToken, int lineInd) {
        Polygon polygon = new Polygon();
        for (String s : wordsInLineWithoutToken) {
            parsePolygonWord(s, polygon, lineInd);
        }
        return polygon;
    }

    // Обратите внимание, что для чтения полигонов я выделил еще один вспомогательный метод.
    // Это бывает очень полезно и с точки зрения структурирования алгоритма в голове, и с точки зрения тестирования.
    // В радикальных случаях не бойтесь выносить в отдельные методы и тестировать код из одной-двух строчек.
    protected static void parsePolygonWord(String wordInLine, Polygon polygon, int lineInd) {
        try {
            String[] wordIndices = wordInLine.split("/");
            int[] arrayIndices = new int[3];
            for (int i = 0; i < arrayIndices.length; i++) {
                if (i >= wordIndices.length || wordIndices[i].equals("")) {
                    arrayIndices[i] = 0;
                } else {
                    arrayIndices[i] = Integer.parseInt(wordIndices[i]);
                }
            }
            polygon.addToPolygon(arrayIndices[0], arrayIndices[1], arrayIndices[2]);

        } catch(NumberFormatException e) {
            throw new ObjReaderException("Failed to parse int value.", lineInd);

        } catch(IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few arguments.", lineInd);
        }
    }


}
