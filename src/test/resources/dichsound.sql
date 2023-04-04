-- можно впринципе создать таблицу для каждого инструмент и сделать связь типо
-- dim_vocal <- dim_plugin,
-- dim_gtr <- dim_plugin.

-- (слово dim_ тут просто так, у меня на проекте такой порядок названия таблиц ыы)

/*==============================================================*/
/* Table: dim_forestry                                          */
/*==============================================================*/
create table if not exists dim_release
(
    release_id                                     bigint       not null,
    band_name                                      varchar(128) not null,
    album_cover_link                               text         null,
    social_network_link                            text         null,
    number_of_release_in_dich_sound                bigint       null,
    release_dttm                                   date         not null,
    hours_of_work                                  bigint       not null,
    price_by_track                                 bigint       not null,
    count_of_track                                 bigint       not null,
    discount                                       decimal      null,
    constraint pk_dim_release primary key (release_id)
);

comment on table dim_release is
    'Таблица релизов';

comment on column dim_release.release_id is
    'Ид релиза';

comment on column dim_release.band_name is
    'Название группы';

comment on column dim_release.album_cover_link is
    'Ссылка на обложку релиза';

comment on column dim_release.social_network_link is
    'Ссылка на релиз';

comment on column dim_release.number_of_release_in_dich_sound is
    'Какой по счету это релиз группы в студии дич-саунд';

comment on column dim_release.release_dttm is
    'Дата релиза';

comment on column dim_release.hours_of_work is
    'Количество часов потраченное на работу';

comment on column dim_release.price_by_track is
    'Цена за трек';

comment on column dim_release.count_of_track is
    'Количество треков в релизе';

comment on column dim_release.discount is
    'Скидка на весь релиз в процентах';

/*==============================================================*/
/* Table: dim_plugins                                           */
/*==============================================================*/
create table if not exists dim_plugins
(
    plugins_id                                     bigint       not null,
    instrument                                     varchar(128) not null,
    settings_description                           text         null,
    plugin_names                                   text         not null,
    release_id                                     bigint       null,
    constraint pk_dim_plugins primary key (plugins_id)
);

comment on table dim_plugins is
    'Таблица плагинов';

comment on column dim_plugins.instrument is
    'Название инструмента';

comment on column dim_plugins.settings_description is
    'Описание основных настроек';

comment on column dim_plugins.plugin_names is
    'Наименование плагинов';

/*==============================================================*/
/* Table: dim_drums                                             */
/*==============================================================*/
create table if not exists dim_drums
(
    drums_id                                       bigint       not null,
    trigger_library                                varchar(128) not null,
    settings_description                           text         null,
    plugin_names                                   text         not null,
    mic_no                                         bigint       null,
    release_id                                     bigint       null,
    constraint pk_dim_drums primary key (drums_id)
);

comment on table dim_drums is
    'Таблица обработки барабанов';

comment on column dim_drums.trigger_library is
    'Библиотека триггеров';

comment on column dim_drums.settings_description is
    'Описание основных настроек';

comment on column dim_drums.plugin_names is
    'Наименование плагинов';

comment on column dim_drums.mic_no is
    'Количество миикрофонов';

alter table dim_plugins
    add constraint fk_plugin_for_release foreign key (release_id)
        references dim_release (release_id)
        on delete restrict on update restrict;

alter table dim_drums
    add constraint fk_drums_for_release foreign key (release_id)
        references dim_release (release_id)
        on delete restrict on update restrict;
