package model;


public class EstacionamentoModel {
    private int idEstaPk;
    private String estaNome;
    private int estaCnpj;
    private String estaNomeFantasia;
    private String estaRazaoSocial;
    private String estaRepresentante;
    private int idEndFk;

    // Construtor vazio
    public EstacionamentoModel() {}

    // Construtor completo
    public EstacionamentoModel(int idEstaPk, String estaNome, int estaCnpj, String estaNomeFantasia,
                               String estaRazaoSocial, String estaRepresentante, int idEndFk) {
        this.idEstaPk = idEstaPk;
        this.estaNome = estaNome;
        this.estaCnpj = estaCnpj;
        this.estaNomeFantasia = estaNomeFantasia;
        this.estaRazaoSocial = estaRazaoSocial;
        this.estaRepresentante = estaRepresentante;
        this.idEndFk = idEndFk;
    }

    // Getters e Setters
    public int getIdEstaPk() { return idEstaPk; }
    public void setIdEstaPk(int idEstaPk) { this.idEstaPk = idEstaPk; }

    public String getEstaNome() { return estaNome; }
    public void setEstaNome(String estaNome) { this.estaNome = estaNome; }

    public int getEstaCnpj() { return estaCnpj; }
    public void setEstaCnpj(int estaCnpj) { this.estaCnpj = estaCnpj; }

    public String getEstaNomeFantasia() { return estaNomeFantasia; }
    public void setEstaNomeFantasia(String estaNomeFantasia) { this.estaNomeFantasia = estaNomeFantasia; }

    public String getEstaRazaoSocial() { return estaRazaoSocial; }
    public void setEstaRazaoSocial(String estaRazaoSocial) { this.estaRazaoSocial = estaRazaoSocial; }

    public String getEstaRepresentante() { return estaRepresentante; }
    public void setEstaRepresentante(String estaRepresentante) { this.estaRepresentante = estaRepresentante; }

    public int getIdEndFk() { return idEndFk; }
    public void setIdEndFk(int idEndFk) { this.idEndFk = idEndFk; }
}
