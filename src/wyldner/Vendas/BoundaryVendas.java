package wyldner.Vendas;

import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import wyldner.Principal.IPrincipal;
import wyldner.Produto.CtrlProduto;
import wyldner.Produto.Produto;

@SuppressWarnings("unchecked")
public class BoundaryVendas implements IPrincipal {
/*===================================================================================================================================
															PRODUTO
===================================================================================================================================*/
	private TextField txtCodProd = new TextField();
	private TextField txtNomeProd = new TextField();
	private TextField txtPrecoUnid = new TextField();
	private TextField txtDescProd = new TextField();
	private TextField txtQntProd = new TextField();
	
	// ---------------------------------------------------------------------------------
	private CtrlProduto ctrlProd;
	private CtrlVendas ctrlVendas;
	private TableView<Produto> tableProd = new TableView<>();

	// ---------------------------------------------------------------------------------
	private AnchorPane principal;

//===================================================================================================================================
	@SuppressWarnings({ "rawtypes" })
	public void ligacoesProd() { 
		Bindings.bindBidirectional(txtCodProd.textProperty(), ctrlProd.getCodProd(),(StringConverter) new LongStringConverter());
		Bindings.bindBidirectional(txtNomeProd.textProperty(), ctrlProd.getNome());
		Bindings.bindBidirectional(txtDescProd.textProperty(), ctrlProd.getDesc());
		Bindings.bindBidirectional(txtPrecoUnid.textProperty(), ctrlProd.getPreco(),(StringConverter) new DoubleStringConverter());
		Bindings.bindBidirectional(txtQntProd.textProperty(), ctrlProd.getQntProd(),(StringConverter) new IntegerStringConverter());
	}


/*===================================================================================================================================
  															TABELA DE PRODUTO
===================================================================================================================================*/
	 
		
		
	
		
	public void tableViewProd() {
		
		TableColumn<Produto, Long> colCodProd = new TableColumn<>("Código");
		colCodProd.setCellValueFactory(new PropertyValueFactory<Produto, Long>("codProduto"));
		
		TableColumn<Produto, String> colNomeProd = new TableColumn<>("Produto");
		colNomeProd.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		
		TableColumn<Produto, String> colDescProd = new TableColumn<>("Descrição");
		colDescProd.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		
		TableColumn<Produto, Double> colPrecoUnit = new TableColumn<>("Preço unidade");
		colPrecoUnit.setCellValueFactory(new PropertyValueFactory<Produto, Double>("precoUnit"));
		
		TableColumn<Produto, Integer> colQntProd = new TableColumn<>("Quantidade");
		colQntProd.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("qntProd"));
		
		//------------------------------------------------------------------------------------------------------
		
		TableColumn<Produto, Void> colAcoes = new TableColumn<>("Ações");
		Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>> callBack = new Callback<>() {

			@Override
			public TableCell<Produto, Void> call(TableColumn<Produto, Void> col) {
				TableCell<Produto, Void> tCell = new TableCell<>() {

					final Button btnExcluir = new Button("Adicionar");
					{
						btnExcluir.setOnAction(e -> {
							Produto p = tableProd.getItems().get(getIndex());
							ctrlVendas.adicionarProd(p);
						});
					}

					
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnExcluir);
						}
					}
				};
				return tCell;
			}
		};
		
		colAcoes.setCellFactory(callBack);
		
		double sexto = 600.0 / 6.0;
		colCodProd.setPrefWidth(sexto);
		colNomeProd.setPrefWidth(sexto);
		colDescProd.setPrefWidth(sexto);
		colPrecoUnit.setPrefWidth(sexto);
		colQntProd.setPrefWidth(sexto);
		colAcoes.setPrefWidth(sexto);
		
		
		tableProd.getColumns().addAll(colCodProd, colNomeProd, colDescProd, colPrecoUnit, colQntProd, colAcoes);
		tableProd.setItems(ctrlProd.getListaProd());

		tableProd.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Produto>() {
			@Override
			public void onChanged(Change<? extends Produto> p) {
				if (!p.getList().isEmpty()) {
					ctrlProd.fromEntity(p.getList().get(0));
				}
			}
		});
	}
/*=================================================================================================================================== 
 															TABELA DE VENDA
===================================================================================================================================*/
	
	private TableView<Vendas> tableVenda = new TableView<>();
	
