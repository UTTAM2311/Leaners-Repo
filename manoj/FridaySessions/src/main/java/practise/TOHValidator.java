package practise;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TOHValidator {

    private static int counter = 1;

    private Queue<Integer> A = new LinkedBlockingQueue<>();
    private Queue<Integer> B = new LinkedBlockingQueue<>();
    private Queue<Integer> C = new LinkedBlockingQueue<>();

    public boolean areValidSteps(List<String> input) {
        for (String element : input) {
            String[] ss = element.split(":");
            String tower = ss[1];
            int disc = Integer.parseInt(ss[0]);
            if (!isValidMove(tower, disc)) {
                System.out.println("NOT VALID STEPS");
                return false;
            }
        }
        System.out.println("VALID STEPS");
        return true;

    }

    private boolean isValidMove(String tower, int disc) {
        boolean move = false;
        if (tower.equals("A") && isValidElementToAddToTower(A, disc, counter)) {
            A.add(disc);
            move = true;
            counter = someStep(B, C, disc, counter);
        } else if (tower.equals("B") && isValidElementToAddToTower(B, disc, counter)) {
            B.add(disc);
            move = true;
            counter = someStep(A, C, disc, counter);
        } else {
            if (isValidElementToAddToTower(C, disc, counter)) {
                C.add(disc);
                move = true;
                counter = someStep(B, A, disc, counter);
            }

        }
        return move;
    }

    private boolean isValidElementToAddToTower(Queue<Integer> queue, int disc, int counter) {
        if (counter >= disc) {
            if (queue.isEmpty()) {
                return true;
            }
            int topDiscInTheQueue = queue.peek();
            return topDiscInTheQueue > disc ? true : false;
        } else {
            return false;
        }
    }

    private boolean isPresentInTower(int disc, Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return false;
        }
        int topDiscInTheQueue = queue.peek();
        return topDiscInTheQueue == disc ? true : false;
    }

    private int someStep(Queue<Integer> queue1, Queue<Integer> queue2, int disc, int counter) {
        int newCounter = counter;
        if (isPresentInTower(disc, queue1)) {
            queue1.remove();
        }
        if (isPresentInTower(disc, queue2)) {
            queue2.remove();
        }
        if (counter == disc) {
            newCounter = counter + 1;
        }
        return newCounter;
    }
}
