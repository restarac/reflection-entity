/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.reflection.proxy.ProxyUtil;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;

/**
 *
 * @author Frederico
 */
class ReflectionColumn {

    private final Field field;
    private final Object objectValue;

    public ReflectionColumn(final Field field, final Object objectValue) {
        if (field == null || objectValue == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }
        this.field = field;
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        try {
            final String nameField = field.getAnnotation(Column.class) != null ? field.getAnnotation(Column.class).name() : field.getName();
            return new StringBuilder(nameField).append("='").append(getColumnValue(field, objectValue)).append("'").toString();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Object getColumnValue(final Field field, final Object objectValue) throws IllegalAccessException {
        field.setAccessible(true);
        return ProxyUtil.getGetterValue(field, objectValue);
    }
}
