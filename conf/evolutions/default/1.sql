# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  first_name                varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  status                    integer,
  creation_date             datetime,
  update_date               datetime,
  admin                     tinyint(1) default 0,
  constraint ck_user_status check (status in (0,1)),
  constraint pk_user primary key (id))
;

create table video (
  id                        bigint auto_increment not null,
  support_type              integer,
  content_type              integer,
  movie_id                  varchar(255),
  input_title               varchar(255),
  year                      bigint,
  directors                 varchar(255),
  countries                 varchar(255),
  actors                    varchar(255),
  original_title            varchar(255),
  creation_date             datetime,
  update_date               datetime,
  genres                    varchar(255),
  rental_date               datetime,
  rented_to                 bigint,
  state                     integer,
  minimum_age               bigint,
  rating                    double,
  poster_path               varchar(255),
  backdrop_path             varchar(255),
  summary                   longtext,
  tagline                   varchar(255),
  runtime                   integer,
  picture_path              varchar(255),
  constraint ck_video_support_type check (support_type in (0,1)),
  constraint ck_video_content_type check (content_type in (0,1,2)),
  constraint ck_video_state check (state in (0,1,2)),
  constraint pk_video primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

drop table video;

SET FOREIGN_KEY_CHECKS=1;

