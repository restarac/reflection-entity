package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author w-tecianeli
 */
@Entity
@Table(name = "sub_grupo_produto")
public class SubGrupoProduto implements Serializable {

    @EmbeddedId
    protected SubGrupoProdutoPK subGrupoProdutoPK;
    @Column(name = "nome_sub_grupo")
    private String nome;
    @Column(name = "descricao_sub_grupo")
    private String descricao;

    public SubGrupoProduto() {
    }

    public SubGrupoProduto(SubGrupoProdutoPK subGrupoProdutoPK, String nome, String descricao) {
        this.subGrupoProdutoPK = subGrupoProdutoPK;
        this.nome = nome;
        this.descricao = descricao;
    }

    public SubGrupoProduto(SubGrupoProdutoPK subGrupoProdutoPK) {
        this.subGrupoProdutoPK = subGrupoProdutoPK;
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
        hash += (this.subGrupoProdutoPK != null ? this.subGrupoProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubGrupoProduto)) {
            return false;
        }
        SubGrupoProduto other = (SubGrupoProduto) object;
        if (this.subGrupoProdutoPK != other.subGrupoProdutoPK && (this.subGrupoProdutoPK == null || !this.subGrupoProdutoPK.equals(other.subGrupoProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubGrupoProduto[subGrupoProdutoPK=" + subGrupoProdutoPK + " nome_sg=" + nome + " descricao_sg=" + descricao
                + "]";
    }
}
