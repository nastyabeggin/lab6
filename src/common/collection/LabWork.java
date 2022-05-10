package common.collection;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Экземпляр лабораторной работы
 */
public class LabWork implements Serializable {

    private Scanner scanner = new Scanner(System.in);
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float minimalPoint; //Значение поля должно быть больше 0
    private long averagePoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле не может быть null

    /**
     * Конструктор лабы, когда даны все параметры
     *
     * @param id           Id лабы. Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     * @param name         Название лабы. Поле не может быть null, Строка не может быть пусто
     * @param coordinates  Координаты. Поле не может быть null
     * @param minimalPoint Минимальный балл. Поле не может быть null, Значение этого поля должно генерироваться автоматически
     * @param averagePoint Средний балл. Значение поля должно быть больше 0
     * @param difficulty   Сложность. Поле может быть null
     * @param discipline   Дисциплина. Поле не может быть null
     */
    public LabWork(long id,
                   String name,
                   Coordinates coordinates,
                   float minimalPoint,
                   long averagePoint,
                   String difficulty,
                   Discipline discipline) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.averagePoint = averagePoint;
        Difficulty tempDiff = Difficulty.VERY_EASY;
        tempDiff.setDifficultyOfLab(difficulty);
        this.difficulty = tempDiff;
        this.discipline = discipline;
    }

    /**
     * Создание лабы
     */
    public LabWork() {
        creationDate = LocalDate.now();
        this.name = enterName();
        this.coordinates = enterCoordinates();
        this.minimalPoint = enterMinimalPoint();
        this.averagePoint = enterAveragePoint();
        this.difficulty = enterDifficulty();
        this.discipline = enterDiscipline();
    }

    /**
     * Создание лабы с известным минимальным баллом
     */
    public LabWork(float minimalPoint) {
        creationDate = LocalDate.now();
        this.name = enterName();
        this.coordinates = enterCoordinates();
        this.minimalPoint = minimalPoint;
        this.averagePoint = enterAveragePoint();
        this.difficulty = enterDifficulty();
        this.discipline = enterDiscipline();
    }

    public LabWork(Long id) {
        creationDate = LocalDate.now();
        this.id = id;
        this.name = enterName();
        this.coordinates = enterCoordinates();
        this.minimalPoint = enterMinimalPoint();
        this.averagePoint = enterAveragePoint();
        this.difficulty = enterDifficulty();
    }

    /**
     * Создает id
     *
     * @return уникальный id
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Пользовательский ввод названия лабы
     *
     * @return название
     */
    private String enterName() {
        System.out.print("Введите название лабы: ");
        name = scanner.nextLine();
        while (name == null || name.equals("")) {
            System.out.println("Значение не может быть null или пустым, попробуйте ещё раз");
            name = scanner.nextLine();
        }
        return (name);
    }

    /**
     * Пользовательский ввод сложности
     *
     * @return название
     */
    private Difficulty enterDifficulty() {
        Difficulty tempDifficulty = Difficulty.VERY_EASY;
        Difficulty[] difficulties = tempDifficulty.getDeclaringClass().getEnumConstants();
        System.out.println("Введите сложность лабы, доступные варианты сложности " + Arrays.toString(difficulties) + " Или оставьте пустую строку");
        String userDifficulty = scanner.nextLine();
        while (!isInEnum(userDifficulty, Difficulty.class) && !userDifficulty.equals("")) {
            System.out.println("Значение не в списке сложностей, попробуйте ещё раз. Список: " + Arrays.toString(difficulties));
            userDifficulty = scanner.nextLine();
        }
        if (userDifficulty.equals("")) {
            return null;
        } else {
            tempDifficulty.setDifficultyOfLab(userDifficulty);
            return (tempDifficulty);
        }
    }

    /**
     * Пользовательский ввод названия координат
     *
     * @return координаты
     */
    private Coordinates enterCoordinates() {
        Long x = null;
        int y = 0;
        System.out.print("Введите координату Long x(>-980): ");
        while (true) {
            try {
                x = Long.parseLong(scanner.nextLine());
                if (x <= -980) throw new UnsupportedOperationException();
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите координату Long x(>-980): ");
            }
        }
        System.out.print("Введите координату int y: ");
        while (true) {
            try {
                y = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите координату int y: ");
            }
        }
        Coordinates tempCoordinates = new Coordinates(x, y);
        return (tempCoordinates);
    }

    /**
     * Пользовательский ввод минимального балла
     *
     * @return минимальный балл
     */
    private float enterMinimalPoint() {
        System.out.print("Введите минимальный балл (>0): ");
        float tempMinimalPoint = 0.0F;
        while (true) {
            try {
                tempMinimalPoint = Float.parseFloat(scanner.nextLine());
                if (tempMinimalPoint <= 0) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите минимальный балл (>0): ");
            }
        }
        return (tempMinimalPoint);
    }

    /**
     * Пользовательский ввод минимального балла
     *
     * @return минимальный балл
     */
    private long enterAveragePoint() {
        long tempAveragePoint = 0;
        System.out.print("Введите средний балл (>0): ");
        while (true) {
            try {
                tempAveragePoint = Long.parseLong(scanner.nextLine());
                if (tempAveragePoint <= 0) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите средний балл (>0): ");
            }
        }
        return tempAveragePoint;
    }

    /**
     * Пользовательский ввод дисциплины
     *
     * @return экземпляр класса Discipline
     */
    private Discipline enterDiscipline() {
        String name; //Поле не может быть null, Строка не может быть пустой
        long lectureHours = 0;
        int practiceHours = 0;
        Long labsCount = null; //Поле может быть null
        System.out.print("Введите название дисциплины(обязательное поле): ");
        while (true) {
            try {
                name = (scanner.nextLine());
                if (Objects.equals(name, "") || name == null) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите название дисциплины(обязательное поле): ");
            }
        }
        System.out.print("Введите количество лекционных часов по этой дисциплине: ");
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    System.out.println("Вы не ввели поле. Оно будет пустым");
                    break;
                }
                lectureHours = Long.parseLong(line);
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите количество лекционных часов по этой дисциплине(или оставьте пустую строку): ");
            }
        }
        System.out.print("Введите количество практических часов по этой дисциплине: ");
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    System.out.println("Вы не ввели поле. Оно будет пустым");
                    break;
                }
                ;
                practiceHours = Integer.parseInt(line);
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите количество практических часов по этой дисциплине(или оставьте пустую строку): ");
            }
        }
        System.out.print("Введите количество лаб этой дисциплине: ");
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    System.out.println("Вы не ввели поле. Оно будет пустым");
                    break;
                }
                labsCount = Long.parseLong(line);
                break;
            } catch (Exception e) {
                System.out.print("Значение некорректно. Введите количество лаб этой дисциплине(или оставьте пустую строку): ");
            }
        }
        Discipline tempDiscipline = new Discipline(name, lectureHours, practiceHours, labsCount);
        return tempDiscipline;
    }


    /**
     * геттер id
     *
     * @return возвращает id
     */
    public long getId() {
        return id;
    }

    public float getMinimalPoint() {
        return minimalPoint;
    }

    public long getAveragePoint() {
        return averagePoint;
    }

    /**
     * @return описание лабы
     */
    public String toPrintableString() {
        return ("Название лабы: " + name + "\nID: " + id + "\nДата создания объекта коллекции: "
                + creationDate + "\nCoordinates X: " + coordinates.getX() + "\nCoordinates Y: " + coordinates.getY()
                + "\nМинимальный балл: " + minimalPoint + "\nСредний балл: " + averagePoint +
                "\nСложность: " + difficulty + "\nДисциплина: " + discipline.getName() + "\nКоличество лекционных часов по дисциплине: "
                + discipline.getLectureHours() + "\nКоличество практических часов по дисциплине: "
                + discipline.getPracticeHours() + "\nКоличество лаб по дисциплине: " + discipline.getLabsCount());
    }

    /**
     * Сравнивает две лабы по id
     *
     * @param l2 вторая лаба
     * @return результат сравнения айдишников
     */
    public int compare(LabWork l2) {
        return (int) (this.id - l2.getId());
    }

    public String[] getAll() {
        String st_discipline = "";
        if (difficulty == null) {
            st_discipline = null;
        } else {
            st_discipline = difficulty.getDifficultyOfLab();
        }
        return new String[]{String.valueOf(this.id), String.valueOf(this.name), String.valueOf(this.coordinates.getX()),
                String.valueOf(this.coordinates.getY()),
                String.valueOf(this.creationDate), String.valueOf(this.minimalPoint), String.valueOf(this.averagePoint),
                st_discipline, discipline.getName(), String.valueOf(discipline.getLectureHours()),
                String.valueOf(discipline.getPracticeHours()), String.valueOf(discipline.getLabsCount())};
    }

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}