package project.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Вспомогательный класс для чтения файлов пропертис
 */
public class PropertiesReader {

    /**
     * Инстанс класса
     */
    private static PropertiesReader instance;

    /**
     * Инпутстрим для чтения пропертис
     */
    private FileInputStream fis;

    /**
     * Значения из файла пропертис
     */
    private final Properties properties = new Properties();

    /**
     * Конструктор класса
     * @throws IOException если файл конфигурации не найден
     */
    private PropertiesReader() throws IOException {
        try {
            fis = new FileInputStream("src/test/resources/conf.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }

    /**
     * Геттер инстанса PropertiesReader
     * @return инстанс PropertiesReader
     * @throws IOException если файл конфигурации не найден
     */
    private static PropertiesReader getInstance() throws IOException {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    /**
     * Метод получения значения проперти
     * @param propertyName имя проперти
     * @return значение проперти
     * @throws IOException если файл конфигурации не найден
     */
    public static String getProperty(String propertyName) throws IOException {
        return getInstance().properties.getProperty(propertyName);
    }
}