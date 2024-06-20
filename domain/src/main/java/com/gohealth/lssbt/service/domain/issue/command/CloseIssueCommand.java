package com.gohealth.lssbt.service.domain.issue.command;

import lssbt.service.domain.shared.command.Command;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface CloseIssueCommand extends Command permits ImmutableCloseIssueCommand {

  @Value.Parameter
  String id();
}
