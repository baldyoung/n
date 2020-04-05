package n.baldyoung.StringSplicing;

import java.io.*;
import java.util.Scanner;
import java.util.WeakHashMap;

import static java.lang.System.in;
import static java.lang.System.out;

public class StringSplicingModule {

    final static String StringFlag = "%s";

    public static void main(String... args) {
        String format = args[0];
        String sourceFilePathName = args[1];

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
            int argsNumber = format.split(StringFlag).length;
            String[] args = new String[argsNumber];
            while (scanner.hasNext()) {
                for (int i=0; i<argsNumber; i++) {
                    args[i] = scanner.nextLine();
                }
                builder.append(String.format(format, args));
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
