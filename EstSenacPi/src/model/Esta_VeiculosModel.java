package model;


public class Esta_VeiculosModel {
    private int id_esta_vei_pk;
    private int id_esta_fk;
    private int id_vei_fk;

    // Construtor vazio
    public Esta_VeiculosModel() {}

    // Construtor completo
    public Esta_VeiculosModel(int id_esta_vei_pk, int id_esta_fk, int id_vei_fk) {
        this.id_esta_vei_pk = id_esta_vei_pk;
        this.id_esta_fk = id_esta_fk;
        this.id_vei_fk = id_vei_fk;
    }

    // Getters e Setters
    public int getId_esta_vei_pk() {
        return id_esta_vei_pk;
    }

    public void setId_esta_vei_pk(int id_esta_vei_pk) {
        this.id_esta_vei_pk = id_esta_vei_pk;
    }

    public int getId_esta_fk() {
        return id_esta_fk;
    }

    public void setId_esta_fk(int id_esta_fk) {
        this.id_esta_fk = id_esta_fk;
    }

    public int getId_vei_fk() {
        return id_vei_fk;
    }

    public void setId_vei_fk(int id_vei_fk) {
        this.id_vei_fk = id_vei_fk;
    }
}

