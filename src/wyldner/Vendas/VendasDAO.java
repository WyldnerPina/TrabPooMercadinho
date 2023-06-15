package wyldner.Vendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wyldner.Principal.AcessoBD;
import wyldner.Principal.VendProd;
import wyldner.Produto.Produto;

public class VendasDAO implements IVendasDAO{
	private Connection con;
	
//===========================================================================================	
	public VendasDAO() throws ClassNotFoundException, SQLException {
		Class.forName(AcessoBD.getJdbcDriver());
		con = DriverManager.getConnection(AcessoBD.getJdbcUrl(), AcessoBD.getJdbcUser(), AcessoBD.getJdbcPass());
	}


	@Override
	public VendProd adicionar(VendProd vp) throws SQLException {
		String sql = "INSERT INTO vendProd (idVendas, total) VALUES (?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setLong(1, vp.getIdVendas());
		st.setDouble(2, vp.getTotal());
			
		st.executeUpdate();
		
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) { 
			long id = rs.getLong(1);
			vp.setIdVendas(id);
		}
		
		String sql2 = "INSERT INTO vendProd (qntidade, dtVenda, hVenda, idVendas, codProduto) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement st2 = con.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
		st2.setInt(1, vp.getQntProd());
		st2.setDate(2, vp.getSqlDate());
		st2.setTime(3, vp.getSqlTime());
		st2.setLong(4, vp.getIdVendas());
		st2.setLong(5, vp.getCodProduto());
		
		st2.executeUpdate();
		
		ResultSet rs2 = st2.getGeneratedKeys();
		if (rs2.next()) { 
			long id = rs2.getLong(1);
			vp.setIdVendas(id);
		}
		return vp;
	}

	@Override
	public void remover(long id) throws SQLException {
		String sql = "DELETE FROM vendProd WHERE idVendas = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		st.executeUpdate();
	}

	@Override
	public List<VendProd> procurarPorTitulo(long id) throws SQLException {
		List<VendProd> lista = new ArrayList<>();
		
//		String sql = "SELECT * FROM Vendas";
		String sql = "SELECT * FROM VendProd vp INNER JOIN Vendas v ON vp.idVendas = v.idVendas INNER JOIN Produto p ON vp.codProduto = p.codProduto";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) { 
			Vendas v = new Vendas();
			v.setIdVendas(rs.getLong("idVendas"));
			v.setTotal(rs.getDouble("total"));
			System.err.println(v);
			v.setQntidade(rs.getInt("qntidade"));
			v.setSqlDate(rs.getDate("dtVenda"));
			v.setSqlTime(rs.getTime("hVenda"));
			
			System.err.println(v);
			
			Produto p = new Produto();
			p.setCodProduto(rs.getLong("codProd"));
			p.setNome(rs.getString("nome"));
			p.setDescricao(rs.getString("descricao"));
			p.setPrecoUnit(rs.getDouble("precoUnit"));
			p.setQntProd(rs.getInt("qntProd"));
			
			System.err.println(p);
			
			VendProd vp = new VendProd(v);

			
			lista.add(vp);
		}
		return lista;
	}

	@Override
	public VendProd procurarPorId(long id) throws SQLException {
		String sql = "SELECT * FROM vendas WHERE idVendas = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		ResultSet rs = st.executeQuery();
		
		
		if ( rs.next() ) { 
			Vendas p = new Vendas();
			p.setIdVendas(rs.getLong("idVendas"));
			p.setTotal(rs.getDouble("total"));
			
			
			VendProd vp = new VendProd(p);
			
			return vp;
		}
		return null;
	}


	@Override
	public long ProcurarTodos() throws SQLException {
		String sql = "SELECT * FROM vendprod";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		long max = 0;
		
		while(rs.next()) { 
			Vendas p = new Vendas();
			p.setIdVendas(rs.getLong("idVendas"));

			if(p.getIdVendas() > max) {
				max = p.getIdVendas();
			}
		}
		System.out.println(max);
		return max + 1;
	}

}
