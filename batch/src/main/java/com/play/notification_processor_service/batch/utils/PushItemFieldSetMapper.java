package com.play.notification_processor_service.batch.utils;

import com.play.notification_processor_service.common.dto.PushItemDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by Ext_IdanF on 30/12/2014.
 */
public class PushItemFieldSetMapper implements FieldSetMapper<PushItemDTO> {


    @Override
    public PushItemDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        PushItemDTO pushItemDTO = new PushItemDTO();
        pushItemDTO.setSnid(fieldSet.readString("user_sn_id"));
        return pushItemDTO;
    }
}