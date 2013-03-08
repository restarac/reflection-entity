package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author w-tecianeli
 */
@Entity
@Table(name = "tipo_produto")
public class TipoProduto implements Serializable {

    @Id
    @Column(name = "codigo_tipo_produto", nullable = false)
    private String codigo;
    @Column(name = "nome_tipo_produto")
    private String nome;
    @Column(name = "descricao_tipo_produto")
    private String descricao;

    public TipoProduto() {
    }

    public TipoProduto(String codigo) {
        this.codigo = codigo;
    }

    public TipoProduto(String codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoProduto)) {
            return false;
        }
        TipoProduto other = (TipoProduto) object;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoProduto[codigo=" + codigo + " nome_tpp=" + nome + " descricao_tpp=" + descricao + "]";
    }
}