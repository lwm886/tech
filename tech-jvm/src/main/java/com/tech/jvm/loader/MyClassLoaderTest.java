//package com.tech.jvm.loader;
//
//import java.io.FileInputStream;
//import java.lang.reflect.Method;
//
///**
// * @Author lw
// * @Date 2022/5/25
// * @Description 自定义类加载器的上级类加载器为应用程序类加载器，根据双亲委派机制，应用程序类加载器比自定义类加载器加载优先级高，
// * 如果classpath下存在该类，则类加载器为应用程序类加载器，如果classpath下不存在，才会由自定义类加载器去指定路径下加载
// */
//public class MyClassLoaderTest {
//    static class MyClassLoader extends ClassLoader {
//        private String classPath;
//
//        public MyClassLoader(String classPath) {
//            this.classPath = classPath;
//        }
//
//        private byte[] loadByte(String name) throws Exception {
//            String fileLocation=name.replaceAll("\\.", "/");
//            FileInputStream fis = new FileInputStream(classPath + "/" + fileLocation + ".class");
//            int len = fis.available();
//            byte[] data = new byte[len];
//            fis.read(data);
//            fis.close();
//            return data;
//        }
//
//        protected Class<?> findClass(String name) throws ClassNotFoundException {
//            try {
//                byte[] data = loadByte(name);
//                //defineClass 将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组
//                return defineClass(name,data,0,data.length);
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new ClassNotFoundException();
//            }
//
//        }
//
//
//        public static void main(String[] args) throws Exception {
//            //初始化自定义类加载器，会先初始化父类ClassLoader,其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
//            MyClassLoader classLoader = new MyClassLoader("C://test");
//            //D盘创建包路径对应的目录，将User类的复制类User1.class丢入该目录
//            Class<?> clazz = classLoader.loadClass("com.tech.jvm.loader.User1");
//            Object obj = clazz.newInstance();
//            Method method = clazz.getDeclaredMethod("sout", null);
//            method.invoke(obj,null);
//            System.out.println(clazz.getClassLoader().getClass().getName());
//        }
//
//    }
//}
