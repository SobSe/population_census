import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Collection<Person> persons = init();

        long numberOfMinors = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.printf("Количество несовершеннолетних %d", numberOfMinors);

        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <=27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> workers = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.MAN && x.getAge() <= 65 || x.getSex() == Sex.WOMAN && x.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily, (String::compareTo)))
                .collect(Collectors.toList());
    }

    public static Collection<Person> init()  {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}