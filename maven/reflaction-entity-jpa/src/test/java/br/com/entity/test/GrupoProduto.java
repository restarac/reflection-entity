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
@Table(name = "grupo_produto")
public class GrupoProduto implements Serializable {

    @EmbeddedId
    protected GrupoProdutoPK grupoProdutoPK;
    @Column(name = "nome_grupo_produto")
    private String nome;
    @Column(name = "descricao_grupo_produto")
    private String descricao;

    public GrupoProduto() {
    }

    public GrupoProduto(GrupoProdutoPK grupoProdutoPK) {
        this.grupoProdutoPK = grupoProdutoPK;
    }

    public GrupoProduto(String codigoGp, String codigoEmpresa) {
        this.grupoProdutoPK = new GrupoProdutoPK(codigoGp, codigoEmpresa);
    }

    public GrupoProduto(GrupoProdutoPK grupoProdutoPK, String nome, String descricao) {
        this.grupoProdutoPK = grupoProdutoPK;
        this.nome = nome;
        this.descricao = descricao;
    }

    public GrupoProdutoPK getGrupoProdutoPK() {
        return this.grupoProdutoPK;
    }

    public void setGrupoProdutoPK(GrupoProdutoPK grupoProdutoPK) {
        this.grupoProdutoPK = grupoProdutoPK;
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
        hash += (this.grupoProdutoPK != null ? this.grupoProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoProduto)) {
            return false;
        }
        GrupoProduto other = (GrupoProduto) object;
        if (this.grupoProdutoPK != other.grupoProdutoPK && (this.grupoProdutoPK == null || !this.grupoProdutoPK.equals(other.grupoProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoProduto[grupoProdutoPK=" + grupoProdutoPK + " nome_gp=" + nome + " descricao_gp=" + descricao + "]";
    }
}
