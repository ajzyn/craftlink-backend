package com.craftlink.backend.shared.dtos;

import com.craftlink.backend.shared.utils.QueryUtils;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class PageableSearchRequestDto {

    @Min(0)
    private int pageNumber = 0;
    @Min(5)
    @Max(100)
    private int pageSize = 5;
    private String[] sortProperties;
    private Direction direction = Direction.ASC;

    @Setter(AccessLevel.NONE)
    private String searchPhrase;

    public Pageable toPageableRequest() {
        var sortConfig = ArrayUtils.isEmpty(sortProperties) ? Sort.unsorted() : Sort.by(sortProperties);

        return PageRequest.of(pageNumber, pageSize, sortConfig);
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = QueryUtils.prepareSearchString(searchPhrase);
    }
}
