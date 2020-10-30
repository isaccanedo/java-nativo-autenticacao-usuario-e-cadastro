package progweb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import progweb.vo.PessoaVO;
public class PesquisaPessoaDAO extends TemplateDAO{
	private PessoaVO pessoaVOFiltros;
	public PesquisaPessoaDAO(PessoaVO pessoaVOFiltros) {
		this.pessoaVOFiltros = pessoaVOFiltros;
	}
	private ArrayList<PessoaVO> resultadoPesquisa;
	public ArrayList<PessoaVO> getResultadoPesquisa() throws Exception {
		if(resultadoPesquisa==null){
			executeSQL();
		}
		return resultadoPesquisa;
	}
	// retorna somente uma pessoa (como busca pelo ID)
	public PessoaVO getPessoa() throws Exception{
		return getResultadoPesquisa().get(0);
	}
	// para pesquisa o SQL pode ser montado dinâmicamente de acordo com os valors recebidos
	@Override
	public PreparedStatement createAndExecuteStatement(Connection c) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id,p.nome,p.cpf,p.id_conjuge,conj.id,conj.nome AS nome_conjuge ");
		sql.append("FROM pessoa p LEFT JOIN pessoa conj ON p.id_conjuge=conj.id ");
		Integer id = pessoaVOFiltros.getId();
		String nome = pessoaVOFiltros.getNome();
		String cpf = pessoaVOFiltros.getCpf();
		Integer idConjuge = pessoaVOFiltros.getIdConjuge();
		// atribui null se tiver String vazia ou com espaços
		if(nome!=null && nome.trim().length()==0) nome=null;
		if(cpf!=null && cpf.trim().length()==0) cpf=null;
		
		// se tem algum filtro gera o WHERE dinamicamente
		if(id!=null || nome!=null || cpf!=null || idConjuge!=null){
			sql.append(" WHERE ");
			if(id!=null){
				sql.append(" p.id = ? ");
			}else{
				// flag para indicar se deve adicionar o operador AND
				boolean addAnd = false;
				if(nome!=null){
					addAnd = true;
					sql.append(" p.nome LIKE ? ");
					nome = "%"+nome+"%";// pesquisa trechos do nome
				}
				if(cpf!=null){
					if(addAnd) sql.append(" AND ");
					addAnd = true;
					sql.append(" p.cpf LIKE ? ");
					cpf = "%"+cpf+"%";// pesquisa trechos do cpf
				}
				if(idConjuge!=null){
					if(addAnd) sql.append(" AND ");
					addAnd = true;
					sql.append(" p.id_conjuge = ? ");
				}
			}
		}
		System.out.println("SQL>"+sql);
		PreparedStatement ps = c.prepareStatement(sql.toString());
		int ct = 1;
		if(id!=null) ps.setInt(ct++, id);
		if(nome!=null) ps.setString(ct++, nome);
		if(cpf!=null) ps.setString(ct++, cpf);
		if(idConjuge!=null) ps.setInt(ct++, idConjuge);
		
		ResultSet rs = ps.executeQuery();
		resultadoPesquisa = new ArrayList<PessoaVO>();
		while(rs.next()){
			PessoaVO p = new PessoaVO();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setCpf(rs.getString("cpf"));
			p.setIdConjuge(rs.getInt("id_conjuge"));
			p.setNomeConjuge(rs.getString("nome_conjuge"));
			resultadoPesquisa.add(p);
		}
		rs.close();
		return ps;
	}
}
