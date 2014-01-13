package com.alltheducks.remotegenerator.renderer;

import com.alltheducks.remotegenerator.model.ConvertedModel;

import java.io.OutputStream;

public interface ModelRenderer {

    public void render(ConvertedModel convertedModel, Appendable out);

}
