CREATE TABLE simple_dictionary (
   description VARCHAR (100) NOT NULL,
   dictionary_type VARCHAR(100) NOT NULL,
   owner_id UUID NOT NULL
)INHERITS (td_object);

ALTER TABLE simple_dictionary ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

ALTER TABLE td_carrier ADD COLUMN owner_id UUID NOT NULL;
ALTER TABLE td_carrier ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

ALTER TABLE td_customer ADD COLUMN owner_id UUID NOT NULL;
ALTER TABLE td_customer ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

ALTER TABLE td_company ADD COLUMN owner_id UUID;
ALTER TABLE td_company ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

ALTER TABLE td_role ADD COLUMN owner_id UUID;
ALTER TABLE td_role ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

ALTER TABLE td_user ADD COLUMN owner_id UUID;
ALTER TABLE td_user ADD CONSTRAINT "fk_owner_id" FOREIGN KEY (owner_id) REFERENCES td_user (object_id);

