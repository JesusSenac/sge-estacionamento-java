package model;


public class FuncionariosModel {
    private int id_func_pk;
    private String func_nome;
    private String func_funcao;
    private String func_jornada_de_trabalho;
    private String func_login;
    private String func_senha;
    private double func_salario;
    private int id_esta_fk;

    // Getters e Setters
    public int getId_func_pk() {
        return id_func_pk;
    }
    public void setId_func_pk(int id_func_pk) {
        this.id_func_pk = id_func_pk;
    }

    public String getFunc_nome() {
        return func_nome;
    }
    public void setFunc_nome(String func_nome) {
        this.func_nome = func_nome;
    }

    public String getFunc_funcao() {
        return func_funcao;
    }
    public void setFunc_funcao(String func_funcao) {
        this.func_funcao = func_funcao;
    }

    public String getFunc_jornada_de_trabalho() {
        return func_jornada_de_trabalho;
    }
    public void setFunc_jornada_de_trabalho(String func_jornada_de_trabalho) {
        this.func_jornada_de_trabalho = func_jornada_de_trabalho;
    }

    public String getFunc_login() {
        return func_login;
    }
    public void setFunc_login(String func_login) {
        this.func_login = func_login;
    }

    public String getFunc_senha() {
        return func_senha;
    }
    public void setFunc_senha(String func_senha) {
        this.func_senha = func_senha;
    }

    public double getFunc_salario() {
        return func_salario;
    }
    public void setFunc_salario(double func_salario) {
        this.func_salario = func_salario;
    }

    public int getId_esta_fk() {
        return id_esta_fk;
    }
    public void setId_esta_fk(int id_esta_fk) {
        this.id_esta_fk = id_esta_fk;
    }
}
