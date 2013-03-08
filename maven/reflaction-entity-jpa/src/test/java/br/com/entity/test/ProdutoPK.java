package br.com.entity.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author w-tecianeli
 */
@Embeddable
public class ProdutoPK implements Serializable {

    @Column(name = "codigo_emp", nullable = false)
    private String codigoEmpresa;
    @Column(name = "codigo_produto", nullable = false)
    private String codigo;

    public ProdutoPK() {
    }

    public ProdutoPK(String codigo, String codigoEmpresa) {
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
        if (!(object instanceof ProdutoPK)) {
            return false;
        }
        ProdutoPK other = (ProdutoPK) object;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        if (this.codigoEmpresa != other.codigoEmpresa && (this.codigoEmpresa == null || !this.codigoEmpresa.equals(other.codigoEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProdutoPK[codigo=" + codigo + " codigoEmpresa=" + codigoEmpresa + "]";
    }
}
