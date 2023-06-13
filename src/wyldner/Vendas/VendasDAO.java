package wyldner.Vendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wyldner.Principal.AcessoBD;


public class VendasDAO implements IVendasDAO{
	private Connection con;
	
//===========================================================================================	
	public VendasDAO() throws ClassNotFoundException, SQLException {
		Class.forName(AcessoBD.getJdbcDriver());
		con = DriverManager.getConnection(AcessoBD.getJdbcUrl(), AcessoBD.getJdbcUser(), AcessoBD.getJdbcPass());
	}

//===========================================================================================
	@Override
	public void adicionar(Vendas v) throws SQLException {
		String sql = "INSERT INTO Vendas (qntidade, total, hVenda, dtVenda) VALUES (?, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setInt(1, v.getQntidade());
		st.setDouble(2, v.getTotal());
		st.setTime(3, v.getSqlTime());
		st.setDate(4, v.getSqlDate());
		
		st.executeUpdate();
	}

//===========================================================================================
	@Override
	public void atualizar(long id, Vendas v) throws SQLException {
		String sql = "UPDATE vendas SET qntidade = ?, total = ? WHERE idVendas = ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, v.getQntidade());
		st.setDouble(2, v.getTotal());
//		st.setTime(3, v.getSqlTime());
//		st.setDate(4, v.getSqlDate());
		st.setLong(3, id);
		
		st.executeUpdate();		
	}

		
//===========================================================================================
	@Override
	public void remover(long id) throws SQLException {
		String sql = "DELETE FROM vendas WHERE idVendas = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		st.executeUpdate();
	}
	
	
//===========================================================================================
	@Override
	public Vendas procurarPorId(long id) throws SQLException {
		String sql = "SELECT * FROM vendas WHERE idVendas = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		ResultSet rs = st.executeQuery();
		
		
		if ( rs.next() ) { 
			Vendas v = new Vendas();
			v.setQntidade(rs.getInt("qntidade"));
			v.setTotal(rs.getDouble("total"));
			v.setSqlTime(rs.getTime("sqlTime"));
			v.setSqlDate(rs.getDate("sqlDate"));
			
			return v;
		}
		return null;
	}
}
