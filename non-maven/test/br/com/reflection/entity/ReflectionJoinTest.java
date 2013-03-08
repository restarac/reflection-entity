/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

//import br.com.el.geral.entity.EmbalagemPK;
//import br.com.el.geral.entity.Empresa;
import br.com.entity.test.Embalagem;
import br.com.entity.test.TipoEmbalagem;
import java.lang.reflect.Field;
import javax.persistence.JoinColumn;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionJoinTest {

    public static final String CODIGO_TIPO_EMBALAGEM = "codigo_tipo_embalagem='1'";
    public static final String CODIGO_TIPO_EMBALAGEM_NULL = "codigo_tipo_embalagem=null";

    public ReflectionJoinTest() {
    }

    @Test
    public void testToString_join() {
        System.out.println("Teste somente JOIN");
        Embalagem embalagem = new Embalagem("321321");
        TipoEmbalagem tipoEmbalagem = new TipoEmbalagem(1, "Garrafa 2 Litros", "Garrafa comun");
        embalagem.setEmbalagem(tipoEmbalagem);

        //
        String entityToString = "";

        for (Field field : embalagem.getClass().getDeclaredFields()) {

            if (field.isAnnotationPresent(JoinColumn.class)) {
                entityToString += new ReflectionJoin(field, embalagem).toString() + " ";
            }
        }
        entityToString = entityToString.trim();
//        System.out.print("ok.");
        assertEquals(CODIGO_TIPO_EMBALAGEM, entityToString);
    }

    @Test
    public void testToString_joinNull() {
        System.out.println("Teste somente JOIN null");
        Embalagem embalagem = new Embalagem("321321");
        embalagem.setEmbalagem(null);

        String entityToString = "";

        for (Field field : embalagem.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JoinColumn.class)) {
                entityToString += new ReflectionJoin(field, embalagem).toString() + " ";
            }
        }
        entityToString = entityToString.trim();
        System.out.print(entityToString);
        assertEquals(CODIGO_TIPO_EMBALAGEM_NULL, entityToString);
    }
}
