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
