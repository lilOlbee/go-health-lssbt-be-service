package com.gohealth.lssbt.service.domain.issue.query;

import lssbt.service.domain.shared.query.Query;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface ListIssuesQuery extends Query permits ImmutableListIssuesQuery {

  @Value.Parameter
  boolean filterByOpenStatus();
}
