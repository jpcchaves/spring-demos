package com.springdemos.springdemos.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import com.springdemos.springdemos.entity.Pet;
import com.springdemos.springdemos.service.usecases.MessageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {
        messageService.generateReport(response);
    }

    private String getHtmlTemplate() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/templates/petcard.html");
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private ResponseEntity<?> dfgfgd() throws IOException {
        String htmlTemplate = getHtmlTemplate();
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Zezin");
        pet.setGender('M');

        Map<String, String> data = new HashMap<>();

        data.put("id", String.valueOf(pet.getId()));
        data.put("name", pet.getName());
        data.put("gender", String.valueOf(pet.getGender()));

        for (Map.Entry<String, String> entry : data.entrySet()) {
            htmlTemplate = htmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlTemplate, outputStream);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "pet_id_card.pdf";
        headers.setContentDispositionFormData(filename, filename);

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
