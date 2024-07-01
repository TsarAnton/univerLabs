package src;

public class Buyer extends Person{

    private int[] possible_price;
    private boolean[] possible_price_checked;

    public Buyer(int[] money, int n){
        super(money, n);
        this.possible_price = new int[n + 1];
        this.possible_price_checked = new boolean[n + 1];
    }

    @Override
    public void changeMoneyToPay(int j, int tmp){
        for(int i = 0; i < this.money_to_pay[j].length; i++){
            if(this.money_to_pay[j][i] != 0){
                this.money_to_pay[j - tmp][i] = this.money_to_pay[j][i];
            }
        }
    }

    public void savePossiblePrice(int index){
        this.possible_price[index] = 1;
        this.possible_price_checked[index] = true;
    }

    public boolean isPossiblePriceChecked(int index){
        return this.possible_price_checked[index];
    }
    
    public boolean isPossiblePriceSaved(int index){
        if(this.possible_price[index] == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
