create table if not exists release
(
    release_id          bigint       not null,
    band_name           varchar(128) not null,
    work_description    text         null,
    album_cover_link    text         null,
    social_network_link text         null,
    count_of_track      bigint       not null,
    hours_of_work       bigint       null,
    release_length      numeric      null,
    start_of_work       date         null,
    end_of_work         date         null,
    release_dttm        date         not null,
    release_status      varchar(128) not null,
    multitrack_link     text         null,
    music_label         varchar(128) null,
    constraint pk_release primary key (release_id)
);

create table if not exists price
(
    price_id            bigint  not null,
    mixing              numeric null,
    mastering           numeric null,
    editing_drums       numeric null,
    editing_vocals      numeric null,
    editing_other_instr numeric null,
    producing           numeric null,
    discount            numeric null,
    total_amount        numeric not null,
    release_id          bigint  not null,
    constraint pk_price primary key (price_id)
);

alter table price
    add constraint fk_price_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

create table if not exists drums
(
    drums_id    bigint       not null,
    drums_type  varchar(128) not null,
    drums_model text         null,
    drums_mics  text         null,
    drums_img   text         null,
    release_id  bigint       not null,
    constraint pk_drums primary key (drums_id)
);
alter table drums
    add constraint fk_drums_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

create table if not exists guitar
(
    guitar_id      bigint       not null,
    guitar_type    varchar(128) not null,
    guitar_model   varchar(128) null,
    tone_stack     varchar(128) null,
    tone_stack_img text         null,
    release_id     bigint       not null,
    constraint pk_guitar primary key (guitar_id)
);
alter table guitar
    add constraint fk_guitar_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

create table if not exists vocal
(
    vocal_id        bigint       not null,
    vocal_type      varchar(128) not null,
    vocal_technique varchar(128) null,
    vocal_mic       varchar(128) null,
    release_id      bigint       not null,
    constraint pk_vocal primary key (vocal_id)
);
alter table vocal
    add constraint fk_vocal_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

create table if not exists instrument
(
    instrument_id             bigint       not null,
    instrument_type           varchar(128) not null,
    instrument_model          varchar(128) null,
    instrument_tone_stack     text         null,
    instrument_tone_stack_img text         null,
    release_id                bigint       not null,
    constraint pk_instrument primary key (instrument_id)
);
alter table instrument
    add constraint fk_instrument_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;


create table if not exists genre
(
    genre_id   bigint       not null,
    genre_name varchar(128) not null,
    release_id bigint       not null,
    constraint pk_genre primary key (genre_id)
);
alter table genre
    add constraint fk_genre_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

create table if not exists studio
(
    studio_id           bigint       not null,
    studio_name         varchar(128) not null,
    studio_place        text         null,
    social_network_link text         null,
    instrument_id       bigint       null,
    guitar_id           bigint       null,
    vocal_id            bigint       null,
    drums_id            bigint       null,
    constraint pk_studio primary key (studio_id)
);
alter table studio
    add constraint fk_instrument_for_release foreign key (instrument_id)
        references instrument (instrument_id)
        on delete restrict on update restrict,
    add constraint fk_guitar_for_release foreign key (guitar_id)
        references guitar (guitar_id)
        on delete restrict on update restrict,
    add constraint fk_vocal_for_release foreign key (vocal_id)
        references vocal (vocal_id)
        on delete restrict on update restrict,
    add constraint fk_drums_for_release foreign key (drums_id)
        references drums (drums_id)
        on delete restrict on update restrict;


