package wyldner.Vendas;

import java.sql.SQLException;

public interface IVendasDAO {
	void adicionar(Vendas v) throws SQLException;

	void atualizar(long id, Vendas v) throws SQLException;

	void remover(long id) throws SQLException;

	Vendas procurarPorId(long id) throws SQLException;
}
