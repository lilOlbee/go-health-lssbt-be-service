package com.gohealth.lssbt.service.business.issue.inbound;

import com.gohealth.lssbt.service.domain.issue.command.CreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.entity.CreateIssueEntity;

public interface CreateIssueUseCase {

  CreateIssueEntity execute(CreateIssueCommand command);
}
