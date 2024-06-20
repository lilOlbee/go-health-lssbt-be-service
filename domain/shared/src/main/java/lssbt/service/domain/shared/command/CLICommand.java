package lssbt.service.domain.shared.command;

import java.util.Arrays;

public enum CLICommand {
  CREATE_ISSUE("create"),
  CLOSE_ISSUE("close"),
  LIST_ISSUES("list"),

  EXIT("exit"),
  HELP("help");

  private final String value;

  CLICommand(String value) {
    this.value = value;
  }

  public final String getValue() {
    return value;
  }

  public static CLICommand parse(String input) {
    return Arrays.stream(values()).toList().parallelStream()
        .filter(value -> value.getValue().equals(input))
        .findFirst()
        .orElse(null);
  }
}
