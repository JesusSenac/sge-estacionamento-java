package model;

public class ClientesModel {
    private int id_cli_pk;
    private String cli_nome;
    private String cli_cpf;
    private String cli_telefone;
    private int id_esta_fk;

    // Construtores
    public ClientesModel() {}

    public ClientesModel(int id_cli_pk, String cli_nome, String cli_cpf, String cli_telefone, int id_esta_fk) {
        this.id_cli_pk = id_cli_pk;
        this.cli_nome = cli_nome;
        this.cli_cpf = cli_cpf;
        this.cli_telefone = cli_telefone;
        this.id_esta_fk = id_esta_fk;
    }

    // Getters e Setters
    public int getId_cli_pk() { return id_cli_pk; }
    public void setId_cli_pk(int id_cli_pk) { this.id_cli_pk = id_cli_pk; }

    public String getCli_nome() { return cli_nome; }
    public void setCli_nome(String cli_nome) { this.cli_nome = cli_nome; }

    public String getCli_cpf() { return cli_cpf; }
    public void setCli_cpf(String cli_cpf) { this.cli_cpf = cli_cpf; }

    public String getCli_telefone() { return cli_telefone; }
    public void setCli_telefone(String cli_telefone) { this.cli_telefone = cli_telefone; }

    public int getId_esta_fk() { return id_esta_fk; }
    public void setId_esta_fk(int id_esta_fk) { this.id_esta_fk = id_esta_fk; }
}
