package com.gohealth.lssbt.service.persistence.issue.txt.mapper;

import static lssbt.service.domain.shared.issue.IssueTxtFileStructure.*;
import static lssbt.service.domain.shared.issue.IssueTxtFileStructure.LINK;

import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.persistence.issue.txt.AbstractIssueTxtRepository;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Optional;

public abstract class IssueTxtRepositoryMapper extends AbstractIssueTxtRepository {

  public static IssueEntity map(String line) {
    return ImmutableIssueEntity.builder()
        .id(getValue(line, FILE_DATA_STRUCTURE.get(ID.name())))
        .description(getValue(line, FILE_DATA_STRUCTURE.get(DESCRIPTION.name())))
        .parentId(Optional.ofNullable(getValue(line, FILE_DATA_STRUCTURE.get(PARENT_ID.name()))))
        .status(getValue(line, FILE_DATA_STRUCTURE.get(STATUS.name())))
        .creationTimestampt(
            OffsetDateTime.parse(
                getValue(line, FILE_DATA_STRUCTURE.get(CREATION_TIMESTAMP.name()))))
        .link(getValue(line, FILE_DATA_STRUCTURE.get(LINK.name())))
        .build();
  }

  private static String getValue(String line, int sequenceNumber) {
    return Arrays.stream(line.split(DELIMITER)).skip(sequenceNumber).findFirst().orElse(null);
  }
}
