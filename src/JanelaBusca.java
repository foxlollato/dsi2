import com.sun.deploy.panel.JreTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class JanelaBusca extends JInternalFrame{
    private JPanel painelBusca;
    private JLabel lblBusca;
    private JTextField txtBusca;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JButton btnBuscar;
    private JButton btnCancelar;
    private JButton btnProx;
    private JButton btnAnterior;
    private Container container;
    private int min =1, max=3, qtd=3;

    public JanelaBusca(){
        super("Busca de Produtos", true, true,true,true);

    criarComponentes();
    ajustarJanela();
    }
    private void criarComponentes(){
        painelBusca = new JPanel();
        lblBusca = new JLabel("Pesquisa nome");
        txtBusca = new JTextField(12);

        btnBuscar = new JButton("Buscar");
        btnCancelar = new JButton("Cancelar");
        btnProx = new JButton("Proximo");
        btnAnterior = new JButton("Anterior");

        btnProx.addActionListener(this::proximo);
        btnAnterior.addActionListener(this::anterior);

        btnBuscar.addActionListener(this::buscaPalavra);
        btnCancelar.addActionListener(e -> dispose());



        model = new DefaultTableModel();
        model.addColumn("NOME");
        model.addColumn("DATA FABRICACAO");
        model.addColumn("ESTOQUE");
        model.addColumn("PRECO");
        model.addColumn("TEMPO");
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        scroll = new JScrollPane();
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, 20*table.getRowHeight()));


        container = getContentPane();

        Box b1 = Box.createHorizontalBox();
        b1.add(Box.createHorizontalGlue());
        b1.add(lblBusca);
        b1.add(txtBusca);
        b1.add(Box.createHorizontalGlue());
        painelBusca.add(b1);

        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createHorizontalGlue());
        b2.add(btnBuscar);
        b2.add(btnCancelar);
        b2.add(Box.createHorizontalGlue());
        painelBusca.add(b2);

        Box b3 = Box.createHorizontalBox();
        b3.add(Box.createHorizontalGlue());
        b3.add(scroll);
        b3.add(Box.createHorizontalGlue());
        painelBusca.add(b3);

        Box b4 = Box.createHorizontalBox();
        b3.add(Box.createHorizontalGlue());
        b4.add(btnAnterior);
        b4.add(btnProx);
        b3.add(Box.createHorizontalGlue());
        painelBusca.add(b4);


        container.add(painelBusca);

    }
    private void proximo(ActionEvent actionEvent){
        min+=qtd; max+=qtd;
        model.setNumRows(0);
        ProdutoBD produtoBD = new ProdutoBD();
        ProdutoUtil pUtil = new ProdutoUtil();
        String busca = txtBusca.getText();
        List<Produto> lista;
        lista=produtoBD.listarTodos(min,max);

        lista.forEach(p -> model.addRow(pUtil.toArray(p)));
    }
    private void anterior(ActionEvent actionEvent){
        min-=qtd; max-=qtd;
        model.setNumRows(0);
        ProdutoBD produtoBD = new ProdutoBD();
        ProdutoUtil pUtil = new ProdutoUtil();
        String busca = txtBusca.getText();
        List<Produto> lista;
        lista=produtoBD.listarTodos(min,max);

        lista.forEach(p -> model.addRow(pUtil.toArray(p)));
    }
    private void buscaPalavra(ActionEvent actionEvent) {
        min=1;
        max=3;
        model.setNumRows(0);
        ProdutoBD produtoBD = new ProdutoBD();
        ProdutoUtil pUtil = new ProdutoUtil();
        String busca = txtBusca.getText();
        List<Produto> lista;
//        lista=produtoBD.listarProdutoPorNome(busca);
        lista=produtoBD.listarTodos(min,max);

//        for(Produto p: lista){
//           model.addRow(pUtil.toArray(p));
//        }

        lista.forEach(p -> model.addRow(pUtil.toArray(p)));
    }

    private void ajustarJanela(){
        setVisible(true);
        pack();
        setSize(800,600);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}


