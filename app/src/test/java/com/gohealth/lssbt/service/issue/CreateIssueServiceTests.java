package com.gohealth.lssbt.service.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gohealth.lssbt.service.domain.issue.command.CreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.command.ImmutableCreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import com.gohealth.lssbt.service.issue.helper.IssueTestsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateIssueServiceTests extends IssueTestsHelper {

  @BeforeEach
  public void setUp() {
    createPrerequisites();
  }

  @AfterEach
  public void tearDown() {
    deletePrerequisites();
  }

  @Test
  public void thatIssueCanBeCreated() {
    deletePrerequisites();

    final ListIssuesQuery query = ImmutableListIssuesQuery.of(true);
    final int issueListSizeBefore = listIssueUseCase.execute(query).size();

    final CreateIssueCommand command =
        ImmutableCreateIssueCommand.builder()
            .description(openedIssue1.description())
            .parentId(openedIssue1.parentId())
            .link(openedIssue1.link())
            .build();
    createIssueUseCase.execute(command);

    final int issueListSizeAfter = listIssueUseCase.execute(query).size();
    assertEquals(issueListSizeBefore + 1, issueListSizeAfter);

    deletePrerequisites();
  }
}
