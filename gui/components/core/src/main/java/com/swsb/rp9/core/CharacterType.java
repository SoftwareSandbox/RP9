package com.swsb.rp9.core;

public enum CharacterType {
    MERCENARY("/com/swsb/rp9/overworld/sprites/hero/Oratio-the-Mercenary.png", "/com/swsb/rp9/fight/sprites/hero/Oratio-the-Mercenary.png"),
    BARD("/com/swsb/rp9/overworld/sprites/hero/Ouzo-the-Wolf-Bard.png", "/com/swsb/rp9/fight/sprites/hero/Ouzo-the-Wolf-Bard.png"),
    SAGE("/com/swsb/rp9/overworld/sprites/hero/Prime-the-Great-Sage.png", "/com/swsb/rp9/fight/sprites/hero/Prime-the-Great-Sage.png");

    private String urlToTextureImage;
    private String fightScreenUrl;

    CharacterType(String urlToTextureImage, String fightScreenUrl) {
        this.urlToTextureImage = urlToTextureImage;
        this.fightScreenUrl = fightScreenUrl;
    }

    public String getUrlToTextureImage() {
        return urlToTextureImage;
    }

    public String getFightScreenUrl() {
        return fightScreenUrl;
    }
}
