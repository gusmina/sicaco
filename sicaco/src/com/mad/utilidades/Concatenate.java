/*
 * $Id: Concatenate.java,v 1.4 2006/10/27 17:24:01 xlv Exp $
 * $Name:  $
 *
 * This code is free software. It may only be copied or modified
 * if you include the following copyright notice:
 *
 * This class by Mark Thompson. Copyright (c) 2002 Mark Thompson.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

/**
 * This class demonstrates copying a PDF file using iText.
 * @author Mark Thompson
 */
package com.mad.utilidades;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

/**
 * Tool that can be used to concatenate existing PDF files.
 */
public class Concatenate {

    /**
     * Esta clase es utilizada para concatenar un conjunto
     * de archivos en formato pdf, en un único archivo
     * @param args Lista de archivo a unir (PDF)
     * @param archivoUnificado nombe del archivo unificado
     */
    public void concatenarPDF(Object args[],String archivoUnificado) {
           try {
                int pageOffset = 0;
                ArrayList master = new ArrayList();
                int f = 0;
                String outFile = archivoUnificado;
                Document document = null;
                PdfCopy  writer = null;
                while (f < args.length) {
                    // we create a reader for a certain document
                    PdfReader reader = new PdfReader((String)args[f]);
                    reader.consolidateNamedDestinations();
                    // we retrieve the total number of pages
                    int n = reader.getNumberOfPages();
                    List bookmarks = SimpleBookmark.getBookmark(reader);
                    if (bookmarks != null) {
                        if (pageOffset != 0)
                            SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                        master.addAll(bookmarks);
                    }
                    pageOffset += n;
                    
                    if (f == 0) {
                        // step 1: creation of a document-object
                        document = new Document(reader.getPageSizeWithRotation(1));
                        // step 2: we create a writer that listens to the document
                        writer = new PdfCopy(document, new FileOutputStream(outFile));
                        // step 3: we open the document
                        document.open();
                    }
                    // step 4: we add content
                    PdfImportedPage page;
                    for (int i = 0; i < n; ) {
                        ++i;
                        page = writer.getImportedPage(reader, i);
                        writer.addPage(page);
                    }
                    PRAcroForm form = reader.getAcroForm();
                    if (form != null)
                        writer.copyAcroForm(reader);
                    f++;
                }
                if (!master.isEmpty())
                    writer.setOutlines(master);
                // step 5: we close the document
                document.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    
}
