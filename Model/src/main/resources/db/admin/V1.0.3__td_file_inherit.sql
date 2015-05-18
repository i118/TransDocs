ALTER TABLE td_customer_file NO INHERIT td_file;
ALTER TABLE td_carrier_file NO INHERIT td_file;

ALTER TABLE td_customer_file DROP COLUMN file_name;
ALTER TABLE td_customer_file DROP COLUMN file_location;
ALTER TABLE td_customer_file DROP COLUMN mime_type;
ALTER TABLE td_customer_file DROP COLUMN file_type;
ALTER TABLE td_customer_file DROP COLUMN extension;
ALTER TABLE td_customer_file DROP COLUMN version;
ALTER TABLE td_customer_file DROP COLUMN creation_date;
ALTER TABLE td_customer_file DROP COLUMN modify_date;
ALTER TABLE td_customer_file DROP COLUMN object_type;
ALTER TABLE td_customer_file DROP COLUMN is_deleted;

ALTER TABLE td_carrier_file DROP COLUMN file_name;
ALTER TABLE td_carrier_file DROP COLUMN file_location;
ALTER TABLE td_carrier_file DROP COLUMN mime_type;
ALTER TABLE td_carrier_file DROP COLUMN file_type;
ALTER TABLE td_carrier_file DROP COLUMN extension;
ALTER TABLE td_carrier_file DROP COLUMN version;
ALTER TABLE td_carrier_file DROP COLUMN creation_date;
ALTER TABLE td_carrier_file DROP COLUMN modify_date;
ALTER TABLE td_carrier_file DROP COLUMN object_type;
ALTER TABLE td_carrier_file DROP COLUMN is_deleted;

DELETE FROM td_customer_file where object_id NOT IN (SELECT filestore_object_id FROM td_customer);
DELETE FROM td_carrier_file where object_id NOT IN (SELECT filestore_object_id FROM td_carrier);

CREATE OR REPLACE FUNCTION restoreFileStore()
  RETURNS INT
AS $$
DECLARE
  store RECORD;
BEGIN
  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
  FOR store IN SELECT object_id
               FROM td_customer_file LOOP
    INSERT INTO td_file (
      object_id,
      file_name,
      creation_date,
      modify_date,
      object_type,
      is_deleted,
      version
    )
    VALUES (
      store.object_id,
      store.object_id::VARCHAR(255),
      now(),
      now(),
      'td_customer_file',
      '0',
      0
    );

  END LOOP;

  FOR store IN SELECT object_id
               FROM td_carrier_file LOOP
    INSERT INTO td_file (
      object_id,
      file_name,
      creation_date,
      modify_date,
      object_type,
      is_deleted,
      version
    )
    VALUES (
      store.object_id,
      store.object_id::VARCHAR(255),
      now(),
      now(),
      'td_carrier_file',
      '0',
      0
    );

  END LOOP;
  RETURN 0 :: INT;
END;
$$ LANGUAGE plpgsql;

SELECT restoreFileStore();


