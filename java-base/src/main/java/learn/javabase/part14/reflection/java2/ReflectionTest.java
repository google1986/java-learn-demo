package learn.javabase.part14.reflection.java2;

import com.sun.scenario.effect.impl.prism.PrTexture;
import learn.javabase.part14.reflection.java.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 */
public class ReflectionTest {

    @Test
    public void  ffff() throws  Exception{
        Class<Person> aClass = Person.class;
        Person person = aClass.newInstance();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("+++++++++++");
        Method showNation = aClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String res = (String) showNation.invoke(person, "中国");

        System.out.println(res);
    }

    @Test
    public void testDemo() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "guge");
        System.out.println(name.get(person));
    }

    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();


        //获取指定的属性：要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("age");

        /**
         * 设置当前属性的值
         * set():参数1：指明设置哪个对象的属性   参数2：将此属性值设置为多少
         */
        id.set(p, 100);

        /**
         * 获取当前属性的值
         * get():参数1：获取哪个对象的当前属性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);
    }

    /**
     * 如何操作运行时类中的指定的属性 -- 需要掌握
     * @throws Exception
     */
    @Test
    public void testField1() throws Exception {
        Class<Person> clazz = Person.class;

        //创建运行时类的对象
        Person p = clazz.newInstance();
        Person p2  = clazz.newInstance();

        //1. getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        name.set(p, "Tom");

        name.set(p2,"Jack");
        System.out.println(name.get(p));
        System.out.println(name.get(p2));
    }

    /**
     * 如何操作运行时类中的指定的方法 -- 需要掌握
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        Method show1 = personClass.getMethod("show");
        show1.invoke(person);

        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        Object zhongguo = showNation.invoke(person, "zhongguo");
        System.out.println(zhongguo);

//        Class clazz = Person.class;
//
//        //创建运行时类的对象
//        Person p = (Person) clazz.newInstance();
//
//        /*
//        1.获取指定的某个方法
//        getDeclaredMethod():参数1 ：指明获取的方法的名称  参数2：指明获取的方法的形参列表
//         */
//        Method show = clazz.getDeclaredMethod("show");
//        //2.保证当前方法是可访问的
//        show.setAccessible(true);
//
//        /*
//        3. 调用方法的invoke():参数1：方法的调用者  参数2：给方法形参赋值的实参
//        invoke()的返回值即为对应类中调用的方法的返回值。
//         */
//        //String nation = p.show("CHN");
//        Object returnValue = show.invoke(p, "CHN");
//        System.out.println(returnValue);
//
//        System.out.println("*************如何调用静态方法*****************");
//
//        // private static void showDesc()
//
//        Method showDesc = clazz.getDeclaredMethod("showDesc");
//        showDesc.setAccessible(true);
//        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
////        Object returnVal = showDesc.invoke(null);
//        Object returnVal = showDesc.invoke(Person.class);
//        System.out.println(returnVal);//null
    }

    /**
     * 如何调用运行时类中的指定的构造器
     */
    @Test
    public void testConstructor() throws Exception {
        Class<Person> clazz = Person.class;

        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person tom = declaredConstructor.newInstance("tom");
        System.out.println(tom);

        //private Person(String name)
        /*
        1.获取指定的构造器
        getDeclaredConstructor():参数：指明构造器的参数列表
         */
        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的
        constructor.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);
    }
}