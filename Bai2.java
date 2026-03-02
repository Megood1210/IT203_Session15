import java.util.LinkedList;
import java.util.Queue;

public class Bai2 {
    static class Patient {
        private String id;
        private String name;
        private int age;

        public Patient(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Tên: " + name + " | Tuổi: " + age;
        }
    }

    static class PatientQueue {
        private Queue<Patient> queue = new LinkedList<>(); //LinkedList vì có thao tác thêm cuối, xóa đầu hợp với Queue

        public void addPatient(Patient p) {
            queue.add(p); //thêm cuối
            System.out.println("Đã thêm bệnh nhân: " + p.getName());
        }

        public Patient callNextPatient() {
            if (queue.isEmpty()) {
                System.out.println("Không có bệnh nhân nào.");
                return null;
            }
            Patient p = queue.remove(); //xóa đầu
            System.out.println("Đang khám: " + p.getName());
            return p;
        }

        public Patient peekNextPatient() {
            if (queue.isEmpty()) {
                System.out.println("Không có bệnh nhân nào.");
                return null;
            }
            return queue.peek(); // xem đầu
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void displayQueue() {
            if (queue.isEmpty()) {
                System.out.println("Hàng đợi trống.");
                return;
            }

            System.out.println("Danh sách chờ khám:");
            for (Patient p : queue) {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        PatientQueue patientQueue = new PatientQueue();

        patientQueue.addPatient(new Patient("P01", "Lan", 30));
        patientQueue.addPatient(new Patient("P02", "Hùng", 45));
        patientQueue.addPatient(new Patient("P03", "Mai", 25));

        System.out.println();
        patientQueue.displayQueue();

        System.out.println();

        System.out.println("Bệnh nhân tiếp theo: " + patientQueue.peekNextPatient());

        System.out.println();
        patientQueue.callNextPatient();

        System.out.println();
        patientQueue.displayQueue();
    }
}