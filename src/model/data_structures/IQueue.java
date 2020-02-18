package model.data_structures;


public interface IQueue<T> {

    public void enQueue(T pElemento);

    public T deQueue();

    public T peek();

    public boolean estaVacia();

    public int darTamano();
}