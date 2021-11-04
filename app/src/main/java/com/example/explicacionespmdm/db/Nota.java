package com.example.explicacionespmdm.db;

import com.orm.SugarRecord;


//Todo 2: Creamos la clase que se va a almacenar en la base de datos y se hereda de SugarRecord
// IMPORTANTE: La biblioteca, te creará una columna id autoincrementable como clave primaria.
public class Nota extends SugarRecord {


    private String mensaje;

    //Todo 2.1: IMPORTANTE! Se debe crear el constructor por defecto, aunque se deje "en blanco"
    public Nota() {
    }

    public Nota(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
