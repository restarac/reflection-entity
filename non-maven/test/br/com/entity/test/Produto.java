package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author w-tecianeli
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @EmbeddedId
    protected ProdutoPK produtoPK;
    @Column(name = "nome_prod")
    private String nome;
    @Column(name = "codigo_barra")
    private String codigoBarra;
    @Column(name = "ativo_inativo")
    private Character ativoInativo;
    @Column(name = "descricao_prod")
    private String descricao;
    @JoinColumn(name = "codigo_embalag", referencedColumnName = "codigo_embalag")
    @ManyToOne(fetch = FetchType.LAZY)
    private Embalagem embalagem;
    @JoinColumns(value = {
        @JoinColumn(name = "codigo_emp", referencedColumnName = "codigo_emp"),
        @JoinColumn(name = "codigo_gp", referencedColumnName = "codigo_grupo_produto")})
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoProduto grupoProduto;
    @JoinColumns(value = {
        @JoinColumn(name = "codigo_emp", referencedColumnName = "codigo_emp"),
        @JoinColumn(name = "codigo_sub_grupo", referencedColumnName = "codigo_sub_grupo")})
    @ManyToOne(fetch = FetchType.LAZY)
    private SubGrupoProduto subGrupoProduto;
    @JoinColumns(value = {
        @JoinColumn(name = "codigo_emp", referencedColumnName = "codigo_emp"),
        @JoinColumn(name = "codigo_tpp", referencedColumnName = "codigo_tipo_produto")})
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoProduto tipoProduto;

    public Produto() {
    }

    public Produto(ProdutoPK produtoPK) {
        this.produtoPK = produtoPK;
    }

    public Produto(String codigoProd, String codigoEmpresa) {
        this.produtoPK = new ProdutoPK(codigoProd, codigoEmpresa);
    }

    public ProdutoPK getProdutoPK() {
        return this.produtoPK;
    }

    public void setProdutoPK(ProdutoPK produtoPK) {
        this.produtoPK = produtoPK;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarra() {
        return this.codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Character getAtivoInativo() {
        return this.ativoInativo;
    }

    public void setAtivoInativo(Character ativoInativo) {
        this.ativoInativo = ativoInativo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricaoProd(String descricao) {
        this.descricao = descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public SubGrupoProduto getSubGrupoProduto() {
        return subGrupoProduto;
    }

    public void setSubGrupoProduto(SubGrupoProduto subGrupoProduto) {
        this.subGrupoProduto = subGrupoProduto;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.produtoPK != null ? this.produtoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if (this.produtoPK != other.produtoPK && (this.produtoPK == null || !this.produtoPK.equals(other.produtoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto[produtoPK=" + produtoPK + " nome_prod=" + nome + " codigo_barra=" + codigoBarra
                + " ativo_inativo=" + ativoInativo + " descricao_prod=" + descricao + "]";
    }
}
