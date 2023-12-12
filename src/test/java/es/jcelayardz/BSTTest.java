package es.jcelayardz;

import com.bst.BST;
import com.bst.Node;
import com.exceptions.DepthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

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

    static Stream<Arguments> casosPruebaInsertValorLimite() {
        return Stream.of(
                Arguments.of(-2501, true, 0),
                Arguments.of(-2501, true, 1),
                Arguments.of(-2501, true, 25),
                Arguments.of(-2501, true, 49),
                Arguments.of(-2501, true, 50),
                Arguments.of(-2501, true, 51),
                Arguments.of(-2501, false, 0),
                Arguments.of(-2501, false, 1),
                Arguments.of(-2501, false, 25),
                Arguments.of(-2501, false, 49),
                Arguments.of(-2501, false, 50),
                Arguments.of(-2501, false, 51),

                Arguments.of(-2500, true, 0),
                Arguments.of(-2500, true, 1),
                Arguments.of(-2500, true, 25),
                Arguments.of(-2500, true, 49),
                Arguments.of(-2500, true, 50),
                Arguments.of(-2500, true, 51),
                Arguments.of(-2500, false, 0),
                Arguments.of(-2500, false, 1),
                Arguments.of(-2500, false, 25),
                Arguments.of(-2500, false, 49),
                Arguments.of(-2500, false, 50),
                Arguments.of(-2500, false, 51),

                Arguments.of(-2499, true, 1),
                Arguments.of(-2499, true, 0),
                Arguments.of(-2499, true, 25),
                Arguments.of(-2499, true, 49),
                Arguments.of(-2499, true, 50),
                Arguments.of(-2499, true, 51),
                Arguments.of(-2499, false, 0),
                Arguments.of(-2499, false, 1),
                Arguments.of(-2499, false, 25),
                Arguments.of(-2499, false, 49),
                Arguments.of(-2499, false, 50),
                Arguments.of(-2499, false, 51),

                Arguments.of(0, true, 1),
                Arguments.of(0, true, 0),
                Arguments.of(0, true, 25),
                Arguments.of(0, true, 49),
                Arguments.of(0, true, 50),
                Arguments.of(0, true, 51),
                Arguments.of(0, false, 0),
                Arguments.of(0, false, 1),
                Arguments.of(0, false, 25),
                Arguments.of(0, false, 49),
                Arguments.of(0, false, 50),
                Arguments.of(0, false, 51),

                Arguments.of(2499, true, 1),
                Arguments.of(2499, true, 0),
                Arguments.of(2499, true, 25),
                Arguments.of(2499, true, 49),
                Arguments.of(2499, true, 50),
                Arguments.of(2499, true, 51),
                Arguments.of(2499, false, 0),
                Arguments.of(2499, false, 1),
                Arguments.of(2499, false, 25),
                Arguments.of(2499, false, 49),
                Arguments.of(2499, false, 50),
                Arguments.of(2499, false, 51),

                Arguments.of(2500, true, 1),
                Arguments.of(2500, true, 0),
                Arguments.of(2500, true, 25),
                Arguments.of(2500, true, 49),
                Arguments.of(2500, true, 50),
                Arguments.of(2500, true, 51),
                Arguments.of(2500, false, 0),
                Arguments.of(2500, false, 1),
                Arguments.of(2500, false, 25),
                Arguments.of(2500, false, 49),
                Arguments.of(2500, false, 50),
                Arguments.of(2500, false, 51),

                Arguments.of(2501, true, 0),
                Arguments.of(2501, true, 1),
                Arguments.of(2501, true, 25),
                Arguments.of(2501, true, 49),
                Arguments.of(2501, true, 50),
                Arguments.of(2501, true, 51),
                Arguments.of(2501, false, 0),
                Arguments.of(2501, false, 1),
                Arguments.of(2501, false, 25),
                Arguments.of(2501, false, 49),
                Arguments.of(2501, false, 50),
                Arguments.of(2501, false, 51)

        );
    }

    @ParameterizedTest
    @MethodSource("casosPruebaInsertValorLimite")
    void testInsertValorLimite(int valor, boolean recursive, int profundidad) throws DepthException {
        // Primero insertamos tantos valores como sea necesario teniendo en cuenta
        // el parametro profundidad
        insertarValores(bst, profundidad, recursive);

        if (valor < -2500 || valor > 2499) {
            assertThrows(IllegalArgumentException.class, () -> {
                bst.insert(valor, recursive);
            });
        } else if (profundidad > 50) {
            assertThrows(DepthException.class, () -> {
                bst.insert(valor, recursive);
            });
        } else {
            assertDoesNotThrow(() -> {
                bst.insert(valor, recursive);
            });
        }
    }

    void insertarValores(BST bst, int numAInsertar, boolean recursirve) throws DepthException {
        // Generamos tantos numeros como profundidad deba tener el arbol
        for (int i=0; i < numAInsertar; i++) {
            int numeroRandom;

            // Iteramos hasta encontrar un numero que NO este repetido en el arbol
            while (true) {
                numeroRandom = generarNumeroValido();
                if (bst.search(numeroRandom) == null) {
                    break;
                }
            }

            bst.insert(numeroRandom, recursirve);
        }
    }

    int generarNumeroValido() {
        Random random = new Random();
        // genera un número aleatorio en el rango de 0 a 4998,
        // luego le restamos 2500 para obtener el rango deseado de -2500 a 2499
        return random.nextInt(4999) - 2500;
    }

    /*
        Clases de equivalencia para getRoot:
            - Arbol con elementos
            - Arbol vacio
     */
    @Test
    @DisplayName("Test getRoot con clases de equivalencia cuando el arbol esta vacio")
    void testGetRootVacio() {
        assertNull(bst.getRoot(), "La raiz deberia ser nula cuando el árbol esta vacio");
    }

    @Test
    @DisplayName("Test getRoot con clases de equivalencia cuando el arbol NO esta vacio")
    void testGetRootNoVacio() {
        bst = new BST(0);
        // Porque no funciona con assertEquals?
        assertTrue(bst.getRoot().equals(new Node(0)));
    }

    /*
        Clases de equivalencia para toList:
            - BST vacio
            - BST con un solo nodo
            - BST con multiples nodos
            - BST con profundidad máxima
            - BST con valores en el limite del rango
     */
    @Test
    @DisplayName("Test toList con clases de equivalencia cuando el BST esta vacio")
    void testToListBstVacio() {
        assertEquals(bst.toList().size(), 0, "La lista deberia de tener 0 elementos");
    }

    @Test
    @DisplayName("Test toList con clases de equivalencia cuando el BST tiene un solo elemento")
    void testToListBstUno() {
        bst = new BST(1);
        ArrayList<Integer> listaEsperada = new ArrayList<>(List.of(1));
        assertEquals(bst.toList(), listaEsperada, "Las listas deberian ser iguales");
    }

    @Test
    @DisplayName("Test toList con clases de equivalencia con varios nodos")
    void testToListBstVarios() throws DepthException {
        bst = new BST(0);
        bst.insert(-1, true);
        bst.insert(1,true);

        ArrayList<Integer> listaEsperada = new ArrayList<>(List.of(-1, 0, 1));

        assertEquals(bst.toList(), listaEsperada, "Las listas deberian ser iguales");
    }

    @Test
    @DisplayName("Test toList con clases de equivalencia y BST lleno")
    void testToListBstLleno() throws DepthException {
        ArrayList<Integer> listaEsperada = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            bst.insert(i, true);
            listaEsperada.add(i);
        }

        assertEquals(bst.toList(), listaEsperada, "Las listas deberian de ser iguales");
    }

    @Test
    @DisplayName("Test toList con clases de equivalencia y valores limite")
    void testToListBstValLimite() throws DepthException {
        ArrayList<Integer> listaEsperada = new ArrayList<>(List.of(-2500, 2499));

        bst.insert(-2500, true);
        bst.insert(2499, true);

        assertEquals(bst.toList(), listaEsperada, "Las listas deberian de ser iguales");
    }
}
