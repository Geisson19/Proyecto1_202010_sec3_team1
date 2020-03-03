package model.data_structures;


public class Queue<E> implements IQueue {

	private Node<E> primero;

	private Node<E> ultimo;

	private int tamano;

	public Queue()
	{
		ultimo = null;
		primero = null;
		tamano = 0;
	}

	public void enqueue(E nuevo)
	{

		if(ultimo == null)
		{
			ultimo = new Node<>(nuevo);
			primero = ultimo;
		}
		else
		{
			Node<E> nodo = new Node<>(nuevo);
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

	public Node<E> getIndex(int index)
	{
		Node<E> actual = primero;

		if(index == 0)
			return actual;
		else
		{
			int j = 0;
			while(actual.darSiguiente() != null && j<index)
			{
				actual = actual.darSiguiente();
				j++;
			}
		}

		return actual;
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