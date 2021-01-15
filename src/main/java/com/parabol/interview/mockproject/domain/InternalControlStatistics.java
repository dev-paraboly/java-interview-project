package com.parabol.interview.mockproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalControlStatistics {
    BigDecimal totalBiddingCost;
    BigDecimal totalAppropriateCost;
}
