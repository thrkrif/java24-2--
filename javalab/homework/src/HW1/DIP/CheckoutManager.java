package HW1.DIP;

import java.util.Map;
import java.util.HashMap;

public class CheckoutManager{
    private Map<LibraryItem,Boolean> checkoutStatus;

    public CheckoutManager(){
        this.checkoutStatus = new HashMap<>();
    }

    public void checkOut(LibraryItem item){
        checkoutStatus.put(item, true);
    }

    public void returnItem(LibraryItem item){
        checkoutStatus.put(item, false);
    }

    public Boolean isCheckedOut(LibraryItem item){
        return checkoutStatus.getOrDefault(item, false);
    }



}
