package progweb.testes;

import progweb.dao.LoginDAO;

public class TesteLoginDAO {

	public static void main(String[] args) throws Exception {
		
		LoginDAO dao1 = new LoginDAO("fulano","senha123");
		dao1.inserirLogin(); // insere o login e senha
		
		LoginDAO dao2 = new LoginDAO("fulano","senha123");
		System.out.println("1 - Usuário e Senha OK?: "+dao2.verificaLogin());
		
		LoginDAO dao3 = new LoginDAO("fulano","senha000");
		System.out.println("2 - Usuário e Senha OK?: "+dao3.verificaLogin());

	}

}
