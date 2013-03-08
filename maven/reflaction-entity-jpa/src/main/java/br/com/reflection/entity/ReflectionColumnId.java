/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.reflection.proxy.ProxyUtil;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;

/**
 *
 * @author Frederico
 */
class ReflectionColumnId {

    private final Field field;
    private final Object objectValue;

    public ReflectionColumnId(final Field field, final Object objectValue) {
        if (field == null || objectValue == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }
        this.field = field;
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        try {
            return "PK(" + getColumnID(field, objectValue) + ")";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionColumnId.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Object getIdValue() {
        try {
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                return ProxyUtil.getGetterValue(field, objectValue);
            }
        } catch (Exception e) {
            Logger.getLogger(ReflectionColumnId.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    private String getColumnID(final Field field, final Object object) throws IllegalAccessException {
        if (field.isAnnotationPresent(Id.class)) {
            ReflectionColumn column = new ReflectionColumn(field, object);
            return column.toString();
        }

        if (field.isAnnotationPresent(EmbeddedId.class)) {
            return getColumnEmbeddeId(field, object);
        }

        return "";
    }

    private String getColumnEmbeddeId(final Field field, final Object id) throws IllegalArgumentException, SecurityException, IllegalAccessException {

        StringBuilder text = new StringBuilder();
        field.setAccessible(true);
        Object embeddedIdObject = ProxyUtil.getGetterValue(field, id);
        Class entityClass = ProxyUtil.getEntityClass(embeddedIdObject);

        if (entityClass == null) {
            return "";
        }

        Field[] fieldsOfEmbeddeId = entityClass.getDeclaredFields();
        for (Field fieldsEmbedde : fieldsOfEmbeddeId) {
            text.append(new ReflectionColumn(fieldsEmbedde, embeddedIdObject)).append(" ");
        }

        return text.toString().trim();
    }
}
