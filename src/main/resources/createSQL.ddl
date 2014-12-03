set search_path=mamlead_hstore;
--this could need a postgres_contrib to bee installed
CREATE EXTENSION IF NOT EXISTS hstore with SCHEMA mamlead_hstore;



--TRUNCATE LEAD CASCADE;
--DROP table lead_data;
--DROP TABLE lead;

CREATE TABLE lead
(
  lead_id bigserial NOT NULL,
  product_id character varying(40) NOT NULL,
  campaign_id bigint NOT NULL,
  order_id bigint NOT NULL,
  partner_id bigint NOT NULL,
  status character varying(40) NOT NULL,
  email character varying(50) NOT NULL,
  creation_date timestamp without time zone NOT NULL,
  modification_date timestamp without time zone,
  export_date timestamp without time zone,
  CONSTRAINT lead_pkey PRIMARY KEY (lead_id),
  CONSTRAINT lead_order_id_partner_id_key UNIQUE (order_id, partner_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE lead_data
(
  lead_id bigint NOT NULL,
  payload hstore ,
  CONSTRAINT lead_data_pkey PRIMARY KEY (lead_id),
  CONSTRAINT lead_lead_id FOREIGN KEY (lead_id)
      REFERENCES lead (lead_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

insert into lead(product_id,campaign_id,order_id,partner_id,status,email,creation_date,modification_date,export_date) values ('gws',1,1000,11,'CREATED','2mail@e.com',NOW(),NOW(),NOW());

select * from lead;

insert into lead_data (lead_id,payload) values(1,'author    => "Katherine Dunn",
  pages     => 368,
  category  => fiction');


select payload->'category' "category" from lead_data where payload->'category'='fiction';
