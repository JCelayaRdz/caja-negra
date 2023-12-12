package es.jcelayardz;

import com.bst.BST;
import com.exceptions.DepthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

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

}
