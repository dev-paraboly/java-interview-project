package com.parabol.interview.mockproject.repositories.custom.impl;

import com.parabol.interview.mockproject.entities.Ekap;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.domain.InternalControlEkapPairFilter;
import com.parabol.interview.mockproject.repositories.custom.InternalControlRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InternalControlRepositoryImpl implements InternalControlRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<InternalControl> getFiltered(InternalControlEkapPairFilter filter) {
        /*
         * Spring Data @Query doesn't support empty list in query repositories, that's
         * why this method is chosen for complex query of such kind. Another way would
         * be creating many spring data queries
         * TODO need to specifications
         */
        String querySql = "select ic as internalControl, ek as ekap from InternalControl ic left join Ekap ek on ic.biddingNo = ek.ikn ";
        String whereCondition = "";
        // FIXME it seems that list that keeps conditions would be better
        if (filter.getFileNoYearList() != null) {
            whereCondition = whereCondition.concat("(ic.fileNoYear IN (:fileNoYear)) ");
        }
        if (filter.getBiddingProcedureIdList() != null) {
            whereCondition = whereCondition.concat("AND (ic.biddingProcedureId IN (:biddingProcedureId))");
        }
        if (filter.getBiddingDepartmentIdList() != null) {
            whereCondition = whereCondition.concat("AND (ic.biddingDepartmentId IN (:biddingDepartmentId))");
        }
        if (filter.getEkapStatus() != null) {
            whereCondition = whereCondition.concat("AND (ic.ekapStatus = :ekapStatus)");
        }
        if (filter.getStatusList() != null) {
            whereCondition = whereCondition.concat("AND (ic.status IN (:status))");
        }
        if (filter.getBiddingTypeList() != null) {
            whereCondition = whereCondition.concat("AND (ic.biddingType IN (:biddingType))");
        }
        if (filter.getAssigneeList() != null) {
            whereCondition = whereCondition.concat("AND ((ic.technicalAssignee IN (:assignee)) OR (ic.financialAssignee IN (:assignee)) )");
        }
        if (whereCondition.length() > 0) {
            if (whereCondition.startsWith("AND")) {
                whereCondition = whereCondition.substring(whereCondition.indexOf(" ") + 1);
            }
            querySql = querySql.concat(" where ").concat(whereCondition);
        }
        TypedQuery<Tuple> typedQuery = entityManager.createQuery(querySql, Tuple.class);
        if (filter.getFileNoYearList() != null) {
            typedQuery.setParameter("fileNoYear", filter.getFileNoYearList());
        }
        if (filter.getBiddingProcedureIdList() != null) {
            typedQuery.setParameter("biddingProcedureId", filter.getBiddingProcedureIdList());
        }
        if (filter.getBiddingDepartmentIdList() != null) {
            typedQuery.setParameter("biddingDepartmentId", filter.getBiddingDepartmentIdList());
        }
        if (filter.getEkapStatus() != null) {
            typedQuery.setParameter("ekapStatus", filter.getEkapStatus());
        }
        if (filter.getStatusList() != null) {
            typedQuery.setParameter("status", filter.getStatusList());
        }
        if (filter.getBiddingTypeList() != null) {
            typedQuery.setParameter("biddingType", filter.getBiddingTypeList());
        }
        if (filter.getAssigneeList() != null) {
            typedQuery.setParameter("assignee", filter.getAssigneeList());
        }
        return typedQuery.getResultList().stream().map(x -> {
            InternalControl internalControl = (InternalControl) x.get("internalControl");
            internalControl.setEkap((Ekap) x.get("ekap"));
            return internalControl;
        }).collect(Collectors.toList());
    }
}
