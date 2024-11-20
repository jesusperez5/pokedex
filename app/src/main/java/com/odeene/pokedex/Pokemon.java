package com.odeene.pokedex;

import java.util.ArrayList;

public class Pokemon {
    private String nombre;
    private int numero;
    private tipo[] tipos;
    private String descripcion;
    private int imagen;
    public enum tipo{
        PLANTA, FUEGO, AGUA, VENENO, VOLADOR, BICHO, LUCHA, PSIQUICO, HIELO, ROCA
    }

    public Pokemon(String nombre, int numero, tipo[] tipos, String descripcion, int imagen) {
        this.nombre = nombre;
        this.numero = numero;
        this.tipos = tipos;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public tipo[] getTipos() {
        return tipos;
    }

    public void setTipos(tipo[] tipos) {
        this.tipos = tipos;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", tipos=" + tipos +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
