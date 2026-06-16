import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            String input = JOptionPane.showInputDialog(
                    null,
                    "LIBRARY SYSTEM MENU\n\n"
                            + "1. Add New Item\n"
                            + "2. View All Items\n"
                            + "3. Search Item by ID\n"
                            + "4. Remove Item\n"
                            + "5. Register New Member\n"
                            + "6. View All Members\n"
                            + "7. Search Member by ID\n"
                            + "8. Borrow Item\n"
                            + "9. Return Item\n"
                            + "10. View Active Loans\n"
                            + "11. View Library Sections\n"
                            + "0. Exit\n\n"
                            + "Enter your choice:"
            );

            if (input == null) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    running = false;
                }

                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        JOptionPane.showMessageDialog(null, "Add New Item selected.");
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "View All Items selected.");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Search Item by ID selected.");
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null, "Remove Item selected.");
                        break;

                    case 5:
                        JOptionPane.showMessageDialog(null, "Register New Member selected.");
                        break;

                    case 6:
                        JOptionPane.showMessageDialog(null, "View All Members selected.");
                        break;

                    case 7:
                        JOptionPane.showMessageDialog(null, "Search Member by ID selected.");
                        break;

                    case 8:
                        JOptionPane.showMessageDialog(null, "Borrow Item selected.");
                        break;

                    case 9:
                        JOptionPane.showMessageDialog(null, "Return Item selected.");
                        break;

                    case 10:
                        JOptionPane.showMessageDialog(null, "View Active Loans selected.");
                        break;

                    case 11:
                        JOptionPane.showMessageDialog(null, "View Library Sections selected.");
                        break;

                    case 0:
                        int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to exit?",
                                "Exit Confirmation",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            running = false;
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Invalid choice. Please enter a number from 0 to 11.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid input. Please enter numbers only.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using Library System.");
    }
}