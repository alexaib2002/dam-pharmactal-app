package org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.view.DefaultComponent;

import net.miginfocom.swing.MigLayout;

public class ArticleDataPanel extends DefaultComponent implements InspectorDataPanel<Article> {

	private static final long serialVersionUID = 1L;

	private JTextField aidTxt;
	private JTextField nameTxt;
	private JLabel priceLbl;
	private JSpinner priceSpn;
	private JLabel stockLbl;
	private JSpinner stockSpn;

	@Override
	public void initComponents() {
		setBorder(new TitledBorder(null, "Article", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[right][grow]", "[][][][]"));

		JLabel aidLbl = new JLabel("Article ID");
		this.add(aidLbl, "flowy,cell 0 0,alignx right,aligny center");

		aidTxt = new JTextField();
		this.add(aidTxt, "cell 1 0,growx,aligny top");
		aidTxt.setColumns(10);

		JLabel nameLbl = new JLabel("Name");
		this.add(nameLbl, "cell 0 1,alignx right");

		nameTxt = new JTextField();
		this.add(nameTxt, "cell 1 1,growx");
		nameTxt.setColumns(10);

		priceLbl = new JLabel("Price");
		add(priceLbl, "cell 0 2,alignx right");

		priceSpn = new JSpinner();
		add(priceSpn, "cell 1 2");

		stockLbl = new JLabel("Stock");
		add(stockLbl, "cell 0 3,alignx right");

		stockSpn = new JSpinner();
		add(stockSpn, "cell 1 3");
	}

	@Override
	public void refreshData(Article article) {
		aidTxt.setText(Integer.toString(article.articleId()));
		nameTxt.setText(article.name());
		priceSpn.setValue(article.price());
		stockSpn.setValue(article.stock());
	}
}
