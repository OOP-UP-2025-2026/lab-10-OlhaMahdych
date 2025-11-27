package ua.opnu;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Клас з простими завданнями (EasyTasks).
 * Усі завдання реалізовані з використанням Streams API, як того вимагає умова лабораторної.
 */
public class EasyTasks {

    public static void main(String[] args) {
        // Тут можна вручну запускати методи для локального тестування
    }

    /**
     * Завдання 1.
     * Подвоїти кожне значення зі списку чисел.
     * Використовуємо stream().map(...) та збір у список.
     *
     * @param nums вхідний список цілих чисел
     * @return новий список, де кожне число помножене на 2
     */
    public List<Integer> doubling(List<Integer> nums) {
        if (nums == null) return List.of();
        return nums.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 2.
     * Піднести кожне число до квадрата.
     *
     * @param nums вхідний список цілих чисел
     * @return новий список зі значеннями у квадраті
     */
    public List<Integer> square(List<Integer> nums) {
        if (nums == null) return List.of();
        return nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 3.
     * Додати символ 'y' на початок і кінець кожного рядка.
     *
     * @param strings вхідний список рядків
     * @return новий список з обрамленими рядками
     */
    public List<String> moreY(List<String> strings) {
        if (strings == null) return List.of();
        return strings.stream()
                .map(s -> "y" + s + "y")
                .collect(Collectors.toList());
    }

    /**
     * Завдання 4.
     * Повертає список без від'ємних чисел (фільтр: залишаємо числа >= 0).
     *
     * @param nums вхідний список цілих чисел
     * @return список без від'ємних елементів
     */
    public List<Integer> noNeg(List<Integer> nums) {
        if (nums == null) return List.of();
        return nums.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 5.
     * Повертає список, у якому відсутні числа, що закінчуються на 9.
     * Використовуємо Math.abs(n) % 10, щоб коректно обробляти від'ємні числа (якщо будуть).
     *
     * @param nums вхідний список цілих чисел
     * @return список без чисел, остання цифра яких = 9
     */
    public List<Integer> no9(List<Integer> nums) {
        if (nums == null) return List.of();
        return nums.stream()
                .filter(n -> Math.abs(n) % 10 != 9)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 6.
     * Повернути список рядків, у яких немає літери 'z'.
     * (чутливість до регістру — як у тестах, шукаємо маленьку 'z').
     *
     * @param strings вхідний список рядків
     * @return відфільтрований список без рядків, які містять 'z'
     */
    public List<String> noZ(List<String> strings) {
        if (strings == null) return List.of();
        return strings.stream()
                .filter(s -> !s.contains("z"))
                .collect(Collectors.toList());
    }

    /**
     * Завдання 7.
     * Повернути список унікальних рядків (без дублів) та відсортувати за спаданням довжини рядка.
     * Використаємо distinct() (залишає першу появу) — і стабільний сортувальник,
     * що збереже порядок між рядками однакової довжини.
     *
     * @param strings вхідний список рядків
     * @return список унікальних рядків, відсортований за довжиною (спадання)
     */
    public List<String> refinedStrings(List<String> strings) {
        if (strings == null) return List.of();
        return strings.stream()
                .distinct() // зберігає першу появу кожного рядка (Encounter order)
                .sorted((a, b) -> Integer.compare(b.length(), a.length())) // сортуємо за довжиною, спадаюче
                .collect(Collectors.toList());
    }

    /**
     * Завдання 8.
     * Розбити кожен рядок за пробілом і "сплюснути" результати в один список.
     * Якщо рядок не має пробілу — він лишається як є.
     *
     * @param strings вхідний список рядків виду "Ім'я Прізвище" або просто "ОдинЕлемент"
     * @return список, в якому імена та прізвища окремо (flatten)
     */
    public List<String> flatten(List<String> strings) {
        if (strings == null) return List.of();
        return strings.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
    }
}
