package test.data_structures;

import static org.junit.Assert.*;

import org.junit.*;

import model.data_structures.*;

public class TestListaEncadenadaCola 
{

	private Queue<String> cola;
	private static int TAMANO= 100;
	
	@Before
	public void setUp1()
	{
		cola= new Queue<>();
	}
	
	public void setUp2()
	{
		int cont=0;
		while(cont<TAMANO)
		{
			cola.enQueue(" "+cont);
			cont++;
		}
	}
	
	
	@Test
	public void testListaEncadenada() 
	{
		// TODO
		assertTrue(cola!=null);
		assertEquals(0,cola.darTamano());
	}
	
	@Test
	public void darLongitud() {
		setUp2();
		// TODO
		assertEquals(100,cola.darTamano());
		
	}
	
	@Test
	public void darCabeza() {
		setUp2();
		// TODO
		assertEquals(" "+0,cola.darPrimero());
		
	}
	
	@Test
	public void esListaVacia() {
		
		assertTrue(cola.estaVacia());
		setUp2();
		assertFalse(cola.estaVacia());
		
	}
	

	@Test
	public void testInsertarFinal() {
		setUp2();
		// TODO
		assertEquals(100,cola.darTamano());
		assertEquals(" "+99,cola.peek());
	}
	
	
	@Test
	public void testEliminarComienzo() {
		setUp2();
		// TODO
		cola.deQueue();
		assertEquals(99,cola.darTamano());
		assertEquals(" "+1,cola.darPrimero());
		
	}

	
	
	
	
	
}
