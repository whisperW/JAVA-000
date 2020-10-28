package org.wannajob.jvm.homework;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Homework :
 *    JVM - 2
 *   自定义classLoader,加载Hello.xlass文件,执行hello方法,此文件内容是一个Hello.class文件的所有字节 (x = 255-x)处理后的文件
 *   Hello.xlass文件已经存放到resources源码目录下面
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {

        try {
            String clazzFileName = "Hello.xlass";
            HelloClassLoader helloClassLoader = new HelloClassLoader();
//            helloClassLoader.printResourcePath();
            Class<?> helloClass = helloClassLoader.findClass(clazzFileName, "Hello");
            Object o = helloClass.newInstance();
            Method helloMethod = helloClass.getDeclaredMethod("hello");
            helloMethod.invoke(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 打印源码资源目录路径
    private void printResourcePath() {
        // file:/D:/learn_java/advancing-java/target/classes/org/wannajob/jvm/homework/
        System.out.println(this.getClass().getResource(""));
        // file:/D:/learn_java/advancing-java/target/classes/
        System.out.println(this.getClass().getResource("/"));
        // file:/D:/learn_java/advancing-java/target/classes/
        System.out.println(this.getClass().getClassLoader().getResource(""));
        // null
        System.out.println(this.getClass().getClassLoader().getResource("/"));
    }

    private Class<?> findClass(String clazzFileName, String clazzName) {
        ByteArrayOutputStream baos = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            baos = new ByteArrayOutputStream();
            inputStream = this.getClass().getClassLoader().getResourceAsStream(clazzFileName);
            BufferedInputStream bis = new BufferedInputStream(inputStream, 1024 * 8);
            int offset = -1;
            while ( (offset = bis.read()) != -1) {
                byte b = (byte) offset;
                byte originByte = (byte)(255 - b);
                baos.write(originByte);
            }
            byte[] clazzBytes = baos.toByteArray();
            return defineClass(clazzName, clazzBytes, 0, clazzBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (baos != null) {
                try {
                    baos.flush();
                } catch (IOException e) {
                }
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

}
