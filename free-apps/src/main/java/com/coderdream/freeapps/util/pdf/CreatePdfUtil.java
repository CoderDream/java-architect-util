package com.coderdream.freeapps.util.pdf;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

/**
 * @author CoderDream
 */
public class CreatePdfUtil {

    public static void main(String args[]) throws Exception {
        // 1、Creating a PdfWriter
        String dest = "D:/sample.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // 2、Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        // 3、Adding an empty page
        pdfDoc.addNewPage();

        // 4、Creating a Document
        Document document = new Document(pdfDoc);

        // 5、Closing the document
        document.close();
        System.out.println("PDF Created");
    }
}
