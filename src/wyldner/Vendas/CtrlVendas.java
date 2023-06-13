package wyldner.Vendas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wyldner.Produto.Produto;

public class CtrlVendas {
	private LongProperty idVendas = new SimpleLongProperty(0);
	private IntegerProperty qntidade = new SimpleIntegerProperty(0);
	private DoubleProperty total = new SimpleDoubleProperty(0.0);
	private ObjectProperty<LocalDate> dtVenda = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalTime> hVenda = new SimpleObjectProperty<>(LocalTime.now());
	private ObservableList<Produto> produtos = FXCollections.observableArrayList();
	
	private static int qntProd = 0;

	private IVendasDAO vDao;
	private ObservableList<Vendas> lista = FXCollections.observableArrayList();

//===================================================================================================================================
	public void fromEntity(Vendas v) { 
		idVendas.set(v.getIdVendas());
		qntidade.set(v.getQntidade());
	}
	
//===================================================================================================================================	
	public void limpar() { 
		idVendas.set(0);
		qntidade.set(0);
	}

	//---------------------------------------------------------------------------------
	public void adicionar() throws SQLException, ClassNotFoundException{ 
		Vendas v = new Vendas();		
		v.setIdVendas(idVendas.get());
		v.setQntidade(qntidade.get());
		
		vDao = new VendasDAO();
		vDao.adicionar(v);
	}

	//---------------------------------------------------------------------------------
	public void atualizar() throws SQLException, ClassNotFoundException { 
		Vendas v = new Vendas();		
		v.setIdVendas(idVendas.get());
		v.setQntidade(qntidade.get());
		
		vDao = new VendasDAO();
		vDao.adicionar(v);
	}
	
	
	
	//---------------------------------------------------------------------------------
	public void pesquisarId() throws SQLException, ClassNotFoundException { 
		Vendas v = new Vendas();		
		v.setIdVendas(idVendas.get());
		v.setQntidade(qntidade.get());
		
		vDao = new VendasDAO();
		v = vDao.procurarPorId(idVendas.get());
		System.out.println(v);
	}
	
	//---------------------------------------------------------------------------------

	public void excluir(long id) throws SQLException, ClassNotFoundException { 
		Vendas v = new Vendas();
		v.setIdVendas(id);
		
		vDao = new VendasDAO();
		vDao.procurarPorId(id);
	}
	
	
	//---------------------------------------------------------------------------------
	public void adicionarProd(Produto p) { 
		produtos.add(p);
		qntProd ++;
	}

//===================================================================================================================================
	public final LongProperty getIdVendas() {
		return  idVendas;
	}
	
	public final IntegerProperty getQntidade() {
		return qntidade;
	}

	public final DoubleProperty getTotal() {
		return total;
	}

	public final ObjectProperty<LocalDate> getDtVenda() {
		return dtVenda;
	}

	public final ObjectProperty<LocalTime> gethVenda() {
		return hVenda;
	}

	public ObservableList<Vendas> getLista() { 
		return lista;
	}

	public final long getCodProd() {
		Produto p = new Produto();
		p = produtos.get(qntProd);
		return p.getCodProduto();
	}

	public static final int getQntProd() {
		return qntProd;
	}
	
}
