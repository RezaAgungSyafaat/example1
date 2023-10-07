import java.util.Scanner;

public class aplikasitodolist {
    public static String[] model = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        testViewShowTodoList();
    }

    public static void showTodoList() {
        for (int i = 0; i < model.length; i++) {
            String todo = model[i];
            int no = i + 1;

            if (todo != null) {
                System.out.println(no + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        // Cek apakah model penuh
        boolean isFull = true;
        for (int i = 0; i < model.length; i++) {
            if (model[i] == null) {
                isFull = false;
                break;
            }
        }

        // Jika penuh, kita resize array menjadi 2x lipat
        if (isFull) {
            String[] temp = model;
            model = new String[model.length * 2];

            for (int i = 0; i < temp.length; i++) {
                model[i] = temp[i];
            }
        }

        // Tambahkan ke posisi array yang NULL
        for (int i = 0; i < model.length; i++) {
            if (model[i] == null) {
                model[i] = todo;
                break;
            }
        }
    }

    public static boolean removeTodoList(int number) {
        if (number < 1 || number > model.length) {
            return false;
        } else if (model[number - 1] == null) {
            return false;
        } else {
            for (int i = number - 1; i < model.length; i++) {
                if (i == model.length - 1) {
                    model[i] = null;
                } else {
                    model[i] = model[i + 1];
                }
            }
            return true;
        }
    }

    public static String input(String info) {
        System.out.print(info + ": ");
        return scanner.nextLine();
    }

    public static void viewShowTodoList() {
        while (true) {
            showTodoList();

            System.out.println("MENU");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Keluar");

            String input = input("Pilih");
            if (input.equals("1")) {
                String todo = input("Tambahkan todo");
                addTodoList(todo);
            } else if (input.equals("2")) {
                int number;
                try {
                    number = Integer.parseInt(input("Nomor todo yang ingin dihapus"));
                    if (removeTodoList(number)) {
                        System.out.println("Todo berhasil dihapus.");
                    } else {
                        System.out.println("Todo tidak ditemukan.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Masukkan nomor yang valid.");
                }
            } else if (input.equals("3")) {
                break;
            } else {
                System.out.println("Pilihan tidak dimengerti");
            }
        }
    }

    public static void testViewShowTodoList() {
        addTodoList("satu");
        addTodoList("dua");
        addTodoList("tiga");
        addTodoList("empat");
        addTodoList("lima");
        viewShowTodoList();
    }
}