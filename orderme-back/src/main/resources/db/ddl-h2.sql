create table T_USERS (
  USER_ID    number(10,0) not null,
  EMAIL      varchar2(250 char),
  PASSWORD   varchar2(250 char),
  ROLE_NAME  varchar2(250 char),
  primary key (USER_ID)
);