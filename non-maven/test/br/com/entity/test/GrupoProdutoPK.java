package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author w-tecianeli
 */
@Embeddable
public class GrupoProdutoPK implements Serializable {

    @Column(name = "codigo_emp", nullable = false)
    private String codigoEmpresa;

    @Column(name = "codigo_grupo_produto", nullable = false)
    private String codigo;
    
    public GrupoProdutoPK() {
    }

    public GrupoProdutoPK(String codigo, String codigoEmpresa) {
        this.codigo = codigo;
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigoEmpresa() {
        return this.codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.codigo != null ? this.codigo.hashCode() : 0);
        hash += (this.codigoEmpresa != null ? this.codigoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoProdutoPK)) {
            return false;
        }
        GrupoProdutoPK other = (GrupoProdutoPK)object;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) return false;
        if (this.codigoEmpresa != other.codigoEmpresa && (this.codigoEmpresa == null || !this.codigoEmpresa.equals(other.codigoEmpresa))) return false;
        return true;
    }

    @Override
    public String toString() {
        return "GrupoProdutoPK[codigo_grupo_produto=" + codigo + " codigo_emp=" + codigoEmpresa + "]";
    }
}