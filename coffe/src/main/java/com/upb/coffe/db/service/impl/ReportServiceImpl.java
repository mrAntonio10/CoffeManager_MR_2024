package com.upb.coffe.db.service.impl;

import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioAuthenticationResponse;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import com.upb.coffe.db.service.AuthenticationService;
import com.upb.coffe.db.service.ReportService;
import com.upb.coffe.db.service.UsuarioService;
import com.upb.coffe.rest.config.JwtService;
import com.upb.coffe.rest.request.UsuarioRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final UsuarioService usuarioService;


    @Override
    public JasperPrint usuariosReport(HttpServletResponse response) throws IOException, JRException {
        List<UsuarioDto> usuario = usuarioService.findAllUsers();

        log.info("Usuarios repo [{}]", usuario);

        File file = ResourceUtils.getFile("classpath:report/userReports.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuario);

        Map<String, Object> params = new HashMap<>();
            params.put("createdBy", "Marcoro");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        return jasperPrint;
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

    }
}
