package model.data_structures;


public interface IQueue<T> {

	public boolean estaVacia();

	public int darTamano();

	public void enQueue(T pElemento);

	public T deQueue();

	public T peek();


}