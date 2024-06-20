package com.gohealth.lssbt.service.issue;

import static org.junit.jupiter.api.Assertions.*;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import com.gohealth.lssbt.service.issue.helper.IssueTestsHelper;
import java.util.List;
import org.junit.jupiter.api.*;

public class ListIssuesServiceTests extends IssueTestsHelper {

  @BeforeEach
  public void setUp() {
    createPrerequisites();
  }

  @AfterEach
  public void tearDown() {
    deletePrerequisites();
  }

  @Test
  public void thatIssueListCanBeRetrieved() {
    ListIssuesQuery query = ImmutableListIssuesQuery.of(false);

    List<IssueEntity> result = listIssueUseCase.execute(query);
    assertFalse(result.isEmpty());
    assertTrue(result.size() >= 3);
  }

  @Test
  public void thatIssueListCanBeFilteredByOpenStatus() {
    ListIssuesQuery query = ImmutableListIssuesQuery.of(true);

    List<IssueEntity> result = listIssueUseCase.execute(query);
    assertFalse(result.isEmpty());
    assertTrue(result.size() >= 2);
  }
}
