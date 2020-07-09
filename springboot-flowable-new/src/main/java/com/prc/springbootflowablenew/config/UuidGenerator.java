package com.prc.springbootflowablenew.config;

import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;

public class UuidGenerator extends StrongUuidGenerator {

    @Override
    public String getNextId() {
        String uuid = super.getNextId();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

}
