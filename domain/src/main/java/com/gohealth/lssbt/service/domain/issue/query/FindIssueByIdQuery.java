package com.gohealth.lssbt.service.domain.issue.query;

import org.immutables.value.Value;

@Value.Immutable
public sealed interface FindIssueByIdQuery permits ImmutableFindIssueByIdQuery {

  @Value.Parameter
  String id();
}
