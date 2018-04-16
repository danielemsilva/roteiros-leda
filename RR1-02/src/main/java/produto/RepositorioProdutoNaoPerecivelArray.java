package produto;

/**
 * Classe que representa um repositorio de produtos usando arrays como estrutura
 * sobrejacente. Alguns metodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercicio, o erro sera
 * representado por uma RuntimeException que nao precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que voce deve utilizar a estrutura de dados array e nao
 * implementacoes de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoNaoPerecivelArray {

	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoNaoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial e -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo codigo do produto
	 * @return indice do produto 
	 */
	private int procurarIndice(int codigo) {
		for (int i = 0; i < this.index; i++) {
			if (this.produtos[i].getCodigo() == codigo) {
				return i;
			}
		}
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo codigo do produto
	 * @return true se o produto existe no array
	 */
	public boolean existe(int codigo) {
		for (int i = 0; i < this.index; i++) {
			if (this.produtos[i].getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 * 
	 * @param produto produto a ser adicionado
	 */
	public void inserir(ProdutoNaoPerecivel produto) {
		if(this.index >= this.produtos.length) {
			throw new UnsupportedOperationException("Not implemented yet!");			
		}
		this.produtos[index] = produto;
		this.index++;
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 * 
	 * @param produto produto a ser atualizado
	 */
	public void atualizar(ProdutoNaoPerecivel produto) {
		int indice = this.procurarIndice(produto.getCodigo());
		this.produtos[indice] = produto;
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo codigo do produto a ser removido
	 */
	public void remover(int codigo) {
		int indice = this.procurarIndice(codigo);
		for (int i = indice; i < this.index; i++) {
			this.produtos[i] = this.produtos[i + 1];
		}
		this.index--;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo codigo do produto
	 * @return produto se for encontrado
	 */
	public ProdutoNaoPerecivel procurar(int codigo) {
		for (ProdutoNaoPerecivel produto : this.produtos) {
			if(produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
}
