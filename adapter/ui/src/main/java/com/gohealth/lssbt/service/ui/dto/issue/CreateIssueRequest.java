package com.gohealth.lssbt.service.ui.dto.issue;

import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface CreateIssueRequest permits ImmutableCreateIssueRequest {

  @Value.Parameter
  String description();

  @Value.Parameter
  Optional<String> parentId();

  @Value.Parameter
  String link();
}
