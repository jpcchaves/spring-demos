package com.springdemos.springdemos.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springdemos.springdemos.entity.Message;
import com.springdemos.springdemos.service.usecases.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public void generatePdf(List<Message> messages,
                            HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph1 = new Paragraph("Lista de Gays", fontTitle);

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(100f);

        table.setWidths(new int[] {6,6});

        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.pink);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nome do Gay", font));
        table.addCell(cell);

        for (Message message: messages) {
            table.addCell(String.valueOf(message.getId()));
            table.addCell(message.getMessage());
        }

        document.add(table);

        document.close();
    }
}
