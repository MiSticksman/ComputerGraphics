package ru.vsu.cs.group2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.group2.Model;
import ru.vsu.cs.group2.Polygon;
import ru.vsu.cs.group2.Vector3f;
import ru.vsu.cs.group2.filesWorking.objReader.ObjReader;
import ru.vsu.cs.group2.filesWorking.objReader.ObjReaderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjReaderTest {
    @Test
    public void testParseVertex01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 5);
        Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.03f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 5);
        Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.10f);
        Assertions.assertFalse(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex03() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o", "ba"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex05() {
        // АГААА! Вот тест, который говорит, что у метода нет проверки на более, чем 3 числа
        // А такой случай лучше не игнорировать, а сообщать пользователю, что у него что-то не так
        // ассерт, чтобы не забыть про тест:
        Assertions.assertTrue(false);


        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0", "3.0", "4.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParsePolygon01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7/7/5", "5/3/2", "1/3/8"));
        Polygon result = ObjReader.parsePolygon(wordsInLineWithoutToken, 5);
        Polygon expectedResult = new Polygon();
        expectedResult.addToPolygon(7, 7, 5);
        expectedResult.addToPolygon(5, 3, 2);
        expectedResult.addToPolygon(1, 3, 8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParsePolygon02() {
        // вылетает исключение из-за того, что в одном полигоне не указана одна из текстурных координат
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7/7/5", "2//4", "1/3/8"));
        Polygon result = ObjReader.parsePolygon(wordsInLineWithoutToken, 5);
        Polygon expectedResult = new Polygon();
        expectedResult.addToPolygon(7, 7, 5);
        expectedResult.addToPolygon(2, 0, 4);
        expectedResult.addToPolygon(1, 3, 8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParsePolygon03() {
        // вылетает исключение из-за того, что в одном полигоне не указана одна из координат нормали
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7/7/5", "2/1/4", "1/3"));
        Polygon result = ObjReader.parsePolygon(wordsInLineWithoutToken, 5);
        Polygon expectedResult = new Polygon();
        expectedResult.addToPolygon(7, 7, 5);
        expectedResult.addToPolygon(2, 1, 4);
        expectedResult.addToPolygon(1, 3, 0);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParsePolygon04() {
        // вылетает исключение из-за того, что в одном полигоне не указана одна из координат нормали
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7/5", "2/1/4", "1/3/5"));
        Polygon result = ObjReader.parsePolygon(wordsInLineWithoutToken, 5);
        Polygon expectedResult = new Polygon();
        expectedResult.addToPolygon(7, 0, 5);
        expectedResult.addToPolygon(2, 1, 4);
        expectedResult.addToPolygon(1, 3, 5);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParsePolygon05() {
        // в полигоне отсутствуют индексы текстурных координат
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7//5", "2//1", "1//3"));
        Polygon result = ObjReader.parsePolygon(wordsInLineWithoutToken, 5);
        Polygon expectedResult = new Polygon();
        expectedResult.addToPolygon(7, 0, 5);
        expectedResult.addToPolygon(2, 0, 1);
        expectedResult.addToPolygon(1, 0, 3);
        Assertions.assertTrue(result.equals(expectedResult));
    }


    @Test
    public void testAddToModel() throws IOException {
        Model model =  ObjReader.read("/home/vadim/SimpleInitTests/TeapotMaterials.obj");
        System.out.println("ok");
    }
}
