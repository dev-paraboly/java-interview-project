package com.parabol.interview.mockproject.services;

import com.parabol.interview.mockproject.domain.reports.*;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.reports.utils.GeneralComparator;

import com.parabol.interview.mockproject.repositories.InternalControlRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.paraboly.reportlib.GenericReports;


@Service
@Slf4j
public class ReportService {

    @Autowired
    InternalControlRepository internalControlRepository ;

    public XSSFWorkbook createGeneralReport(Integer year) throws IOException {
        List<GeneralStatistics> generalStatisticsListByRegion = internalControlRepository.getGeneralStatisticsByRegion(year);
        List<GeneralStatistics> generalStatisticsByBiddingType = internalControlRepository.getGeneralStatisticsByBiddingType(year);
        List<GeneralStatistics> generalStatisticsByBiddingProcedure = internalControlRepository.getGeneralStatisticsByBiddingProcedure(year);


        List<GeneralStatisticsExtended> finalGeneralStatisticsListByRegion = extendStatisticsList(generalStatisticsListByRegion, Constants.BIDDING_DEPARTMENT_LOOKUP, "Bölge");
        List<GeneralStatisticsExtended> finalGeneralStatisticsByBiddingType = extendStatisticsList(generalStatisticsByBiddingType, Constants.BIDDING_TYPE_LOOKUP, "Tür");
        List<GeneralStatisticsExtended> finalGeneralStatisticsByBiddingProcedure = extendStatisticsList(generalStatisticsByBiddingProcedure, Constants.BIDDING_PROCEDURE_LOOKUP, "Usül");

        finalGeneralStatisticsByBiddingProcedure.sort(new GeneralComparator());
        finalGeneralStatisticsByBiddingType.sort(new GeneralComparator());
        finalGeneralStatisticsListByRegion.sort(new GeneralComparator());

        GenericReports.ReportData generalStatisticsByRegionData = new GenericReports.ReportData() {{
           setElementList(finalGeneralStatisticsListByRegion);
           setReportType("Bölgeye Göre İhaleler");
           setColumnToMetadataMapping(new LinkedHashMap<String, GenericReports.ColumnMetadata>() {{
               put("BÖLGE", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingDepartment");
                   setColumnSize(2);
                   setBottomCalculation("string:Genel Toplam");
               }});
               put("İHALE ADET", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingCount");
                   setBottomCalculation("sum");
                   setCellContent("count");
               }});
               put("TENZİLAT", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getDiscount");
                   setBottomCalculation("avg");
                   setCellContent("percentage");
               }});
           }});
        }};

        GenericReports.ReportData generalStatisticsByBiddingTypeData = new GenericReports.ReportData() {{
           setElementList(finalGeneralStatisticsByBiddingType);
           setReportType("Türe Göre İhaleler");
           setColumnToMetadataMapping(new LinkedHashMap<String, GenericReports.ColumnMetadata>() {{
               put("TÜR", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingDepartment");
                   setColumnSize(2);
                   setBottomCalculation("string:Genel Toplam");
               }});
               put("İHALE ADET", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingCount");
                   setBottomCalculation("sum");
                   setCellContent("count");
               }});
               put("TENZİLAT", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getDiscount");
                   setBottomCalculation("avg");
                   setCellContent("percentage");
               }});
           }});
        }};

        GenericReports.ReportData generalStatisticsByBiddingProcedureData = new GenericReports.ReportData() {{
           setElementList(finalGeneralStatisticsByBiddingProcedure);
           setReportType("İhale Usulüne Göre İhaleler (Yapım ve Y-Bakım)");
           setColumnToMetadataMapping(new LinkedHashMap<String, GenericReports.ColumnMetadata>() {{
               put("USÜL", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingDepartment");
                   setColumnSize(2);
                   setBottomCalculation("string:Genel Toplam");
               }});
               put("İHALE ADET", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getBiddingCount");
                   setBottomCalculation("sum");
                   setCellContent("count");
               }});
               put("TENZİLAT", new GenericReports.ColumnMetadata() {{
                   setFunctionName("getDiscount");
                   setBottomCalculation("avg");
                   setCellContent("percentage");
               }});
           }});
        }};

        return new GenericReports.Builder("Genel Rapor")
                .addData(generalStatisticsByRegionData)
                .addData(generalStatisticsByBiddingTypeData)
                .addData(generalStatisticsByBiddingProcedureData)
                .create();
    }

    private static List<GeneralStatisticsExtended> extendStatisticsList(List<GeneralStatistics> statisticsList, Map<String, Integer> lookup, String statisticsType) {
        Map<Integer, String> lookupInversed = null;
        if (lookup != null)
            lookupInversed = lookup.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        List <GeneralStatisticsExtended> extendedList = new ArrayList<>(statisticsList.size());
        for (GeneralStatistics statistics:statisticsList) {
            GeneralStatisticsExtended extended = new GeneralStatisticsExtended(statistics, statisticsType);
            if (lookupInversed != null) {
                try {
                    extended.setBiddingDepartment(lookupInversed.get(Integer.valueOf(extended.getBiddingDepartment())));
                } catch (Exception err) {
                    extended.setBiddingDepartment("Veri Girilmemiştir");
                }
            }
            if (extended.getBiddingDepartment() == null)
                extended.setBiddingDepartment("Veri Girilmemiştir");
            extendedList.add(extended);
        }
        return extendedList;
    }

    public XSSFWorkbook allProjectsReport(Integer year, Integer biddingType) {
        List<InternalControl> internalControlList = internalControlRepository.findAllByFilter(biddingType, year);
        List<InternalControlExtended> internalControlExtendedList = internalControlList.stream().map(internalControl -> {
            InternalControlExtended internalControlExtended = new InternalControlExtended(internalControl);
            internalControlExtended.extend();
            return internalControlExtended;
        }).collect(Collectors.toList());
        GenericReports.ReportData allProjectsData = new GenericReports.ReportData() {{
            setElementList(internalControlExtendedList);
            setReportType("Ön Mali Kontrol İhaleleri");
            setDisableBottomRow(true);
            setRowColorFunction("getColor");
            setColumnToMetadataMapping(new LinkedHashMap<String, GenericReports.ColumnMetadata>() {{
                put("Ön Mali Kontrol Durumu", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getStatusString");
                }});
                put("Dosya Yılı", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getFileNoYear");
                    setCellContent("year");
                }});
                put("İhale Birimi", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getBiddingDepartmentString");
                }});
                put("Kik No", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getBiddingNo");
                }});
                put("Proje Adı", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getName");
                }});
                put("Yüklenici", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getContractor");
                }});
                put("Tenzilat", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getDiscount");
                    setCellContent("percentage");
                }});
                put("İhale Türü", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getBiddingTypeString");
                }});
                put("İhale Usulü", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getBiddingProcedureString");
                }});
                put("İhaleyi Talep Eden Birim", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getRequestingDepartment");
                }});
                put("İhale Dosyası Geliş Tarihi", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getCreatedAtString");
                }});
                put("Onay Tarihi", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getApprovalDateString");
                }});
                put("Sözleşmenin Geliş Tarihi", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getDateOfContractString");
                }});
                put("Açıklama", new GenericReports.ColumnMetadata() {{
                    setFunctionName("getExplanation");
                }});
            }});
        }};
        return new GenericReports.Builder("ÖMK Rapor")
                .addData(allProjectsData)
                .create();
    }
}
