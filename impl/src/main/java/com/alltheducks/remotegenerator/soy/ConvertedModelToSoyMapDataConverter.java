package com.alltheducks.remotegenerator.soy;

import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.util.Collections;
import java.util.Set;

public class ConvertedModelToSoyMapDataConverter {

    public SoyMapData convert(ConvertedModel convertedModel) {
        SoyMapData soyMapData = new SoyMapData();

        soyMapData.put("name", convertedModel.getName());
        soyMapData.put("fields", convert(convertedModel.getConvertedFields()));
        soyMapData.put("requires", convertedModel.getRequires());
        soyMapData.put("genericParameters", convertedModel.getGenericParameters());
        soyMapData.put("originalName", convertedModel.getOriginalName());

        return soyMapData;

    }

    public SoyMapData convert(ConvertedField convertedField) {
        SoyMapData soyMapData = new SoyMapData();

        soyMapData.put("name", convertedField.getName());
        soyMapData.put("type", convert(convertedField.getType()));

        return soyMapData;
    }

    public SoyListData convert(Set<ConvertedField> convertedFields) {
        SoyListData soyListData = new SoyListData();

        for(ConvertedField field : convertedFields) {
            soyListData.add(convert(field));
        }

        return soyListData;
    }

    public SoyMapData convert(ConvertedType convertedType) {
        SoyMapData soyMapData = new SoyMapData();

        soyMapData.put("name", convertedType.getName());
        soyMapData.put("requires", convertedType.getRequires());

        return soyMapData;
    }

}
