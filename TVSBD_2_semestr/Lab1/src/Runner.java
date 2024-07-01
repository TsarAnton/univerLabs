//в папке lab1 создать файлы bank.txt и tax.txt
import java.util.ArrayList;
import java.util.Scanner;

import file.MyFile;
import transaction.Transaction;
import transaction.TransactionBankComission;
import transaction.TransactionGovTax;
import transaction.TransactionGovTaxBankComission;
import file.ClientParser;
import file.ClientRecord;

public class Runner {
    public static void main(String[] args) {

        ArrayList<ClientRecord> data = ClientParser.ClientStringToArray(MyFile.readFile("clients.csv"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер счета отправителя:");
        int numOut = scanner.nextInt();
        if(numOut >= data.size() || numOut < 0) {
            System.out.println("Ошибка: такого клиента");
            System.exit(1);
        }
        System.out.println("Введите номер счета получателя:");
        int numIn = scanner.nextInt();
        if(numIn >= data.size() || numIn < 0) {
            System.out.println("Ошибка: такого клиента");
            System.exit(1);
        }

        String typeOut = data.get(numOut).getType();
        String typeIn = data.get(numIn).getType();

        System.out.println("Введите сумму перевода:");
        double sum = scanner.nextDouble();
        boolean res;

        if(typeOut.equals("phys") && typeIn.equals("phys")) {
            if(sum < 1000000000) {
                Transaction transaction1 = new Transaction();
                res = transaction1.transaction(sum, numOut, numIn, data);
                if(res) {
                    Report.doReport(sum, 0, 0, numOut, numIn);
                    HistoryReport.doHistoryReport(numOut, numIn, sum, 0, 0);
                    MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                }
            } else if(sum < 1E10) {
                TransactionBankComission transaction2 = new TransactionBankComission();
                double bankComission = transaction2.bankComission(sum, 2);
                res = transaction2.transaction(sum, numOut, numIn, data);
                if(res) {
                    Report.doReport(sum, 0, bankComission, numOut, numIn);
                    HistoryReport.doHistoryReport(numOut, numIn, sum, 0, bankComission);
                    MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                    double bankMoney = Double.parseDouble(MyFile.readFile("bank.txt"));
                    MyFile.writeFile(Double.toString(bankMoney + bankComission), "bank.txt");
                }
            } else {
                TransactionGovTaxBankComission transaction3 = new TransactionGovTaxBankComission();
                double bankComission = transaction3.bankComission(sum, 5);
                double govTax = transaction3.govTax(sum, 15);
                res = transaction3.transaction(sum, numOut, numIn, data);
                if(res) {
                    Report.doReport(sum, govTax, bankComission, numOut, numIn);
                    HistoryReport.doHistoryReport(numOut, numIn, sum, govTax, bankComission);
                    MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                    double bankMoney = Double.parseDouble(MyFile.readFile("bank.txt"));
                    MyFile.writeFile(Double.toString(bankMoney + bankComission), "bank.txt");
                    double taxMoney = Double.parseDouble(MyFile.readFile("tax.txt"));
                    MyFile.writeFile(Double.toString(taxMoney + govTax), "tax.txt");
                }
            }
        } else if(typeOut.equals("phys") && typeIn.equals("ur")) {
            TransactionBankComission transaction4 = new TransactionBankComission();
            double bankComission = transaction4.bankComission(sum, 10);
            res = transaction4.transaction(sum, numOut, numIn, data);
            if(res) {
                Report.doReport(sum, 0, bankComission, numOut, numIn);
                HistoryReport.doHistoryReport(numOut, numIn, sum, 0, bankComission);
                MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                double bankMoney = Double.parseDouble(MyFile.readFile("bank.txt"));
                MyFile.writeFile(Double.toString(bankMoney + bankComission), "bank.txt");
            }
        } else if(typeOut.equals("ur") && typeIn.equals("phys")) {
            TransactionGovTaxBankComission transaction5 = new TransactionGovTaxBankComission();
            double bankComission = transaction5.bankComission(sum, 2);
            double govTax = transaction5.govTax(sum, 5);
            res = transaction5.transaction(sum, numOut, numIn, data);
            if(res) {
                Report.doReport(sum, govTax, bankComission, numOut, numIn);
                HistoryReport.doHistoryReport(numOut, numIn, sum, govTax, bankComission);
                MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                double bankMoney = Double.parseDouble(MyFile.readFile("bank.txt"));
                MyFile.writeFile(Double.toString(bankMoney + bankComission), "bank.txt");
                double taxMoney = Double.parseDouble(MyFile.readFile("tax.txt"));
                MyFile.writeFile(Double.toString(taxMoney + govTax), "tax.txt");
            }
        } else {
            TransactionGovTax transaction6 = new TransactionGovTax();
            double govTax = transaction6.govTax(sum, 20);
            res = transaction6.transaction(sum, numOut, numIn, data);
            if(res) {
                Report.doReport(sum, govTax, 0, numOut, numIn);
                HistoryReport.doHistoryReport(numOut, numIn, sum, govTax, 0);
                MyFile.writeFile(ClientParser.ClientArrayToString(data), "clients.csv");
                double taxMoney = Double.parseDouble(MyFile.readFile("tax.txt"));
                MyFile.writeFile(Double.toString(taxMoney + govTax), "tax.txt");
            }
        }
        if(!res) {
            System.out.println("Ошибка перевода");
        }
        scanner.close();
    }
}