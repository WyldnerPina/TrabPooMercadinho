package wyldner.Vendas;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wyldner.Principal.VendProd;
import wyldner.Produto.Produto;

public class CtrlVendas {
	private LongProperty idVend = new SimpleLongProperty(0);
	private DoubleProperty qnt = new SimpleDoubleProperty(0.0);
	
	
	private ObservableList<VendProd> listaVendProd = FXCollections.observableArrayList();
	private IVendasDAO vDao;
	
	private static double total = 0;
	private static int qnt = 0;
//===================================================================================================================================
	public CtrlVendas() throws ClassNotFoundException, SQLException {
		vDao = new VendasDAO();
	}
	
	//---------------------------------------------------------------------------------
	
	public void limpar() {
		System.out.println("limpando");
		idVend.set(0);
		qnt.set(0);
	}
	
	//---------------------------------------------------------------------------------

	
	public void excluir(VendProd vp) throws SQLException { 
		listaVendProd.remove(vp);
		vDao.remover(vp.getIdVendas());
	}
	
	public void fromEntity(VendProd vp) { 
		idVend.set(vp.getIdVendas());
		qnt.set(vp.getTotal());
		
	}
	
	public void atualizar() throws SQLException { 
			Vendas v = new Vendas();
			v.setIdVendas(vDao.ProcurarTodos());
			v.setTotal(total++);
			v.setQntidade(qnt++);
			
			System.out.println(v);
			
			Produto p = new Produto();
			p.setCodProduto(codProd.get());
			p.setNome(nomeProd.get());
			p.setDescricao(desc.get());
			p.setPrecoUnit(precoUnit.get());
			
			System.out.println(p);	
			
			VendProd vp = new VendProd(v, p);
			
//			for (int i = 0; i < listaVendProd.size(); i++) { 
//				VendProd prod = listaVendProd.get(i);
//				if (prod.getIdVendas() == idVend.get()) { 
//					listaVendProd.set(i, vp);
//				}
//			}
			vDao.adicionar(vp);
//			pesquisar();
	}
	
	public void pesquisar() throws SQLException { 
		listaVendProd.clear();
		List<VendProd> lst = vDao.procurarPorTitulo(idVend.get());
		listaVendProd.addAll(lst);		
	}
	
//===================================================================================================================================
	public final ObservableList<VendProd> getListaProd() {
		return listaVendProd;
	}
	public final LongProperty getIdVend() {
		return idVend;
	}
	public final DoubleProperty getTot() {
		return qnt;
	}
}