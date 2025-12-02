package model;

public class VeiculosModel {
    private int idVeiPk;
    private String veiModelo;
    private String veiCor;
    private String veiMarca;
    private String veiPlaca;

    // Construtores
    public VeiculosModel() {}

    public VeiculosModel(int idVeiPk, String veiModelo, String veiCor, String veiMarca, String veiPlaca) {
        this.idVeiPk = idVeiPk;
        this.veiModelo = veiModelo;
        this.veiCor = veiCor;
        this.veiMarca = veiMarca;
        this.veiPlaca = veiPlaca;
    }

    // Getters e Setters
    public int getIdVeiPk() { return idVeiPk; }
    public void setIdVeiPk(int idVeiPk) { this.idVeiPk = idVeiPk; }

    public String getVeiModelo() { return veiModelo; }
    public void setVeiModelo(String veiModelo) { this.veiModelo = veiModelo; }

    public String getVeiCor() { return veiCor; }
    public void setVeiCor(String veiCor) { this.veiCor = veiCor; }

    public String getVeiMarca() { return veiMarca; }
    public void setVeiMarca(String veiMarca) { this.veiMarca = veiMarca; }

    public String getVeiPlaca() { return veiPlaca; }
    public void setVeiPlaca(String veiPlaca) { this.veiPlaca = veiPlaca; }
}

