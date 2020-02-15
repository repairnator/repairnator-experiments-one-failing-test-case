update ACT_GE_PROPERTY set VALUE_ = '6.1.0.0' where NAME_ = 'schema.version';

alter table ACT_RU_EXECUTION add START_ACT_ID_ NVARCHAR2(255);

create table ACT_RU_HISTORY_JOB (
    ID_ NVARCHAR2(64) NOT NULL,
    REV_ INTEGER,
    LOCK_EXP_TIME_ TIMESTAMP(6),
    LOCK_OWNER_ NVARCHAR2(255),
    RETRIES_ INTEGER,
    EXCEPTION_STACK_ID_ NVARCHAR2(64),
    EXCEPTION_MSG_ NVARCHAR2(2000),
    HANDLER_TYPE_ NVARCHAR2(255),
    HANDLER_CFG_ NVARCHAR2(2000),
    ADV_HANDLER_CFG_ID_ NVARCHAR2(64),
    TENANT_ID_ NVARCHAR2(255) DEFAULT '',
    CREATE_TIME_ TIMESTAMP(6),
    primary key (ID_)
);
