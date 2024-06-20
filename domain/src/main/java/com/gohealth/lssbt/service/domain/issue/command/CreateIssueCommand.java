package com.gohealth.lssbt.service.domain.issue.command;

import java.util.Optional;
import lssbt.service.domain.shared.command.Command;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface CreateIssueCommand extends Command permits ImmutableCreateIssueCommand {

  @Value.Parameter
  String description();

  @Value.Parameter
  Optional<String> parentId();

  @Value.Parameter
  String link();
}
