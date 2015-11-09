/*
 Navicat Premium Data Transfer

 Source Server         : postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 90305
 Source Host           : localhost
 Source Database       : transdocsdb
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90305
 File Encoding         : utf-8

 Date: 03/23/2015 23:14:01 PM
*/

-- ----------------------------
--  Sequence structure for "incoming_number"
-- ----------------------------
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SEQUENCE "incoming_number" INCREMENT 1 START 34 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;

-- ----------------------------
--  Sequence structure for "outgoing_number"
-- ----------------------------
CREATE SEQUENCE "outgoing_number" INCREMENT 1 START 34 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;

CREATE TABLE "td_object" (
	"object_id" UUID NOT NULL,
	"creation_date" timestamp(6) WITH TIME ZONE,
	"modify_date" timestamp(6) WITH TIME ZONE,
	"object_type" varchar(50),
	"version" int8,
	"is_deleted" bool
);
-- ----------------------------
--  Table structure for "td_lock"
-- ----------------------------
CREATE TABLE "td_lock" (
	"lock_owner" varchar(255) NOT NULL,
	"lock_object_id" UUID NOT NULL
) INHERITS(td_object);



-- ----------------------------
--  Table structure for "td_customer_person"
-- ----------------------------
CREATE TABLE "td_customer_person" (
	"e_mail" varchar(255),
	"first_name" varchar(255),
	"gender" int4,
	"last_name" varchar(255),
	"patronymic" varchar(255),
	"phone" varchar(255),
	"contractor_object_id" UUID
) INHERITS(td_object);



-- ----------------------------
--  Table structure for "td_driver"
-- ----------------------------
CREATE TABLE "td_driver" (
	"first_name" varchar(255) NOT NULL,
	"last_name" varchar(255),
	"patronymic" varchar(255),
	"gender" int4,
	"passport_serial" varchar(6),
	"passport_number" varchar(8),
	"issued_passport" varchar(255),
	"phone" varchar(50),
	"registration_address" varchar(255),
	"driving_license" varchar(100),
	"car_id" UUID,
	"carrier_object_id" UUID
)INHERITS(td_object);


-- ----------------------------
--  Table structure for "td_customer"
-- ----------------------------
CREATE TABLE "td_customer" (
	"bic" varchar(255),
	"inn" varchar(255),
	"kpp" varchar(255),
	"ogrn" varchar(255),
	"okpo" varchar(255),
	"okved" varchar(255),
	"account" varchar(255),
	"bank" varchar(255),
	"chiefaccountant" varchar(255),
	"correspondentaccount" varchar(255),
	"director" varchar(255),
	"comment" varchar(255),
	"email" varchar(255),
	"full_name" varchar(255) NOT NULL,
	"legal_address" varchar(255),
	"legal_form" varchar(255),
	"mailing_address" varchar(255),
	"phone" varchar(255),
	"short_name" varchar(255) NOT NULL,
	"filestore_object_id" UUID
)INHERITS(td_object);


-- ----------------------------
--  Table structure for "td_file"
-- ----------------------------
CREATE TABLE "td_file" (
	"extension" varchar(255),
	"file_location" varchar(255),
	"file_type" int4,
	"mime_type" varchar(255),
	"file_name" varchar(255),
	"container_object_id" UUID,
	"lockobject_object_id" UUID
)INHERITS(td_object);



-- ----------------------------
--  Table structure for "td_customer_file"
-- ----------------------------
CREATE TABLE "td_customer_file" (
	"owner_object_id" UUID
)INHERITS(td_file);


-- ----------------------------
--  Table structure for "td_carrier_person"
-- ----------------------------
CREATE TABLE "td_carrier_person" (
	"e_mail" varchar(255),
	"first_name" varchar(255),
	"gender" int4,
	"last_name" varchar(255),
	"patronymic" varchar(255),
	"phone" varchar(255),
	"contractor_object_id" UUID
)INHERITS(td_object);


-- ----------------------------
--  Table structure for "td_carrier_file"
-- ----------------------------
CREATE TABLE "td_carrier_file" (
	"owner_object_id" UUID
)INHERITS(td_file);


