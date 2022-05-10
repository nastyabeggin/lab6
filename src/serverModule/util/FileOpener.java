package serverModule.util;

import common.collection.Coordinates;
import common.collection.Discipline;
import common.collection.LabWork;
import serverModule.collection.*;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.*;

/**
 * Открывает файл
 */
public class FileOpener {
    private static final String DELIMETER = ",";

    /**
     * Открывает файл и заполняет коллекцию его значениями. Также происходит сортировка
     *
     * @param filename   путь к файлу
     * @param collection коллекция, где хранятся элементы
     * @throws FileNotFoundException исключение, когда файл не найден
     * @throws AccessDeniedException исключение, когда к файлу нет доступа
     */
    public void processInputFile(String filename, CollectionManager collection) throws FileNotFoundException, AccessDeniedException {
        try {
            File file = new File(filename);
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.canRead()) throw new AccessDeniedException("Нет доступа");
            InputStream input = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line = "";
            String[] tempArr;

            while ((line = br.readLine()) != null) {
                tempArr = line.split(DELIMETER);
                LabWork labWork = new LabWork(Long.parseLong(tempArr[0]),
                        tempArr[1], new Coordinates(Long.parseLong(tempArr[2]), Integer.parseInt(tempArr[3])),
                        Float.parseFloat(tempArr[4]), Long.parseLong(tempArr[5]), tempArr[6],
                        new Discipline(tempArr[7], Long.parseLong(tempArr[8]), Integer.parseInt(tempArr[9]),
                                Long.parseLong(tempArr[10]))) ;
                collection.add(labWork);
            }
            collection.sort();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь и наличие файла");
        } catch (
                AccessDeniedException e) {
            System.out.println("Доступ к файлу запрещен");
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        } catch (
                NoSuchElementException e) {
            System.out.println("Ошибка в синтаксисе файла");
        }
    }
}

