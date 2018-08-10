import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Produto {
    private int id;
    private String nome;
    private LocalDate dataFabricacao;
    private int estoque;
    private float preco;

    public Produto(int id, String nome, LocalDate dataFabricacao, int estoque, float preco) {
        this.id = id;
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.estoque = estoque;
        this.preco = preco;
    }

    public Produto(String nome, LocalDate dataFabricacao, int estoque, float preco) {
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.estoque = estoque;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "id: "+this.getId()+" nome: "+this.getNome()+" data: "+this.getDataFabricacao()+" estoque: "+this.getEstoque()+"preco: "+this.getPreco();
    }
}
