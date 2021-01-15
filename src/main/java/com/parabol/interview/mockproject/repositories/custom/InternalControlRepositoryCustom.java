package com.parabol.interview.mockproject.repositories.custom;

import com.parabol.interview.mockproject.entities.InternalControl;
import com.parabol.interview.mockproject.domain.InternalControlEkapPairFilter;

import java.util.List;

public interface InternalControlRepositoryCustom {
    List<InternalControl> getFiltered(InternalControlEkapPairFilter internalControlEkapPairFilter);
}
