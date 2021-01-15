package com.parabol.interview.mockproject.domain.reports;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralStatisticsExtended implements GeneralStatistics {
	private String biddingDepartment;
	private Integer biddingCount;
	private Float discount;

	@Override
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
			this.discount = discount / 100;
	}

	public String getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	private String statisticsType;

	private Float totalCost;

	public GeneralStatisticsExtended(GeneralStatistics statistics, String statisticsType) {
		this.setBiddingDepartment(statistics.getBiddingDepartment());
		this.setBiddingCount(statistics.getBiddingCount() == null ? 0:  statistics.getBiddingCount());
		this.setStatisticsType(statisticsType);
		this.setDiscount(statistics.getDiscount() == null ? 0: statistics.getDiscount());
	}

	private Float calculatePercentage(Float biddingCost, Float total) {
		biddingCost = biddingCost == null ? 0: biddingCost;
		total = total == null ? 0: total;
		return (biddingCost / total);
	}

	public String getBiddingDepartment() {
		return biddingDepartment;
	}

	public void setBiddingDepartment(String biddingDepartment) {
		this.biddingDepartment = biddingDepartment;
	}

	public Integer getBiddingCount() {
		return biddingCount;
	}

	public void setBiddingCount(Integer biddingCount) {
		this.biddingCount = biddingCount;
	}
}
