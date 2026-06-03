// =============================================
// RestaurantGUI.java  ← MAIN FILE (Run this!)
// Simple GUI using Java Swing
// =============================================

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RestaurantGUI extends JFrame {

    // ── Our core objects (from the given classes) ──
    private Restaurant restaurant;
    private Order currentOrder;          // will be created when submitting
    private Waiter waiter;
    private Chef chef;
    private ArrayList<Fooditem> currentItems;   // store items for display before order submission
    private int nextOrderId = 100;               // simple order ID generator

    // ── GUI components ──
    private JTextArea orderArea;
    private JLabel totalLabel;
    private JLabel statusLabel;
    private JComboBox<String> itemTypeBox;
    private JTextField itemNameField;
    private JTextField itemPriceField;
    private JTextField serviceChargeField;

    // ── Colors for a clean look ──
    private final Color BG_COLOR      = new Color(245, 245, 240);
    private final Color HEADER_COLOR  = new Color(52,  73,  94);
    private final Color BTN_ADD       = new Color(39, 174, 96);
    private final Color BTN_SUBMIT    = new Color(41, 128, 185);
    private final Color BTN_CLEAR     = new Color(192, 57,  43);
    private final Color ACCENT        = new Color(230, 126, 34);
    private final Color TEXT_LIGHT    = Color.WHITE;

    // ── Constructor: build the window ──
    public RestaurantGUI() {
        // Create objects using the given constructors
        restaurant = new Restaurant("Java Bites", "Dhaka, Bangladesh");
        waiter     = new Waiter("Rahim");
        chef       = new Chef("Karim", "Bangladeshi Cuisine");
        currentItems = new ArrayList<>();

        // Window settings
        setTitle("🍽  " + restaurant.getName() + " — Restaurant System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);

        buildUI();
        setVisible(true);
    }

    // ── Build the full UI ──
    private void buildUI() {
        setLayout(new BorderLayout(10, 10));
        add(buildHeaderPanel(),  BorderLayout.NORTH);
        add(buildInputPanel(),   BorderLayout.WEST);
        add(buildOrderPanel(),   BorderLayout.CENTER);
        add(buildStatusBar(),    BorderLayout.SOUTH);
    }

    // ── TOP: Restaurant name banner ──
    private JPanel buildHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(HEADER_COLOR);
        panel.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("🍽  " + restaurant.getName());
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(TEXT_LIGHT);

        JLabel sub = new JLabel(restaurant.getLocation()
                + "  |  Waiter: " + waiter.getName()
                + "  |  Chef: " + chef.getName());
        sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        sub.setForeground(new Color(189, 195, 199));

        panel.add(title, BorderLayout.WEST);
        panel.add(sub,   BorderLayout.EAST);
        return panel;
    }

    // ── LEFT: Input form to add food items ──
    private JPanel buildInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_COLOR);
        panel.setBorder(new CompoundBorder(
                new EmptyBorder(10, 12, 10, 6),
                new TitledBorder(BorderFactory.createLineBorder(ACCENT, 2),
                        " Add Food Item ", TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 13), ACCENT)
        ));
        panel.setPreferredSize(new Dimension(240, 0));

        // Item type selector
        panel.add(makeLabel("Item Type:"));
        itemTypeBox = new JComboBox<>(new String[]{"Main Course", "Dessert"});
        itemTypeBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        itemTypeBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        itemTypeBox.addActionListener(e -> toggleServiceCharge());
        panel.add(itemTypeBox);
        panel.add(Box.createVerticalStrut(10));

        // Item name
        panel.add(makeLabel("Item Name:"));
        itemNameField = makeTextField("e.g. Biryani");
        panel.add(itemNameField);
        panel.add(Box.createVerticalStrut(10));

        // Price
        panel.add(makeLabel("Price ($):"));
        itemPriceField = makeTextField("e.g. 8.50");
        panel.add(itemPriceField);
        panel.add(Box.createVerticalStrut(10));

        // Service charge (only for Main Course)
        panel.add(makeLabel("Service Charge ($):"));
        serviceChargeField = makeTextField("e.g. 1.50");
        panel.add(serviceChargeField);
        panel.add(Box.createVerticalStrut(16));

        // ADD ITEM button
        JButton addBtn = makeButton("➕  Add Item", BTN_ADD);
        addBtn.addActionListener(e -> addFoodItem());
        panel.add(addBtn);
        panel.add(Box.createVerticalStrut(8));

        // SUBMIT ORDER button
        JButton submitBtn = makeButton("✔  Submit Order", BTN_SUBMIT);
        submitBtn.addActionListener(e -> submitOrder());
        panel.add(submitBtn);
        panel.add(Box.createVerticalStrut(8));

        // CLEAR button
        JButton clearBtn = makeButton("✖  Clear Order", BTN_CLEAR);
        clearBtn.addActionListener(e -> clearOrder());
        panel.add(clearBtn);

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    // ── RIGHT/CENTER: Order display area ──
    private JPanel buildOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(BG_COLOR);
        panel.setBorder(new EmptyBorder(10, 6, 10, 12));

        JLabel heading = new JLabel(" 📋  Current Order");
        heading.setFont(new Font("SansSerif", Font.BOLD, 15));
        heading.setForeground(HEADER_COLOR);
        panel.add(heading, BorderLayout.NORTH);

        orderArea = new JTextArea();
        orderArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        orderArea.setEditable(false);
        orderArea.setBackground(new Color(255, 255, 250));
        orderArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        orderArea.setText("  No items yet. Add items from the left panel.\n");

        JScrollPane scroll = new JScrollPane(orderArea);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scroll, BorderLayout.CENTER);

        totalLabel = new JLabel("  Total: $0.00");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalLabel.setForeground(BTN_SUBMIT);
        totalLabel.setBorder(new EmptyBorder(6, 0, 0, 0));
        panel.add(totalLabel, BorderLayout.SOUTH);

        return panel;
    }

    // ── BOTTOM: Status bar ──
    private JPanel buildStatusBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(HEADER_COLOR);
        panel.setBorder(new EmptyBorder(4, 12, 4, 12));

        statusLabel = new JLabel("✅  System ready. Welcome to " + restaurant.getName() + "!");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(200, 230, 200));
        panel.add(statusLabel);
        return panel;
    }

    // ══════════════════════════════════════════
    //  ACTION METHODS
    // ══════════════════════════════════════════

    private void addFoodItem() {
        String name = itemNameField.getText().trim();
        String priceText = itemPriceField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty()) {
            setStatus("⚠  Please enter item name and price.", Color.ORANGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            Fooditem item;

            if (itemTypeBox.getSelectedIndex() == 0) {  // Main Course
                String scText = serviceChargeField.getText().trim();
                double serviceCharge = scText.isEmpty() ? 0 : Double.parseDouble(scText);
                double finalPrice = price + serviceCharge;
                item = new MainCourse(name, finalPrice);
                setStatus("🍛  Chef " + chef.getName() + " is cooking " + name + ".", new Color(34, 153, 84));
            } else {  // Dessert
                item = new Dessert(name, price);
                setStatus("🍰  Chef " + chef.getName() + " is preparing dessert " + name + ".", new Color(34, 153, 84));
            }

            currentItems.add(item);
            refreshOrderDisplay();
            clearInputFields();

        } catch (NumberFormatException ex) {
            setStatus("⚠  Price must be a number (e.g. 5.50)", Color.RED);
        }
    }

    private void submitOrder() {
        if (currentItems.isEmpty()) {
            setStatus("⚠  Order is empty! Add items first.", Color.ORANGE);
            return;
        }

        // Create a new Order with a unique ID
        currentOrder = new Order(nextOrderId++);

        // Add each item's price to the order
        double orderTotal = 0;
        for (Fooditem item : currentItems) {
            double itemTotal = item.totalPrice();
            currentOrder.addItem(itemTotal);
            orderTotal += itemTotal;
        }

        // Waiter takes the order (using the existing takeOrder(String) method)
        String orderSummary = "Order #" + currentOrder.getOrderId() + " with " + currentItems.size() + " items";
        waiter.takeOrder(orderSummary);   // prints to console, also we capture idea for popup

        // Restaurant records the sale
        restaurant.addOrder(currentOrder.getTotal());

        // Show confirmation popup
        JOptionPane.showMessageDialog(this,
                "Waiter " + waiter.getName() + " took your order.\n\nTotal Bill: $"
                        + String.format("%.2f", currentOrder.getTotal())
                        + "\n\nThank you! Come again 😊",
                "Order Submitted",
                JOptionPane.INFORMATION_MESSAGE);

        // Start a fresh order
        currentItems.clear();
        refreshOrderDisplay();
        setStatus("✅  Order #" + currentOrder.getOrderId() + " submitted! Total sales: $"
                + String.format("%.2f", restaurant.getTotalSales()), new Color(41, 128, 185));
    }

    private void clearOrder() {
        currentItems.clear();
        refreshOrderDisplay();
        setStatus("🗑  Order cleared.", Color.GRAY);
    }

    private void refreshOrderDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ─────────────────────────────────\n");
        sb.append(String.format("  %-25s %s%n", "Item", "Price"));
        sb.append("  ─────────────────────────────────\n");

        for (Fooditem item : currentItems) {
            sb.append(String.format("  %-25s $%.2f%n",
                    item.getItemName(), item.totalPrice()));
        }
        sb.append("  ─────────────────────────────────\n");

        orderArea.setText(sb.length() == 0 ? "  No items yet.\n" : sb.toString());

        double total = 0;
        for (Fooditem item : currentItems) total += item.totalPrice();
        totalLabel.setText("  Total: $" + String.format("%.2f", total));
    }

    private void toggleServiceCharge() {
        boolean isMainCourse = itemTypeBox.getSelectedIndex() == 0;
        serviceChargeField.setEnabled(isMainCourse);
        if (!isMainCourse) serviceChargeField.setText("");
    }

    private void clearInputFields() {
        itemNameField.setText("");
        itemPriceField.setText("");
        serviceChargeField.setText("");
    }

    private void setStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    // ══════════════════════════════════════════
    //  HELPER METHODS (UI building)
    // ══════════════════════════════════════════

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(HEADER_COLOR);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField makeTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        field.setToolTipText(placeholder);
        return field;
    }

    private JButton makeButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(TEXT_LIGHT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    // ══════════════════════════════════════════
    //  MAIN METHOD
    // ══════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestaurantGUI());
    }
}