package com.farmadif.neri.afarmadif.model;

public class MedicamentModel {

    private String nombre_comercial;

    private String nombre_compuesto;

    private String num_folio;

    private String tipo_contenido;

    private String num_etiqueta;

    private int anio_caducidad;

    private int mes_caducidad;

    private String estatus;

    private String solucion_tableta;

    private String fecha_registro;

    private int id_medicamento;

    private int dosis;

    public MedicamentModel() {}

    public MedicamentModel(String nombre_comercial, String nombre_compuesto, int dosis) {
        this.nombre_comercial = nombre_comercial;
        this.nombre_compuesto = nombre_compuesto;
        this.dosis = dosis;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public String getNombre_compuesto() {
        return nombre_compuesto;
    }

    public int getDosis() {
        return dosis;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public void setNombre_compuesto(String nombre_compuesto) {
        this.nombre_compuesto = nombre_compuesto;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public String getNum_folio() {
        return num_folio;
    }

    public void setNum_folio(String num_folio) {
        this.num_folio = num_folio;
    }

    public String getTipo_contenido() {
        return tipo_contenido;
    }

    public void setTipo_contenido(String tipo_contenido) {
        this.tipo_contenido = tipo_contenido;
    }

    public int getAnio_caducidad() {
        return anio_caducidad;
    }

    public void setAnio_caducidad(int anio_caducidad) {
        this.anio_caducidad = anio_caducidad;
    }

    public int getMes_caducidad() {
        return mes_caducidad;
    }

    public void setMes_caducidad(int mes_caducidad) {
        this.mes_caducidad = mes_caducidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getSolucion_tableta() {
        return solucion_tableta;
    }

    public void setSolucion_tableta(String solucion_tableta) {
        this.solucion_tableta = solucion_tableta;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getNum_etiqueta() {
        return num_etiqueta;
    }

    public void setNum_etiqueta(String num_etiqueta) {
        this.num_etiqueta = num_etiqueta;
    }
}
