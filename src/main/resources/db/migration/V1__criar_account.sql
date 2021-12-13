CREATE TABLE account (
     idt_account bigint NOT NULL,
     desc_document_number varchar(255) NOT NULL,
     dat_creation timestamp not null,
     CONSTRAINT account_pkey PRIMARY KEY (idt_account)
);

COMMENT ON TABLE account                        IS 'Transaction table';

COMMENT ON COLUMN account.idt_account           IS '[NOT_SECURITY_APPLY] PK - Account identifier number.';
COMMENT ON COLUMN account.desc_document_number  IS '[SECURITY_APPLY] Document of client.';
COMMENT ON COLUMN account.dat_creation          IS '[NOT_SECURITY_APPLY] When account was created.';
