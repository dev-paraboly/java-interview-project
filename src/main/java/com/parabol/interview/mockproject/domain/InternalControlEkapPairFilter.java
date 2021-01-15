package com.parabol.interview.mockproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalControlEkapPairFilter {
    public List<Integer> fileNoYearList;
    public List<String> assigneeList;
    public List<Integer> biddingProcedureIdList;
    public List<Integer> biddingDepartmentIdList;
    public Integer ekapStatus;
    public List<Integer> statusList;
    public List<String> biddingTypeList;
}
