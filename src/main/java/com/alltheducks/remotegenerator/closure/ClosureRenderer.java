package com.alltheducks.remotegenerator.closure;

import com.alltheducks.remotegenerator.soy.SoyModelRenderer;

public class ClosureRenderer extends SoyModelRenderer {

    @Override
    public String getSoyFilePath() {
        return "templates/closure.soy";
    }

}
