import javax.naming.Context;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame{

    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemCadastro, itemBusca, itemSair;
    private JDesktopPane desktop;

    private JanelaCadastro janelaCadastro;
    private JanelaBusca janelaBusca;

    public JanelaPrincipal() {
        super("Controle de Produtos");
        criarComponentes();
        ajustarJanela();
    }

    private static void actionPerformed2(ActionEvent e) {
        System.exit(0);
    }

    private void criarComponentes() {
        desktop = new JDesktopPane();
        barraMenu = new JMenuBar();

        //Menu Principal
        menu = new JMenu("Produto");

        //Sub-menus dentro do menu principal
        itemCadastro = new JMenuItem("Cadastrar Produto");
        itemCadastro.addActionListener(e -> {
            janelaCadastro = new JanelaCadastro();
            desktop.add(janelaCadastro);
            desktop.moveToFront(janelaCadastro);
        });

        itemBusca = new JMenuItem("Buscar Produto");
        itemBusca.addActionListener(e -> {
            janelaBusca = new JanelaBusca();
            desktop.add(janelaBusca);
            desktop.moveToFront(janelaBusca);
        });

        itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));

        menu.add(itemCadastro);
        menu.add(itemBusca);
        menu.addSeparator();
        menu.add(itemSair);
        barraMenu.add(menu);

        setJMenuBar(barraMenu);

        add(desktop);
    }

    private void ajustarJanela() {
        setVisible(true);
        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
