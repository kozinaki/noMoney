CREATE TABLE user_s (name character varying(100), login character varying(20), password character varying(128), id bigint NOT NULL,  CONSTRAINT users_pkey PRIMARY KEY (id), CONSTRAINT users_ukey UNIQUE (login));

CREATE TABLE item_s (name character varying(100), description character varying(500), id bigint NOT NULL, CONSTRAINT items_pkey PRIMARY KEY (id));

CREATE TABLE lot_s (give_number character varying(5), take_number character varying(5), give_what character varying(20), take_what character varying(20), give_item bigint, take_item bigint, id bigint NOT NULL, CONSTRAINT lots_pkey PRIMARY KEY (id), CONSTRAINT fk_give_item FOREIGN KEY (give_item) REFERENCES item_s (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT fk_take_item FOREIGN KEY (take_item) REFERENCES item_s (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);

CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15),PRIMARY KEY (SEQ_NAME));

INSERT INTO SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
