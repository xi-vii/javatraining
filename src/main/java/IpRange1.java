/*
Решение задачи с использованием библиотеки IPAddress
 */

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressSeqRange;
import inet.ipaddr.IPAddressString;
import java.math.BigInteger;


class IpAddressNew {
    String address;

    IpAddressNew(String address) {
        this.address = address;
    }

    // Метод проверяет IP на корректность ввода
    private boolean isIpCorrect(IPAddress addrr1, IPAddress addrr2) {

        if (addrr1 == null) {
            System.out.println("Первый IP записан неверно");
            return false;
        } else if(addrr2 == null){
            System.out.println("Второй IP записан неверно");
            return false;
        } else {
            return true;
        }
    }

    // Метод выводит все IP из заданного диапазона
    public void getIpRange(IpAddressNew otherAddress) {

        // Конвертируем строки в нормальные IP адреса
        IPAddress startIPAddress = new IPAddressString(this.address).getAddress();
        IPAddress endIPAddress = new IPAddressString(otherAddress.address).getAddress();

        // Проверяем, что оба IP адреса записаны верно (иначе их значение будет null)
        if(!this.isIpCorrect(startIPAddress, endIPAddress)){
            return;
        }

        // Получаем диапазон между заданными адресами
        IPAddressSeqRange ipRange = startIPAddress.toSequentialRange(endIPAddress);

        BigInteger totalIpInRange = ipRange.getCount(); // Общее кол-во адресов в диапазоне
        BigInteger i = new BigInteger("0");     // Номер выводимого адреса

        for (IPAddress addr : ipRange.getIterable()) {
            i = i.add(new BigInteger("1"));

            // Игнорируем 1 и последний адреса (по условию задачи). Остальные выводим
            if (totalIpInRange.compareTo(i) == 0 || i.compareTo(new BigInteger("1")) == 0) {
                continue;
            } else {
                System.out.println(addr);
            }
        }
    }
}


public class IpRange1 {

    public static void main(String[] args) {
        IpAddressNew address1 = new IpAddressNew("111.125.111.250");
        IpAddressNew address2 = new IpAddressNew("111.125.112.1");

        address1.getIpRange(address2);
    }
}
