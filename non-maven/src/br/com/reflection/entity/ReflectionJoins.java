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
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

/**
 *
 * @author Frederico
 */
class ReflectionJoins {

    private final Field field;
    private final Object objectValue;

    public ReflectionJoins(final Field field, final Object objectValue) {
        if (field == null || objectValue == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }
        this.field = field;
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        try {
//            String columnText = "";
            StringBuilder columnText = new StringBuilder();
            JoinColumn[] joins = getAnnotationValue(field);
            for (JoinColumn joinColumn : joins) {
                columnText.append(joinColumn.name()).append("='").append(getColumnValue(joinColumn, field, objectValue)).append("' ");
            }

            return new ReflectionTableName(field.getType()) + "_PK(" + columnText.toString().trim() + ")";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Vazio...";
    }

    private JoinColumn[] getAnnotationValue(final Field field) throws IllegalAccessException {
        JoinColumns annotation = (JoinColumns) field.getAnnotation(JoinColumns.class);
        return annotation.value();
    }

    private String getColumnValue(final JoinColumn joinColumn, final Field field, final Object objectWithJoins) throws IllegalAccessException {
        String referencedColumnName = joinColumn.referencedColumnName();
        //Pegar objeto join...
        field.setAccessible(true);
        Object joinObject = ProxyUtil.getGetterValue(field, objectWithJoins);
        if (joinObject == null) {
            return "'null'";
        }

        Field[] declaredFields = ProxyUtil.getEntityClass(joinObject).getDeclaredFields();

        for (Field fieldID : declaredFields) {
            //se for com id e so verificar e retornar
//            if (fieldID.isAnnotationPresent(Id.class) && fieldID.getAnnotation(Column.class).name().equals(referencedColumnName)) {
            if (fieldID.isAnnotationPresent(Id.class)) {
                return ProxyUtil.getGetterValue(fieldID, joinObject).toString();
            }

            //se for com embedde preciso entrar verificar e retornar
            if (fieldID.isAnnotationPresent(EmbeddedId.class)) {
                return getColumnsEmbeddeId(fieldID, joinObject, referencedColumnName);
            }
        }
        return "Vazio...";
    }

    private String getColumnsEmbeddeId(final Field fieldID, final Object joinObject, final String referencedColumnName) throws IllegalArgumentException, SecurityException, IllegalAccessException {
        //entro no objeto do join
        fieldID.setAccessible(true);
        Object embeddeIdObject = ProxyUtil.getGetterValue(fieldID, joinObject);
        Field[] fieldsEmbedde = ProxyUtil.getEntityClass(embeddeIdObject).getDeclaredFields();

        for (Field fieldEmbeddeID : fieldsEmbedde) {
            String nomeCampo = fieldEmbeddeID.getAnnotation(Column.class).name();
            if (nomeCampo.equals(referencedColumnName)) {
                fieldEmbeddeID.setAccessible(true);
                return String.valueOf(ProxyUtil.getGetterValue(fieldEmbeddeID, embeddeIdObject));
            }
        }

        return "Vazio...";
    }
}
