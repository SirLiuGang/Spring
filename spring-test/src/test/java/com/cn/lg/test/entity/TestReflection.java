package com.cn.lg.test.entity;

import org.junit.Test;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 利用反射完成对实体类或DTO的测试
 * @author: 刘钢
 * @Date: 2019/3/4 22:36
 * @Description:
 */
public class TestReflection {

    /**
     * 获取所有需要测试的Class
     */
    private List<Class<?>> getClasses() {
        List<Class<?>> list = new ArrayList<>();
        list.add(User.class);   // 对User实体类进行测试
        return list;
    }

    /**
     * 利用反射完成对实体类或DTO的测试
     */
    @Test
    public void test() {

        List<Class<?>> allClass = getClasses();
        if (null != allClass) {
            for (Class classes : allClass) {// 循环反射执行所有类
                try {
                    boolean isAbstract = Modifier.isAbstract(classes.getModifiers());
                    if (classes.isInterface() || isAbstract) {// 如果是接口或抽象类,跳过
                        continue;
                    }
                    Constructor[] constructorArr = classes.getConstructors();
                    Object clazzObj = newConstructor(constructorArr, classes);

                    fieldTest(classes, clazzObj);

                    methodInvoke(classes, clazzObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对属性进行测试
     */
    private void fieldTest(Class<?> classes, Object clazzObj) throws Exception {
        if (null == clazzObj) {
            return;
        }

        Field[] fields = classes.getDeclaredFields();
        if (null != fields && fields.length > 0) {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object fieldGetObj = field.get(clazzObj);
                if (!Modifier.isFinal(field.getModifiers()) || null == fieldGetObj) {
                    field.set(clazzObj, adaptorGenObj(field.getType()));
                }
            }
        }
    }

    /**
     * 功能描述: 执行方法<br>
     */
    private void methodInvoke(Class<?> classes, Object clazzObj) throws Exception {
        Method[] methods = classes.getDeclaredMethods();
        if (null != methods && methods.length > 0) {
            for (Method method : methods) {
                String methodName = method.getName();

                // 无论如何，先把权限放开
                method.setAccessible(true);
                Class<?>[] paramClassArrs = method.getParameterTypes();

                // 执行getset方法
                if (methodName.startsWith("set") && null != clazzObj) {
                    methodInvokeGetSet(classes, clazzObj, method, paramClassArrs, methodName);
                    continue;
                }

                // 如果是静态方法
                if (Modifier.isStatic(method.getModifiers()) && !classes.isEnum()) {
                    if (paramClassArrs.length == 0) {
                        method.invoke(null);
                    } else if (paramClassArrs.length == 1) {
                        method.invoke(null, adaptorGenObj(paramClassArrs[0]));
                    } else if (paramClassArrs.length == 2) {
                        method.invoke(null, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]));
                    } else if (paramClassArrs.length == 3) {
                        method.invoke(null, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]),
                                adaptorGenObj(paramClassArrs[2]));
                    } else if (paramClassArrs.length == 4) {
                        method.invoke(null, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]),
                                adaptorGenObj(paramClassArrs[2]), adaptorGenObj(paramClassArrs[3]));
                    }
                    continue;
                }

                if (null == clazzObj) {
                    continue;
                }

                // 如果方法是toString,直接执行
                if ("toString".equals(methodName)) {
                    try {
                        Method toStringMethod = classes.getDeclaredMethod(methodName);
                        toStringMethod.invoke(clazzObj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                // 其他方法
                if (paramClassArrs.length == 0) {
                    method.invoke(clazzObj);
                } else if (paramClassArrs.length == 1) {
                    method.invoke(clazzObj, adaptorGenObj(paramClassArrs[0]));
                } else if (paramClassArrs.length == 2) {
                    method.invoke(clazzObj, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]));
                } else if (paramClassArrs.length == 3) {
                    method.invoke(clazzObj, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]),
                            adaptorGenObj(paramClassArrs[2]));
                } else if (paramClassArrs.length == 4) {
                    method.invoke(clazzObj, adaptorGenObj(paramClassArrs[0]), adaptorGenObj(paramClassArrs[1]),
                            adaptorGenObj(paramClassArrs[2]), adaptorGenObj(paramClassArrs[3]));
                }
            }
        }
    }

    /**
     * 功能描述: 执行getset方法,先执行set，获取set执行get<br>
     */
    private void methodInvokeGetSet(Class<?> classes, Object clazzObj, Method method, Class<?>[] paramClassArrs, String methodName)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object getObj;
        String methodNameSuffix = methodName.substring(3);
        Method getMethod;
        try {
            getMethod = classes.getDeclaredMethod("get" + methodNameSuffix);
        } catch (NoSuchMethodException e) {
            // 如果对应的get方法找不到,会有is开头的属性名,其get方法就是其属性名称
            Character firstChar = methodNameSuffix.charAt(0);// 取出第一个字符转小写
            String firstLowerStr = firstChar.toString().toLowerCase();
            try {
                getMethod = classes.getDeclaredMethod(firstLowerStr + methodNameSuffix.substring(1));
            } catch (NoSuchMethodException e2) {
                return;
            }
        }

        // 如果get返回结果和set参数结果一样,才可以执行,否则不可以执行
        Class<?> returnClass = getMethod.getReturnType();
        if (paramClassArrs.length == 1 && paramClassArrs[0].toString().equals(returnClass.toString())) {
            getObj = getMethod.invoke(clazzObj);
            method.invoke(clazzObj, getObj);
        }

    }

    /**
     * 功能描述: 构造函数构造对象<br>
     */
    @SuppressWarnings("rawtypes")
    private Object newConstructor(Constructor[] constructorArr, Class<?> classes) throws Exception {
        if (null == constructorArr || constructorArr.length < 1) {
            return null;
        }
        Object clazzObj = null;
        boolean isExitNoParamConstruct = false;
        for (Constructor constructor : constructorArr) {
            Class[] constructParamClazzArr = constructor.getParameterTypes();
            if (constructParamClazzArr.length == 0) {
                constructor.setAccessible(true);
                clazzObj = classes.newInstance();
                isExitNoParamConstruct = true;
                break;
            }
        }
        // 没有无参构造取第一个
        if (!isExitNoParamConstruct) {
            boolean isContinueFor = false;
            Class[] constructParamClazzArr = constructorArr[0].getParameterTypes();
            Object[] construParamObjArr = new Object[constructParamClazzArr.length];
            for (int i = 0; i < constructParamClazzArr.length; i++) {
                Class constructParamClazz = constructParamClazzArr[i];
                construParamObjArr[i] = adaptorGenObj(constructParamClazz);
                if (null == construParamObjArr[i]) {
                    isContinueFor = true;
                }
            }
            if (!isContinueFor) {
                clazzObj = constructorArr[0].newInstance(construParamObjArr);
            }
        }
        return clazzObj;
    }

    /**
     * 功能描述: 根据类的不同，进行不同实例化<br>
     */
    private Object adaptorGenObj(Class<?> clazz) throws Exception {
        if (null == clazz) {
            return null;
        }
        if ("int".equals(clazz.getName())) {
            return 1;
        } else if ("char".equals(clazz.getName())) {
            return 'x';
        } else if ("boolean".equals(clazz.getName())) {
            return true;
        } else if ("double".equals(clazz.getName())) {
            return 1.0;
        } else if ("float".equals(clazz.getName())) {
            return 1.0f;
        } else if ("long".equals(clazz.getName())) {
            return 1L;
        } else if ("byte".equals(clazz.getName())) {
            return 0xFFFFFFFF;
        } else if ("java.lang.Class".equals(clazz.getName())) {
            return this.getClass();
        } else if ("java.math.BigDecimal".equals(clazz.getName())) {
            return new BigDecimal(1);
        } else if ("java.lang.String".equals(clazz.getName())) {
            return "333";
        } else if ("java.util.Hashtable".equals(clazz.getName())) {
            return new Hashtable();
        } else if ("java.util.Hashtable".equals(clazz.getName())) {
            return new Hashtable();
        } else if ("java.util.List".equals(clazz.getName())) {
            return new ArrayList();
        } else {
            // 如果是接口或抽象类,直接跳过
            boolean paramIsAbstract = Modifier.isAbstract(clazz.getModifiers());
            boolean paramIsInterface = Modifier.isInterface(clazz.getModifiers());
            if (paramIsInterface || paramIsAbstract) {
                return null;
            }
            Constructor<?>[] constructorArrs = clazz.getConstructors();
            return newConstructor(constructorArrs, clazz);
        }
    }

}
