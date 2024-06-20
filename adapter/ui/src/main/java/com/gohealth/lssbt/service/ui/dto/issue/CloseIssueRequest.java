package com.gohealth.lssbt.service.ui.dto.issue;

import org.immutables.value.Value;

@Value.Immutable
public sealed interface CloseIssueRequest permits ImmutableCloseIssueRequest {

  @Value.Parameter
  String id();
}
