package com.gohealth.lssbt.service.business.issue.outbound;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;

public interface PersistIssuePort {

  void insert(IssueEntity entity);

  void close(IssueEntity entity);
}
