package jim.android.utils;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class BasketItemMsg {

    private int imageId;
    private String clothesName;
    private int account;
    private int price;

    public BasketItemMsg(int imageId, String clothesName, int account, int price) {
        this.imageId = imageId;
        this.clothesName = clothesName;
        this.account = account;
        this.price = price;
    }

    public BasketItemMsg(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
