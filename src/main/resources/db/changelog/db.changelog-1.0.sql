-- liquibase formatted sql

-- changeset iOvcharenko:1
create table if not exists release
(
    release_id          bigint                   not null,
    band_name           varchar(128)             not null,
    work_description    text                     null,
    album_cover_link    text                     null,
    social_network_link text                     null,
    count_of_track      bigint                   not null,
    hours_of_work       bigint                   null,
    release_length      numeric                  null,
    start_of_work       timestamp with time zone null,
    end_of_work         timestamp with time zone not null,
    release_dttm        timestamp with time zone null,
    release_status      varchar(128)             not null,
    multitrack_link     text                     null,
    music_label         varchar(128)             null,
    constraint pk_release primary key (release_id)
);

comment on table release is
    'Таблица релизов';
comment on column release.release_id is
    'Ид релиза';
comment on column release.band_name is
    'Название группы';
comment on column release.work_description is
    'Пожелания по звуку, описание работы';
comment on column release.album_cover_link is
    'Ссылка на обложку релиза';
comment on column release.social_network_link is
    'Ссылка на релиз';
comment on column release.count_of_track is
    'Количество треков';
comment on column release.hours_of_work is
    'Количество часов работы';
comment on column release.release_length is
    'Длительность релиза';
comment on column release.start_of_work is
    'Дата старта работы над релизом';
comment on column release.end_of_work is
    'Дата когда нужно сдать работу';
comment on column release.release_dttm is
    'Дата релиза';
comment on column release.release_status is
    'Статус работы над релизом';
comment on column release.multitrack_link is
    'Ссылка на исходники';
comment on column release.music_label is
    'Лейбл на котором издается релиз';

-- changeset iOvcharenko:2
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
comment on table studio is
    'Таблица студии на которой записывался инструмент';
comment on column studio.studio_name is
    'Название студии';
comment on column studio.studio_place is
    'Местоположение студии';
comment on column studio.social_network_link is
    'Ссылка на студию';

-- changeset iOvcharenko:3
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
comment on table price is
    'Таблица расчета цены за работу';
comment on column price.price_id is
    'Ид цены';
comment on column price.mixing is
    'Цена сведения';
comment on column price.mastering is
    'Цена мастеринга';
comment on column price.editing_drums is
    'Цена редактирования барабанов';
comment on column price.editing_vocals is
    'Цена редактирования вокалов';
comment on column price.editing_other_instr is
    'Цена редактирования инстумента';
comment on column price.producing is
    'Цена продюсирования';
comment on column price.discount is
    'Скидка в процентах';
comment on column price.total_amount is
    'Общая стоимость';

alter table price
    add constraint fk_price_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

-- changeset iOvcharenko:4
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

comment on table drums is
    'Таблица информации о барабанах';
comment on column drums.drums_id is
    'Ид барабанов';
comment on column drums.drums_type is
    'Тип барабанов живые\семплы';
comment on column drums.drums_model is
    'Кит используемый на релизе';
comment on column drums.drums_mics is
    'Микрофоны поканально';
comment on column drums.drums_img is
    'Фото или скрин барабанов\пресета';

alter table drums
    add constraint fk_drums_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

-- changeset iOvcharenko:5
create table if not exists guitar
(
    guitar_id      bigint       not null,
    guitar_type    varchar(128) not null,
    guitar_model   varchar(128) null,
    tone_stack     varchar(128) null,
    tone_stack_img text         null,
    release_id     bigint       not null,
    studio_id      bigint       null,
    constraint pk_guitar primary key (guitar_id)
);

comment on table guitar is
    'Табица гитар';
comment on column guitar.guitar_id is
    'Ид гитары';
comment on column guitar.guitar_type is
    'Тип гитары электро\акустика и тд';
comment on column guitar.guitar_model is
    'Модель гитары';
comment on column guitar.tone_stack is
    'Цепочка эффектов';
comment on column guitar.tone_stack_img is
    'Фото или скрин цепочки эффектов';

alter table guitar
    add constraint fk_guitar_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict,
    add constraint fk_guitar_for_studio foreign key (studio_id)
        references studio (studio_id)
        on delete restrict on UPDATE restrict;


-- changeset iOvcharenko:6
create table if not exists vocal
(
    vocal_id        bigint       not null,
    vocal_type      varchar(128) not null,
    vocal_technique varchar(128) null,
    vocal_mic       varchar(128) null,
    release_id      bigint       not null,
    constraint pk_vocal primary key (vocal_id)
);

comment on table vocal is
    'Таблица вокалов';
comment on column vocal.vocal_id is
    'Id вокалов';
comment on column vocal.vocal_type is
    'Типа вокала основной\бэк вокал и тд';
comment on column vocal.vocal_technique is
    'Техника исполнения';
comment on column vocal.vocal_mic is
    'Какой микрофон использовался на записи';

alter table vocal
    add constraint fk_vocal_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

-- changeset iOvcharenko:7
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
comment on table instrument is
    'Таблица остальных инструментов';
comment on column instrument.instrument_type is
    'Тип инстурмента, например перкуссия или струнный';
comment on column instrument.instrument_model is
    'Модель инструмента';
comment on column instrument.instrument_tone_stack is
    'Цепочка эффектов инструмента';
comment on column instrument.instrument_tone_stack_img is
    'Скрин или фото цепочки эффектов инструмента';
alter table instrument
    add constraint fk_instrument_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;

-- changeset iOvcharenko:8
create table if not exists genre
(
    genre_id   bigint       not null,
    genre_name varchar(128) not null,
    release_id bigint       not null,
    constraint pk_genre primary key (genre_id)
);
comment on table genre is
    'Таблица музыкальных жанров';
comment on column genre.genre_name is
    'Название жанра';
alter table genre
    add constraint fk_genre_for_release foreign key (release_id)
        references release (release_id)
        on delete restrict on update restrict;


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


