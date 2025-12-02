package model;

import java.util.Date;

public class Entrada_SaidaModel {
    private int id_entra_pk;
    private Date entra_data_hora_entrada;
    private Date entra_data_hora_saida;
    private int id_vei_fk;

    // Construtores
    public Entrada_SaidaModel() {}

    public Entrada_SaidaModel(int id_entra_pk, Date entrada, Date saida, int id_vei_fk) {
        this.id_entra_pk = id_entra_pk;
        this.entra_data_hora_entrada = entrada;
        this.entra_data_hora_saida = saida;
        this.id_vei_fk = id_vei_fk;
    }

    // Getters e Setters
    public int getId_entra_pk() {
        return id_entra_pk;
    }

    public void setId_entra_pk(int id_entra_pk) {
        this.id_entra_pk = id_entra_pk;
    }

    public Date getEntra_data_hora_entrada() {
        return entra_data_hora_entrada;
    }

    public void setEntra_data_hora_entrada(Date entra_data_hora_entrada) {
        this.entra_data_hora_entrada = entra_data_hora_entrada;
    }

    public Date getEntra_data_hora_saida() {
        return entra_data_hora_saida;
    }

    public void setEntra_data_hora_saida(Date entra_data_hora_saida) {
        this.entra_data_hora_saida = entra_data_hora_saida;
    }

    public int getId_vei_fk() {
        return id_vei_fk;
    }

    public void setId_vei_fk(int id_vei_fk) {
        this.id_vei_fk = id_vei_fk;
    }
}

