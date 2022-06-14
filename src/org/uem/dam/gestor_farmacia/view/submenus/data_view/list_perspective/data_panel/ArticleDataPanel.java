package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.uem.dam.gestor_farmacia.model.Article;

import net.miginfocom.swing.MigLayout;

public class ArticleDataPanel extends UpdateDataDefaultPanel implements RefreshableDataPanel<Article> {

	private static final long serialVersionUID = 1L;

	private JTextField aidTxt;
	private JTextField nameTxt;
	private JLabel priceLbl;
	private JSpinner priceSpn;
	private JLabel stockLbl;
	private JSpinner stockSpn;
	private JSpinner pidSpn;

	@Override
	public void initComponents() {
		super.initComponents();

		setBorder(new TitledBorder(null, "Article", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		editPanel.setLayout(new MigLayout("", "[right][grow]", "[][][][]"));
		JLabel aidLbl = new JLabel("Article ID");
		editPanel.add(aidLbl, "flowy,cell 0 0,alignx right,aligny center");

		aidTxt = new JTextField();
		editPanel.add(aidTxt, "cell 1 0,growx,aligny top");
		aidTxt.setColumns(10);

		JLabel pidLbl = new JLabel("Provider ID");
		editPanel.add(pidLbl, "cell 0 1,alignx right");
		pidSpn = new JSpinner();
		editPanel.add(pidSpn, "cell 1 1");

		JLabel nameLbl = new JLabel("Name");
		editPanel.add(nameLbl, "cell 0 2,alignx right");

		nameTxt = new JTextField();

		editPanel.add(nameTxt, "cell 1 2,growx");
		nameTxt.setColumns(10);

		priceLbl = new JLabel("Price");
		editPanel.add(priceLbl, "cell 0 3,alignx right");

		priceSpn = new JSpinner();
		editPanel.add(priceSpn, "cell 1 3");

		stockLbl = new JLabel("Stock");
		editPanel.add(stockLbl, "cell 0 4,alignx right");

		stockSpn = new JSpinner();
		editPanel.add(stockSpn, "cell 1 4");

	}

	@Override
	public void refreshData(Article article) {
		aidTxt.setText(Integer.toString(article.articleId()));
		pidSpn.setValue(article.providerId());
		nameTxt.setText(article.name());
		priceSpn.setValue(article.price());
		stockSpn.setValue(article.stock());
	}

	@Override
	public Article getInputItem() {
		return new Article(
				Integer.parseInt(aidTxt.getText()),
				(int) pidSpn.getValue(),
				nameTxt.getText(),
				Double.parseDouble(priceSpn.getValue().toString()),
				(int) stockSpn.getValue()
		);
	}

	@Override
	public void setEditsEnabled(boolean enabled) {
		aidTxt.setEnabled(enabled);
		nameTxt.setEnabled(enabled);
		priceLbl.setEnabled(enabled);
		priceSpn.setEnabled(enabled);
		stockLbl.setEnabled(enabled);
		stockSpn.setEnabled(enabled);
		pidSpn.setEnabled(enabled);
		updateBtn.setEnabled(enabled);
		removeBtn.setEnabled(enabled);
	}

//	@Override
//	public void updateListeners(ActionListener controller) {
//		// TODO Auto-generated method stub-
//
//	}

}
