package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.soy.SoyModelRenderer;

public class ClosureRenderer extends SoyModelRenderer {

    @Override
    public String getSoyFilePath() {
        return "templates/closure.soy";
    }

}
