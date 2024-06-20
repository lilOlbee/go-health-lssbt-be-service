package com.gohealth.lssbt.service.domain.issue.entity;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface IssueEntity extends BaseIssueEntity permits ImmutableIssueEntity {

  String description();

  Optional<String> parentId();

  String status();

  OffsetDateTime creationTimestampt();

  String link();
}
