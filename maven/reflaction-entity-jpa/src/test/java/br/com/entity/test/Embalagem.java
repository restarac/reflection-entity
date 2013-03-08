package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author w-tecianeli
 */
@Entity
@Table(name = "embalagem")
public class Embalagem implements Serializable {

    @Id
    @Column(name = "codigo_embalagem", nullable = false)
    private String codigo;
    @Column(name = "nome_embalagem")
    private String nome;
    @Column(name = "desc_embalagem")
    private String descricao;
    @Column(name = "abreviatura_embalagem")
    private String abreviatura;
    @JoinColumn(name = "codigo_tipo_embalagem", referencedColumnName = "codigo_tipo_embalagem")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoEmbalagem embalagem;

    public Embalagem() {
    }

    public Embalagem(String codigo) {
        this.codigo = codigo;
    }

    public Embalagem(String codigo, String nome, String descricao, String abreviatura) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.abreviatura = abreviatura;
    }
    

    public TipoEmbalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(TipoEmbalagem embalagem) {
        this.embalagem = embalagem;
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

    public String getAbreviatura() {
        return this.abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Embalagem)) {
            return false;
        }
        Embalagem other = (Embalagem) object;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Embalagem[codigo_embalagem=" + codigo + " nome_embalag=" + nome + " desc_embalagem=" + descricao
                + " abreviatura_embalag=" + abreviatura + "]";
    }
}