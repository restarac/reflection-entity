package br.coml.reflection.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtil {

    /**
     * Retorna a annotation presente na classe passada como parametro.Tambem
     * realiza a pesquisa, nas anotacoes presentes na classe.
     *
     * @param classe Classe que sera capturado a annotation
     * @param annot Classe da annotation
     * @return Retorna a ANNOTATION encontrado, ou NULL se nao encontrar.
     */
    public static <T extends Annotation> T getAnnotation(Class classe, Class<T> annot) {
        return retornaAnnotation(classe, annot);
    }

    /**
     * Retorna a annotation presente na classe passada como parametro.Tambem
     * realiza a pesquisa, nas anotacoes presentes no metodo.
     *
     * @param method Metodo que sera capturado a annotation
     * @param annot Classe da annotation
     * @return Retorna a ANNOTATION encontrado, ou NULL se nao encontrar.
     */
    public static <T extends Annotation> T getAnnotation(Method method, Class<T> annot) {
        return retornaAnnotation(method, annot);
    }

    private static <T extends Annotation> T retornaAnnotation(AnnotatedElement method, Class<T> annot) {
        T returned = null;
        if (method.getAnnotation(annot) == null) {
            //Preciso procurar pela anotacao dentro das anotacoes...
            for (Annotation annotation : method.getAnnotations()) {
                T acc = annotation.annotationType().getAnnotation(annot);
                if (acc != null) {
                    returned = acc;
                    break;
                }
            }
        } else {
            returned = method.getAnnotation(annot);
        }
        return returned;
    }

    /**
     * Procura pelo primeiro metodo que contiver a annotation passada como
     * parametro. Tambem faz a pesquisa nas anotacoes que o metodo possui. Assim
     * verificada se aquela annotacao está anotada com a notacao procurada.
     *
     * @param classe Classe que será o alvo da pesquisa
     * @param annot A annotation que será procurada.
     * @return Retorna o metodo encontrado, ou NULL se nao encontrar.
     */
    public static <T extends Annotation> Method getMethodAnnotated(Class classe, Class<T> annot) {

        while (classe != null) {
            Method[] methods = classe.getDeclaredMethods();
            for (Method method : methods) {
                T annotation = getAnnotation(method, annot);
                if (annotation != null) {
                    return method;
                }
            }
            classe = classe.getSuperclass();
        }

        return null;
    }

    /**
     * Procura por TODOS os metodos que possuem a annotation e os retorna numa
     * list
     *
     * @param classe Classe que será o alvo da pesquisa
     * @param annot A annotation que será procurada.
     * @return Retorna o uma LIST encontrado, retorna uma list vazia.
     */
    public static <T extends Annotation> List<Method> getListMethodAnnotated(Class classe, Class<T> annot) {
        Method[] methods = classe.getMethods();
        List<Method> list = new ArrayList<Method>();
        for (Method method : methods) {
            T annotation = getAnnotation(method, annot);
            if (annotation != null) {
                list.add(method);
            }
        }

        return list;
    }

    private AnnotationUtil() {
    }
}
