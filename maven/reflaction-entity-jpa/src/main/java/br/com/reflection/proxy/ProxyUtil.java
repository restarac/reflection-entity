/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author Frederico
 */
public class ProxyUtil {

    /**
     * returns the proxy classes. <b> This not work on proxies different than
     * {@link java.lang.reflect.Proxy} or
     * {@link javassist.util.proxy.ProxyFactory} because this not work the same
     * way.</b>
     *
     * @param proxy
     * @return The SUPERCLASS, or if there isn't a proxy class something like
     * 'getClass()';
     */
    public static Class<?> getEntityClass(final Object proxy) {
        if (proxy == null) {
            return null;
        }

        Class<? extends Object> classe = proxy.getClass();
        //Se o nome contiver $ é um proxy
        if (ProxyUtil.isProxy(proxy)) {
            if (Proxy.isProxyClass(classe)) {
                return classe.getInterfaces()[0];
            }
            return classe.getSuperclass();
        }

        return classe;
    }

    /**
     * Verify if that is an proxy
     *
     * @param proxy
     * @return TRUE if is, otherwise FALSE.
     */
    public static boolean isProxy(final Object proxy) {
        String className = proxy.getClass().getName();
        //se conter $ é um proxy
        return className.contains("$") || className.contains("_");
    }

    /**
     * Returns the value of the field by GETTER.
     *
     * @param field
     * @param proxy
     * @return returns the value object. if not exists the getter or something
     * go wrong returns NULL.
     */
    public static Object getGetterValue(final Field field, final Object proxy) {
        try {
            if (ProxyUtil.isProxy(proxy)) {
                StringBuilder nameField = new StringBuilder("get").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1));
                Method get = proxy.getClass().getMethod(nameField.toString());
                get.setAccessible(true);
                return get.invoke(proxy);
            } else {
                field.setAccessible(true);
                return field.get(proxy);
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private ProxyUtil() {
    }
}
