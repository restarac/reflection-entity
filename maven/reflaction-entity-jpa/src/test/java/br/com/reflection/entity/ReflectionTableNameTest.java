/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import br.com.entity.test.GrupoProduto;
import br.com.entity.test.GrupoProdutoPK;
import br.com.entity.test.TipoEmbalagem;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionTableNameTest {

    public ReflectionTableNameTest() {
    }

    @Test
    public void testEmbalagem() {
        System.out.println("toString embalagem");
        assertEquals("embalagem", new ReflectionTableName(new Embalagem("001")).toString());
    }

    @Test
    public void testGrupo() {
        System.out.println("toString grupo...");
        assertEquals("grupo_produto", new ReflectionTableName(new GrupoProduto(new GrupoProdutoPK("000001", "1"))).toString());
    }

    @Test
    public void testTipoEmbalagem() {
        System.out.println("toString TipoEmbalagem...");
        assertEquals("TipoEmbalagem", new ReflectionTableName(new TipoEmbalagem(122)).toString());
    }
}
