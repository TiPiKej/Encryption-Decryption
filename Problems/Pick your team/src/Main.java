import java.util.Scanner;

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

// //

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return algorithm.select(persons);
    }
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    private int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        int size = 0;
        for (int i = 0; i < persons.length; i += step, size++);

        Person[] retPersons = new Person[size];
        for (int i = 0; i < size; i++) {
            retPersons[i] = persons[i * step];
        }

        return retPersons;
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    private int count;

    public TakeLastPersonsAlgorithm(int count) {
        // write your code here
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        Person[] retPersons = new Person[count];
        for (int i = 0; i < count; i++) {
            retPersons[i] = new Person(persons[i + persons.length - count].name);
        }

        return retPersons;
    }
}