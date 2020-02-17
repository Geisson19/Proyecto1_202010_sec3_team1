package model.data_structures;

public class Node<T> {

    //Atributos
    private T elemento;
    private Node<T> siguiente;

    //Constructor
    public Node(T pElemento) {
        elemento = pElemento;
        siguiente = null;
    }

    //Metodos

    /**
     * Metodo que retorna el siguiente nodo.
     *
     * @return Siguiente nodo.
     */
    public Node<T> darSiguiente() {
        return siguiente;
    }

    /**
     * Da el elemento que contiene el nodo.
     *
     * @return Elemento que contiene el nodo.
     */
    public T darElemento() {
        return elemento;
    }

    /**
     * Cambia el elemento del nodo.
     *
     * @param pElemento
     */
    public void cambiarElemento(T pElemento) {
        elemento = pElemento;
    }

    /**
     * Cambia el siguiente atado a este nodo.
     *
     * @param pNodo
     */
    public void cambiarSiguiente(Node<T> pNodo) {
        siguiente = pNodo;
    }

}