package src;

public class Seller extends Person {

    public Seller(int[] array_B, int n){
        super(array_B, n);
    }

    @Override
    public void changeMoneyToPay(int j, int tmp){
        for(int i = 0; i < this.money_to_pay[j].length; i++){
            if(this.money_to_pay[j][i] != 0) {
                if(i == tmp){
                    this.money_to_pay[j - tmp][i] += 1;
                }
                else{
                    this.money_to_pay[j - tmp][i] = this.money_to_pay[j][i];
                }
            }
        }
    }
    
}
