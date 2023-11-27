package com.techhub.crm.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.techhub.crm.entity.Lead;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFHelper {

    public static ByteArrayInputStream leadPDFReport
            (List<Lead> leads) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory
                    .getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Lead Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(10);
            // Add PDF Table Header ->
            Stream.of("LeadId", "First Name", "Last Name", "Email", "Mobile", "Address",
                            "Company Name", "Description", "Lead Status")
                    .forEach(headerTitle ->
                    {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.
                                getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (Lead lead : leads) {
                PdfPCell leadIdCell = new PdfPCell(new Phrase(lead.getLeadId().
                        toString()));
                leadIdCell.setPaddingLeft(4);
                leadIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                leadIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(leadIdCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase
                        (lead.getFirstName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getLastName())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);

                PdfPCell emailNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getEmail())));
                emailNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                emailNameCell.setPaddingRight(4);
                table.addCell(emailNameCell);

                PdfPCell mobileNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getMobile())));
                mobileNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mobileNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                mobileNameCell.setPaddingRight(4);
                table.addCell(mobileNameCell);

                PdfPCell addressNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getAddress())));
                addressNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                addressNameCell.setPaddingRight(4);
                table.addCell(addressNameCell);

                PdfPCell companyNameTypeNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getCompanyName())));
                companyNameTypeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                companyNameTypeNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                companyNameTypeNameCell.setPaddingRight(4);
                table.addCell(companyNameTypeNameCell);

                PdfPCell descriptionNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getDescription())));
                descriptionNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                descriptionNameCell.setPaddingRight(4);
                table.addCell(descriptionNameCell);

                PdfPCell leadStatusTypeNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getLeadStatus())));
                leadStatusTypeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                leadStatusTypeNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                leadStatusTypeNameCell.setPaddingRight(4);
                table.addCell(leadStatusTypeNameCell);
            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            System.out.println(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

