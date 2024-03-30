-- liquibase formatted sql

-- changeset iOvcharenko:1
ALTER TABLE drums
    add    studio_id     bigint       null,
    add constraint fk_drums_for_studio foreign key (studio_id)
        references studio (studio_id)
        on delete restrict on UPDATE restrict;

-- changeset iOvcharenko:2

ALTER TABLE instrument
    add    studio_id     bigint       null,
    add constraint fk_instrument_for_studio foreign key (studio_id)
        references studio (studio_id)
        on delete restrict on UPDATE restrict;

-- changeset iOvcharenko:3

ALTER TABLE vocal
    add    studio_id     bigint       null,
    add constraint fk_vocal_for_studio foreign key (studio_id)
        references studio (studio_id)
        on delete restrict on UPDATE restrict;
