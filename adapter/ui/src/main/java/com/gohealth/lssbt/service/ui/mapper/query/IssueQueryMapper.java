package com.gohealth.lssbt.service.ui.mapper.query;

import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;

// mapstruct could be used, but Ii prefer this way of mapping
public abstract class IssueQueryMapper {

  public static ListIssuesQuery map(boolean filterByOpenStatus) {
    return ImmutableListIssuesQuery.of(filterByOpenStatus);
  }
}
