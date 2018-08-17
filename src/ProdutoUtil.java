import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoUtil {

    public Object[] toArray(Produto prod){
        String nome = prod.getNome();
        String dataFab = formatarData(prod.getDataFabricacao());
        String estoque = String.valueOf(prod.getEstoque());
        String preco = formatarPreco(prod.getPreco());
        Object[] dados = {nome, dataFab, estoque, preco};
        return dados;
    }

    private String formatarData(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return data.format(formatter);
    }

    private String formatarPreco(Float preco){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMinimumFractionDigits(2);

        return formatter.format(preco);
    }
}
