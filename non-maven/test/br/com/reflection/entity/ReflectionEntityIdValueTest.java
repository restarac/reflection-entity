/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import br.com.entity.test.GrupoProduto;
import br.com.entity.test.GrupoProdutoPK;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frederico
 */
public class ReflectionEntityIdValueTest {

    @Test
    public void testGetIdValueNotNull() {
        Embalagem embalagem = new Embalagem("0000021");
        Object idValueB = ReflectionEntity.entityIdValue(embalagem);
        assertEquals("Tem que ter o mesmo valor do codigo (ID)", idValueB, embalagem.getCodigo());
    }

    @Test
    public void testGetIdValueNull() {
        Embalagem embalagem = new Embalagem(null);
        Object idValueP = ReflectionEntity.entityIdValue(embalagem);
        assertEquals("Ambos deverao estar null", idValueP, embalagem.getCodigo());
    }

    @Test
    public void testGetIdValuePerformance() throws InterruptedException {
        long total;
        //
        GrupoProdutoPK pk = new GrupoProdutoPK("0000012", "1");
        GrupoProduto grupoProduto = new GrupoProduto(pk);
        Date ini = new Date();//10:10
        Object idValueP = ReflectionEntity.entityIdValue(grupoProduto); //10:11
        total = (new Date().getTime() - ini.getTime()); //10:11 - 10:10 == 1 min
        System.out.println("ATENCAO TEMPO TOTAL DE REFLECTION EMBEDDED_ID: " + total);
        assertTrue("Precisará ser menor que 10ms!", 10 > total);
        assertEquals("Deverá ser exatamente igual ate na referencia...", idValueP, pk);
        //
        Embalagem embalagem = new Embalagem("0000012");
        ini = new Date();
        Object idValueB = ReflectionEntity.entityIdValue(embalagem);
        total = (new Date().getTime() - ini.getTime());
        System.out.println("ATENCAO TEMPO TOTAL DE REFLECTION ID: " + total);
        assertTrue("Precisará ser menor que 5ms!", 5 > total);
        assertEquals("Deverá ser exatamente igual ate na referencia...", idValueB, "0000012");
    }
}
