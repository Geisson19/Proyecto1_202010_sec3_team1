package model.data_structures;

public interface IQueue<E> {

	public E dequeue();


	public E primeroNodo();


	public E ultimoNodo();


	public int size();


	public boolean estaVacia();
}