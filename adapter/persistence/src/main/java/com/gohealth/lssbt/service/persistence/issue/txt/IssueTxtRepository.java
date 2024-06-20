package com.gohealth.lssbt.service.persistence.issue.txt;

import static com.gohealth.lssbt.service.persistence.issue.txt.AbstractIssueTxtRepository.DELIMITER;
import static lssbt.service.domain.shared.issue.IssueStatus.CLOSED;
import static lssbt.service.domain.shared.issue.IssueStatus.OPEN;

import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.FindIssueByIdQuery;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import com.gohealth.lssbt.service.persistence.issue.IssuePersistenceAdapter;
import com.gohealth.lssbt.service.persistence.issue.txt.mapper.IssueTxtRepositoryMapper;
import java.io.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "local-storage.type", havingValue = "txt")
public class IssueTxtRepository implements IssuePersistenceAdapter {

  @Value("${local-storage.txt.file-path}")
  private String filePath;

  @Value("${local-storage.txt.file-name}")
  private String fileName;

  private BufferedWriter bufferedWriter;

  @Autowired
  public IssueTxtRepository(BufferedWriter bufferedWriter) {
    this.bufferedWriter = bufferedWriter;
  }

  // it would be better to replace only the line that is related to the issue we want to close, but
  // for now I decided to leave it as it is
  @Override
  public void close(IssueEntity entity) {
    final ListIssuesQuery listQuery = ImmutableListIssuesQuery.of(true);
    List<IssueEntity> issues =
        this.list(listQuery).parallelStream()
            .map(
                item ->
                    item.id().equals(entity.id())
                        ? ImmutableIssueEntity.copyOf(entity).withStatus(CLOSED.getValue())
                        : item)
            .toList();

    try {
      // close and open with `append = false`,  so the file will be rewritten
      bufferedWriter.close();
      bufferedWriter = new BufferedWriter(new FileWriter(filePath + "/" + fileName));
      issues.forEach(this::insert);
    } catch (IOException e) {
      // TODO: throw exception and move `System.out.printf` to UI Controller
      System.out.printf(
          "\nIOException occurred while using bufferedWriter with '%s' and name '%s'.\n",
          filePath, fileName);
    }
  }

  // it would be better to use another delimiter here, as it could be a problem if the input data
  // will include this delimiter, but for now I decided to leave it as it is
  @Override
  public void insert(IssueEntity entity) {
    final String inputData =
        String.join(
            DELIMITER,
            entity.id(),
            entity.description(),
            entity.parentId().orElse(null),
            entity.status(),
            entity.creationTimestampt().toString(),
            entity.link());

    try {
      bufferedWriter.write(inputData);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      // TODO: throw exception and move `System.out.printf` to UI Controller
      System.out.printf(
          "\nIOException occurred while using bufferedWriter with '%s' and name '%s'.\n",
          filePath, fileName);
    }
  }

  @Override
  public IssueEntity findOne(FindIssueByIdQuery query) {
    final ListIssuesQuery listQuery = ImmutableListIssuesQuery.of(true);
    final List<IssueEntity> result =
        this.list(listQuery).stream().filter(entity -> entity.id().equals(query.id())).toList();

    return result.getFirst();
  }

  // it would be better to filter when reading from the file rather than afterward, but for now I
  // decided to leave it as it is
  @Override
  public List<IssueEntity> list(ListIssuesQuery query) {
    try {
      final BufferedReader bufferedReader =
          new BufferedReader(new FileReader(filePath + "/" + fileName));
      return bufferedReader
          .lines()
          .map(IssueTxtRepositoryMapper::map)
          .filter(entity -> !query.filterByOpenStatus() || entity.status().equals(OPEN.getValue()))
          .toList();
    } catch (FileNotFoundException e) {
      // TODO: throw exception and move `System.out.printf` to UI Controller
      System.out.printf("\nFile with path '%s' and name '%s' was not found.\n", filePath, fileName);
      return Collections.emptyList();
    }
  }
}
