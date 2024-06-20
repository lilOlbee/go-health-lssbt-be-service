package com.gohealth.lssbt.service.persistence.issue.db;

import static nu.studer.sample.tables.Issue.ISSUE;
import static org.jooq.impl.DSL.trueCondition;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.FindIssueByIdQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import com.gohealth.lssbt.service.persistence.issue.IssuePersistenceAdapter;
import com.gohealth.lssbt.service.persistence.issue.db.mapper.IssueDBRepositoryMapper;
import java.util.List;
import lssbt.service.domain.shared.issue.IssueStatus;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "local-storage.type", havingValue = "db")
public class IssueDBRepository implements IssuePersistenceAdapter {

  private final DSLContext dslContext;

  @Autowired
  public IssueDBRepository(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Override
  public void insert(IssueEntity entity) {
    dslContext
        .insertInto(ISSUE)
        .set(ISSUE.ID, entity.id())
        .set(ISSUE.DESCRIPTION, entity.description())
        .set(ISSUE.PARENTID, entity.parentId().orElse(null))
        .set(ISSUE.STATUS, entity.status())
        .set(ISSUE.CREATIONTIMESTAMP, entity.creationTimestampt())
        .set(ISSUE.LINK, entity.link())
        .execute();
  }

  @Override
  public void close(IssueEntity entity) {
    dslContext
        .update(ISSUE)
        .set(ISSUE.DESCRIPTION, entity.description())
        .set(ISSUE.PARENTID, entity.parentId().orElse("null"))
        .set(ISSUE.STATUS, entity.status())
        .set(ISSUE.CREATIONTIMESTAMP, entity.creationTimestampt())
        .set(ISSUE.LINK, entity.link())
        .where(ISSUE.ID.eq(entity.id()))
        .execute();
  }

  @Override
  public IssueEntity findOne(FindIssueByIdQuery query) {
    final Result<Record> result =
        dslContext.select().from(ISSUE).where(ISSUE.ID.eq(query.id())).fetch();

    if (result.isEmpty()) {
      throw new RuntimeException("Issue with id " + query.id() + " not found");
    }

    return result.getFirst().map(IssueDBRepositoryMapper::map);
  }

  @Override
  public List<IssueEntity> list(ListIssuesQuery query) {
    Condition condition = trueCondition();
    if (query.filterByOpenStatus()) {
      condition = resolveFilteredByOpenStatus(condition);
    }

    final Result<?> result = dslContext.select().from(ISSUE).where(condition).fetch();

    return result.stream().map(IssueDBRepositoryMapper::map).toList();
  }

  private Condition resolveFilteredByOpenStatus(Condition condition) {
    return condition.and(ISSUE.STATUS.eq(IssueStatus.OPEN.getValue()));
  }
}
