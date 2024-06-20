package com.gohealth.lssbt.service.ui.mapper.response;

import com.gohealth.lssbt.service.domain.issue.entity.CreateIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.ui.dto.issue.*;
import com.gohealth.lssbt.service.ui.dto.issue.FindIssueByIdDetailResponse;
import java.util.List;

// mapStruct could be used, but I prefer this method of mapping.
public abstract class IssueResponseMapper {

  public static CreateIssueDetailResponse mapToCreateIssueDetailResponse(CreateIssueEntity entity) {
    return ImmutableCreateIssueDetailResponse.of(entity.id());
  }

  public static FindIssueByIdDetailResponse mapToFindOneIssueByIdDetailResponse(
      IssueEntity entity) {
    return ImmutableFindIssueByIdDetailResponse.builder()
        .id(entity.id())
        .description(entity.description())
        .parentId(entity.parentId())
        .status(entity.status())
        .creationTimestampt(entity.creationTimestampt())
        .link(entity.link())
        .build();
  }

  public static ListIssueDetailResponse mapToListIssueDetailResponse(List<IssueEntity> entities) {
    return ImmutableListIssueDetailResponse.builder()
        .addAllIssues(
            entities.stream()
                .map(IssueResponseMapper::mapToFindOneIssueByIdDetailResponse)
                .toList())
        .build();
  }
}
