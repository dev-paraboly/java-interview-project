package com.parabol.interview.mockproject.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.parabol.interview.mockproject.domain.InternalControlEkapPairFilter;
import com.parabol.interview.mockproject.domain.InternalControlStatistics;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.services.InternalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("internal-controls")
public class InternalController {

    @Autowired
	InternalControlService internalControlService;

    @PostMapping("")
    public InternalControl add(@RequestBody InternalControl internalControl) throws JsonProcessingException {
        return internalControlService.save(internalControl);
    }

    @PutMapping("")
    public InternalControl update(@RequestBody InternalControl internalControl) throws JsonProcessingException {
        return internalControlService.update(internalControl);
    }

    @GetMapping("")
    public List<InternalControl> get(
            //2020-03-09 @nurgasemetey
            //The main reason why filters are explicitly declared is SDK generation of OpenApi generator(4.2.3).
            //SDK generated for Javascript contained variables like endYearList2 which
            // totally destroys readability. Maybe later versions of OpenApi generator will fix this
            @RequestParam(value = "fileNoYear", required=false) List<Integer> fileNoYearList,
            @RequestParam(value = "assignee", required=false) List<String> assigneeList,
            @RequestParam(value = "biddingProcedureId", required=false) List<Integer> biddingProcedureList,
            @RequestParam(value = "biddingDepartmentId", required=false) List<Integer> biddingDepartmentList,
            @RequestParam(value = "ekapStatus", required=false) Integer ekapStatus,
            @RequestParam(value = "status", required=false) List<Integer> statusList,
            @RequestParam(value = "biddingType", required=false) List<String> biddingTypeList,
            @RequestParam(value = "field", required=false) List<String> fields

    ) {
        InternalControlEkapPairFilter internalControlFilter = InternalControlEkapPairFilter.builder()
                .fileNoYearList(fileNoYearList)
                .assigneeList(assigneeList)
                .biddingProcedureIdList(biddingProcedureList)
                .biddingDepartmentIdList(biddingDepartmentList)
                .ekapStatus(ekapStatus)
                .statusList(statusList)
                .biddingTypeList(biddingTypeList)
                .build();

        String filter = "**";
        if(fields !=null && !fields.isEmpty())
            filter =String.join(",", fields);
        ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), filter);
        return SquigglyUtils.listify(objectMapper, internalControlService.getFiltered(internalControlFilter), InternalControl.class);
    }

    @GetMapping("/{id}")
    public InternalControl getById(@PathVariable("id") UUID id){
        return internalControlService.findById(id);
    }

    @GetMapping("/byBiddingNo")
    public List<InternalControl> getById( @RequestParam("biddingNo") String biddingNo){
        return internalControlService.findByBiddingNo(biddingNo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        internalControlService.delete(id);
    }
 }
