package wyldner.Produto;

import java.sql.SQLException;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CtrlProduto {
	private LongProperty codProd = new SimpleLongProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty desc = new SimpleStringProperty("");
	private DoubleProperty precoUnit = new SimpleDoubleProperty(0);
	private IntegerProperty qntProd = new SimpleIntegerProperty(0);
	
	private ObservableList<Produto> listaProd = FXCollections.observableArrayList();
	private IProdDAO pDao;
//===================================================================================================================================
	public CtrlProduto() throws ClassNotFoundException, SQLException {
		pDao = new ProdDAO();
	}
	
	//---------------------------------------------------------------------------------
	
	public void limpar() {
		System.out.println("limpando");
		codProd.set(0);
		nome.set("");
		desc.set("");
		precoUnit.set(0);
		qntProd.set(0);
	}
	
	//---------------------------------------------------------------------------------

	
	public void excluir(Produto p) throws SQLException { 
		listaProd.remove(p);
		pDao.remover(p.getCodProduto());
	}
	
	public void fromEntity(Produto p) { 
		codProd.set(p.getCodProduto());
		nome.set(p.getNome());
		desc.set(p.getDescricao());
		precoUnit.set(p.getPrecoUnit());
		qntProd.set(p.getQntProd());
	}
	
	public void adicionar() throws SQLException { 
		if (codProd.get() == 0) {
			Produto p = new Produto();
			p.setCodProduto(codProd.get());
			p.setNome(nome.get());
			p.setDescricao(desc.get());
			p.setPrecoUnit(precoUnit.get());
			p.setQntProd(qntProd.get());
			
			p = pDao.adicionar(p);
			listaProd.add(p);
		} else { 
			Produto p = new Produto();
			p.setCodProduto(codProd.get());
			p.setNome(nome.get());
			p.setDescricao(desc.get());
			p.setPrecoUnit(precoUnit.get());
			p.setQntProd(qntProd.get());
			
			for (int i = 0; i < listaProd.size(); i++) { 
				Produto prod = listaProd.get(i);
				if (prod.getCodProduto() == codProd.get()) { 
					listaProd.set(i, p);
				}
			}
			pDao.adicionar(p);
		}
	}
	
	public void pesquisar() throws SQLException { 
		listaProd.clear();
		List<Produto> lst = pDao.procurarNome(nome.get());
		listaProd.addAll(lst);		
	}
	
	/* FALTA FAZER O PESQUISAR POR COD */
	
//===================================================================================================================================
	public final ObservableList<Produto> getListaProd() {
		return listaProd;
	}
	public final LongProperty getCodProd() {
		return codProd;
	}
	public final StringProperty getNome() {
		return nome;
	}
	public final StringProperty getDesc() {
		return desc;
	}
	public final DoubleProperty getPreco() {
		return precoUnit;
	}
	public final IntegerProperty getQntProd() {
		return qntProd;
	}
}
