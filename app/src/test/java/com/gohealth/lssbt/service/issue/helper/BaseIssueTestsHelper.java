package com.gohealth.lssbt.service.issue.helper;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;

public interface BaseIssueTestsHelper {

  void createIssue(IssueEntity issue);

  void deleteIssue(IssueEntity issue);

  String resolveIncrementedId();
}
