package model;

public class VagasModel {
    private int idVagaPk;
    private String vagaCodigo;
    private String vagaStatus;
    private boolean vagaPreferencial;
    private int vagaAndar;
    private String vagaTipo;
    private int idEstaFk;

    // Construtor vazio
    public VagasModel() {}

    // Construtor completo
    public VagasModel(int idVagaPk, String vagaCodigo, String vagaStatus, boolean vagaPreferencial,
                      int vagaAndar, String vagaTipo, int idEstaFk) {
        this.idVagaPk = idVagaPk;
        this.vagaCodigo = vagaCodigo;
        this.vagaStatus = vagaStatus;
        this.vagaPreferencial = vagaPreferencial;
        this.vagaAndar = vagaAndar;
        this.vagaTipo = vagaTipo;
        this.idEstaFk = idEstaFk;
    }

    // Getters e Setters
    public int getIdVagaPk() { return idVagaPk; }
    public void setIdVagaPk(int idVagaPk) { this.idVagaPk = idVagaPk; }

    public String getVagaCodigo() { return vagaCodigo; }
    public void setVagaCodigo(String vagaCodigo) { this.vagaCodigo = vagaCodigo; }

    public String getVagaStatus() { return vagaStatus; }
    public void setVagaStatus(String vagaStatus) { this.vagaStatus = vagaStatus; }

    public boolean isVagaPreferencial() { return vagaPreferencial; }
    public void setVagaPreferencial(boolean vagaPreferencial) { this.vagaPreferencial = vagaPreferencial; }

    public int getVagaAndar() { return vagaAndar; }
    public void setVagaAndar(int vagaAndar) { this.vagaAndar = vagaAndar; }

    public String getVagaTipo() { return vagaTipo; }
    public void setVagaTipo(String vagaTipo) { this.vagaTipo = vagaTipo; }

    public int getIdEstaFk() { return idEstaFk; }
    public void setIdEstaFk(int idEstaFk) { this.idEstaFk = idEstaFk; }
}

