package com.parabol.interview.mockproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "bidding_fix", schema = "mock_schema")
public class BiddingFixAnnouncement {
	@Id
	private long id;

	@Column(length = 20)
	private String ikn;

	@Column(name = "biddingFixDate")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date fixingAnnouncementDate;
}