-- ----------------------------
--  Table structure for "td_carrier"
-- ----------------------------
CREATE TABLE "td_carrier" (
	"bic" varchar(255),
	"inn" varchar(255),
	"kpp" varchar(255),
	"ogrn" varchar(255),
	"okpo" varchar(255),
	"okved" varchar(255),
	"account" varchar(255),
	"bank" varchar(255),
	"chiefaccountant" varchar(255),
	"correspondentaccount" varchar(255),
	"director" varchar(255),
	"comment" varchar(255),
	"email" varchar(255),
	"full_name" varchar(255) NOT NULL,
	"legal_address" varchar(255),
	"legal_form" varchar(255),
	"mailing_address" varchar(255),
	"phone" varchar(255),
	"short_name" varchar(255) NOT NULL,
	"filestore_object_id" UUID
)INHERITS(td_object);

-- ----------------------------
--  Table structure for "td_car"
-- ----------------------------
CREATE TABLE "td_car" (
	"carrier_object_id" UUID,
	"car_brand" varchar(150) NOT NULL,
	"car_number" varchar(20) NOT NULL,
	"trailer_brand" varchar(150),
	"trailer_number" varchar(20),
	"trailer_type" varchar(255),
	"capacity" varchar(255),
	"cubage" varchar(255)
)INHERITS(td_object);

-- ----------------------------
--  Table structure for "order_document"
-- ----------------------------
CREATE TABLE "order_document" (
	"incoming_number" varchar(255),
	"outgoing_number" varchar(255),
	"transportation_type" varchar(50),
	"manager_object_id" UUID,
	"customer_object_id" UUID,
	"carrier_object_id" UUID,
	"company_object_id" UUID,
	"amount_transaction" varchar(100),
	"customer_payment_method" varchar(50),
	"customer_person_id" UUID,
	"customer_phone" varchar(255),
	"customer_address" varchar(255),
	"customer_email" varchar(255),
	"carrier_payment_method" varchar(50),
	"carrier_email" varchar(100),
	"carrier_address" varchar(255),
	"carrier_phone" varchar(50),
	"carrier_person_id" UUID
)INHERITS(td_object);

-- ----------------------------
--  Table structure for "td_user"
-- ----------------------------
CREATE TABLE "td_user" (
	"first_name" varchar(255),
	"gender" int4 NOT NULL,
	"last_name" varchar(255),
	"login" varchar(255) NOT NULL,
	"e_mail" varchar(255),
	"password" varchar(255) NOT NULL,
	"patronymic" varchar(255),
	"phone" varchar(255),
	"company_object_id" UUID NOT NULL
)INHERITS(td_object);

