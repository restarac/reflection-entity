/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.reflection.proxy.ProxyUtil;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author Frederico
 */
class ReflectionJoin {

    private final Field field;
    private final Object objectValue;

    public ReflectionJoin(final Field field, final Object objectValue) {
        if (field == null || objectValue == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }
        this.field = field;
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        try {
            return getReferencedColumnId(field, objectValue);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Vazio...";
    }

    private String getReferencedColumnId(final Field field, final Object objectValue) throws IllegalAccessException {
        field.setAccessible(true);
        Object embeddeId = ProxyUtil.getGetterValue(field, objectValue);

        if (embeddeId == null) {
            ProxyUtil.getEntityClass(field.getType());
            return new ReflectionTableName(field.getType()).toString() + "_PK(" + new StringBuilder(field.getAnnotation(JoinColumn.class).name()).append("=").append("'null'").toString() + ")";
        }

        //vai no objeto do join e pega todos os campo ate achar o ID.
        Field[] declaredFields = ProxyUtil.getEntityClass(embeddeId).getDeclaredFields();
        for (Field fieldEmbeddeID : declaredFields) {
            if (fieldEmbeddeID.isAnnotationPresent(Id.class) || fieldEmbeddeID.isAnnotationPresent(Id.class)) {
                return new ReflectionTableName(embeddeId).toString() + "_" + new ReflectionColumnId(fieldEmbeddeID, embeddeId).toString();
            }
        }

        return "Vazio...";
    }
}
