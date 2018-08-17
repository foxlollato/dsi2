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
    private JLabel lblMin;
    private JTextField txtMin;
    private JLabel lblMax;
    private JTextField txtMax;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JButton btnBuscar;
    private JButton btnCancelar;
    private JButton btnProx;
    private JButton btnAnterior;
    private Container container;
    List<Produto> lista = null;
    private int min =1, max=100, qtd=45;

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

        lblMin = new JLabel("Minimo");
        txtMin = new JTextField(4);
        lblMax = new JLabel("Maximo");
        txtMax = new JTextField(4);
        txtMax.setEditable(false);
        txtMin.addActionListener(e->{
            txtMax.setText(String.valueOf(Integer.parseInt(txtMin.getText())+9));
        });


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

//        Box b1 = Box.createHorizontalBox();
//        b1.add(lblBusca);
//        b1.add(txtBusca);
//        painelBusca.add(b1);

        Box b2 = Box.createHorizontalBox();
        b2.add(btnBuscar);
        b2.add(btnCancelar);
        painelBusca.add(b2);

        Box b3 = Box.createHorizontalBox();
        b3.add(scroll);
        painelBusca.add(b3);

        Box b4 = Box.createHorizontalBox();
        b4.add(btnAnterior);
        b4.add(btnProx);
        b4.add(lblMin);
        b4.add(txtMin);
        b4.add(lblMax);
        b4.add(txtMax);
        painelBusca.add(b4);


        container.add(painelBusca);

    }
    private void proximo(ActionEvent actionEvent){
        min+=10; max +=10;
        model.setNumRows(0);
        ProdutoUtil pUtil = new ProdutoUtil();

        for(int i = 0 ; i < 10; i++){
            model.addRow(pUtil.toArray(lista.get(i+min-1)));
        }
        System.out.println(lista.size());
    }
    private void anterior(ActionEvent actionEvent){
        min-=10; max -=10;
        model.setNumRows(0);
        ProdutoUtil pUtil = new ProdutoUtil();

        for(int i = 0 ; i < 10; i++){
            model.addRow(pUtil.toArray(lista.get(i+min-1)));
        }
        System.out.println(lista.size());
    }
    private void buscaPalavra(ActionEvent actionEvent) {
        min = Integer.parseInt(txtMin.getText());
        max = Integer.parseInt(txtMax.getText());
        model.setNumRows(0);
        ProdutoBD produtoBD = new ProdutoBD();
        ProdutoUtil pUtil = new ProdutoUtil();


        if(lista == null){
            if(min <=46){
                lista=produtoBD.listarTodos(1,100);
            }else{
                lista=produtoBD.listarTodos(min-qtd,max+qtd);
            }

        }else if(lista.size() < max){
                lista=null;
                lista = produtoBD.listarTodos(min-qtd,max+qtd);
        }else if(min <=46){
            lista=produtoBD.listarTodos(1,100);
        }

        if(min < 46) {
            for(int i = min ; i < min+10; i++){
                model.addRow(pUtil.toArray(lista.get(i-1)));
            }
        }else{
                min = 46; max = 56;
                for(int i = min; i < max; i++){
                    model.addRow(pUtil.toArray(lista.get(i-1)));
            }

        }
    }

    private void ajustarJanela(){
        setVisible(true);
        pack();
        setSize(800,600);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}


