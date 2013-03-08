/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Frederico
 */
@Entity
public class TipoEmbalagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_tipo_embalagem")
    private Integer id;
    @Column(name = "nome_tipo_embalagem")
    private String nome;
    @Column(name = "descricao_tipo_embalagem")
    private String descricao;

    public TipoEmbalagem() {
    }

    public TipoEmbalagem(Integer id) {
        this.id = id;
    }

    public TipoEmbalagem(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmbalagem)) {
            return false;
        }
        TipoEmbalagem other = (TipoEmbalagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entity.test.TipoEmbalagem[ id=" + id + " ]";
    }
}
