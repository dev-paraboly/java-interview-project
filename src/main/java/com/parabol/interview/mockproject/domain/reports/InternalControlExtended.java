package com.parabol.interview.mockproject.domain.reports;

import com.parabol.interview.mockproject.entities.InternalControl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static com.parabol.interview.mockproject.domain.reports.Constants.*;

@Slf4j
@Data
public class InternalControlExtended extends InternalControl {
	private HashMap<Integer, String> statusLookup = new HashMap() {{
		put(1,"İncelemede");
		put(2,"Onaylandı");
		put(3,"Şartlı Onaylandı");
		put(4,"İade");
		put(5,"Yeni Kayıt");
		put(6,"İtiraz Süreci");
	}};

	private HashMap<Integer, String> colorLookup = new HashMap() {{
		put(1, "#77b5e0");
		put(2, "#8de091");
		put(3, "#ff9800");
		put(4, "#ff6a6a");
		put(5, "#e066ff");
		put(6, "#17bfbf");
	}};

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyy", new Locale("tr", "TR"));

	public InternalControlExtended(InternalControl original) {
		this.original = original;
	}

	private InternalControl original;
	private String color;
	private String statusString;
	private String biddingTypeString;
	private String biddingProcedureString;
	private String biddingDepartmentString;
	private String approvalDateString;
	private String dateOfContractString;
	private String createdAtString;

	public void extend() {
		InternalControl original = this.original;
		this.setFileNoYear(original.getFileNoYear());
		this.setBiddingDepartmentString(BIDDING_DEPARTMENT_LOOKUP_REVERSE.get(original.getBiddingDepartmentId()));
		this.setBiddingNo(original.getBiddingNo());
		this.setName(original.getName());
		this.setContractor(original.getContractor());
		this.setDiscount(original.getDiscount() != null ? original.getDiscount() / 100 : 0);
		this.setBiddingTypeString(BIDDING_TYPE_LOOKUP_REVERSE.get(original.getBiddingType()));
		this.setBiddingProcedureString(BIDDING_PROCEDURE_LOOKUP_REVERSE.get(original.getBiddingProcedureId()));
		this.setRequestingDepartment(original.getRequestingDepartment());
		this.setCreatedAtString(original.getCreatedAt() != null ? simpleDateFormat.format(Date.from(original.getCreatedAt().toInstant().plus(Duration.ofHours(3)))) : "");
		this.setStatusString(statusLookup.get(original.getStatus()));
		this.setColor(colorLookup.get(original.getStatus()));
		this.setApprovalDateString(original.getApprovalDate() != null ? simpleDateFormat.format(Date.from(original.getApprovalDate().toInstant().plus(Duration.ofHours(3)))) : "");
		this.setDateOfContractString(original.getDateOfContract() != null ? simpleDateFormat.format(Date.from(original.getDateOfContract().toInstant().plus(Duration.ofHours(3)))) : "");
		this.setExplanation(original.getExplanation());
	}
}
