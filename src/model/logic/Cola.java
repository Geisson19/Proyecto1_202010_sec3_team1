package model.logic;


public class Cola<E> implements ICola{

	private Node<E> primero;

	private Node<E> ultimo;

	private int tamano;

	public Cola()
	{
		ultimo = null;
		primero = null;
		tamano = 0;
	}
	
	public void enqueue(E nuevo)
	{
		
		if(ultimo == null)
		{
			ultimo = new Node<>(nuevo,null);
			primero = ultimo;
		}
		else
		{
			Node<E> nodo = new Node<>(nuevo, null);
			ultimo.cambiarSiguiente(nodo);
			ultimo = nodo;
		}
		tamano++;
		
	}

	public E dequeue()
	{
		if(estaVacia()){
			return null;
		}
		else{
			E toReturn = primero.darElemento();
			primero = primero.darSiguiente();
			tamano--;
			return toReturn;
		}
	}

	public E primeroNodo()
	{
		if(estaVacia())
			return null;
		return primero.darElemento();
	}

	public E ultimoNodo()
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
