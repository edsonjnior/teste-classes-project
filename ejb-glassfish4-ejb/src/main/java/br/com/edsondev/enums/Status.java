package br.com.edsondev.enums;

public enum Status {
    EM_ANDAMENTO("Em andamento"),
    ENCERRADO("Encerrado"),
    CONCLUIDO("Concluído"),
    RASCUNHO("Rascunto"),
    INATIVO("Inativo");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
