package com.gohealth.lssbt.service.ui.controller;

import static com.gohealth.lssbt.service.ui.util.IssueUIUtil.*;

import com.gohealth.lssbt.service.business.issue.inbound.CloseIssueUseCase;
import com.gohealth.lssbt.service.business.issue.inbound.CreateIssueUseCase;
import com.gohealth.lssbt.service.business.issue.inbound.ListIssueUseCase;
import com.gohealth.lssbt.service.ui.dto.issue.CloseIssueRequest;
import com.gohealth.lssbt.service.ui.dto.issue.CreateIssueRequest;
import com.gohealth.lssbt.service.ui.dto.issue.ListIssueDetailResponse;
import com.gohealth.lssbt.service.ui.mapper.command.IssueCommandMapper;
import com.gohealth.lssbt.service.ui.mapper.query.IssueQueryMapper;
import com.gohealth.lssbt.service.ui.mapper.response.IssueResponseMapper;
import java.util.Scanner;
import lssbt.service.domain.shared.command.CLICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IssueUIController implements UIController {

  private final CreateIssueUseCase createIssueUseCase;
  private final CloseIssueUseCase closeIssueUseCase;
  private final ListIssueUseCase listIssueUseCase;
  private final Scanner scanner;

  @Autowired
  public IssueUIController(
      CreateIssueUseCase createIssueUseCase,
      CloseIssueUseCase closeIssueUseCase,
      ListIssueUseCase listIssueUseCase,
      Scanner scanner) {
    this.createIssueUseCase = createIssueUseCase;
    this.closeIssueUseCase = closeIssueUseCase;
    this.listIssueUseCase = listIssueUseCase;
    this.scanner = scanner;
  }

  @Override
  public void run() {
    printHelp();
    while (true) {
      System.out.print("\nType your command here: ");
      final String userInput = scanner.nextLine();
      switch (CLICommand.parse(userInput)) {
        case CREATE_ISSUE -> handleCreateIssueRequest();
        case CLOSE_ISSUE -> handleCloseIssueRequest();
        case LIST_ISSUES -> handleListAllIssuesRequest();

        case EXIT -> System.exit(0);
        case HELP -> printHelp();

        case null -> System.out.println("That command is not supported. Please, try again.");
      }
    }
  }

  private void handleCreateIssueRequest() {
    final CreateIssueRequest request = resolveCreateIssueRequest(scanner);
    if (isCreateIssueRequestValid(request)) {
      IssueResponseMapper.mapToCreateIssueDetailResponse(
          createIssueUseCase.execute(IssueCommandMapper.map(request)));
    }
  }

  private void handleCloseIssueRequest() {
    final CloseIssueRequest request = resolveCloseIssueRequest(scanner);
    if (isCloseIssueRequestValid(request)) {
      closeIssueUseCase.execute(IssueCommandMapper.map(request));
    }
  }

  private void handleListAllIssuesRequest() {
    final ListIssueDetailResponse response =
        IssueResponseMapper.mapToListIssueDetailResponse(
            listIssueUseCase.execute(IssueQueryMapper.map(true)));

    if (response.issues().isEmpty()) {
      System.out.println(
          "\nCongratulations, all the (open) issues were successfully listed, but the was no items.");
    } else {
      System.out.println();
      response.issues().forEach(System.out::println);
    }
  }

  private void printHelp() {
    System.out.print(
        """

                          Welcome to the Local Storage System Bugs Tracker Service!
                          Supported commands:
                          1. "create" -> create new issue
                          2. "close" -> close issue
                          3. "list" -> list all (open) issues
                          4. "exit" -> exit the app
                          5. "help" -> print the help command menu
                          """);
  }
}
