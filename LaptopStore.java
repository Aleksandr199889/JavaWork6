import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
        // Добавьте здесь ноутбуки в магазин
        laptops.add(new Laptop("HP", 8, 512, "Windows", "Silver"));
        laptops.add(new Laptop("Dell", 16, 1024, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 16, 512, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 8, 256, "Windows", "Black"));
        laptops.add(new Laptop("Asus", 32, 1000, "Windows", "Blue"));
    }

    public void filterLaptops(Map<String, Object> filters) {
        for (Laptop laptop : laptops) {
            boolean passesFilter = true;
            for (String key : filters.keySet()) {
                Object value = filters.get(key);
                switch (key) {
                    case "ramMin":
                        int minRam = (int) value;
                        if (laptop.getRam() < minRam) {
                            passesFilter = false;
                        }
                        break;
                    case "ramMax":
                        int maxRam = (int) value;
                        if (laptop.getRam() > maxRam) {
                            passesFilter = false;
                        }
                        break;
                    case "storageMin":
                        int minStorage = (int) value;
                        if (laptop.getStorage() < minStorage) {
                            passesFilter = false;
                        }
                        break;
                    case "storageMax":
                        int maxStorage = (int) value;
                        if (laptop.getStorage() > maxStorage) {
                            passesFilter = false;
                        }
                        break;
                    case "operatingSystems":
                        List<String> osList = (List<String>) value;
                        if (!osList.contains(laptop.getOperatingSystem())) {
                            passesFilter = false;
                        }
                        break;
                    case "color":
                        String color = (String) value;
                        if (!color.equalsIgnoreCase(laptop.getColor())) {
                            passesFilter = false;
                        }
                        break;
                }
            }
            if (passesFilter) {
                System.out.println("Бренд: " + laptop.getBrand());
                System.out.println("ОЗУ: " + laptop.getRam() + "GB");
                System.out.println("Объем ЖД: " + laptop.getStorage() + "GB");
                System.out.println("Операционная система: " + laptop.getOperatingSystem());
                System.out.println("Цвет: " + laptop.getColor());
                System.out.println("------------------------");
            }
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();

        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criteria = scanner.nextInt();

        switch (criteria) {
            case 1:
                System.out.println("Введите минимальный объем ОЗУ (в GB):");
                int ramMin = scanner.nextInt();
                System.out.println("Введите максимальный объем ОЗУ (в GB):");
                int ramMax = scanner.nextInt();
                filters.put("ramMin", ramMin);
                filters.put("ramMax", ramMax);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД (в GB):");
                int storageMin = scanner.nextInt();
                System.out.println("Введите максимальный объем ЖД (в GB):");
                int storageMax = scanner.nextInt();
                filters.put("storageMin", storageMin);
                filters.put("storageMax", storageMax);
                break;
            case 3:
                List<String> operatingSystems = new ArrayList<>();
                scanner.nextLine(); // очистка буфера
                System.out.println("Введите операционные системы (через запятую):");
                String input = scanner.nextLine();
                String[] osArray = input.split(",");
                for (String os : osArray) {
                    operatingSystems.add(os.trim());
                }
                filters.put("operatingSystems", operatingSystems);
                break;
            case 4:
                scanner.nextLine(); // очистка буфера
                System.out.println("Введите цвет:");
                String color = scanner.nextLine();
                filters.put("color", color);
                break;
            default:
                System.out.println("Недопустимый критерий.");
                return;
        }

        System.out.println("Результаты фильтрации:");
        store.filterLaptops(filters);
    }
}
