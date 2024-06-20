package com.gohealth.lssbt.service.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gohealth.lssbt.service.domain.issue.command.CloseIssueCommand;
import com.gohealth.lssbt.service.domain.issue.command.ImmutableCloseIssueCommand;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import com.gohealth.lssbt.service.issue.helper.IssueTestsHelper;
import org.junit.jupiter.api.*;

public class CloseIssueServiceTests extends IssueTestsHelper {

  @BeforeEach
  public void setUp() {
    createPrerequisites();
  }

  @AfterEach
  public void tearDown() {
    deletePrerequisites();
  }

  @Test
  public void thatIssueCanBeClosed() {
    final ListIssuesQuery query = ImmutableListIssuesQuery.of(true);
    final int issueListSizeBefore = listIssueUseCase.execute(query).size();

    final CloseIssueCommand command = ImmutableCloseIssueCommand.of(openedIssue1.id());
    closeIssueUseCase.execute(command);

    final int issueListSizeAfter = listIssueUseCase.execute(query).size();
    assertEquals(issueListSizeBefore - 1, issueListSizeAfter);
  }

  @Test
  public void thatIssueWithNotExistingIdCannotBeClosed() {
    final ListIssuesQuery query = ImmutableListIssuesQuery.of(true);
    final int issueListSizeBefore = listIssueUseCase.execute(query).size();

    final String notExistingId = resolveIncrementedId();
    final CloseIssueCommand command = ImmutableCloseIssueCommand.of(notExistingId);
    closeIssueUseCase.execute(command);

    final int issueListSizeAfter = listIssueUseCase.execute(query).size();
    assertEquals(issueListSizeBefore, issueListSizeAfter);
  }
}
