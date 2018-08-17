import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoBD {

    public void salvar(Produto prod){
        String sql = "insert into produto(id, nome, dataFabricacao, estoque, preco) values (produtoId_seq.NEXTVAL ,?, ?, ?, ?)";
        ConexaoBD conexao = ConexaoBD.getInstance();

        try(
                Connection con = conexao.getConection();
                PreparedStatement pStat = con.prepareStatement(sql, new String[]{"id"})
                ) {
            pStat.setString(1, prod.getNome());
            pStat.setDate(2, Date.valueOf(prod.getDataFabricacao()));
            pStat.setInt(3, prod.getEstoque());
            pStat.setFloat(4, prod.getPreco());
            pStat.executeUpdate();

            try(ResultSet rs = pStat.getGeneratedKeys()) {
                if(rs.next()){
                    prod.setId(rs.getInt(1));
                }
            }


        }catch(SQLException error){
                    throw new RuntimeException(error);

        }

    }

    public List<Produto> listarProdutoPorNome(String query){
        String sql = "select id, nome, dataFabricacao, estoque, preco from produto where UPPER(nome) LIKE UPPER(?)";
        ConexaoBD conexao = ConexaoBD.getInstance();
        List<Produto> lista = new ArrayList<>();
        try(
                Connection con = conexao.getConection();
                PreparedStatement pStat = con.prepareStatement(sql))
        {
            pStat.setString(1, "%"+query+"%");
            try (ResultSet rs = pStat.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt(1);
                    String nome= rs.getString(2);
//                    LocalDate dataFabricacao = LocalDate.parse(rs.getDate(3).toString());
                    LocalDate dataFabricacao = rs.getDate(3).toLocalDate();
                    int estoque = rs.getInt(4);
                    float preco = rs.getFloat(5);
                    lista.add(new Produto(id, nome, dataFabricacao, estoque, preco));}
            }
            return lista;
        }catch(SQLException error){
            throw new RuntimeException(error);
        }
    }

    public List<Produto>  listarTodos(int min, int max) {
//        String sql = "select id, nome, dataFabricacao, estoque, preco from produto";
        String sql = String.format("SELECT * FROM (SELECT E.*, ROWNUM RN FROM\n" +
                "                (SELECT * FROM produto\n" +
                "                ORDER BY id) E\n" +
                "                WHERE ROWNUM <= %d) WHERE RN >= %d",max,min);

        ConexaoBD conexao = ConexaoBD.getInstance();
        List<Produto> lista = new ArrayList<>();
        try(
                Connection con = conexao.getConection();
                PreparedStatement pStat = con.prepareStatement(sql);
                ResultSet rs = pStat.executeQuery()){

            while(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                LocalDate dataFabricacao = LocalDate.parse(rs.getDate(3).toString());
                int estoque = rs.getInt(4);
                float preco = rs.getFloat(5);
                lista.add(new Produto(id, nome, dataFabricacao, estoque, preco));
        }
        return lista;
        }catch(SQLException error){
            throw new RuntimeException(error);
        }
    }

}
