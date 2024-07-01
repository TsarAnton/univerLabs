public class Report {
    public static void doReport(double sum, double govTax, double bankComission, int numOut, int numIn) {
        System.out.println("Транзакция совершена:");
        System.out.println("    Номер отправителя - " + numOut);
        System.out.println("    Номер получателя - " + numIn);
        System.out.println("    Сумма - " + sum);
        System.out.println("    Коммиссия банку - " + bankComission);
        System.out.println("    Налог государству - " + govTax);
    }
}
