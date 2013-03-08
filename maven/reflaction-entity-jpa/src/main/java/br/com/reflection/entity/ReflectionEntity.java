/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.reflection.proxy.ProxyUtil;
import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

/**
 *
 * @author Frederico
 */
public class ReflectionEntity {

    /**
     * Get the id of the entity and return an string representing the value on
     * database.
     *
     * @param entity to get the id.
     * @return An string representing the id of the entity. Empty String if it
     * no has any JPA annotations
     */
    public static String entityIdToString(final Object entity) {
        if (entity == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }

        StringBuilder entityToString = new StringBuilder(new ReflectionTableName(entity).toString()).append("[");

        for (Field field : ProxyUtil.getEntityClass(entity).getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                entityToString.append(new ReflectionColumnId(field, entity).toString()).append(" ");
                break;
            }
        }

        return entityToString.toString().trim() + "]";
    }

    public static Object entityIdValue(final Object entity) {
        if (entity == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }

        Field[] declaredFields = ProxyUtil.getEntityClass(entity).getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                return new ReflectionColumnId(field, entity).getIdValue();
            }
        }

        return null;
    }

    /**
     * Transforms the entity into an string representing the table on database.
     *
     * @param entity the entity to be transformed into an string
     * @return An string representing the entity on database. Empty String if it
     * no has any JPA annotations
     */
    public static String entityToString(final Object entity) {
        if (entity == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }

        StringBuilder entityToString = new StringBuilder(new ReflectionTableName(entity).toString()).append("[");

        for (Field field : ProxyUtil.getEntityClass(entity).getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                entityToString.append(new ReflectionColumnId(field, entity).toString()).append(" ");
                continue;
            }
            if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
                entityToString.append(new ReflectionColumn(field, entity).toString()).append(" ");
                continue;
            }
            if (field.isAnnotationPresent(JoinColumn.class)) {
                entityToString.append(new ReflectionJoin(field, entity).toString()).append(" ");
                continue;
            }
            if (field.isAnnotationPresent(JoinColumns.class)) {
                entityToString.append(new ReflectionJoins(field, entity).toString()).append(" ");
                continue;
            }
        }

        return entityToString.toString().trim() + "]";
    }

    private ReflectionEntity() {
    }
}
