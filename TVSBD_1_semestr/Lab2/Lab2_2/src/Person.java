package src;

public class Person {
    protected int[] money;
    protected int[][] money_to_pay;

    public Person(int[] money, int n){
        this.money = new int[money.length];
        for(int i = 0; i < this.money.length; i++){
            this.money[i] = money[i];
        }

        this.money_to_pay = new int[n + 1][];
        for(int i = 0; i < this.money_to_pay.length; i++){
            this.money_to_pay[i] = new int[n + 1];
        }
    }

    public int[] getMoney(){
        return this.money;
    }

    public void saveMoneyToPay(int i, int j){
        this.money_to_pay[i][j] += 1;
    }

    public void clearMoneyToPay(int nominal){
        for(int i = 0; i < this.money_to_pay[nominal].length; i++){
            this.money_to_pay[nominal][i] = 0;
        }
    }

    public boolean isClearMoneyToPay(int nominal){
        boolean result = true;
        for(int i = 0; i < this.money_to_pay[nominal].length && result; i++){
            if(money_to_pay[nominal][i] != 0){
                result = false;
            }
        }
        return result;
    }

    public int[] getMoneyToPay(int S) {

		int n = 0;
        int index = 0;

        for(int i = 0; i < this.money_to_pay[S].length; i++){
            if(this.money_to_pay[S][i] != 0){
                    n += this.money_to_pay[S][i];
            }
        }

        int[] result_array = new int[n];
        for(int i = 0; i < this.money_to_pay[S].length; i++){
            if(this.money_to_pay[S][i] != 0){
                for(int j = 0; j < this.money_to_pay[S][i]; j++){
                    result_array[index] = i;
                    index++;
                }
            }
        }
        
        return result_array;
	}

    public void changeMoneyToPay(int j, int tmp){};
}
