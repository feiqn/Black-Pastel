package com.feiqn.blackpastel.dialog;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class DialogTree {

    private final HashMap<String, Array<DialogFrame>> scripts;

    public DialogTree() {
        scripts = new HashMap<>();
        scripts.put("main", new Array<DialogFrame>());
    }

    public void addOnBranch(String key, DialogFrame frame) {
        if(!scripts.containsKey(key)) {
            scripts.put(key, new Array<DialogFrame>());
        }
        scripts.get(key).add(frame);
    }

    public Array<DialogFrame> getBranch(String key) {
        return scripts.get(key);
    }
}
