package com.example.demo.p01.dp06.singleton.bean;

import com.example.demo.p01.dp06.singleton.Priority;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Logger {
    // singleton - pattern
    private static Logger logger;
    private String fileName;
    private Priority priority;
    private Properties properties;

    /**
     * Private constructor构造函数是私有的，因此，此类不能被继承
     */
    private Logger() {
        logger = this;
    }

    /**
     * this method initialises the logger, creates an object
     */
    public static void initialize() {
        logger = new Logger();
    }

    public static Logger getLogger() {
        return logger;
    }

    /**
     * Level of logging, error or information etc
     *
     * @return level, int
     */
    public int getRegisteredLevel() {
        int i = 0;
        try {
            InputStream inputstream = getClass().getResourceAsStream(
                    "Logger.properties");
            properties.load(inputstream);
            inputstream.close();
            i = Integer.parseInt(properties.getProperty(
                    "**logger.registeredlevel**"));
            if (i < 0 || i > 3) {
                i = 0;
            }
        } catch (Exception exception) {
            System.out.println("Logger: Failed in the getRegisteredLevel method");
            exception.printStackTrace();
        }
        return i;
    }

    /**
     * One file will be made daily. So, putting date time in file
     * name.
     *
     * @param gc GregorianCalendar
     * @return String, name of file
     */
    private String getFileName(GregorianCalendar gc) {
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateString = dateFormat1.format(gc.getTime());
//        String fileName = "C:\\temp\\log\\PatternsExceptionLog-" +
//                dateString + ".txt"; System.getProperty("user.dir")

        String fileName = System.getProperty("user.dir")
                + "\\src\\main\\java\\com\\example\\demo\\p01\\dp06\\singleton\\bean\\log.txt";
        System.out.println(fileName);
        return fileName;
    }

    /**
     * A mechanism to log message to the file.
     *
     * @param message String
     */
    public void logMsg(String message) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            String fileName = getFileName(gc);
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintStream ps = new PrintStream(fos);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat(
                    "EEE, MMM d, yyyy 'at' hh:mm:ss a");
            ps.println("<" + dateFormat2.format(gc.getTime()) + ">[" + message +
                    "]");
            ps.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
