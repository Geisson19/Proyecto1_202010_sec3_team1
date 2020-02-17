package model.logic;

public class Node<E> {
	
	
	private Node<E> siguiente;
	
	
	private E elemento;
	
	public Node(E pElemento, Node<E> pSiguiente)
	{
		elemento = pElemento;
		siguiente = pSiguiente;
	}
	
	public E darElemento(){
		return elemento;
	}
	
	public Node<E> darSiguiente()
	{
		return siguiente;
	}
	
	public void cambiarSiguiente(Node<E> nodo)
	{
		siguiente = nodo;
	}

}
