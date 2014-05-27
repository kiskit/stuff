# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        varchar(255) not null,
  name                      varchar(255),
  first_name                varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  status                    integer,
  creation_date             timestamp,
  update_date               timestamp,
  is_admin                  boolean,
  constraint ck_user_status check (status in (0,1)),
  constraint pk_user primary key (id))
;

create table video (
  id                        bigint not null,
  support_type              integer,
  content_type              integer,
  movie_id                  varchar(255),
  input_title               varchar(255),
  creation_date             timestamp,
  update_date               timestamp,
  rental_date               timestamp,
  rented_to                 bigint,
  state                     integer,
  constraint ck_video_support_type check (support_type in (0,1)),
  constraint ck_video_content_type check (content_type in (0,1,2)),
  constraint ck_video_state check (state in (0,1,2)),
  constraint pk_video primary key (id))
;

create sequence user_seq;

create sequence video_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

drop table if exists video;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

drop sequence if exists video_seq;

