package jim.android.utils;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class PieceItemMsg {
    private int imageId;
    private int price;
    private String clothesName;

    public PieceItemMsg(int imageId, String clothesName, int price) {
        this.imageId = imageId;
        this.clothesName = clothesName;
        this.price = price;
    }
    public PieceItemMsg(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }
}
