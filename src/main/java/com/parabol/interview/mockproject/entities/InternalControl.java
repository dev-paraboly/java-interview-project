package com.parabol.interview.mockproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "internal_control", schema = "mock_schema")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InternalControl implements Serializable {
    private static final long serialVersionUID = -2343243243242432341L;
    @Id @Type(type="org.hibernate.type.UUIDCharType") @GeneratedValue
    private UUID id; //remain

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt; //remain

    private String biddingNo; //remain
    private Integer biddingNoYear;
    private String biddingNoText;
    @NotNull
    private String fileNo; //remain
    @NotNull
    private int fileNoYear; //remain
    @NotNull
    private int fileNoText; //remain

    @Column(length = 2000)
    private String name;
    @NotNull
    private int status; //remain
    @NotNull
    private float latitude; //remain
    @NotNull
    private float longitude; //remain
    private Integer biddingDepartmentId;
    private Integer biddingProcedureId;
    private String requestingDepartment; //remain
    private Float discount; //remain

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date arrivalDate; //remain
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date approvalDate; //remain
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateOfContract; //remain
    @Column(length=1000)
    private String contractor; //remain
    @Column(length=1000)
    private String explanation; //remain
    private Integer biddingType;

    @Transient
    private Ekap ekap;

}
