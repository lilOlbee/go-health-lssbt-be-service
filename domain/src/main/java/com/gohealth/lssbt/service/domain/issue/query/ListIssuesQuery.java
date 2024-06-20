package com.gohealth.lssbt.service.domain.issue.query;

import org.immutables.value.Value;

@Value.Immutable
public sealed interface ListIssuesQuery permits ImmutableListIssuesQuery {

  @Value.Parameter
  boolean filterByOpenStatus();
}
