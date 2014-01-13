package com.alltheducks.remotegenerator.renderer;

import com.alltheducks.remotegenerator.model.ConvertedModel;

public interface ModelRenderer {

    public void render(ConvertedModel convertedModel, Appendable out);

}
