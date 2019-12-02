package com.example.eva3_examen;

public class Rolas {
    private int imagen;
    private String autor;
    private String nombre;
    private int rola;
    private double duracion;

    public Rolas(){
        imagen = R.drawable.unkown;
        autor = "unknow";
        nombre = "unknow";
        rola = R.raw.november_rain;
    }

    public Rolas(int imagen, String autor, String nombre,int rola, double duracion) {
        this.imagen = imagen;
        this.autor = autor;
        this.nombre = nombre;
        this.rola = rola;
        this.duracion = duracion;
}

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRola() {
        return rola;
    }

    public void setRola(int rola) {
        this.rola = rola;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
