package src;

import java.util.Arrays;
import java.util.Collections;

public class Runner {
	public static void main(String[] args) {

		int S = Integer.parseInt(args[0]);

		int max_price = 0;

        int n = Integer.parseInt(args[1]);
        int array_A[] = new int[n];
		for(int i = 0 ; i < array_A.length; i++) {
			array_A[i] = Integer.parseInt(args[i + 2]);
			max_price += array_A[i];
		}

		Arrays.sort(array_A);

		if(S <= max_price || S < 0){

			int m = Integer.parseInt(args[array_A.length + 2]);
			Integer array_B[] = new Integer[m];
			for(int i = 0; i< array_B.length; i++) {
				array_B[i] = Integer.parseInt(args[i + array_A.length + 3]);
			}

			Arrays.sort(array_B, Collections.reverseOrder());

			int[] array_B_buff = new int[array_B.length];
			for( int i = 0; i < array_B_buff.length; i++){
				array_B_buff[i] = array_B[i];
			}

			Buyer buyer = new Buyer(array_A, max_price);
			Seller seller = new Seller(array_B_buff, max_price);

			for(int i = 0; i < buyer.getMoney().length; i++){
				for(int j = max_price - 1; j >= 0; j--){
					int tmp = j + buyer.getMoney()[i];

					if(buyer.isPossiblePriceSaved(j) && tmp <= max_price){
						buyer.savePossiblePrice(tmp);

						buyer.saveMoneyToPay(tmp, j);
						buyer.saveMoneyToPay(tmp, buyer.getMoney()[i]);
					}

					if(buyer.isPossiblePriceChecked(S)){
						break;
					}

				}

				buyer.savePossiblePrice(buyer.getMoney()[i]);

				//if(!buyer.isClearMoneyToPay(buyer.getMoney()[i])){
				//	buyer.clearMoneyToPay(buyer.getMoney()[i]);
				//}

				buyer.saveMoneyToPay(buyer.getMoney()[i], buyer.getMoney()[i]);

				if(buyer.isPossiblePriceChecked(S)){
					break;
				}
			}

			for(int i = 0; i < seller.getMoney().length; i++){
				for(int j = 0; j <= max_price; j++){
					int tmp = j - seller.getMoney()[i];

					if(buyer.isPossiblePriceSaved(j) && tmp >= 0 && !buyer.isPossiblePriceChecked(tmp)){
						buyer.savePossiblePrice(tmp);

						seller.saveMoneyToPay(tmp, seller.getMoney()[i]);

						buyer.changeMoneyToPay(j, seller.getMoney()[i]);
						seller.changeMoneyToPay(j, seller.getMoney()[i]);
					}

					if(buyer.isPossiblePriceChecked(S)){
						break;
					}
				}

				if(buyer.isPossiblePriceChecked(S)){
					break;
				}
			}

			if(!buyer.isPossiblePriceSaved(S)){
				System.out.println("Impossible");
			}
			else{
				System.out.println("Possible");

				int buyer_money[] = buyer.getMoneyToPay(S);
				int seller_money[] = seller.getMoneyToPay(S);

				System.out.println("Buyer money:");
				for(int i = 0;i < buyer_money.length; i++){
					System.out.println(buyer_money[i]);
				}
				
				System.out.println("Seller money:");
				for(int i = 0;i < seller_money.length; i++){
					System.out.println(seller_money[i]);
				}
			}
		}
		else{
			System.out.println("Impossible");
		}
	}
}