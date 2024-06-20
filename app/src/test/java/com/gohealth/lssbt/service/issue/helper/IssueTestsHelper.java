package com.gohealth.lssbt.service.issue.helper;

import com.gohealth.lssbt.service.business.issue.inbound.CloseIssueUseCase;
import com.gohealth.lssbt.service.business.issue.inbound.CreateIssueUseCase;
import com.gohealth.lssbt.service.business.issue.inbound.ListIssueUseCase;
import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.issue.helper.localstorage.IssueDBTestsHelper;
import com.gohealth.lssbt.service.issue.helper.localstorage.IssueTxtTestsHelper;
import com.gohealth.lssbt.service.ui.controller.IssueUIController;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Scanner;
import lssbt.service.domain.shared.issue.IssueStatus;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@SpringBootConfiguration
@ExtendWith(value = {SpringExtension.class, MockitoExtension.class})
@ActiveProfiles("test")
@EnabledIf(expression = "#{environment['local-storage.type'] == 'db'}", loadContext = true)
public class IssueTestsHelper implements BaseIssueTestsHelper {

  @Autowired protected DSLContext dslContext;

  @Autowired protected CreateIssueUseCase createIssueUseCase;

  @Autowired protected CloseIssueUseCase closeIssueUseCase;

  @Autowired protected ListIssueUseCase listIssueUseCase;

  @MockBean protected IssueUIController issueUIController;

  @MockBean protected Scanner scanner;

  @Value("${local-storage.type}")
  private String localStorageType;

  protected BaseIssueTestsHelper baseIssueTestsHelper;

  protected static IssueEntity openedIssue1;
  protected static IssueEntity openedIssue2;
  protected static IssueEntity closedIssue3;

  protected static final String IDENTIFICATION_DELIMITER = "I-";

  @BeforeEach
  public void setup() {
    baseIssueTestsHelper =
        switch (localStorageType) {
          case "db" -> new IssueDBTestsHelper(dslContext);
          case "txt" -> new IssueTxtTestsHelper();
          default -> new IssueTestsHelper();
        };
  }

  public void createPrerequisites() {
    openedIssue1 =
        ImmutableIssueEntity.builder()
            .id(resolveIncrementedId())
            .description("description-1")
            .parentId(Optional.empty())
            .status(IssueStatus.OPEN.getValue())
            .creationTimestampt(OffsetDateTime.now())
            .link("link-1")
            .build();
    createIssue(openedIssue1);

    openedIssue2 =
        ImmutableIssueEntity.builder()
            .id(resolveIncrementedId())
            .description("description-2")
            .parentId(Optional.empty())
            .status(IssueStatus.OPEN.getValue())
            .creationTimestampt(OffsetDateTime.now())
            .link("link-2")
            .build();
    createIssue(openedIssue2);

    closedIssue3 =
        ImmutableIssueEntity.builder()
            .id(resolveIncrementedId())
            .description("description-3")
            .parentId(Optional.empty())
            .status(IssueStatus.CLOSED.getValue())
            .creationTimestampt(OffsetDateTime.now())
            .link("link-3")
            .build();
    createIssue(closedIssue3);
  }

  public void deletePrerequisites() {
    deleteIssue(openedIssue1);
    deleteIssue(openedIssue2);
    deleteIssue(closedIssue3);
  }

  @Override
  public void createIssue(IssueEntity issue) {
    baseIssueTestsHelper.createIssue(issue);
  }

  @Override
  public void deleteIssue(IssueEntity issue) {
    baseIssueTestsHelper.deleteIssue(issue);
  }

  @Override
  public String resolveIncrementedId() {
    return baseIssueTestsHelper.resolveIncrementedId();
  }
}
