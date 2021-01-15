package com.parabol.interview.mockproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.domain.InternalControlEkapPairFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface InternalControlService {

    InternalControl save(InternalControl internalControl) throws JsonProcessingException;
    InternalControl findById(UUID id);
    void delete(UUID id);
    List<InternalControl> getFiltered(InternalControlEkapPairFilter filter);

    InternalControl update(InternalControl internalControl) throws JsonProcessingException;

    List<InternalControl> findByBiddingNo(String biddingNo);
}
