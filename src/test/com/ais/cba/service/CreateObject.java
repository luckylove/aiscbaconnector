package com.ais.cba.service;

import com.ais.cba.util.AISUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * User: son.nguyen
 * Date: 8/6/14
 * Time: 4:53 PM
 */
public class CreateObject {

    private boolean isLog;
    private String ouputName;
    private String input = "E:\\temp\\object.txt";

    public CreateObject(String ouputName, boolean isLog) {
        this.isLog = isLog;
        this.ouputName = ouputName;
    }


    public static void main(String[] args) throws Exception {
        CreateObject ob = new CreateObject("CBA_SMS_CONF", false);
        BufferedReader r = null;
        OutputStreamWriter out = null;
        try {
            InputStream in = new FileInputStream(new File(ob.input));
            r = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String str = null;
            StringBuilder cls = ob.createCls();

            while ((str = r.readLine()) != null) {
                String s = buildLine(str);
                cls.append(s);
                //out.write(s);
                System.out.println("data from InputStream as String : " + s);
            }

            cls.append("\n\n}");
            out = ob.writeCls(cls.toString());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (r != null) {
                r.close();
                out.flush();
                out.close();
            }
        }
    }

    private StringBuilder createCls(){
        StringBuilder bf = new StringBuilder(50);
        if (this.isLog) {
            bf.append("package com.ais.cba.service.model.log;\n\n\n");
        } else {
            bf.append("package com.ais.cba.service.model.config;\n\n\n");
        }
        bf.append("public class " + this.ouputName + " {\n\n");
        return bf;
    }

    private OutputStreamWriter writeCls(String s) throws IOException {
        String fileP = "E:\\Project\\testf\\AISCBA\\src\\main\\java\\com\\ais\\cba\\service\\model\\log\\";
        if (!this.isLog) {
            fileP = "E:\\Project\\testf\\AISCBA\\src\\main\\java\\com\\ais\\cba\\service\\model\\config\\";
        }
        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(fileP + this.ouputName + ".java"), "UTF-8");
        out.write(s);
        return out;
    }

    private static String buildLine(String line) {
        StringBuilder bf = new StringBuilder(50);
        if (StringUtils.isNotEmpty(line)) {
            bf.append("private");
            if (line.toUpperCase().indexOf("VARCHAR2") > 0) {
                bf.append(" String ");
            } else if (line.indexOf("NUMBER") > 0) {
                bf.append(" Long ");
            } else if (line.indexOf("DATE") > 0) {
                bf.append(" Date ");
            }
            int fi = line.indexOf("\""), li = line.lastIndexOf("\"");
            if (fi >= 0 && li > 0 && fi < li) {
                String aka = line.substring(fi + 1, li);
                aka = AISUtils.toVariable(aka);
                bf.append(aka).append(" ;\n");
            } else if(line.toLowerCase().indexOf("i") >=0 ){
                String aka = line.substring(line.indexOf("i") + 1, line.toLowerCase().lastIndexOf(" in"));
                aka = AISUtils.toVariable(aka);
                bf.append(aka).append(" ;\n");
            }
        }
        return bf.toString();
    }
}
