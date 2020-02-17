package model.data_structures;

public class Node<T> {
	
	
	private Node<T> siguiente;
	
	
	private T elemento;
	
	public Node(T pElemento, Node<T> pSiguiente)
	{
		elemento = pElemento;
		siguiente = pSiguiente;
	}
	
	public T darElemento(){
		return elemento;
	}
	
	public Node<T> darSiguiente()
	{
		return siguiente;
	}
	
	public void cambiarSiguiente(Node<T> nodo)
	{
		siguiente = nodo;
	}

}
