package com.silly.controller.tools;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

import static jdk.internal.org.jline.utils.Log.error;
import static jdk.internal.org.jline.utils.Log.info;

public class PDF {
    public static void makePDF(String path,int filename, Map map) {
        // 获取项目部署的根目录
        String desFile = path+String.valueOf(filename)+".pdf";// 正式pdf保存路径
        String srcFile =path+"租房合同模板.pdf";// 合同模版的路径
        PdfReader reader = null;
        PdfStamper stamper = null;
        ByteArrayOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 防止中文乱码
            reader = new PdfReader(srcFile);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);

            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(bf);

            // 1、替换表单元素中的变量为具体的值
            for (Iterator<String> it = form.getFields().keySet().iterator(); it.hasNext(); ) {
                String name = (String) it.next();
                form.setField(name, (String) map.get(name));
                form.setFieldProperty(name, "textfont", bf, null);
            }

            // 这两步必须有,否则pdf生成失败
            stamper.setFormFlattening(false);
            stamper.close();

            // 生成填充完成的PDF合同文件
            fos = new FileOutputStream(desFile);
            fos.write(bos.toByteArray());

        } catch (FileNotFoundException e) {
            error("FileNotFoundException, srcFile==" + srcFile, e);
        } catch (Exception e) {
            error("Exception, srcFile==" + srcFile, e);
        } finally {
            if (null != reader) {
                reader.close();
            }
            try {
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                info("关闭ByteArrayOutputStream失败", e);
            }

            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                info("关闭FileOutputStream失败", e);
            }
        }
    }
}
