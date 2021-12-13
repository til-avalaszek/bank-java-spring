CREATE TABLE transaction (
    idt_transaction bigint not null primary key,
    idt_account bigint not null,
    ind_operation_type smallint not null,
    num_amount DECIMAL(12,2) NOT NULL,
    dat_event timestamp not null
);

ALTER TABLE transaction
    ADD CONSTRAINT transaction_account FOREIGN KEY (idt_account)
        REFERENCES account (idt_account);

ALTER TABLE transaction
    ADD CONSTRAINT transaction_operation_type FOREIGN KEY (ind_operation_type)
        REFERENCES operation_type (ind_operation_type);

CREATE INDEX TRAN_IDX01 ON transaction (idt_account);
CREATE INDEX TRAN_IDX02 ON transaction (idt_account, ind_operation_type);

COMMENT ON TABLE transaction                        IS 'Transaction table';

COMMENT ON COLUMN transaction.idt_transaction       IS '[NOT_SECURITY_APPLY] PK - Transaction identifier number.';
COMMENT ON COLUMN transaction.idt_account           IS '[NOT_SECURITY_APPLY] FK - References Account Table.';
COMMENT ON COLUMN transaction.ind_operation_type    IS '[NOT_SECURITY_APPLY] FK - References Operation Type Table.';
COMMENT ON COLUMN transaction.num_amount            IS '[NOT_SECURITY_APPLY] Amount of transaction.';
COMMENT ON COLUMN transaction.dat_event             IS '[NOT_SECURITY_APPLY] When transaction was created.';
