package model.data_structures;

public class Queue<T> implements IQueue<T> {

    //Atributo
    private Node<T> primero;
    private Node<T> ultimo;
    private int tamano;

    //Constructor
    public Queue() {
        tamano = 0;
        primero = null;
        ultimo = null;
    }

    /**
     * Se agrega un elemento al final de la cola.
     *
     * @param pElemento
     */
    public void enQueue(T pElemento) {
        Node<T> nuevoUltimo = new Node<T>(pElemento);
        nuevoUltimo.cambiarSiguiente(null);
        if (estaVacia()) {
            primero = nuevoUltimo;
            ultimo = nuevoUltimo;
        } else {
            ultimo.cambiarSiguiente(nuevoUltimo);
            ultimo = nuevoUltimo;
        }
        tamano++;
    }

    /**
     * Se regresa el primer elemento y se elimina de la cola.
     *
     * @return Primer elemento.
     */
    public T deQueue() {
        if (estaVacia()) {
            ultimo = null;
            return null;
        }

        T temp = primero.darElemento();
        primero = primero.darSiguiente();
        tamano--;

        return temp;
    }

    /**
     * Se comprueba que exista al menos un elemento en la cola.
     *
     * @return True si está vacia, false si hay al menos un elemento.
     */
    public boolean estaVacia() {
        return tamano == 0;
    }

    /**
     * Se da el primer elemento y no es eliminado.
     *
     * @return Primer elemento
     */
    public T peek() {
        if (estaVacia()) {
            return null;
        } else {
            return primero.darElemento();
        }
    }

    /**
     * Se da el tamaño de la cola.
     *
     * @return tamano
     */
    public int darTamano() {
        return tamano;
    }
}