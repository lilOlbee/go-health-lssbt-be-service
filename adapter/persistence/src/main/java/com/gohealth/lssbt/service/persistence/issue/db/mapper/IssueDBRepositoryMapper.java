package com.gohealth.lssbt.service.persistence.issue.db.mapper;

import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import java.util.Optional;
import nu.studer.sample.tables.records.IssueRecord;
import org.jooq.Record;

public abstract class IssueDBRepositoryMapper {

  public static IssueEntity map(Record record) {
    final IssueRecord issueRecord = record.into(IssueRecord.class);
    return ImmutableIssueEntity.builder()
        .id(issueRecord.getId())
        .description(issueRecord.getDescription())
        .parentId(Optional.ofNullable(issueRecord.getParentid()))
        .status(issueRecord.getStatus())
        .creationTimestampt(issueRecord.getCreationtimestamp())
        .link(issueRecord.getLink())
        .build();
  }
}
