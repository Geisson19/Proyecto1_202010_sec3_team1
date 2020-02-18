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
        Node<T> nuevo = new Node<T>(pElemento);

        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;

        } else {
            ultimo.cambiarSiguiente(nuevo);
            ultimo = nuevo;

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

        T elemento = primero.darElemento();
        primero = primero.darSiguiente();
        tamano--;

        return elemento;
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

    public Node darPrimero()
    {
        return primero;
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