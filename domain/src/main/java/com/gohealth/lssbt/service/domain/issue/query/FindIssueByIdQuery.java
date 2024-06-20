package com.gohealth.lssbt.service.domain.issue.query;

import lssbt.service.domain.shared.query.Query;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface FindIssueByIdQuery extends Query permits ImmutableFindIssueByIdQuery {

  @Value.Parameter
  String id();
}
