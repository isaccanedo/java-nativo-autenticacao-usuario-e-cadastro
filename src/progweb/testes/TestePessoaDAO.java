package progweb.testes;

import progweb.dao.PesquisaPessoaDAO;
import progweb.dao.PessoaDAO;
import progweb.vo.PessoaVO;

public class TestePessoaDAO {

	public static void main(String[] args) throws Exception {
		
		PessoaVO fulano = new PessoaVO();// não tem ID
		fulano.setNome("Isac");
		fulano.setCpf("111.111.111-11");
		
		PessoaVO fulana = new PessoaVO();// não tem ID
		fulana.setNome("Canedo");
		fulana.setCpf("222.222.222-22");
		
		// *** INSERIR ***
		
		PessoaDAO daoInserirFulano = new PessoaDAO(fulano);
		daoInserirFulano.inserir();
		// se a linha acima não lançar exceção o insert foi executado com sucesso
		System.out.println("Dados inseridos com sucesso! Pessoa: "+fulano.getNome());
		
		PessoaDAO daoInserirFulana = new PessoaDAO(fulana);
		daoInserirFulana.inserir();
		// se a linha acima não lançar exceção o insert foi executado com sucesso
		System.out.println("Dados inseridos com sucesso! Pessoa: "+fulana.getNome());
		
		// *** PESQUISAR ***
		
		PesquisaPessoaDAO psqFulano = new PesquisaPessoaDAO(fulano);
		PessoaVO dadosFulano = psqFulano.getPessoa();// tem ID
		
		PesquisaPessoaDAO psqFulana = new PesquisaPessoaDAO(fulana);
		PessoaVO dadosFulana = psqFulana.getPessoa();// tem ID
		
		// *** ALTERAR ***
		
		dadosFulano.setIdConjuge(dadosFulana.getId());
		PessoaDAO daoAlterarFulano = new PessoaDAO(dadosFulano);
		daoAlterarFulano.alterar();
		System.out.println(
			"Dados alterados com sucesso! Pessoa: "+dadosFulano.getNome());
		
		dadosFulana.setIdConjuge(dadosFulano.getId());
		PessoaDAO daoAlterarFulana = new PessoaDAO(dadosFulana);
		daoAlterarFulana.alterar();
		System.out.println(
			"Dados alterados com sucesso! Pessoa: "+dadosFulana.getNome());

		// *** EXCLUIR ***
		
		PessoaVO pessoaQualquer = new PessoaVO();
		pessoaQualquer.setId(99);// somente para exemplo
		PessoaDAO daoExcluir = new PessoaDAO(pessoaQualquer);
		daoExcluir.excluir();
		
		
	}

}
