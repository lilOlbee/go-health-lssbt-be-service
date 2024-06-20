CREATE TABLE public.ISSUE
(
    ID                TEXT PRIMARY KEY         NOT NULL,
    Description       VARCHAR(100)             NOT NULL,
    ParentId          TEXT                     NULL,
    Status            VARCHAR(25)              NOT NULL,
    CreationTimestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    Link              VARCHAR(100)             NOT NULL
);

-- CREATE TABLE public.SUB_ISSUE_ISSUE
-- (
--     IssueId       TEXT NOT NULL,
--     ParentIssueId TEXT NOT NULL,
--
--     FOREIGN KEY (IssueId) REFERENCES public.ISSUE (ID),
--     FOREIGN KEY (ParentIssueId) REFERENCES public.ISSUE (ID)
-- );