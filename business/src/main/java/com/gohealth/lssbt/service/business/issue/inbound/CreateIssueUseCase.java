package com.gohealth.lssbt.service.business.issue.inbound;

import com.gohealth.lssbt.service.domain.issue.command.CreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;

public interface CreateIssueUseCase {

  IssueEntity execute(CreateIssueCommand command);
}
