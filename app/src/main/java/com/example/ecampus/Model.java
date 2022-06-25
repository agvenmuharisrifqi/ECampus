package com.example.ecampus;

public class Model {
    String dNim, dNama;
    public Model(String nim, String nama){
        this.dNim = nim;
        this.dNama = nama;
    }

    public String getNim(){
        return dNim;
    }
    public String getNama(){
        return dNama;
    }
}
