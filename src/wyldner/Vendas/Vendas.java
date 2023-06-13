package wyldner.Vendas;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import wyldner.Produto.Produto;

public class Vendas {
	private long idVendas;
	private int qntidade;
	private double total;
	
	private LocalTime hVenda = LocalTime.now();
	private Time sqlTime = Time.valueOf(hVenda);
	
	private LocalDate dtVenda = LocalDate.now();
	private Date sqlDate = Date.valueOf(dtVenda);

	
	private List<Produto> lista = new ArrayList<>();
//===================================================================================================================================	

	public final long getIdVendas() {
		return idVendas;
	}
	public final void setIdVendas(long idVendas) {
		this.idVendas = idVendas;
	}
	
	//---------------------------------------------------------------------------------
	
	public final int getQntidade() {
		return qntidade;
	}
	public final void setQntidade(int qntidade) {
		this.qntidade = qntidade;
	}

	//---------------------------------------------------------------------------------
	
	public final double getTotal() {
		return total;
	}
	public final void setTotal(double total) {
		this.total = total;
	}

	//---------------------------------------------------------------------------------
	
	public final Time getSqlTime() {
		return sqlTime;
	}
	
	public final void setSqlTime(Time sqlTime) {
		this.sqlTime = sqlTime;
	}
	
	
	public final void setSqlDate(Date sqlDate) {
		this.sqlDate = sqlDate;
	}
	public final Date getSqlDate() {
		return sqlDate;
	}


	//---------------------------------------------------------------------------------
	
	public final List<Produto> getLista() {
		return lista;
	}
	public final void setLista(List<Produto> lista) {
		this.lista = lista;
	}
	
	//---------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Vendas [idVendas=" + idVendas + ", qntidade=" + qntidade + ", total=" + total + ", hVenda=" + hVenda
				+ ", dtVenda=" + dtVenda + ", lista=" + lista + "]";
	}
	
}
