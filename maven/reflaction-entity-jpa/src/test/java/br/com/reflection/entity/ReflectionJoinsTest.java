/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import br.com.entity.test.GrupoProduto;
import br.com.entity.test.GrupoProdutoPK;
import br.com.entity.test.Produto;
import br.com.entity.test.ProdutoPK;
import br.com.entity.test.SubGrupoProduto;
import br.com.entity.test.SubGrupoProdutoPK;
import br.com.entity.test.TipoProduto;
import java.lang.reflect.Field;
import javax.persistence.JoinColumns;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionJoinsTest {

    public ReflectionJoinsTest() {
    }

    @Test
    public void testToString_todosJoinsPreenchidos() {
        System.out.println("Teste somente JOINS todos preenchidos...");
        Embalagem embalagem = new Embalagem("321321");
        embalagem.setNome("Gramas");
        embalagem.setAbreviatura("g");
        Produto produto = new Produto(new ProdutoPK("123123", "001"));
        produto.setAtivoInativo('A');
        produto.setGrupoProduto(new GrupoProduto(new GrupoProdutoPK("111111", "001")));
        produto.setSubGrupoProduto(new SubGrupoProduto(new SubGrupoProdutoPK("1111111", "001")));
        produto.setTipoProduto(new TipoProduto("11111111"));
        produto.setEmbalagem(embalagem);

        //
        String entityToString = "";

        for (Field field : produto.getClass().getDeclaredFields()) {

            if (field.isAnnotationPresent(JoinColumns.class)) {
                entityToString += " Join:  " + new ReflectionJoins(field, produto).toString();
            }
        }

        entityToString = entityToString.trim();
        System.out.println(entityToString);
        assertEquals("Join:  codigo_emp=001 codigo_gp= Join:  codigo_emp=001 codigo_sub_grupo=1111111 Join:  codigo_emp=Vazio... codigo_tpp=Vazio...", entityToString);
    }

    @Test
    public void testToString_algunsJoinsPreenchidos() {
        System.out.println("Teste somente JOINS alguns preenchidos...");
        Produto produto = new Produto(new ProdutoPK("123123", "001"));
        produto.setAtivoInativo('A');
        produto.setGrupoProduto(new GrupoProduto(new GrupoProdutoPK("111111", "001")));
        produto.setSubGrupoProduto(new SubGrupoProduto(new SubGrupoProdutoPK("1111111", "001")));
        //Join null... produto.setEmbalagem(embalagem);

        String entityToString = "";

        for (Field field : produto.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JoinColumns.class)) {
                entityToString += " Join:  " + new ReflectionJoins(field, produto).toString();
            }
        }

        entityToString = entityToString.trim();
        System.out.println(entityToString);
        assertEquals("Join:  codigo_emp=001 codigo_gp= Join:  codigo_emp=001 codigo_sub_grupo=1111111 Join:  codigo_emp=null codigo_tpp=null", entityToString);
    }
}
