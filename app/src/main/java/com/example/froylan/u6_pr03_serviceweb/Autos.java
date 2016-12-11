package com.example.froylan.u6_pr03_serviceweb;

/**
 * Created by froylan on 24/11/16.
 */

public class Autos {

     int id;
     String marca,modelo,color,esp,anio;
     double costo;

     public Autos(int id, String marca, String modelo, String color, String esp, String anio, double costo) {
          this.id = id;
          this.marca = marca;
          this.modelo = modelo;
          this.color = color;
          this.esp = esp;
          this.anio = anio;
          this.costo = costo;
     }

    @Override
    public String toString() {
        return "Autos{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", esp='" + esp + '\'' +
                ", anio='" + anio + '\'' +
                ", costo=" + costo +
                '}';
    }
}
