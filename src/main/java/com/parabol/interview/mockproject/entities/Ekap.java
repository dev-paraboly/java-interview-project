package com.parabol.interview.mockproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

//it is same as vw_ihale_ve_ilan, due to legacy we didn't want to change name
@Entity
@Data
@Table(name = "ekap", schema = "mock_schema")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ekap {
    @Id
    private long id;
    @Column(name = "yil")
    private int year;
    @Column(length = 20)
    private String ikn;
    @Column(name = "bolge_no")
    private int regionId;
    @Column(name = "bolge_adi", length = 100)
    private String regionName;
    @Column(name = "ihale_adi", length = 2000)
    private String biddingName;
    @Column(name = "ihale_tarih_saat")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date biddingDate;
    @Column(name = "ekap_numarasi")
    private long ekapNumber;
    @Column(name = "proje_no", length = 200)
    private String projectNumber;
    @Column(name = "ihale_turu_no")
    private int biddingTypeId;

    @Column(name = "ihale_turu_text")
    private String biddingTypeText;

    @Column(name = "ihale_usulu_no")
    private int biddingMethodId;

    @Column(name = "ihale_usulu_text")
    private String biddingMethodText;

    @Column(name = "ihale_finansman_turu_no")
    private int biddingFinanceTypeId;

    @Column(name = "ihale_finansman_turu_text")
    private String biddingFinanceTypeText;

    @Column(name = "ihale_yasa_kapsami_no")
    private int biddingLawScopeId;

    @Column(name = "ihale_yasa_kapsami_text")
    private String biddingLawScopeText;

    @Column(name = "ihale_sozlesme_turu_no")
    private int biddingAgreementTypeId;

    @Column(name = "ihale_sozlesme_turu_text")
    private String biddingAgreementTypeText;

    @Column(name = "ilan_tarihi")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date announcementDate;

    @Column(name = "ilan_suresi")
    private int announcementDuration;

    @Column(name = "kismi_teklife_acik_mi")
    private Boolean isOpenToPartialBidding;

    @Column(name = "kisim_adet")
    private long partCount;

    @Column(name = "e_eksiltme_mi")
    private Boolean isElectronicDecrease;

    @Column(name = "e_ihale_mi")
    private Boolean isElectronicBidding;

    @Column(name = "ihale_durum_no")
    private int biddingStatusId;

    @Column(name = "ihale_durum_text")
    private String biddingStatusText;

    @Column(name = "di_adet") // istekli sayısı
    private Integer numOfTenderers;

    @Column(name = "tk_gec") // teklif veren istekli sayısı
    private Integer numOfBidders;

    @Column(name = "tk_gec2") // teklifi geçersiz istekli sayısı
    private Integer numOfInvalidBids;

    @Column(name = "tk_gec0") // teklifi kabul edilen istekli sayısı
    private Integer numOfAcceptedBids;

    @Column(name = "tk_gec1") // geçerli teklif sayısı
    private Integer numOfValidBids;

}
