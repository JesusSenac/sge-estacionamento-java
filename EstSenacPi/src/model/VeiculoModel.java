package model;


import java.util.Date;

public class VeiculoModel {
    private int idVeiPk;
    private String veiNome;
    private String veiModelo;
    private String veiPlaca;
    private String veiCor;
    private String veiTel;
    private String veiHora;
    private Date veiDtEntrada;
    private String veiVaga;
    private String veiAndar;

    // Getters e Setters
    public int getIdVeiPk() {
        return idVeiPk;
    }
    public void setIdVeiPk(int idVeiPk) {
        this.idVeiPk = idVeiPk;
    }

    public String getVeiNome() {
        return veiNome;
    }
    public void setVeiNome(String veiNome) {
        this.veiNome = veiNome;
    }

    public String getVeiModelo() {
        return veiModelo;
    }
    public void setVeiModelo(String veiModelo) {
        this.veiModelo = veiModelo;
    }

    public String getVeiPlaca() {
        return veiPlaca;
    }
    public void setVeiPlaca(String veiPlaca) {
        this.veiPlaca = veiPlaca;
    }

    public String getVeiCor() {
        return veiCor;
    }
    public void setVeiCor(String veiCor) {
        this.veiCor = veiCor;
    }

    public String getVeiTel() {
        return veiTel;
    }
    public void setVeiTel(String veiTel) {
        this.veiTel = veiTel;
    }

    public String getVeiHora() {
        return veiHora;
    }
    public void setVeiHora(String veiHora) {
        this.veiHora = veiHora;
    }

    public Date getVeiDtEntrada() {
        return veiDtEntrada;
    }
    public void setVeiDtEntrada(Date veiDtEntrada) {
        this.veiDtEntrada = veiDtEntrada;
    }

    public String getVeiVaga() {
        return veiVaga;
    }
    public void setVeiVaga(String veiVaga) {
        this.veiVaga = veiVaga;
    }

    public String getVeiAndar() {
        return veiAndar;
    }
    public void setVeiAndar(String veiAndar) {
        this.veiAndar = veiAndar;
    }
}

