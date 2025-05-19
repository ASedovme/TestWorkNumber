package me.asedov.TestWorkNumber.service.impl;

import me.asedov.TestWorkNumber.service.NumberFinderService;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Реализация сервиса для поиска N-го минимального простого числа в Excel файле.
 */
@Service
public class NumberFinderServiceImpl implements NumberFinderService {

    /**
     * Находит N-ое минимальное простое число в Excel файле по указанному пути.
     *
     * @param filePath путь к XLSX файлу, содержащему простые числа в первом столбце.
     * @param N        порядковый номер минимального числа, которое нужно найти (1-ое минимальное, 2-ое и т.д.).
     * @return         N-ое минимальное число из файла.
     * @throws Exception если возникнут ошибки при чтении файла или если N некорректен.
     */
    @Override
    public int findNthMinimalNumber(String filePath, int N) throws Exception {
        int[] numbers = readNumbersFromXLSX(filePath);
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Файл не содержит чисел");
        }
        if (N <= 0 || N > numbers.length) {
            throw new IllegalArgumentException("N должно быть в диапазоне от 1 до " + numbers.length);
        }
        return quickSelect(numbers, 0, numbers.length - 1, N - 1);
    }

    /**
     * Читает числа из первого столбца Excel файла (.xlsx).
     *
     * @param filePath путь к файлу Excel.
     * @return         массив чисел, считанных из файла.
     * @throws IOException если возникнут ошибки при чтении файла.
     */
    private int[] readNumbersFromXLSX(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int size = sheet.getPhysicalNumberOfRows();
            int[] nums = new int[size];
            int index = 0;

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    nums[index++] = (int) cell.getNumericCellValue();
                }
                // так как предполагается только простые числа, дополнительных проверок не нужно
                if (index >= size) break;
            }

            if (index != size) {
                int[] trimmed = new int[index];
                System.arraycopy(nums, 0, trimmed, 0, index);
                return trimmed;
            }
            return nums;
        }
    }

    /**
     * Быстрый выбор (Quickselect) для поиска N-го минимального элемента массива.
     *
     * @param arr      массив чисел.
     * @param left     левый индекс подмассива для обработки.
     * @param right    правый индекс подмассива для обработки.
     * @param nIndex   индекс искомого элемента в отсортированном массиве (нумерация с нуля).
     * @return         значение N-го минимального элемента.
     */
    private int quickSelect(int[] arr, int left, int right, int nIndex) {
        if (left == right) { // один элемент
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);

        if (nIndex == pivotIndex) {
            return arr[nIndex];
        } else if (nIndex < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, nIndex);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, nIndex);
        }
    }

    /**
     * Разделение массива по опорному элементу для алгоритма Quickselect.
     *
     * @param arr   массив чисел.
     * @param left  левый индекс сегмента массива.
     * @param right правый индекс сегмента массива.
     * @return      индекс опорного элемента после разделения.
     */
    private int partition(int[] arr, int left, int right) {
        int pivotValue = arr[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] <= pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    /**
     * Обмен значений элементов массива по индексам i и j.
     *
     * @param arr массив элементов.
     * @param i   индекс первого элемента.
     * @param j   индекс второго элемента.
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}