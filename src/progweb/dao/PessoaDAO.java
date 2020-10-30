package progweb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import progweb.vo.PessoaVO;

public class PessoaDAO{

	private PessoaVO pessoaVO;

	public PessoaDAO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}
	
	public void inserir() throws Exception{
		InserirPessoaDAO dao = new InserirPessoaDAO();
		dao.executeSQL();
	}
	// altera filtrando pelo ID
	public void alterar() throws Exception{
		AlterarPessoaDAO dao = new AlterarPessoaDAO();
		dao.executeSQL();
	}
	// exclui filtrando pelo ID
	public void excluir() throws Exception{
		ExcluirPessoaDAO dao = new ExcluirPessoaDAO();
		dao.executeSQL();
	}
	class InserirPessoaDAO extends TemplateDAO{
		@Override
		public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO pessoa (nome,cpf,id_conjuge) VALUES (?,?,?)");
			ps.setString(1, pessoaVO.getNome());
			ps.setString(2, pessoaVO.getCpf());
			
			if(pessoaVO.getIdConjuge()!=null){
				ps.setInt(3, pessoaVO.getIdConjuge());
			}else{
				// valores nulos devem ser setados com setNull
				ps.setNull(3, Types.INTEGER);
			}
			ps.executeUpdate();
			return ps;
		}
	}
	class AlterarPessoaDAO extends TemplateDAO{
		@Override
		public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
			PreparedStatement ps = c.prepareStatement(
					"UPDATE pessoa SET nome=?, cpf=?, id_conjuge=? WHERE id=? ");
			ps.setString(1, pessoaVO.getNome());
			ps.setString(2, pessoaVO.getCpf());
			
			if(pessoaVO.getIdConjuge()!=null){
				ps.setInt(3, pessoaVO.getIdConjuge());
			}else{
				// valores nulos devem ser setados com setNull
				ps.setNull(3, Types.INTEGER);
			}
			
			ps.setInt(4, pessoaVO.getId());
			ps.executeUpdate();
			return ps;
		}
	}
	class ExcluirPessoaDAO extends TemplateDAO{
		@Override
		public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
			PreparedStatement ps = c.prepareStatement(
					"DELETE FROM pessoa WHERE id = ?");
			ps.setInt(1, pessoaVO.getId());
			ps.executeUpdate();
			return ps;
		}
	}
}
