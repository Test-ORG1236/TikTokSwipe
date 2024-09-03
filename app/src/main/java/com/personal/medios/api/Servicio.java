package com.personal.medios.api;

public class Servicio {

    private int idtipo_servicio;
    private String tipo_servicio, descripcion, url_imagen;

    public int getIdtipo_servicio() {
        return idtipo_servicio;
    }

    public void setIdtipo_servicio(int idtipo_servicio) {
        this.idtipo_servicio = idtipo_servicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }
}
