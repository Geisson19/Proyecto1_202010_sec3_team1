package model.data_structures;


public class Cola<T extends Comparable<T>> implements ICola<T>{

	private Node<T> primero;

	private Node<T> ultimo;

	private int tamano;

	public Cola()
	{
		ultimo = null;
		primero = null;
		tamano = 0;
	}
	
	public void enqueue(T nuevo)
	{
		
		if(ultimo == null)
		{
			ultimo = new Node<>(nuevo,null);
			primero = ultimo;
		}
		else
		{
			Node<T> nodo = new Node<>(nuevo, null);
			ultimo.cambiarSiguiente(nodo);
			ultimo = nodo;
		}
		tamano++;
		
	}

	public T dequeue()
	{
		if(estaVacia()){
			return null;
		}
		else{
			T toReturn = primero.darElemento();
			primero = primero.darSiguiente();
			tamano--;
			return toReturn;
		}
	}

	public T primeroNodo()
	{
		if(estaVacia())
			return null;
		return primero.darElemento();
	}

	public T ultimoNodo()
	{
		if(estaVacia())
			return null;
		return ultimo.darElemento();
	}

	public int size()
	{
		return tamano;
	}

	public boolean estaVacia()
	{
		return tamano == 0;
	}

}
