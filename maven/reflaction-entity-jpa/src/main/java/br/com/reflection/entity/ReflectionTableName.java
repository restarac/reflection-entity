/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.reflection.proxy.ProxyUtil;
import java.io.Serializable;
import javax.persistence.Table;

/**
 *
 * @author Frederico
 */
public class ReflectionTableName {

    private final Class<?> entity;

    public ReflectionTableName(final Object entity) {
        if (entity == null) {
            throw new NullPointerException("O parametro do construtor precisa ter um valor diferente de NULL");
        }
        if (entity instanceof Class) {
            this.entity = (Class<?>) entity;
        } else {
            this.entity = ProxyUtil.getEntityClass(entity);
        }
    }

    @Override
    public String toString() {
        return getTableName(entity);
    }

    private String getTableName(final Class<?> classe) {
        Table table = classe.getAnnotation(Table.class);
        if (table != null) {
            return table.name();
        }

        return classe.getSimpleName();
    }
}
