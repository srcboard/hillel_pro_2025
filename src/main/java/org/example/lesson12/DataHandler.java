package org.example.lesson12;

public class DataHandler {

    private final String[] fruits = new DataRepository().getData();

    public void getOutput() {
        // критичний блок коду
        synchronized (this) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (String fruit : fruits) {
                sb.append(String.format("(%d) %s ",
                        count, fruit));
                count++;
            }
            System.out.println(Thread.currentThread().getName() + ": " + sb);
        }
    }
}
