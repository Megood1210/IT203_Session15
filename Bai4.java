import java.util.PriorityQueue;
import java.util.Comparator;

class EmergencyPatient {
    private String id;
    private String name;
    private int priority;
    private long order;

    public EmergencyPatient(String id, String name, int priority, long order) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.order = order;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPriority() {
        return priority;
    }
    public long getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Tên: " + name + " | Mức độ: " + (priority == 1 ? "Cấp cứu" : "Thường");
    }
}

class EmergencyQueue {
    private long counter = 0;
    private PriorityQueue<EmergencyPatient> queue = new PriorityQueue<>(new Comparator<EmergencyPatient>() { //PriorityQueue vì ưu tiên thì ra trước
                @Override
                public int compare(EmergencyPatient p1, EmergencyPatient p2) {
                    if (p1.getPriority() != p2.getPriority()) {
                        return Integer.compare(p1.getPriority(), p2.getPriority());
                    }

                    return Long.compare(p1.getOrder(), p2.getOrder()); // compare để biết cái nào được ưu tiên
                }
            });

    public void addPatient(String id, String name, int priority) {
        EmergencyPatient p = new EmergencyPatient(id, name, priority, counter++);
        queue.add(p);
        System.out.println("Đã tiếp nhận: " + name);
    }

    public EmergencyPatient callNextPatient() {
        if (queue.isEmpty()) {
            System.out.println("Không có bệnh nhân.");
            return null;
        }
        EmergencyPatient p = queue.poll();
        System.out.println("Đang khám: " + p.getName());
        return p;
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Hàng đợi trống.");
            return;
        }

        System.out.println("Danh sách chờ:");
        for (EmergencyPatient p : queue) {
            System.out.println(p);
        }
    }
}

public class Bai4 {
    public static void main(String[] args) {
        EmergencyQueue eq = new EmergencyQueue();

        eq.addPatient("E01", "Lan", 2);
        eq.addPatient("E02", "Hùng", 1);
        eq.addPatient("E03", "Mai", 2);
        eq.addPatient("E04", "An", 1);

        System.out.println();
        eq.displayQueue();

        System.out.println("\n--- Gọi khám ---");
        eq.callNextPatient();
        eq.callNextPatient();
    }
}