-- ----------------------------
--  Table structure for "user_to_roles"
-- ----------------------------
CREATE TABLE "user_to_roles" (
	"user_id" UUID NOT NULL,
	"role_id" UUID NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Table structure for "td_role"
-- ----------------------------
CREATE TABLE "td_role" (
	"description" varchar(255),
	"role_name" varchar(255) NOT NULL
)INHERITS(td_object);

-- ----------------------------
--  Table structure for "td_company"
-- ----------------------------
CREATE TABLE "td_company" (
	"bic" varchar(255),
	"inn" varchar(255),
	"kpp" varchar(255),
	"ogrn" varchar(255),
	"okpo" varchar(255),
	"okved" varchar(255),
	"account" varchar(255),
	"bank" varchar(255),
	"chiefaccountant" varchar(255),
	"correspondentaccount" varchar(255),
	"director" varchar(255),
	"comment" varchar(255),
	"email" varchar(255),
	"full_name" varchar(255) NOT NULL,
	"legal_address" varchar(255),
	"legal_form" varchar(255) NOT NULL,
	"mailing_address" varchar(255),
	"phone" varchar(255),
	"short_name" varchar(255) NOT NULL,
	"login" varchar(50)
)INHERITS(td_object);


CREATE OR REPLACE FUNCTION initAdmin()
	RETURNS INT
AS $$
DECLARE
	userId UUID;
	roleAdminId UUID;
	companyId UUID;
BEGIN
  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

  SELECT uuid_generate_v4() INTO userId;
  SELECT uuid_generate_v4() INTO roleAdminId;
  SELECT uuid_generate_v4() INTO companyId;
  INSERT INTO "td_company" (
    object_id,
    full_name,
    short_name,
    legal_form,
    login,
    creation_date,
    modify_date,
    object_type,
    version,
    is_deleted
  )
  VALUES (
    companyId,
    'TransDocs',
    'TransDocs',
    'LLC',
    'admin',
			now(),
			now(),
			'td_company',
			0,
			'0'
   );

  INSERT INTO "td_user"(
			first_name,
			last_name,
			patronymic,
			login,
			password,
			object_id,
			company_object_id,
			creation_date,
			modify_date,
			object_type,
			version,
			is_deleted,
      gender
	)
  VALUES (
    'Admin',
    'Admin',
    'Admin',
    'admin',
    '041a520fe70f0baf956a56916ea128d464fb1f35',
    userId,
    companyId,
    now(),
    now(),
    'td_user',
    0,
    '0',
    0
  );
  INSERT INTO "td_role" (
    description,
    role_name,
    object_id,
    creation_date,
    modify_date,
    object_type,
    is_deleted,
    version
    )
  VALUES (
    'Администратор',
    'ROLE_SUPER_ADMIN',
    roleAdminId,
    now(),
    now(),
    'td_role',
    '0',
    0
  );
  INSERT INTO "user_to_roles"(user_id, role_id) VALUES (userId,roleAdminId);
  RETURN 0::INT;
END;
$$ LANGUAGE plpgsql;

SELECT initAdmin();

-- ----------------------------
--  View structure for "order_document_view"
-- ----------------------------
DROP VIEW IF EXISTS "order_document_view";
CREATE VIEW "order_document_view" AS  SELECT ord.object_id,
    ord.incoming_number,
    ord.outgoing_number,
    ord.transportation_type,
    customer.full_name AS customer_full_name,
    customer.object_id AS customer_id,
    carrier.full_name AS carrier_full_name,
    carrier.object_id AS carrier_id,
    manager.object_id AS manager_id,
    manager.first_name AS m_first_name,
    manager.last_name AS m_last_name,
    manager.patronymic AS m_patronymic,
    manager.login AS m_login
   FROM order_document ord
     LEFT JOIN td_customer customer ON customer.object_id = ord.customer_object_id
     LEFT JOIN td_carrier carrier ON carrier.object_id = ord.carrier_object_id
     LEFT JOIN td_user manager ON manager.object_id = ord.manager_object_id
  ORDER BY ord.outgoing_number;

-- ----------------------------
--  Primary key structure for table "td_lock"
-- ----------------------------
ALTER TABLE "td_lock" ADD CONSTRAINT "td_lock_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_customer_person"
-- ----------------------------
ALTER TABLE "td_customer_person" ADD CONSTRAINT "td_customer_person_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_driver"
-- ----------------------------
ALTER TABLE "td_driver" ADD CONSTRAINT "td_driver_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_customer"
-- ----------------------------
ALTER TABLE "td_customer" ADD CONSTRAINT "td_customer_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_file"
-- ----------------------------
ALTER TABLE "td_file" ADD CONSTRAINT "td_file_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_customer_file"
-- ----------------------------
ALTER TABLE "td_customer_file" ADD CONSTRAINT "td_customer_file_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_carrier_person"
-- ----------------------------
ALTER TABLE "td_carrier_person" ADD CONSTRAINT "td_carrier_person_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_carrier_file"
-- ----------------------------
ALTER TABLE "td_carrier_file" ADD CONSTRAINT "td_carrier_file_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_carrier"
-- ----------------------------
ALTER TABLE "td_carrier" ADD CONSTRAINT "td_carrier_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_car"
-- ----------------------------
ALTER TABLE "td_car" ADD CONSTRAINT "td_car_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "order_document"
-- ----------------------------
ALTER TABLE "order_document" ADD CONSTRAINT "order_document_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

CREATE OR REPLACE FUNCTION generateOrderNumber()
  RETURNS TRIGGER
AS $$
DECLARE
BEGIN
  NEW.incoming_number = to_char(nextval('incoming_number'), '99999') || '#' ||
                        to_char('today' :: TIMESTAMP, 'dd.MM.yyyy');
  NEW.outgoing_number = to_char(nextval('outgoing_number'), '99999') || '#' ||
                        to_char('today' :: TIMESTAMP, 'dd.MM.yyyy');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ----------------------------
--  Triggers structure for table "order_document"
-- ----------------------------
CREATE TRIGGER "generate_order_number_t" BEFORE INSERT ON "order_document" FOR EACH ROW EXECUTE PROCEDURE "generateordernumber"();

-- ----------------------------
--  Primary key structure for table "td_user"
-- ----------------------------
ALTER TABLE "td_user" ADD CONSTRAINT "td_user_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table "td_user"
-- ----------------------------
ALTER TABLE "td_user" ADD CONSTRAINT "uk_login" UNIQUE ("login") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_role"
-- ----------------------------
ALTER TABLE "td_role" ADD CONSTRAINT "td_role_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table "td_role"
-- ----------------------------
ALTER TABLE "td_role" ADD CONSTRAINT "uk_role_name" UNIQUE ("role_name") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_object"
-- ----------------------------
ALTER TABLE "td_object" ADD CONSTRAINT "td_object_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "td_company"
-- ----------------------------
ALTER TABLE "td_company" ADD CONSTRAINT "td_company_pkey" PRIMARY KEY ("object_id") NOT DEFERRABLE INITIALLY IMMEDIATE;


-- ----------------------------
--  Foreign keys structure for table "td_customer_person"
-- ----------------------------
ALTER TABLE "td_customer_person" ADD CONSTRAINT "fk_65pvovq3orr98srx5ctsjcnfp" FOREIGN KEY ("contractor_object_id") REFERENCES "td_customer" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_driver"
-- ----------------------------
ALTER TABLE "td_driver" ADD CONSTRAINT "fk_td_carr" FOREIGN KEY ("car_id") REFERENCES "td_car" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "td_driver" ADD CONSTRAINT "fk_td_carrier" FOREIGN KEY ("carrier_object_id") REFERENCES "td_carrier" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_customer"
-- ----------------------------
ALTER TABLE "td_customer" ADD CONSTRAINT "fk_file_store" FOREIGN KEY ("filestore_object_id") REFERENCES "td_customer_file" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_file"
-- ----------------------------
ALTER TABLE "td_file" ADD CONSTRAINT "fk_container" FOREIGN KEY ("container_object_id") REFERENCES "td_file" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "td_file" ADD CONSTRAINT "fk_lock_object" FOREIGN KEY ("lockobject_object_id") REFERENCES "td_lock" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_customer_file"
-- ----------------------------
ALTER TABLE "td_customer_file" ADD CONSTRAINT "fk_owner_object_id" FOREIGN KEY ("owner_object_id") REFERENCES "td_customer" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_carrier_person"
-- ----------------------------
ALTER TABLE "td_carrier_person" ADD CONSTRAINT "fk_td_carrier" FOREIGN KEY ("contractor_object_id") REFERENCES "td_carrier" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_carrier_file"
-- ----------------------------
ALTER TABLE "td_carrier_file" ADD CONSTRAINT "fk_td_carrier" FOREIGN KEY ("owner_object_id") REFERENCES "td_carrier" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_carrier"
-- ----------------------------
ALTER TABLE "td_carrier" ADD CONSTRAINT "fk_td_carrier_file" FOREIGN KEY ("filestore_object_id") REFERENCES "td_carrier_file" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_car"
-- ----------------------------
ALTER TABLE "td_car" ADD CONSTRAINT "fk_td_carrier" FOREIGN KEY ("carrier_object_id") REFERENCES "td_carrier" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "order_document"
-- ----------------------------
ALTER TABLE "order_document" ADD CONSTRAINT "fk_manager" FOREIGN KEY ("manager_object_id") REFERENCES "td_user" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "order_document" ADD CONSTRAINT "fk_customer" FOREIGN KEY ("customer_object_id") REFERENCES "td_customer" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "order_document" ADD CONSTRAINT "fk_carrier" FOREIGN KEY ("carrier_object_id") REFERENCES "td_carrier" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "order_document" ADD CONSTRAINT "fk_company" FOREIGN KEY ("company_object_id") REFERENCES "td_company" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "order_document" ADD CONSTRAINT "fk_customer_person" FOREIGN KEY ("customer_person_id") REFERENCES "td_customer_person" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "order_document" ADD CONSTRAINT "fk_carrier_person" FOREIGN KEY ("carrier_person_id") REFERENCES "td_carrier_person" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table "td_user"
-- ----------------------------
ALTER TABLE "td_user" ADD CONSTRAINT "fk_td_company" FOREIGN KEY ("company_object_id") REFERENCES "td_company" ("object_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;


CREATE OR REPLACE FUNCTION initDemo()
	RETURNS INT
AS $$
DECLARE
	userId UUID;
	roleAdminId UUID;
	companyId UUID;
BEGIN
  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
	CREATE SCHEMA td_demo;
  RETURN 0::INT;
END;
$$ LANGUAGE plpgsql;

SELECT initDemo();



