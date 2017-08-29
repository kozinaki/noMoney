CREATE TABLE user_s (name character varying(100), login character varying(20), password character varying(128), id character varying(100) NOT NULL,  CONSTRAINT users_pkey PRIMARY KEY (id), CONSTRAINT users_ukey UNIQUE (login));

CREATE TABLE item_s (name character varying(100), description character varying(500), image bytea, user_id character varying(100) NOT NULL, id character varying(100) NOT NULL, CONSTRAINT items_pkey PRIMARY KEY (id), CONSTRAINT fk_user_item FOREIGN KEY (user_id)  REFERENCES user_s(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);

CREATE TABLE lot_s (give_item_id character varying(100) NOT NULL, give_number character varying(5), take_name character varying(100), take_description character varying(500), take_image bytea, take_number character varying(5), id character varying(100) NOT NULL, CONSTRAINT lots_pkey PRIMARY KEY (id), CONSTRAINT fk_give_item FOREIGN KEY (give_item_id) REFERENCES item_s (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);

CREATE TABLE shot_s (lot_id character varying(100) NOT NULL, take_item_id character varying(100) NOT NULL, take_number character varying(5), id character varying(100) NOT NULL, CONSTRAINT shots_pkey PRIMARY KEY (id), CONSTRAINT fk_take_item FOREIGN KEY (take_item_id) REFERENCES item_s (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);
