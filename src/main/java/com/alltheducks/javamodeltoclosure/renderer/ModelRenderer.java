package com.alltheducks.javamodeltoclosure.renderer;

import com.alltheducks.javamodeltoclosure.model.ConvertedModel;

import java.io.OutputStream;

public interface ModelRenderer {

    public void render(ConvertedModel convertedModel, Appendable out);

}