//===================================================================================================================================
	@SuppressWarnings({ "rawtypes" })
	public void ligacoesVendas() { 
		Bindings.bindBidirectional(txtCodProd.textProperty(), ctrlVendas.getIdVendas(),(StringConverter) new LongStringConverter());
		Bindings.bindBidirectional(txtQntProd.textProperty(), ctrlVendas.getQntidade(),(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtPrecoUnid.textProperty(), ctrlVendas.getTotal(),(StringConverter) new DoubleStringConverter());
//		Bindings.bindBidirectional(tableProd.accessibleTextProperty(), ctrlVendas.getCodProd(),(StringConverter) new DoubleStringConverter());
	}
	
	
	@SuppressWarnings("unchecked")
	public void vendaTableView() {
		TableColumn<Vendas, Integer> colQntdd = new TableColumn<>("Qtdade");
		colQntdd.setCellValueFactory(new PropertyValueFactory<Vendas, Integer>("qntidade"));
		
		TableColumn<Vendas, Double> colTotal = new TableColumn<>("Total");
		colTotal.setCellValueFactory(new PropertyValueFactory<Vendas, Double>("Total"));
		
		// ---------------------------------------------------------------------------------
		
		TableColumn<Vendas, String> colNomeProd = new TableColumn<>("Produto");
//		colNomeProd.setText("nossa");
		colNomeProd.setCellValueFactory(ctrlVendas.getCodProd());
		

		TableColumn<Vendas, Double> colPrecoUnit = new TableColumn<>("Preço unidade");
		colPrecoUnit.setCellValueFactory(new PropertyValueFactory<Vendas, Double>("precoUnit"));
		
		

		// ---------------------------------------------------------------------------------
		TableColumn<Vendas, Void> colAcoes = new TableColumn<>("Ações");
		Callback<TableColumn<Vendas, Void>, TableCell<Vendas, Void>> callBack = new Callback<>() {

			@Override
			public TableCell<Vendas, Void> call(TableColumn<Vendas, Void> col) {
				TableCell<Vendas, Void> tCell = new TableCell<>() {

					final Button btnExcluir = new Button("Excluir");
					{
						btnExcluir.setOnAction(e -> {
								Vendas v = tableVenda.getItems().get(getIndex());

								try {
									ctrlVendas.excluir(v.getIdVendas());
								} catch (ClassNotFoundException | SQLException e1) {
									e1.printStackTrace();
								}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnExcluir);
						}
					}
				};
				return tCell;
			}
		};

		colAcoes.setCellFactory(callBack);
		
		double quarto = 600.0 / 4.0;
		colQntdd.setPrefWidth(quarto);
		colNomeProd.setPrefWidth(quarto);
		colPrecoUnit.setPrefWidth(quarto);
		colTotal.setPrefWidth(quarto);
		colAcoes.setPrefWidth(quarto);

		// ---------------------------------------------------------------------------------
		tableVenda.getColumns().addAll(colQntdd, colTotal, colNomeProd, colPrecoUnit, colAcoes);
		tableVenda.setItems(ctrlVendas.getLista());

		tableVenda.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Vendas>() {
			@Override
			public void onChanged(Change<? extends Vendas> c) {
				if (!c.getList().isEmpty()) {
					ctrlVendas.fromEntity(c.getList().get(0));// arrumar no vendas=================
				}
			}
		});
	}
	
	// VALORES FINAIS
	private TextField txtPago = new TextField();
	private TextField txtTroco = new TextField();
	private TextField txtTotal = new TextField();

