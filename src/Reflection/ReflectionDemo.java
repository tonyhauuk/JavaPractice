package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo {
    /**
     * 为了看清楚Java反射部分代码，所有异常我都最后抛出来给虚拟机处理！
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
        // Demo1. 通过Java反射机制得到类的包名和类名
        Demo1();
        System.out.println("===============================================");

        // Demo2. 验证所有的类都是Class类的实例对象
        Demo2();
        System.out.println("===============================================");

        // Demo3. 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
        Demo3();
        System.out.println("===============================================");

        // Demo4: 通过Java反射机制得到一个类的构造函数，并实现构造带参实例对象
        Demo4();
        System.out.println("===============================================");

        // Demo5: 通过Java反射机制操作成员变量, set 和 get
        Demo5();
        System.out.println("===============================================");

        // Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
        Demo6();
        System.out.println("===============================================");

        // Demo7: 通过Java反射机制调用类中方法
        Demo7();
        System.out.println("===============================================");

        // Demo8: 通过Java反射机制获得类加载器
        Demo8();
        System.out.println("===============================================");

    }

    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    public static void Demo1() {
        Person person = new Person();
        System.out.println("Demo1: 包名: " + person.getClass().getPackage().getName() + "，" + "完整类名: " + person.getClass().getName());

        /**
         运行结果：
         Demo1: 包名: com.b510.hongten.test.reflex，完整类名: com.b510.hongten.test.reflex.Person
         */
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     *
     * @throws ClassNotFoundException
     */
    public static void Demo2() throws ClassNotFoundException {
        // 定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;

        // 写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName("com.b510.hongten.test.reflex.Person");
        System.out.println("Demo2:(写法1) 包名: " + class1.getPackage().getName() + "，" + "完整类名: " + class1.getName());

        /**
         运行结果：
         Demo2:(写法1) 包名: com.b510.hongten.test.reflex，完整类名: com.b510.hongten.test.reflex.Person
         */

        // 写法2
        class2 = Person.class;
        System.out.println("Demo2:(写法2) 包名: " + class2.getPackage().getName() + "，" + "完整类名: " + class2.getName());

        /**
         运行结果：
         Demo2:(写法2) 包名: com.b510.hongten.test.reflex，完整类名: com.b510.hongten.test.reflex.Person
         */
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void Demo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> class1 = null;
        class1 = Class.forName("com.b510.hongten.test.reflex.Person");
        // 由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数哈～
        Person person = (Person) class1.newInstance();
        person.setAge(20);
        person.setName("Hongten");
        System.out.println("Demo3: " + person.getName() + " : " + person.getAge());

        /**
         运行结果：
         Demo3: Hongten : 20
         */
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     *
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     */
    public static void Demo4() throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;

        class1 = Class.forName("com.b510.hongten.test.reflex.Person");
        // 得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();

        person1 = (Person) constructors[0].newInstance();
        person1.setAge(30);
        person1.setName("Hongten");

        person2 = (Person) constructors[1].newInstance(20, "Hongten");

        System.out.println("Demo4: " + person1.getName() + " : " + person1.getAge() + "  ,   " + person2.getName() + " : " + person2.getAge());

        /**
         运行结果：
         Demo4: Hongten : 30  ,   Hongten : 20
         */
    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void Demo5() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.b510.hongten.test.reflex.Person");
        Object obj = class1.newInstance();

        Field personNameField = class1.getDeclaredField("name");
        personNameField.setAccessible(true);
        personNameField.set(obj, "HONGTEN");

        System.out.println("Demo5: 修改属性之后得到属性变量的值：" + personNameField.get(obj));

        /**
         运行结果：
         Demo5: 修改属性之后得到属性变量的值：HONGTEN
         */
    }

    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     *
     * @throws ClassNotFoundException
     */
    public static void Demo6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.b510.hongten.test.reflex.SuperMan");

        // 取得父类名称
        Class<?> superClass = class1.getSuperclass();
        System.out.println("Demo6:  SuperMan类的父类名: " + superClass.getName());

        /**
         运行结果：
         Demo6:  SuperMan类的父类名: com.b510.hongten.test.reflex.Person
         */

        System.out.println("===============================================");

        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }
        /**
         运行结果：
         类中的成员: private boolean com.b510.hongten.test.reflex.SuperMan.BlueBriefs
         */

        System.out.println("===============================================");

        // 取得类方法
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("Demo6,取得SuperMan类的方法：");
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法： " + methods[i]);
        }

        /**
         运行结果：
         Demo6,取得SuperMan类的方法：
         函数名：isBlueBriefs
         函数返回类型：boolean
         函数访问修饰符：public
         函数代码写法： public boolean com.b510.hongten.test.reflex.SuperMan.isBlueBriefs()
         Demo6,取得SuperMan类的方法：
         函数名：setBlueBriefs
         函数返回类型：void
         函数访问修饰符：public
         函数代码写法： public void com.b510.hongten.test.reflex.SuperMan.setBlueBriefs(boolean)
         Demo6,取得SuperMan类的方法：
         函数名：fly
         函数返回类型：void
         函数访问修饰符：public
         函数代码写法： public void com.b510.hongten.test.reflex.SuperMan.fly()
         Demo6,取得SuperMan类的方法：
         函数名：walk
         函数返回类型：void
         函数访问修饰符：public
         函数代码写法： public void com.b510.hongten.test.reflex.SuperMan.walk(int)
         */

        System.out.println("===============================================");

        // 取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到哈
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName());
        }

        /**
         运行结果：
         实现的接口类名: com.b510.hongten.test.reflex.ActionInterface
         */

    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    public static void Demo7() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("com.b510.hongten.test.reflex.SuperMan");

        System.out.println("Demo7: \n调用无参方法fly()");
        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        System.out.println("调用有参方法walk(int m)");
        method = class1.getMethod("walk", int.class);
        method.invoke(class1.newInstance(), 100);

        /**
         运行结果：
         Demo7:
         调用无参方法fly()
         fly method....
         调用有参方法walk(int m)
         fly in 100 m
         */

    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     * <p>
     * 在java中有三种类类加载器。[这段资料网上截取]
     * <p>
     * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * <p>
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * <p>
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     *
     * @throws ClassNotFoundException
     */
    public static void Demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.b510.hongten.test.reflex.SuperMan");
        String nameString = class1.getClassLoader().getClass().getName();

        System.out.println("Demo8: 类加载器类名: " + nameString);

        /**
         运行结果：
         Demo8: 类加载器类名: sun.misc.Launcher$AppClassLoader
         */
    }

}


class Person {
    private int age;
    private String name;

    public Person() {

    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class SuperMan extends Person implements ActionInterface {
    private boolean BlueBriefs;

    public void fly() {
        System.out.println("fly method....");
    }

    public boolean isBlueBriefs() {
        return BlueBriefs;
    }

    public void setBlueBriefs(boolean blueBriefs) {
        BlueBriefs = blueBriefs;
    }

    public void walk(int m) {
        System.out.println("fly in " + m + " m");
    }
}

interface ActionInterface {
    public void walk(int m);
}