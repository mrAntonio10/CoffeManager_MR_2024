package com.upb.coffe.db.service;

import com.upb.coffe.db.model.usuario.dto.UsuarioAuthenticationResponse;
import com.upb.coffe.rest.request.UsuarioRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReportService {

    JasperPrint usuariosReport(HttpServletResponse response) throws IOException, JRException;
}
