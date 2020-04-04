package dating_app;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> malesStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(malesStack::push);

        Deque<Integer> femalesQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(femalesQueue::offer);

        int countMatches = 0;
        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {
            int currentMale = malesStack.peek();
            int currentFemale = femalesQueue.peek();
            if (currentMale <= 0 || currentFemale <= 0) {
                if (currentMale <= 0) {
                    malesStack.pop();
                } else {
                    femalesQueue.poll();
                }
            } else if (currentMale % 25 == 0 || currentFemale % 25 == 0) {
                if (currentMale % 25 == 0) {
                    malesStack.pop();
                    if (malesStack.size() > 0) {
                        malesStack.pop();
                    }
                } else {
                    femalesQueue.poll();
                    if (femalesQueue.size() > 0) {
                        femalesQueue.poll();
                    }
                }

            } else if (currentFemale == currentMale) {
                femalesQueue.pop();
                malesStack.pop();
                countMatches++;
            } else {
                femalesQueue.poll();
                malesStack.push(malesStack.pop() - 2);
            }
        }

        System.out.println(String.format("Matches: %d", countMatches));

        if (malesStack.isEmpty()) {
            System.out.println("Males left: none");
        } else {

            List<String> toPrint = new ArrayList<>();
            for (Integer male : malesStack) {
                toPrint.add(String.valueOf(male));
            }

            System.out.print("Males left: ");
            System.out.println(String.join(", ", toPrint));
        }

        if (femalesQueue.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            List<String> toPrint = new ArrayList<>();
            for (Integer female : femalesQueue) {
                toPrint.add(String.valueOf(female));
            }

            System.out.print("Females left: ");
            System.out.println(String.join(", ", toPrint));
        }
    }
}


