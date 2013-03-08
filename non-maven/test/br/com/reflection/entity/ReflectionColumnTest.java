/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionColumnTest {

//    public static final String ENTITY_TO_STRING_COLUMNS = "nome_pais=Brasil nacionalidade_pais=Brasileiro";

    public ReflectionColumnTest() {
    }

    @Test
    public void testToString() {
        System.out.println("Teste somente Column...");
        //        Pais pais = new Pais("321321");
        //        pais.setNome("Brasil");
        //        pais.setNacionalidade("Brasileiro");
        Embalagem embalagem = new Embalagem("000012", "Garrafa", "Garrafa de 2 litros", "GARRAFA");
        //
        String entityToString = "";

        for (Field field : embalagem.getClass().getDeclaredFields()) {
                entityToString += new ReflectionColumn(field, embalagem).toString() + " ";
        }
        entityToString = entityToString.trim();
        System.out.println(entityToString);
        assertEquals("codigo_embalagem='000012' nome_embalagem='Garrafa' desc_embalagem='Garrafa de 2 litros' abreviatura_embalagem='GARRAFA'", entityToString);
    }
}
