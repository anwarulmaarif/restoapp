import java.util.Scanner;

public class Main 
{
    // Global variable (bisa diakses semua method)
    static Scanner input = new Scanner(System.in);
    static Menu[] menu;
    
    public static void main(String[] args) 
    {
        // Persiapan data menu
        arrayMenu();
        displayMenuHeader();
        
        // Input makanan dan minuman
        int[] makanan = inputMakanan();
        int[] minuman = inputMinuman();
        
        // Hitung total & cek promo
        double totalMakanan = hitungTotalMakanan(makanan);
        boolean dptPromo = cekPromoMakanan(totalMakanan);
        
        // Hitung diskon & total akhir
        double[] diskonPromo = hitungDiskonPromo(minuman, dptPromo);
        double diskonPromo1 = diskonPromo[0];
        double diskonPromo2 = diskonPromo[1];
        
        double[] diskonKhusus = hitungDiskonKhusus(makanan, minuman, diskonPromo1, diskonPromo2);
        double totalDasar = diskonKhusus[1];
        double diskonKhususValue = diskonKhusus[0];
        double totalAkhir = hitungTotalAkhir(totalDasar, diskonKhususValue);
        
        // Tampilkan struk
        tampilkanStruk(makanan, minuman, diskonPromo1, diskonPromo2, diskonKhususValue, totalDasar, totalAkhir);
    }
    
    // ===== METHOD 1: ARRAY MENU =====
    static void arrayMenu() 
    {
        menu = new Menu[8];
        menu[0] = new Menu("Nasi Padang ", 25000, "makanan");
        menu[1] = new Menu("Ayam Bakar  ", 30000, "makanan");
        menu[2] = new Menu("Sate Kambing", 45000, "makanan");
        menu[3] = new Menu("Gado-Gado   ", 20000, "makanan");
        menu[4] = new Menu("Es Teh Manis", 5000, "minuman");
        menu[5] = new Menu("Jus Jeruk   ", 12000, "minuman");
        menu[6] = new Menu("Kopi Susu   ", 15000, "minuman");
        menu[7] = new Menu("Air Mineral ", 4000, "minuman");
    }
    
    // ===== METHOD 2: TAMPILKAN HEADER =====
    static void displayMenuHeader() 
    {
        System.out.println("======= RESTO JAVA - MENU =======");
        
    }
    
    // ===== METHOD 3: INPUT MAKANAN =====
    static int[] inputMakanan() 
    {
        System.out.println("[ MAKANAN ]");
        System.out.println("1. Nasi Padang (25rb)");
        System.out.println("2. Ayam Bakar (30rb)");
        System.out.println("3. Sate Kambing (45rb)");
        System.out.println("4. Gado-Gado (20rb)");
        
        System.out.print("\nMakanan 1 (Nomor 1-4): ");
        int makananKe1 = input.nextInt() - 1;
        System.out.print("Jumlah: ");
        int jumlahMakananke1 = input.nextInt();
        
        System.out.print("Makanan 2 (Nomor 1-4, atau 0 untuk SKIP): ");
        int makananKe2 = input.nextInt() - 1;
        int jumlahMakananke2 = 0;
        if (makananKe2 != -1) {
            System.out.print("Jumlah: ");
            jumlahMakananke2 = input.nextInt();
        }
        
        // Return array: [index1, jumlah1, index2, jumlah2]
        return new int[]{makananKe1, jumlahMakananke1, makananKe2, jumlahMakananke2};
    }
    
    // ===== METHOD 4: INPUT MINUMAN =====
    static int[] inputMinuman() 
    {
        System.out.println("\n[ MINUMAN ]");
        System.out.println("5. Es Teh (5rb)");
        System.out.println("6. Jus Jeruk (12rb)");
        System.out.println("7. Kopi Susu (15rb)");
        System.out.println("8. Air Mineral (4rb)");
        
        System.out.print("\nMinuman 1 (Nomor 5-8): ");
        int minumanKe1 = input.nextInt() - 1;
        System.out.print("Jumlah: ");
        int jumlahMinumanke1 = input.nextInt();
        
        System.out.print("Minuman 2 (Nomor 5-8, atau 0 untuk SKIP): ");
        int minumanKe2 = input.nextInt() - 1;
        int jumlahMinumanke2 = 0;
        if (minumanKe2 != -1) {
            System.out.print("Jumlah: ");
            jumlahMinumanke2 = input.nextInt();
        }
        
        // Return array: [index1, jumlah1, index2, jumlah2]
        return new int[]{minumanKe1, jumlahMinumanke1, minumanKe2, jumlahMinumanke2};
    }
    
    // ===== METHOD 5: HITUNG TOTAL MAKANAN =====
    static double hitungTotalMakanan(int[] makanan) 
    {
        int makananKe1 = makanan[0];
        int jumlahMakananke1 = makanan[1];
        int makananKe2 = makanan[2];
        int jumlahMakananke2 = makanan[3];
        
        double total = menu[makananKe1].harga * jumlahMakananke1;
        if (makananKe2 != -1) {
            total += menu[makananKe2].harga * jumlahMakananke2;
        }
        return total;
    }
    
    // ===== METHOD 6: CEK PROMO MAKANAN =====
    static boolean cekPromoMakanan(double totalMakanan) 
    {
        if (totalMakanan > 50000) {
            System.out.println("\n>>> SELAMAT! Karena belanja makanan > 50rb, Anda dapat Promo Beli 1 Gratis 1 Minuman! <<<");
            return true;
        }
        return false;
    }
    
