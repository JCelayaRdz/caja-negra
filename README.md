## Worst Case Robust Boundary Value Testing
* insert
* search
* printBetweenLevel

## Clases de Equivalencia (Strong Robust)
* getSubTree
* size
* toList
* depth
* iterator

## Errores detectados en los metodos
 * El constructor de la clase `Node` admite el 2500.
 * El método `search` si le pasas un valor nulo lanza
   una expceción en lugar de comprobar que el valor no es
   nulo.
 * El método `iterator` esta mal implementado, deberia de
   devolver un iterador vacio cuando la raiz es nula, pero
   lanza una excepción ya que no verifica esto.
 * El método `equals` de `Node` esta mal implementado.