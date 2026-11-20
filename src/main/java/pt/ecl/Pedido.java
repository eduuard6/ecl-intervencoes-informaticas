package pt.ecl;

public class Pedido {
    public final int id;
    public final String nome, sala, equipamento, descricao, data, estado, observacao;

    public Pedido(int id, String nome, String sala, String equipamento, String descricao) {
        this(id, nome, sala, equipamento, descricao, "", "Por analisar", "");
    }

    public Pedido(int id, String nome, String sala, String equipamento, String descricao,
                  String data, String estado, String observacao) {
        this.id=id; this.nome=nome; this.sala=sala; this.equipamento=equipamento;
        this.descricao=descricao; this.data=data; this.estado=estado; this.observacao=observacao;
    }
}
