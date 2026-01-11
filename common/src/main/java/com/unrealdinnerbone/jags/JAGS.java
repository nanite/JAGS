package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.resources.Identifier;

public class JAGS {

    public static final String MOD_ID = "jags";

    public static void init() {

    }

    public static Identifier rl(String path) {
        return IDUtils.id(MOD_ID, path);
    }
}