package com.example.demo.pdf;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader {

    /**   
     * @Title: main   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param args      
     * @return: void      
     * @throws 
     * 
     *   PDF文件加载有两种方式，无明显差异，方式二:代码较简洁
     */  
    public static void main(String[] args){
//    	readText();
    	readImage();
    }
    
    public static void readText(){
    	File pdfFile = new File("E:\\work\\微信资料\\WeChat Files\\liyansheng1989\\FileStorage\\File\\2020-08\\润和员工福利计划方案(1).pdf");
        PDDocument document = null;
        try
        {
            // 方式一：
            /**
            InputStream input = null;
            input = new FileInputStream( pdfFile );
            //加载 pdf 文档
            PDFParser parser = new PDFParser(new RandomAccessBuffer(input));
            parser.parse();
            document = parser.getPDDocument();
            **/

            // 方式二：代码较简洁
            document=PDDocument.load(pdfFile);

            // 获取页码
            int pages = document.getNumberOfPages();

            // 读文本内容
            PDFTextStripper stripper=new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pages);
            String content = stripper.getText(document);
            System.out.println(content);     
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public static void readImage(){

        // 待解析PDF
        File pdfFile = new File("E:\\tianjin\\doc\\信用卡\\整理\\电子签章\\天津银行信用卡声明及签署函.pdf");      
        // 空白PDF
        File pdfFile_out = new File("E:\\tianjin\\doc\\信用卡\\整理\\电子签章\\testout.pdf");

        PDDocument document = null;  
        PDDocument document_out = null;  
        try {  
            document = PDDocument.load(pdfFile);  
            document_out = PDDocument.load(pdfFile_out);  
        } catch (IOException e) {  
            e.printStackTrace();
        }  

        int pages_size = document.getNumberOfPages();

        System.out.println("getAllPages==============="+pages_size);  
        int j=0;

        for(int i=0;i<pages_size;i++) {  
            PDPage page = document.getPage(i);
            PDPage page1 = document_out.getPage(0);
            PDResources resources = page.getResources();  
            Iterable xobjects = resources.getXObjectNames();

            if (xobjects != null) {  
                Iterator imageIter = xobjects.iterator();  
                while (imageIter.hasNext()) {  
                    COSName key = (COSName) imageIter.next();  
                    if(resources.isImageXObject(key)){              
                        try {
                            PDImageXObject image = (PDImageXObject) resources.getXObject(key);

                            // 方式一：将PDF文档中的图片 分别存到一个空白PDF中。
                            PDPageContentStream contentStream = new PDPageContentStream(document_out,page1,AppendMode.APPEND,true);

                            float scale = 1f;
                            contentStream.drawImage(image, 20,20,image.getWidth()*scale,image.getHeight()*scale);
                            contentStream.close();
                            document_out.save("E:\\tianjin\\doc\\信用卡\\整理\\电子签章\\123"+j+".pdf");

                            System.out.println(image.getSuffix() + ","+image.getHeight() +"," + image.getWidth());

                            /**
                            // 方式二：将PDF文档中的图片 分别另存为图片。
                            File file = new File("/Users/xiaolong/Downloads/123"+j+".png");
                            FileOutputStream out = new FileOutputStream(file);

                            InputStream input = image.createInputStream();                   

                            int byteCount = 0;
                            byte[] bytes = new byte[1024];

                            while ((byteCount = input.read(bytes)) > 0)
                            {                       
                                out.write(bytes,0,byteCount);       
                            }

                            out.close();
                            input.close();
                            **/

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } 
                        //image count
                        j++;  
                    }                 
                }  
            } 
        } 

        System.out.println(j);
    }  

}