package com.alltheducks.javamodeltoclosure.soy;

import com.alltheducks.javamodeltoclosure.model.ConvertedModel;
import com.alltheducks.javamodeltoclosure.renderer.ModelRenderer;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;

import java.net.URL;

public abstract class SoyModelRenderer implements ModelRenderer {

    public static final String TEMPLATE_NAMESPACE = "com.alltheducks.javamodeltoclosure";
    public static final String CONVERTED_MODEL_TEMPLATE_NAME = "convertedModel";

    public abstract String getSoyFilePath();

    @Override
    public void render(ConvertedModel convertedModel, Appendable out) {


        URL soyTemplateFile = this.getClass().getClassLoader().getResource(this.getSoyFilePath());
        SoyFileSet sfs = new SoyFileSet.Builder().add(soyTemplateFile).build();
        SoyTofu tofu = sfs.compileToTofu();
        SoyTofu.Renderer soyRenderer = tofu.newRenderer(TEMPLATE_NAMESPACE + '.' + CONVERTED_MODEL_TEMPLATE_NAME);

        ConvertedModelToSoyMapDataConverter convertedModelToSoyMapDataConverter = new ConvertedModelToSoyMapDataConverter();
        SoyMapData data = convertedModelToSoyMapDataConverter.convert(convertedModel);
        soyRenderer.setData(data);

        soyRenderer.render(out);

    }

}
