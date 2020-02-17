package model.logic;

public interface ICola<E> {

	public E dequeue();
	

	public E primeroNodo();


	public E ultimoNodo();


	public int size();


	public boolean estaVacia();
}
