package wyldner.Vendas;

import java.sql.SQLException;
import java.util.List;

import wyldner.Principal.VendProd;


public interface IVendasDAO {
	VendProd adicionar(VendProd P) throws SQLException;

//	void atualizar(long id, VendProd P) throws SQLException;

	void remover(long id) throws SQLException;

	public List<VendProd> procurarPorTitulo(long id) throws SQLException;

	VendProd procurarPorId(long id) throws SQLException;
	
	public long ProcurarTodos () throws SQLException;
}
