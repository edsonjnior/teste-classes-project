package br.com.edsondev.enums;

public enum Segmento {
    COLEGIO("Colégio"),
    VESTIBULAR("Vestibular"),
    ENSINO_SUPERIOR("Ensino Superior");

    private String descricao;

    Segmento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
