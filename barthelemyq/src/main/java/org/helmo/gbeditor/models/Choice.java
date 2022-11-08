package org.helmo.gbeditor.models;

import org.helmo.gbeditor.utils.InputUtil;

public class Choice {

    private String text;

    private Page ref;

    public Choice(String text, Page ref) {
        if(InputUtil.isEmptyOrBlank(text)) {
            //TODO throw error
        } else if (ref == null) {
            //todo throw err
        }

        this.text = text;
        this.ref = ref;
    }

    public String getText() {
        return text;
    }

    public Page getRef() {
        return ref;
    }
}
