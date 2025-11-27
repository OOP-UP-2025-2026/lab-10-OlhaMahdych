package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Клас зі складними завданнями (HardTasks).
 * Методи працюють з даними з DataProvider (customers, orders, products).
 * Коментарі українською мовою пояснюють кожен крок.
 */
public class HardTasks {

    // Підключаємо дані з DataProvider (статичні списки)
    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();

        // Щоб побачити результат у консолі, розкоментуйте потрібний виклик нижче:

        // Завдання 1 — товари категорії "Books" з ціною понад 100
        Objects.requireNonNull(tasks.getBooksWithPrice(), "Method getBooksWithPrice() returns null").forEach(System.out::println);

        // Завдання 2 — замовлення, які містять товари категорії "Baby"
        Objects.requireNonNull(tasks.getOrdersWithBabyProducts(), "Method getOrdersWithBabyProducts() returns null").forEach(System.out::println);

        // Інші виклики можна розкоментувати для ручної перевірки
    }

    /**
     * Завдання 1.
     * Повертає товари категорії "Books" і з ціною понад 100.
     *
     * @return список продуктів Books з price > 100
     */
    public List<Product> getBooksWithPrice() {
        // Фільтруємо products за категорією Books і ціною > 100
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 2.
     * Повертає список замовлень, які містять принаймні один продукт з категорією "Baby".
     * Порядок замовлень залишається таким, як у DataProvider.orders (тобто за id зростанням).
     *
     * @return список замовлень, що містять товари категорії Baby
     */
    public List<Order> getOrdersWithBabyProducts() {
        return orders.stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> "Baby".equals(product.getCategory())))
                .collect(Collectors.toList());
    }

    /**
     * Завдання 3.
     * Знайти товари категорії "Toys", застосувати до їх ціни знижку 50% (поділити на 2),
     * і повернути список цих товарів (з оновленими цінами).
     * <p>
     * ВАЖЛИВО: метод змінює поле price в об'єктах Product (тобто mutates products list).
     *
     * @return список товарів категорії Toys з оновленою ціною
     */
    public List<Product> applyDiscountToToys() {
        // Фільтруємо товари за категорією Toys, для кожного товару встановлюємо нову ціну = oldPrice / 2
        List<Product> toys = products.stream()
                .filter(p -> "Toys".equals(p.getCategory()))
                .collect(Collectors.toList());

        toys.forEach(p -> {
            double newPrice = p.getPrice() / 2.0;
            p.setPrice(newPrice);
        });

        return toys;
    }

    /**
     * Завдання 4.
     * Знайти найдешевшу книгу (категорія "Books").
     *
     * @return Optional<Product> — найменша ціна серед Books або порожній Optional, якщо книг немає
     */
    public Optional<Product> getCheapestBook() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .min(Comparator.comparingDouble(Product::getPrice));
    }

    /**
     * Завдання 5.
     * Повернути три останні замовлення за датою orderDate (найсвіжіші — найбільш пізні дати).
     * Сортування — за зменшенням дати (найновіші спочатку), беремо перші 3.
     *
     * @return список з трьома останніми замовленнями
     */
    public List<Order> getRecentOrders() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Завдання 6.
     * Повертає статистику (DoubleSummaryStatistics) за ціною товарів категорії "Books".
     *
     * @return DoubleSummaryStatistics для Books
     */
    public DoubleSummaryStatistics getBooksStats() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    /**
     * Завдання 7.
     * Повертає відображення де ключ = id замовлення, значення = кількість товарів у цьому замовленні.
     *
     * @return Map<orderId, productsCount>
     */
    public Map<Integer, Integer> getOrdersProductsMap() {
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size(),
                        (a, b) -> a, // не повинно бути дублікатів ключів
                        LinkedHashMap::new // зберігаємо порядок вставки (за порядком orders)
                ));
    }

    /**
     * Завдання 8.
     * Повернути товари, згруповані за категорією. Кожне значення — список id товарів
     * (порядок id відповідає порядку проходження у products).
     *
     * @return Map<CategoryName, List<productId>>
     */
    public Map<String, List<Integer>> getProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        LinkedHashMap::new,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
