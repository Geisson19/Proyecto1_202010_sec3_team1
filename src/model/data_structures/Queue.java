package model.data_structures;

import model.logic.Comparendo;

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


    public boolean estaVacia() {
        return tamano == 0;
    }

    public int darTamano() {
        return tamano;
    }

    public void enQueue(T pElemento) {
        Node<T> nuevo = new Node(pElemento);

        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;

        } else {
            ultimo.cambiarSiguiente(nuevo);
            ultimo = nuevo;

        }
        tamano++;

    }

    public T deQueue() {
        if (estaVacia()) {

            return null;
        } else {
            T elemento = primero.darElemento();
            primero = primero.darSiguiente();
            tamano--;
            return elemento;
        }


    }

    public T peek() {

        if (estaVacia()) {
            return null;

        } else {
            return primero.darElemento();
        }
    }

    // Metodos auxiliares

    public Node darPrimero() {
        return primero;
    }


}