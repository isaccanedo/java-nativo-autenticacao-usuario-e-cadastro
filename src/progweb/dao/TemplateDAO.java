package progweb.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public abstract class TemplateDAO {
	// bloco static para registro do Driver
	static{
		try{
			//a string com nome da classe pode ser 
			//armazenada em um arquivo de configuração
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao registrar Driver");
			e.printStackTrace();
		}
	}
	public void executeSQL(){
		Connection c = null;
		PreparedStatement ps = null;
		try{
			//a url, login e senha podem ser
			//armazenados em um arquivo de configuração
			String url = "jdbc:mariadb://localhost:3306/progweb";
			c = DriverManager.getConnection(url,"root","root");
			
			ps = createAndExecuteStatement(c);
		
		}catch (Exception e) {
			// tratamento de erro, mensagens para usuário, logs, etc
			System.out.println("Erro ao executar SQL");
			e.printStackTrace();
		}finally{
			try {
				if(c!=null) c.close();
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				// tratamento de erro, mensagens para usuário, logs, etc
				System.out.println("Erro ao finalizar operação SQL");
				e.printStackTrace();
			}
		}
	}
	
	public abstract PreparedStatement createAndExecuteStatement(Connection c) throws Exception;
}
