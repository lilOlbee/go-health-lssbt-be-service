package com.gohealth.lssbt.service.ui.dto.issue;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface FindIssueByIdDetailResponse permits ImmutableFindIssueByIdDetailResponse {

  String id();

  String description();

  Optional<String> parentId();

  String status();

  OffsetDateTime creationTimestampt();

  String link();
}
