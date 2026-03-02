import java.util.Stack;

public class Bai1 {
    static class EditAction {
        private String description;
        private String time;

        public EditAction(String description, String time) {
            this.description = description;
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public String getTime() {
            return time;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Mô tả: " + description + " | Thời điểm: " + time;
        }
    }

    static class MedicalRecordHistory {
        private Stack<EditAction> history = new Stack<>(); //Stack vì hoạt động theo nguyên tắc LIFO
        public void addEdit(EditAction action) {
            history.push(action);
            System.out.println("Đã thêm chỉnh sửa: " + action.getDescription());
        }

        public EditAction undoEdit() {
            if (history.isEmpty()) {
                System.out.println("Không có chỉnh sửa nào để hoàn tác.");
                return null;
            }
            EditAction removed = history.pop();
            System.out.println("Đã hoàn tác: " + removed.getDescription());
            return removed;
        }

        public EditAction getLatestEdit() {
            if (history.isEmpty()) {
                System.out.println("Không có chỉnh sửa nào.");
                return null;
            }
            return history.peek();
        }

        public boolean isEmpty() {
            return history.isEmpty();
        }

        public void displayHistory() {
            if (history.isEmpty()) {
                System.out.println("Lịch sử chỉnh sửa trống.");
                return;
            }

            System.out.println("Danh sách chỉnh sửa:");
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.get(i));
            }
        }
    }

    public static void main(String[] args) {
        MedicalRecordHistory recordHistory = new MedicalRecordHistory();

        recordHistory.addEdit(new EditAction("Thêm chẩn đoán: Viêm họng", "10:00"));
        recordHistory.addEdit(new EditAction("Cập nhật toa thuốc", "10:15"));
        recordHistory.addEdit(new EditAction("Chỉnh sửa tiền sử bệnh", "10:30"));

        System.out.println();

        System.out.println("Chỉnh sửa gần nhất:");
        System.out.println(recordHistory.getLatestEdit());

        System.out.println();

        recordHistory.displayHistory();

        System.out.println();

        recordHistory.undoEdit();

        System.out.println();

        recordHistory.displayHistory();
    }
}