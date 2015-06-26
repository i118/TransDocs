ALTER TABLE order_document ADD COLUMN transport_type VARCHAR (100);
ALTER TABLE order_document ADD COLUMN border_сrossing VARCHAR (255);
ALTER TABLE order_document ADD COLUMN temperature_regime VARCHAR (255);
ALTER TABLE order_document ADD COLUMN additional_service VARCHAR (255);

CREATE TABLE order_transport (
  object_id UUID PRIMARY KEY,
  driver_id UUID,
  car_id UUID,
  trailer VARCHAR(255),
  driver_passport_number VARCHAR(6),
  driver_passport_serial VARCHAR(8),
  driver_issued_passport VARCHAR(255)
)INHERITS (td_object);

ALTER TABLE order_document ADD COLUMN transport_id UUID UNIQUE;

ALTER TABLE order_document ADD CONSTRAINT "fk_transport_id" FOREIGN KEY (transport_id) REFERENCES order_transport (object_id);

CREATE TABLE order_additional_condition (
  object_id UUID PRIMARY KEY,
  additional_condition VARCHAR(255),
  penalty VARCHAR(255),
  agreement_content TEXT
)INHERITS (td_object);

ALTER TABLE order_document ADD COLUMN customer_additional_condition_id UUID UNIQUE;
ALTER TABLE order_document ADD COLUMN carrier_additional_condition_id UUID UNIQUE;

ALTER TABLE order_document ADD CONSTRAINT "fk_customer_condition_id" FOREIGN KEY (customer_additional_condition_id) REFERENCES order_additional_condition (object_id);
ALTER TABLE order_document ADD CONSTRAINT "fk_carrier_condition_id" FOREIGN KEY (carrier_additional_condition_id) REFERENCES order_additional_condition (object_id);

