INSERT INTO public.release (release_id, band_name, work_description, album_cover_link, social_network_link,
                            count_of_track, hours_of_work, release_length, start_of_work, end_of_work, release_dttm,
                            release_status, multitrack_link, music_label)
VALUES (1, 'wlvs', 'nihuya ne sdelal', 'pornhub.com', 'vk.com/wlvs', 8, 666, 52, '2023-01-04 23:51:19.211000 +00:00',
        '2023-01-04 23:51:58.724000 +00:00', null, 'IN_PROGRESS', 'xxx.com', null);

INSERT INTO public.drums (drums_id, drums_type, drums_model, drums_mics, drums_img, release_id)
VALUES (1, 'wood', 'dw', 'shures mures', 'pornhub.com', 1);

INSERT INTO public.guitar (guitar_id, guitar_type, guitar_model, tone_stack, tone_stack_img, release_id)
VALUES (1, 'hui guitar', 'gibsons88', 'standart', '666.jpg', 1);