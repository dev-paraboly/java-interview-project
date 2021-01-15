package com.parabol.interview.mockproject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parabol.interview.mockproject.domain.InternalControlEkapPairFilter;
import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.repositories.InternalControlRepository;
import com.parabol.interview.mockproject.services.InternalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class InternalControlServiceImpl implements InternalControlService {

    @Autowired
    InternalControlRepository internalControlRepository;

    @Override
    public InternalControl save(InternalControl internalControl) throws JsonProcessingException {

        List<InternalControl> internalControlList = internalControlRepository
                .findTop1ByFileNoYearOrderByFileNoTextDesc(internalControl.getFileNoYear());
        if (internalControlList.isEmpty()) {
            internalControl.setFileNoText(1);
        } else {
            internalControl.setFileNoText(internalControlList.get(0).getFileNoText() + 1);
        }
        internalControl.setFileNo(
                String.format("%d/%d", internalControl.getFileNoYear(), internalControl.getFileNoText()));

        internalControlRepository.save(internalControl);
        return internalControl;
    }

    @Override
    public InternalControl findById(UUID id) {
        return internalControlRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("NO_ENTITY"));
    }

    @Override
    public void delete(UUID id) {
        internalControlRepository.deleteById(id);
    }

    @Override
    public List<InternalControl> getFiltered(InternalControlEkapPairFilter filter) {
        List<InternalControl> internalControlList = internalControlRepository.getFiltered(filter);
        return internalControlList;
    }

    @Override
    public InternalControl update(InternalControl internalControl) throws JsonProcessingException {
        InternalControl existing = internalControlRepository.findById(internalControl.getId()).orElseThrow(() -> new EntityNotFoundException("NO_ENTITY"));
        internalControl.setFileNo(existing.getFileNo());
        internalControl.setFileNoYear(existing.getFileNoYear());
        internalControl.setFileNoText(existing.getFileNoText());
        internalControlRepository.save(internalControl);
        return internalControl;
    }

    @Override
    public List<InternalControl> findByBiddingNo(String biddingNo) {
        return internalControlRepository.findByBiddingNo(biddingNo);
    }
}
