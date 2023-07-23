package com.springdemos.springdemos.service.impl;

import com.springdemos.springdemos.entity.Message;
import com.springdemos.springdemos.repository.MessageRepository;
import com.springdemos.springdemos.service.usecases.MessageService;
import com.springdemos.springdemos.service.usecases.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final PdfService pdfService;

    public MessageServiceImpl(MessageRepository messageRepository,
                              PdfService pdfService) {
        this.messageRepository = messageRepository;
        this.pdfService = pdfService;
    }

    @Override
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @Override
    public void generateReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        long timestamp = new Date().getTime();
        String headerkey = "Content-Disposition";
        String headervalue = buildFileName("broslist", timestamp, "pdf");
        response.setHeader(headerkey, headervalue);

        pdfService.generatePdf(list(), response);
    }

    private String buildFileName(String filename, long timestamp, String fileExtesion) {
        StringBuilder sb = new StringBuilder();

        sb.append("attachment; ");
        sb.append("filename=").append(filename);
        sb.append(timestamp);
        sb.append(".").append(fileExtesion);

        return sb.toString();
    }
}
