package com.example.fw5_nmf;

public class GalleryItem {
    private int imageResId;
    private int itemType;

    public GalleryItem(int imageResId, int itemType) {
        this.imageResId = imageResId;
        this.itemType = itemType;
    }

    public int getImageResId(){
        return imageResId;
    }

    public int getItemType(){
        return itemType;
    }

//    public boolean isHeader(){
//        return itemType == ItemType.HEADER;
//    }
}
