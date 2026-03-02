import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Patient {
    private String id;
    private String name;
    private int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Tên: " + name + " | Tuổi: " + age;
    }
}

class TreatmentStep {
    private String description;
    private String time;

    public TreatmentStep(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return time + " - " + description;
    }
}

class EmergencyCase {
    private Patient patient;
    private Stack<TreatmentStep> steps = new Stack<>();

    public EmergencyCase(Patient patient) {
        this.patient = patient;
    }

    public void addStep(TreatmentStep step) {
        steps.push(step);
        System.out.println("Đã thêm bước: " + step.getDescription());
    }

    public TreatmentStep undoStep() {
        if (steps.isEmpty()) {
            System.out.println("Không có bước nào để hoàn tác.");
            return null;
        }
        TreatmentStep removed = steps.pop();
        System.out.println("Đã hoàn tác bước: " + removed.getDescription());
        return removed;
    }

    public void displaySteps() {
        if (steps.isEmpty()) {
            System.out.println("Chưa có bước xử lý.");
            return;
        }

        System.out.println("Các bước xử lý của bệnh nhân " + patient.getName() + ":");
        for (TreatmentStep step : steps) {
            System.out.println(step);
        }
    }

    public Patient getPatient() {
        return patient;
    }
}

class EmergencyCaseQueue {
    private Queue<EmergencyCase> cases = new LinkedList<>();

    public void addCase(EmergencyCase c) {
        cases.add(c);
        System.out.println("Đã tiếp nhận ca: " + c.getPatient().getName());
    }

    public EmergencyCase getNextCase() {
        if (cases.isEmpty()) {
            System.out.println("Không còn ca cấp cứu.");
            return null;
        }
        EmergencyCase c = cases.poll();
        System.out.println("Đang xử lý ca: " + c.getPatient().getName());
        return c;
    }
}

public class Bai5 {
    public static void main(String[] args) {
        EmergencyCaseQueue queue = new EmergencyCaseQueue();

        Patient p1 = new Patient("E01", "Lan", 30);
        Patient p2 = new Patient("E02", "Hùng", 45);

        EmergencyCase case1 = new EmergencyCase(p1);
        EmergencyCase case2 = new EmergencyCase(p2);

        queue.addCase(case1);
        queue.addCase(case2);

        System.out.println();

        EmergencyCase currentCase = queue.getNextCase();

        currentCase.addStep(new TreatmentStep("Tiếp nhận", "08:00"));
        currentCase.addStep(new TreatmentStep("Chẩn đoán", "08:10"));
        currentCase.addStep(new TreatmentStep("Điều trị", "08:20"));

        System.out.println();
        currentCase.displaySteps();

        System.out.println();
        currentCase.undoStep();

        System.out.println();
        currentCase.displaySteps();
    }
}