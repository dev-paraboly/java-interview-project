package com.parabol.interview.mockproject.controllers;

import com.parabol.interview.mockproject.repositories.InternalControlRepository;
import com.parabol.interview.mockproject.services.ReportService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("reports")
public class ReportController {
    @Autowired
	ReportService reportService;

    @Autowired
	InternalControlRepository internalControlRepository;

    @GetMapping(value = "/general")
    public void generalReport(@RequestParam Integer year, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = reportService.createGeneralReport(year);
        response.addHeader("Content-disposition", fileNameBuilder("genel-rapor", year));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        workbook.write(response.getOutputStream());
        response.flushBuffer();
    }

    @GetMapping(value = "/all")
    public void getAllProjectsByFilter(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "bidding_type", required = false) Integer biddingType,
            HttpServletResponse response
    ) throws IOException {
        XSSFWorkbook workbook = reportService.allProjectsReport(year, biddingType);
        String nameBase = "rapor";
        if (biddingType != null) {
            nameBase += biddingType;
        }
        response.addHeader("Content-disposition", fileNameBuilder(nameBase, year));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        workbook.write(response.getOutputStream());
        response.flushBuffer();
    }

    private static String fileNameBuilder(String name, Integer year) {
        StringBuilder builder = new StringBuilder("attachment;filename=");
        String filename = builder
                .append(name)
                .append("-")
                .append(year)
                .append(".xlsx")
                .toString();
        return filename;
    }
}