    // ===== METHOD 7: HITUNG DISKON =====
    
    static double[] hitungDiskonPromo(int[] minuman, boolean dptPromo) 
    {
        int minumanKe1 = minuman[0];
        int jumlahMinumanke1 = minuman[1];
        int minumanKe2 = minuman[2];
        int jumlahMinumanke2 = minuman[3];
        double diskonPromo1 = 0;
        double diskonPromo2 = 0;
        
        // B1G1: beli 2 gratis 1
        if (dptPromo && jumlahMinumanke1 >= 2) {
            diskonPromo1 = menu[minumanKe1].harga;
        }
        if (dptPromo && jumlahMinumanke2 >= 2 && minumanKe2 != -1) {
            diskonPromo2 = menu[minumanKe2].harga;
        }
        
        return new double[]{diskonPromo1, diskonPromo2};
    }

    static double[] hitungDiskonKhusus(int[] makanan, int[] minuman, double diskonPromo1, double diskonPromo2) 
    {
        // Pakai total dasar yang sudah dikurangi B1G1
        double totalDasarSetelahB1G1 = hitungTotalDasar(makanan, minuman, 
                                                        diskonPromo1, diskonPromo2);
        
        // Hitung diskon Khusus 10% dari total yang sudah dikurangi B1G1
        double diskonKhusus = 0;
        if (totalDasarSetelahB1G1 > 100000) {
            diskonKhusus = totalDasarSetelahB1G1 * 0.10;
        }
        
        return new double[]{diskonKhusus, totalDasarSetelahB1G1};
    }
    
    // ===== METHOD 8: HITUNG TOTAL DASAR =====
    static double hitungTotalDasar(int[] makanan, int[] minuman, double diskonPromo1, double diskonPromo2) 
    {
        
        int makananKe1 = makanan[0];
        int jumlahMakananke1 = makanan[1];
        int makananKe2 = makanan[2];
        int jumlahMakananke2 = makanan[3];
        int minumanKe1 = minuman[0];
        int jumlahMinumanke1 = minuman[1];
        int minumanKe2 = minuman[2];
        int jumlahMinumanke2 = minuman[3];

        double total = (menu[makananKe1].harga * jumlahMakananke1);
        if (makananKe2 != -1) total += (menu[makananKe2].harga * jumlahMakananke2);
        total += (menu[minumanKe1].harga * jumlahMinumanke1);
        if (minumanKe2 != -1) total += (menu[minumanKe2].harga * jumlahMinumanke2);
        
        total -= (diskonPromo1 + diskonPromo2);
        
        return total;  // Total dasar = Makanan + Minuman - B1G1
    }
    
    // ===== METHOD 9: HITUNG TOTAL AKHIR =====
    static double hitungTotalAkhir(double totalDasar, double diskonKhusus) {
        double biayaSetelahDiskon = totalDasar - diskonKhusus;
        double pajak = biayaSetelahDiskon * 0.10;
        double pelayanan = 20000;
        return biayaSetelahDiskon + pajak + pelayanan;
    }
    
    // ===== METHOD 10: TAMPILKAN STRUK =====
    static void tampilkanStruk(int[] makanan, int[] minuman, double diskonPromo1, double diskonPromo2, double diskonKhusus, double totalDasar, double totalAkhir) 
    {
        int makananKe1 = makanan[0], jumlahMakananke1 = makanan[1], makananKe2 = makanan[2], jumlahMakananke2 = makanan[3];
        int minumanKe1 = minuman[0], jumlahMinumanke1 = minuman[1], minumanKe2 = minuman[2], jumlahMinumanke2 = minuman[3];
        
        System.out.println("\n========== STRUK PEMBAYARAN ==========");
        System.out.println(menu[makananKe1].nama + " x" + jumlahMakananke1 + " : Rp " + (menu[makananKe1].harga * jumlahMakananke1));
        if (makananKe2 != -1) System.out.println(menu[makananKe2].nama + " x" + jumlahMakananke2 + " : Rp " + (menu[makananKe2].harga * jumlahMakananke2));
        
        System.out.println(menu[minumanKe1].nama + " x" + jumlahMinumanke1 + " : Rp " + (menu[minumanKe1].harga * jumlahMinumanke1));
        if (diskonPromo1 > 0) System.out.println("  > Promo B1G1  : -Rp " + diskonPromo1);
        
        if (minumanKe2 != -1) {
            System.out.println(menu[minumanKe2].nama + " x" + jumlahMinumanke2 + " : Rp " + (menu[minumanKe2].harga * jumlahMinumanke2));
            if (diskonPromo2 > 0) System.out.println("  > Promo B1G1  : -Rp " + diskonPromo2);
        }
        
        System.out.println("--------------------------------------");
        System.out.println("Subtotal        : Rp " + totalDasar);
        if (diskonKhusus > 0) System.out.println("Diskon 10%      : -Rp " + diskonKhusus);
        
        double pajak = (totalDasar - diskonKhusus) * 0.10;
        System.out.println("Pajak 10%       : Rp " + pajak);
        System.out.println("Pelayanan       : Rp 20000");
        System.out.println("--------------------------------------");
        System.out.println("TOTAL BAYAR     : Rp " + totalAkhir);
        System.out.println("======================================");
    }
}