import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Bai6 {

    public static class Patient {
        String id, name, gender;
        int age;

        public Patient(String id, String name, int age, String gender) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Patient [id=" + id + ", name=" + name + "]";
        }
    }

    public static class PatientWaitingQueue {
        Queue<Patient> waitingQueue = new LinkedList<>();

        public void addPatient(Patient p) {
            waitingQueue.add(p);
        }

        public Patient nextPatient() {
            return waitingQueue.poll();
        }

        public int getTotalPatients() {
            return waitingQueue.size();
        }
    }

    public static class EditAction {
        String description, editedBy, editTime;

        public EditAction(String description, String editedBy, String editTime) {
            this.description = description;
            this.editedBy = editedBy;
            this.editTime = editTime;
        }

        @Override
        public String toString() {
            return editTime + " - " + description + " (By: " + editedBy + ")";
        }
    }

    public static class MedicalRecordHistory {
        Stack<EditAction> editStack = new Stack<>();
        String recordId;

        public MedicalRecordHistory(String recordId) {
            this.recordId = recordId;
        }

        public void addEditAction(EditAction action) {
            editStack.push(action);
        }

        public void displayHistory() {
            System.out.println("History for Record: " + recordId);
            for (int i = editStack.size() - 1; i >= 0; i--) {
                System.out.println(editStack.get(i));
            }
        }
    }

    public static class Ticket {
        int ticketNumber;
        String issuedTime;

        public Ticket(int ticketNumber, String issuedTime) {
            this.ticketNumber = ticketNumber;
            this.issuedTime = issuedTime;
        }
    }

    public static class TicketSystem {
        Queue<Ticket> ticketQueue = new LinkedList<>();
        int currentNumber = 0;

        public void issueTicket(String time) {
            currentNumber++;
            ticketQueue.add(new Ticket(currentNumber, time));
            System.out.println("Issued Ticket No: " + currentNumber);
        }

        public void callNext() {
            Ticket t = ticketQueue.poll();
            if (t != null) {
                System.out.println("Calling Ticket: " + t.ticketNumber);
            }
        }
    }

    public static class InputAction {
        String fieldName, oldValue, newValue, actionTime;

        public InputAction(String fieldName, String oldValue, String newValue, String actionTime) {
            this.fieldName = fieldName;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.actionTime = actionTime;
        }
    }

    public static class UndoManager {
        Stack<InputAction> undoStack = new Stack<>();
        int maxUndoSteps;

        public UndoManager(int maxUndoSteps) {
            this.maxUndoSteps = maxUndoSteps;
        }

        public void recordAction(InputAction action) {
            if (undoStack.size() >= maxUndoSteps) {
                undoStack.remove(0);
            }
            undoStack.push(action);
        }

        public void undo() {
            if (!undoStack.isEmpty()) {
                InputAction last = undoStack.pop();
                System.out.println("Undo field [" + last.fieldName + "] to value: " + last.oldValue);
            } else {
                System.out.println("Nothing to undo");
            }
        }
    }

    public static void main(String[] args) {
        PatientWaitingQueue pQueue = new PatientWaitingQueue();
        pQueue.addPatient(new Patient("BN01", "Nguyen Van A", 20, "Nam"));
        System.out.println("Next: " + pQueue.nextPatient());

        MedicalRecordHistory history = new MedicalRecordHistory("REC-100");
        history.addEditAction(new EditAction("Update Heart Rate", "Dr. Smith", "10:00"));
        history.addEditAction(new EditAction("Update Blood Type", "Nurse Joy", "10:05"));
        history.displayHistory();

        TicketSystem ts = new TicketSystem();
        ts.issueTicket("08:00");
        ts.issueTicket("08:01");
        ts.callNext();

        UndoManager undoer = new UndoManager(5);
        undoer.recordAction(new InputAction("Diagnosis", "None", "Flu", "09:00"));
        undoer.undo();
    }
}