/*===================================================================================================================================
 																 START
===================================================================================================================================*/
	@Override
	public void start() {
		try {
			ctrlProd = new CtrlProduto();
			ctrlVendas = new CtrlVendas();
		} catch(SQLException | ClassNotFoundException e) { 
			Alert a = new Alert(AlertType.ERROR, "Erro ao acessar o banco de dados, contate o Administrador", ButtonType.OK);
			a.showAndWait();
		}
		
		principal = new AnchorPane();

//==================================================== PRODUTO ====================================================
		ligacoesProd();
		tableViewProd();

		// ---------------------------------------------------------------------------------

		Label lblProduto = new Label("PRODUTO");
		lblProduto.setCenterShape(true);
		lblProduto.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 20px; -fx-font-family: Arial;");

		Label lblCodProd = new Label("Codigo do Produto:");
		lblCodProd.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 12px; -fx-font-family: Arial;");

		Label lblPreco = new Label("Preco unidade:");
		lblPreco.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 12px; -fx-font-family: Arial;");

		Label lblNome = new Label("Nome:");
		lblNome.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 12px; -fx-font-family: Arial;");
		
		Label lblQntProd = new Label("Quantidade:");
		lblQntProd.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 12px; -fx-font-family: Arial;");

		Label lblDesc = new Label("Descricao:");
		lblDesc.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 12px; -fx-font-family: Arial;");
		
		// ---------------------------------------------------------------------------------
		
		AnchorPane.setTopAnchor(lblProduto, 15.0);
		AnchorPane.setLeftAnchor(lblProduto, 20.0);

		AnchorPane.setTopAnchor(lblCodProd, 50.0);
		AnchorPane.setLeftAnchor(lblCodProd, 20.0);

		AnchorPane.setTopAnchor(lblPreco, 50.0);
		AnchorPane.setLeftAnchor(lblPreco, 320.0);

		AnchorPane.setTopAnchor(lblNome, 50.0);
		AnchorPane.setLeftAnchor(lblNome, 510.0);

		AnchorPane.setTopAnchor(lblQntProd, 85.0);
		AnchorPane.setLeftAnchor(lblQntProd, 20.0);
		
		AnchorPane.setTopAnchor(lblDesc, 85.0);
		AnchorPane.setLeftAnchor(lblDesc, 150.0);

		// ---------------------------------------------------------------------------------
		AnchorPane.setTopAnchor(txtCodProd, 45.0);
		AnchorPane.setLeftAnchor(txtCodProd, 130.0);
		txtCodProd.setPrefWidth(185.0);

		AnchorPane.setTopAnchor(txtPrecoUnid, 45.0);
		AnchorPane.setLeftAnchor(txtPrecoUnid, 405.0);
		txtPrecoUnid.setPrefWidth(100.0);

		AnchorPane.setTopAnchor(txtNomeProd, 45.0);
		AnchorPane.setLeftAnchor(txtNomeProd, 550.0);
		AnchorPane.setRightAnchor(txtNomeProd, 20.0);
		
		AnchorPane.setTopAnchor(txtQntProd, 80.0);
		AnchorPane.setLeftAnchor(txtQntProd, 90.0);
		txtQntProd.setPrefWidth(50.0);

		AnchorPane.setTopAnchor(txtDescProd, 80.0);
		AnchorPane.setLeftAnchor(txtDescProd, 210.0);
		AnchorPane.setRightAnchor(txtDescProd, 20.0);
		
		// ---------------------------------------------------------------------------------
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> ctrlVendas.limpar());

		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> {
			try {
				ctrlProd.pesquisar();
			} catch (SQLException e1) {
				Alert a = new Alert(AlertType.ERROR, "Erro ao pesquisar no banco de dados, contate o Administrador",
						ButtonType.OK);
				a.showAndWait();
			}
		});

		AnchorPane.setTopAnchor(btnPesquisar, 120.0);
		AnchorPane.setRightAnchor(btnPesquisar, 140.0);
		btnPesquisar.setPrefWidth(100.0);

		AnchorPane.setTopAnchor(btnLimpar, 120.0);
		AnchorPane.setRightAnchor(btnLimpar, 20.0);
		btnLimpar.setPrefWidth(100.0);

		// ---------------------------------------------------------------------------------

		AnchorPane.setTopAnchor(tableProd, 160.0);
		AnchorPane.setLeftAnchor(tableProd, 20.0);
		AnchorPane.setRightAnchor(tableProd, 20.0);
		tableProd.setPrefHeight(120.0);
		
		
//==================================================== VENDA ====================================================		
		vendaTableView();
		
		AnchorPane.setTopAnchor(tableVenda, 300.0);
		AnchorPane.setLeftAnchor(tableVenda, 20.0);
		AnchorPane.setRightAnchor(tableVenda, 20.0);
		tableVenda.setPrefHeight(300.0);

		// ---------------------------------------------------------------------------------

		Label lblPago = new Label("PAGO:");
		lblPago.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 20px; -fx-font-family: Arial;");

		Label lblTroco = new Label("TROCO:");
		lblTroco.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 20px; -fx-font-family: Arial;");

		Label lblTotal = new Label("TOTAL:");
		lblTotal.setStyle("-fx-text-fill: '#c1c5d3'; -fx-font-size: 20px; -fx-font-family: Arial;");

		// ---------------------------------------------------------------------------------
		AnchorPane.setTopAnchor(lblPago, 610.0);
		AnchorPane.setRightAnchor(lblPago, 580.0);

		AnchorPane.setTopAnchor(lblTroco, 610.0);
		AnchorPane.setRightAnchor(lblTroco, 360.0);

		AnchorPane.setTopAnchor(lblTotal, 610.0);
		AnchorPane.setRightAnchor(lblTotal, 145.0);

		// ---------------------------------------------------------------------------------
		AnchorPane.setTopAnchor(txtPago, 610.0);
		AnchorPane.setRightAnchor(txtPago, 450.0);
		txtPago.setPrefWidth(120.0);

		AnchorPane.setTopAnchor(txtTroco, 610.0);
		AnchorPane.setRightAnchor(txtTroco, 230.0);
		txtTroco.setPrefWidth(120.0);

		AnchorPane.setTopAnchor(txtTotal, 610.0);
		AnchorPane.setRightAnchor(txtTotal, 20.0);
		txtTotal.setPrefWidth(120.0);

		// ---------------------------------------------------------------------------------
		principal.getChildren().addAll(lblProduto, lblCodProd, lblPreco, lblNome, lblQntProd, lblDesc, 
				txtCodProd, txtNomeProd, txtPrecoUnid, txtQntProd, txtDescProd, btnLimpar, btnPesquisar, 
				tableProd, tableVenda, 
				lblPago, lblTroco, lblTotal, txtPago, txtTroco, txtTotal);
	}

//===================================================================================================================================
	@Override
	public Pane render() {
		return principal;
	}

}
