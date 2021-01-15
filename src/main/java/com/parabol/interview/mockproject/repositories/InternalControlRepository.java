package com.parabol.interview.mockproject.repositories;

import com.parabol.interview.mockproject.domain.reports.*;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.repositories.custom.InternalControlRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InternalControlRepository extends JpaRepository<InternalControl, UUID>, InternalControlRepositoryCustom {
    List<InternalControl> findTop1ByFileNoYearOrderByFileNoTextDesc(Integer fileNoYear);

    @Query("select CONCAT(internalControl.biddingDepartmentId,'') as biddingDepartment\n"
            + ",  count(internalControl.id) as biddingCount\n"
            + ",  avg(internalControl.discount) as discount\n" + "from InternalControl internalControl\n"
            + "where internalControl.fileNoYear = :year \n"
            + "group by internalControl.biddingDepartmentId\n" + "order by internalControl.biddingDepartmentId")
    List<GeneralStatistics> getGeneralStatisticsByRegion(@Param("year") Integer year);

    // This should take group by column as an argument, and calculate the
    // percentages.
    @Query("select internalControl.biddingType as biddingDepartment\n"
            + ",  count(internalControl.id) as biddingCount\n"
            + ",  avg(internalControl.discount) as discount\n" + "from InternalControl internalControl\n"
            + "where internalControl.fileNoYear = :year \n"
            + "group by internalControl.biddingType\n"
            + "order by internalControl.biddingType")
    List<GeneralStatistics> getGeneralStatisticsByBiddingType(@Param("year") Integer year);

    //CONCAT is used because GeneralStatistics waits for String
    //biddingProcedureId's type is integer
    //But comparator complains about it, that's why it is not changed
    @Query("select CONCAT(internalControl.biddingProcedureId,'') as biddingDepartment\n"
            + ",  count(internalControl.id) as biddingCount\n"
            + ",  avg(internalControl.discount) as discount\n" + "from InternalControl internalControl\n"
            + "where internalControl.fileNoYear = :year AND \n"
            + " (internalControl.biddingType = 4 or internalControl.biddingType = 5) \n"
            + "group by internalControl.biddingProcedureId\n" + "order by internalControl.biddingProcedureId")
    List<GeneralStatistics> getGeneralStatisticsByBiddingProcedure(@Param("year") Integer year);

    @Query("SELECT internalControl from InternalControl internalControl " +
            "WHERE (:bidding_type is null or :bidding_type=internalControl.biddingType) " +
            "AND (:year is null or :year=internalControl.fileNoYear) " +
            "ORDER BY internalControl.fileNo")
    List<InternalControl> findAllByFilter(@Param("bidding_type") Integer biddingType, @Param("year") Integer fileNoYear);

    List<InternalControl> findByBiddingNo(String biddingNo);

}
