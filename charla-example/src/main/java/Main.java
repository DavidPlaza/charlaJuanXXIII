import controllers.AdminController;
import jdk.swing.interop.SwingInterOpUtils;
import repositories.AdminRepository;
import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;
import services.AdminService;
import repositories.impl.AdminRepositoryImpl;
import services.impl.AdminServiceImpl;

import java.util.Scanner;

public class Main {
    private final static String COD_REQUESTER = "0";

    public static void main(String[] args) {
        AdminRepository adminRepository = new AdminRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        AdminService adminService = new AdminServiceImpl(userRepository, adminRepository);
        AdminController adminController = new AdminController(adminService);
        Scanner sc = new Scanner(System.in);

        initProgram(adminController, sc);
    }

    private static void initProgram(AdminController adminController, Scanner sc) {

        String option;
        do {
            option = showOptions(sc);
            switch (option) {
                case "1":
                    System.out.println("Introduce cod:");
                    System.out.println(adminController.createAdmin(sc.nextLine(), COD_REQUESTER));
                    break;

                case "2":
                    System.out.println("Introduce cod:");
                    System.out.println(adminController.deleteAdmin(sc.nextLine(), COD_REQUESTER));
                    break;
            }
        } while (!option.equalsIgnoreCase("0"));
    }

    private static String showOptions(Scanner sc) {
        System.out.println("Select an option:");
        System.out.println("1. Insert admin.");
        System.out.println("2. Delete admin.");
        System.out.println("0. Exit.");
        return sc.nextLine();
    }
}
