package model;

public class TarifasModel {
    private int idTarifPk;
    private String tarifTipo;
    private double tarifValor;
    private String tarifPeriodo;
    private int idEstaFk;

    // Construtores
    public TarifasModel() {}

    public TarifasModel(int idTarifPk, String tarifTipo, double tarifValor, String tarifPeriodo, int idEstaFk) {
        this.idTarifPk = idTarifPk;
        this.tarifTipo = tarifTipo;
        this.tarifValor = tarifValor;
        this.tarifPeriodo = tarifPeriodo;
        this.idEstaFk = idEstaFk;
    }

    // Getters e Setters
    public int getIdTarifPk() { return idTarifPk; }
    public void setIdTarifPk(int idTarifPk) { this.idTarifPk = idTarifPk; }

    public String getTarifTipo() { return tarifTipo; }
    public void setTarifTipo(String tarifTipo) { this.tarifTipo = tarifTipo; }

    public double getTarifValor() { return tarifValor; }
    public void setTarifValor(double tarifValor) { this.tarifValor = tarifValor; }

    public String getTarifPeriodo() { return tarifPeriodo; }
    public void setTarifPeriodo(String tarifPeriodo) { this.tarifPeriodo = tarifPeriodo; }

    public int getIdEstaFk() { return idEstaFk; }
    public void setIdEstaFk(int idEstaFk) { this.idEstaFk = idEstaFk; }
}

