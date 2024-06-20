package com.gohealth.lssbt.service.domain.issue.entity;

import java.time.OffsetDateTime;
import java.util.Optional;
import lssbt.service.domain.shared.entity.Entity;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface IssueEntity extends Entity permits ImmutableIssueEntity {

  String id();

  String description();

  Optional<String> parentId();

  String status();

  OffsetDateTime creationTimestampt();

  String link();
}
