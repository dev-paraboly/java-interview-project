package com.parabol.interview.mockproject.reports.utils;

import com.parabol.interview.mockproject.domain.reports.GeneralStatistics;

import java.util.Comparator;

public class GeneralComparator implements Comparator<GeneralStatistics> {
    @Override
    public int compare(GeneralStatistics o1, GeneralStatistics o2) {
        if (this.isNumber(o1) && this.isNumber(o2)) {
            Integer num1 = Integer.valueOf(o1.getBiddingDepartment().split("\\.")[0]);
            Integer num2 = Integer.valueOf(o2.getBiddingDepartment().split("\\.")[0]);
            return num1.compareTo(num2);
        } else {
            return o1.getBiddingDepartment().compareTo(o2.getBiddingDepartment());
        }
    }

    public boolean isNumber(GeneralStatistics o1) {
        try {
            Integer val = Integer.parseInt(o1.getBiddingDepartment().substring(0, 1));
            return (val instanceof Integer);
        } catch (Exception err) {
            return false;
        }
    }
}