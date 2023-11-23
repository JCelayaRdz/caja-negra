package es.jcelayardz;

import com.bst.BST;
import com.exceptions.DepthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class BSTTest {

    BST bst;

    @BeforeEach
    void setUp() {
        bst = new BST();
    }

    @Test
    @DisplayName("test insertar valores con recursive a true")
    void testInsertRecursirve() throws DepthException {
        int[] valoresAceptados = {-2500,-2499,1250,2498,2499};

        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            bst.insert(-2501, true);
        });
        assertEquals("El dato que intentas insertar está fuera del rango permitido", e1.getMessage());

        for (int i = 0; i < valoresAceptados.length; i++) {
            bst.insert(valoresAceptados[i], true);
            assertEquals(i+1,
                    bst.size(),
                    String.format("El árbol debería tener %d elementos pero tiene %d.",i+1, bst.size()));
        }

        Exception e2 = assertThrows(IllegalArgumentException.class, () -> {
           bst.insert(2500, true);
        });
        assertEquals("El dato que intentas insertar está fuera del rango permitido", e2.getMessage());
    }

    @Test
    @DisplayName("test insertar valores con recursive a false")
    void testInsertNotRecursirve() throws DepthException {
        int[] valoresAceptados = {-2500,-2499,1250,2498,2499};

        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            bst.insert(-2501, false);
        });
        assertEquals("El dato que intentas insertar está fuera del rango permitido", e1.getMessage());

        for (int i = 0; i < valoresAceptados.length; i++) {
            bst.insert(valoresAceptados[i], false);
            assertEquals(i+1,
                    bst.size(),
                    String.format("El árbol debería tener %d elementos pero tiene %d.",i+1, bst.size()));
        }

        Exception e2 = assertThrows(IllegalArgumentException.class, () -> {
            bst.insert(2500, false);
        });
        assertEquals("El dato que intentas insertar está fuera del rango permitido", e2.getMessage());
    }

    @Test
    @DisplayName("test insertar mas de 50 valores de forma recursiva")
    void testInsertar50ValoresRecursivo() {
        Random rand = new Random();

        Exception e = assertThrows(DepthException.class, () -> {
        for (int i = 0; i <= 50; i++) {
            bst.insert(i, true);
        }});

        System.out.println(e.getMessage());

        assertEquals(
                "Intentas insertar en la altura 51, la máxima permitida es 50",
                e.getMessage()
        );
    }

    @Test
    @DisplayName("test insertar mas de 50 valores de forma no recursiva")
    void testInsertar50ValoresNoRecursivo() {
        Random rand = new Random();

        Exception e = assertThrows(DepthException.class, () -> {
            for (int i = 0; i <= 50; i++) {
                bst.insert(i, false);
            }});

        System.out.println(e.getMessage());

        assertEquals(
                "Intentas insertar en la altura 51, la máxima permitida es 50",
                e.getMessage()
        );
    }
}
