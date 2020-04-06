package n.baldyoung.StringSplicing;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * SQL拼接小工具
 * 用途举例：在需要对某表进行批量插入时，生成批量插入的SQL语句。
 * 使用方法：（以Windows举例）命令行编译当前源码，生成字节码后，使用java运行该可执行文件。
 *          javac -encoding utf-8 StringSplicingModule.java
 *          java StringSplicingModule -f "('%s', %s, 0, 'defaultImg.jpg')" -s product.txt -o out.txt
 *
 */
public class StringSplicingModule {

    final static String StringFlag = "%s";

    static String[] commandParameter;

    /**
     * -f 字符串模板
     * -s 输入文件
     * -se 输入文件编码格式
     * -o 输出文件
     * -oe 输出文件编码格式
     * @param args
     */
    public static void main(String... args) {
        commandParameter = args;
        // 获取必要参数
        String format = getCommandParameter("-f");
        String sourceFilePathName = getCommandParameter("-s");
        if (null == format || null == sourceFilePathName) {
            out.println("命令行参数错误");
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(sourceFilePathName);
        } catch (FileNotFoundException e) {
            out.println("输入文件不存在");
            return;
        }
        // 获取编码参数
        String sEncoding = getCommandParameter("-se");
        String oEncoding = getCommandParameter("-oe");
        if (null == sEncoding) {
            sEncoding = "utf-8";
        }
        if (null == oEncoding) {
            oEncoding = "utf-8";
        }
        // 获取输出指定
        String outFilePathName = getCommandParameter("-o");
        OutputStream outputStream;
        if (null == outFilePathName) {
            outputStream = System.out;
            String defaultSystemEncoding = System.getProperties().getProperty("file.encoding");
            oEncoding = defaultSystemEncoding;
            // System.out.println(defaultSystemEncoding);
        } else {
            try {
                outputStream = new FileOutputStream(outFilePathName);
            } catch (FileNotFoundException e) {
                out.println("输出文件不存在");
                return;
            }
        }
        // 拼接字符串
        StringSplicing(format, inputStream, outputStream, sEncoding, oEncoding);
    }

    /**
     * 获取命令行指定参数
     * @param key
     * @return
     */
    static String getCommandParameter(String key) {
        if (null == commandParameter) {
            return null;
        }
        int i;
        for (i=0; i<commandParameter.length; i++) {
            if (key.equals(commandParameter[i])) {
                i++;
                break;
            }
        }
        if (i >= commandParameter.length) {
            return null;
        }
        return commandParameter[i];
    }

    static void StringSplicing(String format, InputStream source) {
        StringSplicing(format, source, out);
    }

    static void StringSplicing(String format, InputStream source, OutputStream out) {
        StringSplicing(format, source, out, "utf-8", "utf-8");
    }

    static void StringSplicing(String format, InputStream source, OutputStream out, String inputCharset, String outCharset) {
        try {
            Scanner scanner = new Scanner(source, inputCharset);
            Writer writer = new OutputStreamWriter(out, outCharset);
            StringBuilder builder = new StringBuilder();
            int index = 0;
            String[] subPart = format.split(StringFlag);
            int argsNumber = subPart.length - 1;
            while (scanner.hasNext()) {
                builder.append(subPart[0]);
                for (int i=0; i<argsNumber; i++) {
                    String temp = scanner.nextLine();
                    builder.append(temp);
                    builder.append(subPart[i+1]);
                }
                index++;
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
