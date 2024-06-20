package com.gohealth.lssbt.service.issue.helper.localstorage;

import static nu.studer.sample.tables.Issue.ISSUE;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.issue.helper.IssueTestsHelper;
import java.util.List;
import org.jooq.DSLContext;

public class IssueDBTestsHelper extends IssueTestsHelper {

  private final DSLContext dslContext;

  public IssueDBTestsHelper(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Override
  public void createIssue(IssueEntity issue) {
    dslContext
        .insertInto(ISSUE)
        .set(ISSUE.ID, issue.id())
        .set(ISSUE.DESCRIPTION, issue.description())
        .set(ISSUE.PARENTID, issue.parentId().orElse(null))
        .set(ISSUE.STATUS, issue.status())
        .set(ISSUE.CREATIONTIMESTAMP, issue.creationTimestampt())
        .set(ISSUE.LINK, issue.link())
        .execute();
  }

  @Override
  public void deleteIssue(IssueEntity issue) {
    dslContext.delete(ISSUE).where(ISSUE.ID.eq(issue.id())).execute();
  }

  @Override
  public String resolveIncrementedId() {
    final List<Integer> issueIds =
        dslContext.select(ISSUE.ID).from(ISSUE).fetchInto(String.class).stream()
            .map(issue -> issue.replace(IDENTIFICATION_DELIMITER, ""))
            .map(Integer::parseInt)
            .sorted()
            .toList();

    return IDENTIFICATION_DELIMITER + (!issueIds.isEmpty() ? issueIds.getLast() + 1 : 1);
  }
}
