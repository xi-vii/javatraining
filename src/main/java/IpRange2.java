/*
Решение задачи без использования сторонних библиотек
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Класс сделан для вывода двух чисел из метода getNextSectorMinMax
class Num {
    int min;
    int max;


    public Num(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

class IpAddress2 {
    private final String address;

    IpAddress2(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    // Метод проверки правильности чисел в каждом октете IP
    private static boolean ifAddressCorrect(String ipAddress) {
        String[] addressArray = ipAddress.split("\\.");
        boolean addressCorrect = true;

        for (int i = 0; i < addressArray.length; i++) {
            int sectorValue = Integer.parseInt(addressArray[i]); // Значение проверяемого октета
            if (sectorValue >= 256 || sectorValue < 0) {
                addressCorrect = false;
                System.out.println(ipAddress + " - введён несуществующий IP");
                return addressCorrect;
            }
        }
        return addressCorrect;
    }

    // Метод сравнивает два IP, определяя мин. и макс.
    static private String[][] getMinMaxIp(String firstIp, String secondIp) {

        // Делаем массивы из строк
        String[] array1 = firstIp.split("\\.");
        String[] array2 = secondIp.split("\\.");
        String[] arrayMax = array2;
        String[] arrayMin = array1;
        String[][] arrayTotal = new String[2][];

        // Сравниваем значения по октетам пока не найдём неравные
        for (int i = 0; i < 4; i++) {
            int value1 = Integer.parseInt(array1[i]);   // Значение октета 1-го IP-адреса
            int value2 = Integer.parseInt(array2[i]);   // Значение октета 2-го IP-адреса

            if (value1 != value2) {
                if (value1 > value2) {
                    arrayMax = array1;
                    arrayMin = array2;
                }
                break;
            }
        }
        arrayTotal[0] = arrayMin;
        arrayTotal[1] = arrayMax;
        return arrayTotal;
    }

    // Метод формирует словарь вида <<сектор№ : {знач.минимального IP, знач.максимального IP}>>
    static private HashMap<String, ArrayList<Integer>> getSectorValues(String[] arrayMin, String[] arrayMax) {
        HashMap<String, ArrayList<Integer>> sectorValues = new HashMap<>();

        // Проходим октету каждого IP и добавляем значение в словарь
        for (int i = 0; i < 4; i++) {
            String key = "sector" + i;
            int valueMin = Integer.parseInt(arrayMin[i]);
            int valueMax = Integer.parseInt(arrayMax[i]);

            // Исключаем отображение первого и последнего IP диапазона (по условию задачи)
            if (i == 3) {
                valueMin++;
                valueMax--;
                if (valueMax < 0) {
                    valueMax = 0;
                }
            }
            ArrayList<Integer> value = new ArrayList<>(List.of(valueMin, valueMax));
            sectorValues.put(key, value);   // Добавляем в словарь
        }
        return sectorValues;
    }

    // Метод для определения минимального и масимального значений следующего октета(сектора) IP-адреса
    static private Num getNextSectorMinMax(int index, String currentSector, String nextSector,
                                           HashMap<String, ArrayList<Integer>> sectorData) {
        int min = 0;
        int max = 255;
        int currentSectorMinValue = sectorData.get(currentSector).get(0);   // Мин. значение тек. сектора
        int currentSectorMaxValue = sectorData.get(currentSector).get(1); // Макс. значение тек. сектора
        int nextSectorMinValue = sectorData.get(nextSector).get(0); // Мин. значение следующего сектора
        int nextSectorMaxValue = sectorData.get(nextSector).get(1); // Макс. значение следующего сектора

        // В зависимости от положения index относительно крайних значений сектора определяем промежуток
        // значений следующего сектора
        if (index == currentSectorMinValue && index == currentSectorMaxValue) {
            min = nextSectorMinValue;
            max = nextSectorMaxValue;
        } else if (index == currentSectorMinValue && index != currentSectorMaxValue) {
            min = nextSectorMinValue;
        } else if (index != currentSectorMinValue && index == currentSectorMaxValue) {
            max = nextSectorMaxValue;
        }
        return new Num(min, max);   // Возвращает минимальное и максимальное значение октета
    }

    // Метод выводит все IP из заданного диапазона
    static public void getIpRange2(String firstIp, String secondIp) {

        // Проверяем, что IP записан верно
        if (!ifAddressCorrect(firstIp) || !ifAddressCorrect(secondIp)) {
            return;
        }

        String[] arrayMin = getMinMaxIp(firstIp, secondIp)[0];  // Получаем минимальный IP адрес
        String[] arrayMax = getMinMaxIp(firstIp, secondIp)[1];  // Получаем максимальный IP адрес

        HashMap<String, ArrayList<Integer>> sectorData = getSectorValues(arrayMin, arrayMax);   // Cловарь макс. и мин. значений секторов (<<сектор№ : {знач.минимального IP, знач.максимального IP}>>)

        // Проходим по каждому октету, вычисляя его значение, потом выдавая итоговый IP
        for (int a = sectorData.get("sector0").get(0); a <= sectorData.get("sector0").get(1); a++) {
            Num resultMinMax = getNextSectorMinMax(a, "sector0", "sector1", sectorData);
            int minB = resultMinMax.getMin();
            int maxB = resultMinMax.getMax();

            for (int b = minB; b <= maxB; b++) {
                resultMinMax = getNextSectorMinMax(b, "sector1", "sector2", sectorData);
                int minC = resultMinMax.getMin();
                int maxC = resultMinMax.getMax();

                for (int c = minC; c <= maxC; c++) {
                    resultMinMax = getNextSectorMinMax(c, "sector2", "sector3", sectorData);
                    int minD = resultMinMax.getMin();
                    int maxD = resultMinMax.getMax();

                    for (int d = minD; d <= maxD; d++)
                        System.out.println(String.valueOf(a) + "." + String.valueOf(b) + "." + String.valueOf(c)
                                + "." + String.valueOf(d));
                }
            }
        }
    }
}


public class IpRange2 {

    public static void main(String[] args) {
        IpAddress2 address1 = new IpAddress2("120.114.255.255");
        IpAddress2 address2 = new IpAddress2("121.114.254.245");

        IpAddress2.getIpRange2(address1.getAddress(), address2.getAddress());
    }
}
