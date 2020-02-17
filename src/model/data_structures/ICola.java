package model.data_structures;

public interface ICola<T extends Comparable<T>> {

	public T dequeue();
	

	public T primeroNodo();


	public T ultimoNodo();


	public int size();


	public boolean estaVacia();
}
