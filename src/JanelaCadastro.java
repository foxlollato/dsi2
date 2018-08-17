import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;

public class JanelaCadastro extends JInternalFrame {
    private JPanel painelCadastro;
    private JLabel labelId, labelNome, labelDataFabricacao, labelEstoque, labelPreco;
    private JTextField txtId, txtNome,  txtEstoque, txtPreco;
    private JFormattedTextField txtDataFabricacao;
    private JButton btnCadastrar, btnCancelar;
    private Container container;

    public JanelaCadastro() {
        super("Cadastro de Produtos", true, true,false,true);

        criarComponentes();
        ajustarJanela();
    }
    private void criarComponentes(){

        painelCadastro = new JPanel();

        labelId =  new JLabel("ID");
        labelNome = new JLabel("Nome");
        labelDataFabricacao = new JLabel("Fabricacao");
        labelEstoque = new JLabel("Estoque");
        labelPreco = new JLabel("Preco");
        txtId = new JTextField(2);
        txtId.setEditable(false);
        txtNome = new JTextField(12);
        txtDataFabricacao = new JFormattedTextField();
        txtDataFabricacao.setColumns(7);

        try {
            MaskFormatter dateMask;
            dateMask = new MaskFormatter("##/##/####");
            dateMask.setValidCharacters("1234567890");
            dateMask.install(txtDataFabricacao);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtEstoque = new JTextField(4);
        txtPreco = new JTextField(8);


        btnCadastrar = new JButton("Cadastrar");
        btnCancelar = new JButton("Cancelar");

        btnCadastrar.addActionListener(this::cadastrarProduto);
        btnCancelar.addActionListener(e -> dispose());

        container = getContentPane();

        Box b1 = Box.createHorizontalBox();
        b1.add(Box.createHorizontalGlue());
        b1.add(labelId);
        b1.add(txtId);

        b1.add(labelNome);
        b1.add(txtNome);

        b1.add(labelDataFabricacao);
        b1.add(txtDataFabricacao);


        b1.add(labelEstoque);
        b1.add(txtEstoque);

        b1.add(labelPreco);
        b1.add(txtPreco);
        b1.add(Box.createHorizontalGlue());
        painelCadastro.add(b1);


        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createHorizontalGlue());
        b2.add(btnCadastrar);
        b2.add(btnCancelar);
        b2.add(Box.createHorizontalGlue());
        painelCadastro.add(b2);

        container.add(painelCadastro);

    }

    private void ajustarJanela(){
        setVisible(true);
        pack();
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private void cadastrarProduto(ActionEvent e){
            ProdutoBD produtoBd;
            produtoBd = new ProdutoBD();
            String nome = txtNome.getText();
            LocalDate dataFabricacao = LocalDate.parse(txtDataFabricacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int estoque = Integer.parseInt(txtEstoque.getText());
            float preco = Float.parseFloat(txtPreco.getText());
            Produto prod = new Produto(nome, dataFabricacao, estoque, preco);
            produtoBd.salvar(prod);
            txtId.setText(String.valueOf(prod.getId()));

    }

}
