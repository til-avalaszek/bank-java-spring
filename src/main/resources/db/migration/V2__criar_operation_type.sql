CREATE TABLE operation_type (
    ind_operation_type smallint not null primary key,
    desc_description varchar(50) not null
);

INSERT INTO operation_type (ind_operation_type, desc_description) values
    (1, 'COMPRA A VISTA'),
    (2, 'COMPRA PARCELADA'),
    (3, 'SAQUE'),
    (4, 'PAGAMENTO');

COMMENT ON TABLE operation_type                        IS 'Operation Type table';

COMMENT ON COLUMN operation_type.ind_operation_type    IS '[NOT_SECURITY_APPLY] PK - Operation Type identifier number.';
COMMENT ON COLUMN operation_type.desc_description      IS '[NOT_SECURITY_APPLY] Description of operation.';
