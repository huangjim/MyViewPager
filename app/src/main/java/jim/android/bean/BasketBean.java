package jim.android.bean;

import java.io.Serializable;

/**
 * Created by Jim Huang on 2015/9/8.
 */
public class BasketBean implements Serializable{

    private String clothesName;
    private int accoumt;
    private int price;
    private String image;

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public int getAccoumt() {
        return accoumt;
    }

    public void setAccoumt(int accoumt) {
        this.accoumt = accoumt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
