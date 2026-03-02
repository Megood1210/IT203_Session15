import java.util.Stack;

public class Bai3 {
    private Stack<String> stack = new Stack<>();
    public boolean checkProcess(String[] actions) {
        for (String action : actions) {
            if (action.equals("PUSH")) {
                stack.push(action);
            } else if (action.equals("POP")) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public void reset() {
        stack.clear();
    }

    public static void main(String[] args) {
        Bai3 checker = new Bai3();

        String[] validCase = {"PUSH", "PUSH", "POP", "POP"};
        String[] invalidCase1 = {"POP", "PUSH"};
        String[] invalidCase2 = {"PUSH", "PUSH", "POP"};

        System.out.println("Valid Case: " + checker.checkProcess(validCase));
        checker.reset();

        System.out.println("Invalid Case 1: " + checker.checkProcess(invalidCase1));
        checker.reset();

        System.out.println("Invalid Case 2: " + checker.checkProcess(invalidCase2));
    }
}