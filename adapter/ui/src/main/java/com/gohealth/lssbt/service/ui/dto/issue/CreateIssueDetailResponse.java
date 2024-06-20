package com.gohealth.lssbt.service.ui.dto.issue;

import org.immutables.value.Value;

@Value.Immutable
public sealed interface CreateIssueDetailResponse permits ImmutableCreateIssueDetailResponse {

  @Value.Parameter
  String id();
}
