package com.swsb.rp9.core;

public enum CharacterType {
    MERCENARY("/com/swsb/rp9/overworld/sprites/hero/Oratio-the-Mercenary.png"),
    BARD("/com/swsb/rp9/overworld/sprites/hero/Ouzo-the-Wolf-Bard.png"),
    SAGE("/com/swsb/rp9/overworld/sprites/hero/Prime-the-Great-Sage.png");

    private String urlToTextureImage;

    CharacterType(String urlToTextureImage) {
        this.urlToTextureImage = urlToTextureImage;
    }

    public String getUrlToTextureImage() {
        return urlToTextureImage;
    }
}
