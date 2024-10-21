import java.util.Scanner;
import java.util.Stack;

// Kelas TextEditor
class TextEditor {
    private String currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditor() {
        this.currentText = "";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    // Menampilkan isi teks editor
    public void show() {
        System.out.println("Teks saat ini: " + currentText);
    }

    // Menulis teks baru ke dalam editor
    public void write(String text) {
        undoStack.push(currentText); // Simpan teks saat ini sebelum mengubah
        currentText += text; // Tambahkan teks baru
        redoStack.clear(); // Hapus redo stack karena ada perubahan baru
    }

    // Mengembalikan teks ke isi sebelumnya
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText); // Simpan teks saat ini untuk redo
            currentText = undoStack.pop(); // Kembali ke teks sebelumnya
        } else {
            System.out.println("Tidak ada aksi yang bisa di-undo.");
        }
    }

    // Memulihkan teks ke isi yang lebih baru
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText); // Simpan teks saat ini untuk undo
            currentText = redoStack.pop(); // Kembali ke teks yang di-redo
        } else {
            System.out.println("Tidak ada aksi yang bisa di-redo.");
        }
    }
}

// Kelas Main
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        String command;

        System.out.println("Selamat datang di Text Editor. Masukkan perintah (show, write, undo, redo, exit):");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "show":
                    editor.show();
                    break;
                case "write":
                    System.out.print("Masukkan teks yang ingin ditambahkan: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case "undo":
                    editor.undo();
                    break;
                case "redo":
                    editor.redo();
                    break;
                case "exit":
                    System.out.println("Keluar dari Text Editor.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenali. Gunakan show, write, undo, redo, atau exit.");
                    break;
            }
        }
    }
}
