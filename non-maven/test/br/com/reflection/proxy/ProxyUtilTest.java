/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.proxy;

import br.com.reflection.proxy.ProxyUtil;
import br.com.reflection.proxy.ProxyUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ProxyUtilTest {

    public ProxyUtilTest() {
    }

    @Test
    public void testGetEntityClass_javassist() {
        ISum proxy = SumFactory.createJavassistProxy(1, 1);
        //Nome da classe e nome do proxy diferentes
        assertFalse(Sum.class.getName().equals(proxy.getClass().getName()));
        //Nome utilizando o proxy util
        assertEquals(Sum.class.getName(), ProxyUtil.getEntityClass(proxy).getName());
    }

    @Test
    public void testGetEntityClass_reflect() {
        //No caso do REFLECT retornará 
        ISum proxy = SumFactory.createReflectProxy(1, 1);
        //Nome da classe e nome do proxy diferentes
        assertFalse(ISum.class.getName().equals(proxy.getClass().getName()));
        //Nome utilizando o proxy util
        assertEquals(ISum.class.getName(), ProxyUtil.getEntityClass(proxy).getName());
    }

    @Test
    public void testIsProxy_Javassist() {
        //proxy
        assertTrue(ProxyUtil.isProxy(SumFactory.createJavassistProxy(1, 1)));
        //Nao proxy
        assertFalse(ProxyUtil.isProxy(new Sum(1, 1)));
    }

    @Test
    public void testIsProxy_false() {
        //proxy
        assertTrue(ProxyUtil.isProxy(SumFactory.createReflectProxy(1, 1)));
        //Nao proxy
        assertFalse(ProxyUtil.isProxy(new Sum(1, 1)));
    }

    @Test
    public void testGetGetterValue_getValue1() throws NoSuchFieldException {
        //espero 2 - sem proxy
        Field field = Sum.class.getDeclaredField("value1");
        Object value1 = ProxyUtil.getGetterValue(field, new Sum(2, 1));
        assertEquals(2, value1);
    }

    @Test
    public void testGetGetterValue_getValue1_Proxy() throws NoSuchFieldException {
        Field field = Sum.class.getDeclaredField("value1");
        //Com proxy javassist
        ISum proxy = SumFactory.createJavassistProxy(2, 1);
        Object value1Proxy = ProxyUtil.getGetterValue(field, proxy);
        assertEquals(2, value1Proxy);

        //Com proxy reflect
        proxy = SumFactory.createReflectProxy(2, 1);
        value1Proxy = ProxyUtil.getGetterValue(field, proxy);
        assertEquals(2, value1Proxy);
    }
    
    @Test
    public void testGetGetterValue_getValue2() throws NoSuchFieldException {
        //espero 2 - sem proxy
        Field field = Sum.class.getDeclaredField("value2");
        Object value2 = ProxyUtil.getGetterValue(field, new Sum(2, 1));
        assertEquals(1, value2);
    }

    @Test
    public void testGetGetterValue_getValue2_Proxy() throws NoSuchFieldException {
        Field field = Sum.class.getDeclaredField("value1");
        //Com proxy javassist
        ISum proxy = SumFactory.createJavassistProxy(2, 1);
        Object value2Proxy = ProxyUtil.getGetterValue(field, proxy);
        assertEquals(2, value2Proxy);

        //Com proxy reflect
        proxy = SumFactory.createReflectProxy(2, 1);
        value2Proxy = ProxyUtil.getGetterValue(field, proxy);
        assertEquals(2, value2Proxy);
    }
}

class SumFactory {

    public static Sum createJavassistProxy(Integer val1, Integer val2) {
        try {
            ProxyFactory f = new ProxyFactory();
            f.setSuperclass(Sum.class);
            MethodHandler mi = new MethodHandler() {
                public Object invoke(Object self, Method m, Method proceed,
                        Object[] args) throws Throwable {
                    System.out.println("Name: " + m.getName());
                    return proceed.invoke(self, args);  // execute the original method.
                }
            };
            Object proxy = f.create(new Class[]{Integer.class, Integer.class}, new Object[]{val1, val2}, mi);
            return (Sum) proxy;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ISum createReflectProxy(Integer val1, Integer val2) {
        try {
            Sum sum = new Sum(val1, val2);
            Handler handler = new Handler(sum);
            Class[] interfacesArray = new Class[]{ISum.class};
            return (ISum) Proxy.newProxyInstance(Sum.class.getClassLoader(), interfacesArray, handler);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private SumFactory() {
    }
}

class Handler implements InvocationHandler {

    private Sum trueSum;

    public Handler(Sum sum) {
        this.trueSum = sum;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(trueSum, args);
        } catch (Exception e) {
            return new Integer(0);
        }
    }
}

interface ISum {

    Integer getValue1();

    Integer sum();
}

class Sum implements ISum {

    private Integer value1;
    private Integer value2;

    public Sum(Integer val1, Integer val2) {
        value1 = val1;
        value2 = val2;
    }

    @Override
    public Integer sum() {
        return new Integer(value1.intValue() + value2.intValue());
    }

    @Override
    public Integer getValue1() {
        return value1;
    }
}
