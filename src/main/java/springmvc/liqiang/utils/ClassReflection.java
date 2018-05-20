package springmvc.liqiang.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体类赋值 A类中有和B类相同的字段
 * 实体类比较 A类中有和B类相同的字段
 *
 * @author liqiang
 */
public class ClassReflection {
    /**
     * @param class1 用于赋值的实体类
     * @param class2 需要待赋值的实体类
     */
    public static void reflectionAttr(Object class1, Object class2) throws Exception {
        Class clazz1 = Class.forName(class1.getClass().getName());
        Class clazz2 = Class.forName(class2.getClass().getName());
        // 获取两个实体类的所有属性
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        ClassReflection cr = new ClassReflection();
        // 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
        for (Field f1 : fields1) {
            if ("id".equals(f1.getName()) || "serialVersionUID".equals(f1.getName())) {
                continue;//跳过id这个字段
            }
            Object value = cr.invokeGetMethod(class1, f1.getName());
            for (Field f2 : fields2) {
                if (f1.getName().equals(f2.getName())) {
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    cr.invokeSetMethod(class2, f2.getName(), obj);
                }
            }
        }

    }

    /**
     * 比较两个类的相同字段值
     *
     * @param class1 用于比较的实体类1
     * @param class2 用于比较的实体类2
     */
    public static Map<String, List<Object>> compareObj(Object class1, Object class2) throws Exception {
        Map<String, List<Object>> map = new HashMap<>(16);
        Class clazz1 = Class.forName(class1.getClass().getName());
        Class clazz2 = Class.forName(class2.getClass().getName());
        // 获取两个实体类的所有属性
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        ClassReflection cr = new ClassReflection();
        // 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
        for (Field f1 : fields1) {
            if ("id".equals(f1.getName()) || "serialVersionUID".equals(f1.getName())) {
                continue;//跳过id这个字段
            }
            Object value = cr.invokeGetMethod(class1, f1.getName());
            for (Field f2 : fields2) {
                if (f1.getName().equals(f2.getName())) {
                    Object value2 = cr.invokeGetMethod(class2, f1.getName());
                    if (value != null && value2 != null && !value.equals(value2)) {
                        List<Object> list = new ArrayList<>();
                        list.add(value);
                        list.add(value2);
                        map.put(f1.getName(), list);
                    } else if (value != null && value2 == null) {
                        List<Object> list = new ArrayList<>();
                        list.add(value);
                        list.add(null);
                        map.put(f1.getName(), list);
                    } else if (value == null && value2 != null) {
                        List<Object> list = new ArrayList<>();
                        list.add(null);
                        list.add(value2);
                        map.put(f1.getName(), list);
                    }
                }
            }
        }

        return map;

    }

    /**
     * 执行某个Field的getField方法
     *
     * @param clazz     类
     * @param fieldName 类的属性名称
     */
    private Object invokeGetMethod(Object clazz, String fieldName) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method;
        try {
            method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 执行某个Field的setField方法
     *
     * @param clazz     类
     * @param fieldName 类的属性名称
     * @param args      参数，默认为null
     */
    @SuppressWarnings("unchecked")
    private void invokeSetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method;
        try {
            Class[] parameterTypes = new Class[1];
            Class c = Class.forName(clazz.getClass().getName());
            Field field = c.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            method = c.getDeclaredMethod("set" + methodName, parameterTypes);
            method.invoke(clazz, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
