package model;

public class EnderecoModel {

    // Atributos privados (correspondem às colunas da tabela)
    private int id_end_pk;
    private String end_rua;
    private String end_bairro;
    private String end_cidade;
    private String end_pais;

    // Construtor padrão (necessário para JavaBeans)
    public EnderecoModel() {
    }

    // Construtor completo
    public EnderecoModel(int id_end_pk, String end_rua, String end_bairro, String end_cidade, String end_pais) {
        this.id_end_pk = id_end_pk;
        this.end_rua = end_rua;
        this.end_bairro = end_bairro;
        this.end_cidade = end_cidade;
        this.end_pais = end_pais;
    }
    
    // Construtor para inserção (sem a chave primária auto_increment)
    public EnderecoModel(String end_rua, String end_bairro, String end_cidade, String end_pais) {
        this.end_rua = end_rua;
        this.end_bairro = end_bairro;
        this.end_cidade = end_cidade;
        this.end_pais = end_pais;
    }

    // Métodos Getters e Setters
    public int getId_end_pk() {
        return id_end_pk;
    }

    public void setId_end_pk(int id_end_pk) {
        this.id_end_pk = id_end_pk;
    }

    public String getEnd_rua() {
        return end_rua;
    }

    public void setEnd_rua(String end_rua) {
        this.end_rua = end_rua;
    }

    public String getEnd_bairro() {
        return end_bairro;
    }

    public void setEnd_bairro(String end_bairro) {
        this.end_bairro = end_bairro;
    }

    public String getEnd_cidade() {
        return end_cidade;
    }

    public void setEnd_cidade(String end_cidade) {
        this.end_cidade = end_cidade;
    }

    public String getEnd_pais() {
        return end_pais;
    }

    public void setEnd_pais(String end_pais) {
        this.end_pais = end_pais;
    }
    
    // Opcional: Para facilitar a visualização no console
    @Override
    public String toString() {
        return "ID: " + id_end_pk + ", Rua: " + end_rua + 
               ", Bairro: " + end_bairro + ", Cidade: " + end_cidade + 
               ", País: " + end_pais;
    }
}

