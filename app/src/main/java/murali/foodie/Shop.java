package murali.foodie;

import static android.R.attr.id;

/**
 * Created by Student on 11-04-2017.
 */

public class Shop {
    private int itemid;
    private String item;
    private int price;
public Shop()
        {

        }
    public Shop(int itemid,String item,int price)
    {
        this.itemid=itemid;
        this.item=item;
        this.price=price;
    }
    public Shop(String item,int price)
    {

        this.item=item;
        this.price=price;
    }
    public void setItemid(int itemid) {
        this.itemid = itemid;
    }
    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getItemId() {
        return itemid;
    }
    public int getPrice() {
        return price;
    }
    public String getItem() {
        return item;
    }
}