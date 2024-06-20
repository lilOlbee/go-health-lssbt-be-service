package com.gohealth.lssbt.service.ui.mapper.query;

import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;

// mapStruct could be used, but I prefer this method of mapping.
public abstract class IssueQueryMapper {

  public static ListIssuesQuery map(boolean filterByOpenStatus) {
    return ImmutableListIssuesQuery.of(filterByOpenStatus);
  }
}
