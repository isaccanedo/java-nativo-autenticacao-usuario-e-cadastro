package progweb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class LoginDAO {

	private String login;
	private String senha;
	
	public LoginDAO(String login, String senha){
		this.login = login;
		this.senha = senha;
	}
	
	public boolean verificaLogin() throws Exception{
		VerificarLoginDAO dao = new VerificarLoginDAO();
		return dao.isOk();
	}
	
	// usado somente para classes de testes
	public void inserirLogin() throws Exception{
		if(verificaLogin()) return;// se já existe não insere de novo
		InserirLoginDAO dao = new InserirLoginDAO();
		dao.executeSQL();
	}
	
	// veja que as classes que extendem TemplateDAO podem ser classes internas
	class VerificarLoginDAO extends TemplateDAO{
		private Boolean ok; // observe que esta variável NÃO é um tipo primitivo
		@Override
		public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
			PreparedStatement ps = c.prepareStatement(
				"SELECT * FROM login WHERE login = ? AND senha = ? ");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			this.ok = rs.next();// se retornar algum registro o login e senha estão ok
			rs.close();
			return ps;
		}
		public boolean isOk() throws Exception{
			// como a variável é uma Wrapper class (não é primitivo)
			// é possível saber se ela já tem valor atribuido
			if(ok==null){
				// lembrando que o método 'executeSQL()' 
				// irá chamar o 'createAndExecuteStatement(...)'
				executeSQL();
			}
			return ok;
		}
	}

	
	// INNER CLASS (CLASSE INTERNA)
	class InserirLoginDAO extends TemplateDAO{
		@Override
		public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
			PreparedStatement ps = c.prepareStatement(
						" INSERT INTO login(login,senha) VALUES (?,?) ");
			ps.setString(1, login);
			ps.setString(2, senha);
			ps.executeUpdate();
			return ps;
		}
	}
	
}

