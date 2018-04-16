package produto;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRepositorioProdutos {
	
	private RepositorioProdutoArrayList repositorio;
	
	@Before
	public void setUp() {
		this.repositorio = new RepositorioProdutoArrayList(10);
	}
	
	@Test
	public void testExiste() {
		assertFalse(repositorio.existe(5));
	}

	@Test
	public void testInserir() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijao carioca"));
		assertTrue(repositorio.existe(5));
	}

	@Test
	public void testAtualizar() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijao carioca"));
		repositorio.atualizar(new Produto(5,"Feijao",10.0,"Feijao macassar"));
		assertEquals("Feijao macassar",repositorio.procurar(5).getDescricao());
	}

	@Test
	public void testRemover() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijao carioca"));
		repositorio.remover(5);
		assertFalse(repositorio.existe(5));
	}

	@Test
	public void testProcurar() {
		assertNull(repositorio.procurar(5));
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijao carioca"));
		assertNotNull(repositorio.procurar(5));
	}

}
