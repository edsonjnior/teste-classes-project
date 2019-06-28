package br.com.edsondev.enums;

public enum TipoArquivo {
    ROTEIRO("Roteiro"), RESULTADO("Resultado");

    private String descricao;

    TipoArquivo(String descricao) {
	this.descricao = descricao;
    }

    public String getDescricao() {
	return descricao;
    }

}
