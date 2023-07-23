package com.springdemos.springdemos.service.usecases;

import com.springdemos.springdemos.entity.Message;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface PdfService {
    void generatePdf(List<Message> messages, HttpServletResponse response) throws IOException;
}
