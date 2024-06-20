package com.gohealth.lssbt.service.business.issue.inbound;

import com.gohealth.lssbt.service.domain.issue.command.CloseIssueCommand;

public interface CloseIssueUseCase {

  void execute(CloseIssueCommand command);
}
