/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import br.com.entity.test.GrupoProduto;
import br.com.entity.test.GrupoProdutoPK;
import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionColumnIdTest {

//    public static final String ID_PAIS = "codigo_pais=321321";
//    public static final String ID_EMBALAGEM = "codigo_emp=001 codigo_embalag=321321";
    public ReflectionColumnIdTest() {
    }

    @Test
    public void testToString_ID() {
        System.out.println("Teste somente Id");
        Embalagem embalagem = new br.com.entity.test.Embalagem();
        embalagem.setCodigo("000000122");
        String entityToString = "";
        for (Field field : embalagem.getClass().getDeclaredFields()) {
            entityToString += new ReflectionColumnId(field, embalagem).toString() + " ";
        }
        entityToString = entityToString.trim();
        System.out.println(entityToString);
        assertEquals("Deverá ter a mesma string...", "codigo_embalagem='000000122'", entityToString);
    }

    @Test
    public void testToString_EmbeddedId() {
        System.out.println("Teste somente EmbeddedId");
        //        Embalagem embalagem = new Embalagem(new EmbalagemPK("321321", "001"));
        //        embalagem.setNome("Gramas");
        //        embalagem.setAbreviatura("g");
        //        embalagem.setEmpresa(new Empresa("001"));
        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setGrupoProdutoPK(new GrupoProdutoPK("00002", "1"));

        //
        String entityToString = "";

        for (Field field : grupoProduto.getClass().getDeclaredFields()) {
            entityToString += new ReflectionColumnId(field, grupoProduto).toString() + " ";
        }

        entityToString = entityToString.trim();
        System.out.println(entityToString);
        assertEquals("codigo_emp='1' codigo_grupo_produto='00002'", entityToString);
    }
}
