import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


//Merupakan inheritance dari Angkot
public class Angkot extends Transportasi{
    
    static Scanner input = new Scanner(System.in);
    
    //Constructor
    public Angkot() {
    }

    public Angkot(String kodeAngkot, String namaAngkot, String PlatTransportasi, int JumlahPenumpang, String StatusAngkot, int HargaSewa) {
        this.kodeTransport = kodeAngkot;
        this.namaTransport = namaAngkot;
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusTransport = StatusAngkot;
        this.HargaSewa = HargaSewa;
    }

    //Getter Setter
    public int getJumlahPenumpang() {
        return this.JumlahPenumpang;
    }

    public void setJumlahPenumpang(int JumlahPenumpang) {
        this.JumlahPenumpang = JumlahPenumpang;
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk memasukkan data dari file angkot.txt ke arraylist angkot
    public static ArrayList<Angkot> updateAngkot (ArrayList<Angkot> angkot) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/angkot.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                angkot.add(new Angkot(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
            }
        }
        return angkot;
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : - Berfungsi untuk mengupdate textfile dan arraylist jika ada 1 data yang perlu diganti
    //               - Contoh : Menggantikan status angkot "Tersedia" ke "Dipinjam" atau sebaliknya yang ada di file txt
    //               - OverLoading dengan method diatas karena nama yang sama tetapi parameter berbeda
    public static void updateAngkot (String kodeAngkot, String status) throws IOException{
        String FilePath = "data/angkot.txt";
        File oldFile = new File ("data/angkot.txt");
        File newFile = new File ("data/temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/angkot.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeAngkot)) {
                        String row = data[0] + "," + data[1] + "," + data[2] +  "," + data[3] + "," ;
                        row = row + status + "," + data[5];
                        pw.print(row);
                    } else {
                        String row =  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeAngkot)) {
                        String row = "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," ;
                        row = row + status + "," + data[5];
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                }
                i++;
            }
            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(FilePath);
            newFile.renameTo(dump);
        }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : - Berfungsi untuk mencetak informasi mengenai angkot dengan kriteria tertentu
    //               - Contoh : Yang dibawah hanya mencetak Kode, Jenis, Transimisi, Penumpang, dan juga Harga.
    public static void displayAturanAngkot (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/angkot.txt"))) {
            String s = "";
            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    for (int i = 0; i < 6; i++) {
                        if ((i == 1) || (i == 5)) {
                            System.out.print(data[i] + "\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t|"); 
                        } else if ((i==3) || (i==1) ){
                            System.out.print(data[i] + "\t\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk mendaftar angkot baru ke arraylist dan juga txt file
    public static void DaftarAngkot (ArrayList<Angkot> angkots) throws Exception {
        System.out.println("Daftar Angkot Baru");
                System.out.println("-----------------");
                System.out.print("Nama angkot : ");
                String namaAngkot = input.next();
                System.out.print("Masukkan jumlah penumpang : ");
                int penumpang = input.nextInt();
                System.out.print("Masukkan harga sewa : ");
                int harga = input.nextInt();
                System.out.print("Masukkan plat transportasi : ");
                String plat1 = input.next();
                String plat2 = input.next();
                String plat3 = input.next();
                String plat = plat1 + " " + plat2 + " " + plat3;

                String kodeAngkot = "V0" + Integer.toString(angkots.size()+1);

                //Data pelanggan masuk ke ArrayList
                angkots.add(new Angkot(kodeAngkot, namaAngkot, plat, penumpang, "Tersedia", harga));
                //Data pelanggan dicetak ke file txt
                try (FileWriter pwAngkot = new FileWriter("data/angkot.txt", true)) {
                    pwAngkot.append("\n" + kodeAngkot + "," + namaAngkot + "," + plat + "," + penumpang + "," + "Tersedia" + "," + harga);
                }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk sewa angkot, sekaligus mengupdate status angkot jika angkot terlah disewa serta menambahkannya ke transaksi pinjam dan pelanggan
    public static void SewaAngkot (ArrayList<Angkot> angkots, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans) throws Exception {
        
        String tanggalPinjam = util.inputTanggal("peminjaman");
        int durasi = util.inputDurasi("peminjaman");
        String lokasiPinjam = util.inputLokasi("peminjaman");
        util.clearScreen();

        //Print angkot yang tersedia
        Angkot.displayAturanAngkot( "Tersedia");

        ///Input angkot yang ingin disewa
        System.out.print("Masukkan kode angkot yang ingin disewakan : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();

        //Cetak detail angkot kode inputan
        for (Angkot angkot : angkots) {
            if (angkot.getKodeTransport().equalsIgnoreCase(kodeInput) && angkot.getStatusTransport().equalsIgnoreCase("Tersedia")) {
                System.out.println("Kode Angkot : " + angkot.getKodeTransport());
                System.out.println("Nama Angkot : " + angkot.getNamaTransport());
                System.out.println("Plat Angkot : " + angkot.getPlatTransportasi());
                System.out.println("Harga Sewa per Hari : Rp" + angkot.getHargaSewa());
                int deposit = angkot.getHargaSewa()/10;
                System.out.println("Harga Deposito : Rp" + deposit);
                System.out.println("-----------------------------------");
                int hargaTotal =  angkot.getHargaSewa()*durasi + deposit;
                System.out.println("Harga total : Rp" + hargaTotal);
                System.out.print("Apakah anda yakin?(y/n) : ");
                String pilyakin = input.next();

                //Data pelanggan akan di-input jika sudah yakin
                if (pilyakin.equalsIgnoreCase("y")) {
                    util.clearScreen();
                    System.out.println("Masukkan detail pelanggan");
                    System.out.println("-------------------------");
                    System.out.print("Nama Pelanggan : ");
                    String namaPelanggan = input.next();
                    System.out.print("Nomor Telepon : ");
                    String noTelp = input.next();
                    System.out.print("Umur : ");
                    int umurPelanggan = input.nextInt();
                    if (umurPelanggan < 18) {
                        throw new Exception("Umur pelanggan tidak mencukupi");
                    }
                    System.out.print("Email : ");
                    String emailPelanggan = input.next();

                    String kodePelanggan = "P0" + Integer.toString(pelanggans.size()+1);
                    String kodePinjam = "T0" + Integer.toString(pinjams.size()+1);

                    //Data pelanggan masuk ke ArrayList
                    pelanggans.add(new Pelanggan(kodePelanggan, namaPelanggan, noTelp, umurPelanggan, emailPelanggan, "meminjam"));
                    //Data pelanggan dicetak ke file txt
                    try (FileWriter pwPelanggan = new FileWriter("data/pelanggan.txt", true)) {
                        pwPelanggan.append("\n" + kodePelanggan + "," + namaPelanggan + "," + noTelp + "," + umurPelanggan + "," + emailPelanggan + "," + "meminjam");
                    }

                    //Data peminjaman masuk ke ArrayList
                    pinjams.add(new TransaksiPeminjaman(kodePinjam, kodeInput, kodePelanggan, lokasiPinjam, tanggalPinjam, deposit, hargaTotal, durasi, "Meminjam"));
                    //Data pinjam dicetak ke file txt
                    try (FileWriter pwPinjam = new FileWriter("data/peminjaman.txt", true)) {
                        pwPinjam.append( "\n" +kodePinjam + "," + kodeInput + "," + kodePelanggan + "," + lokasiPinjam + "," + tanggalPinjam + "," + deposit + "," + hargaTotal + "," + durasi + ",Meminjam");
                    }
                    Angkot.updateAngkot(kodeInput, "Dipinjam");

                    //Cetak reciept 
                    util.clearScreen();
                    TransaksiPeminjaman.cetakRecieptPinjam(kodePinjam,pinjams);
                }
                break;
            }
        }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk pengembalian angkot, sekaligus mengupdate status angkot jika angkot terlah disewa serta menambahkannya ke transaksi pinjam dan pelanggan
    public static void kembaliAngkot (ArrayList<Angkot> angkots, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans, ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException{
        TransaksiPeminjaman.displayAturanPinjam ("Meminjam", "v");
        //Input nomor peminjaman yang mau dikembalikan
        System.out.print("Masukkan kode transaksi : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        util.clearScreen();
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeInput) && pinjam.getStatusPinjam().equalsIgnoreCase("Meminjam")) {
                System.out.println("Nomor Transaksi : " + pinjam.getNomorTransaksi());
                System.out.println("Nama Angkot : " + pinjam.getAngkotPinjam().getNamaTransport());
                System.out.println("Plat Angkot : " + pinjam.getAngkotPinjam().getPlatTransportasi());
                System.out.println("Peminjam : " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("-----------------------");
                String tanggalKembali = util.inputTanggal("pengembalian");
                String lokasiKembali = util.inputLokasi("pengembalian");

                String tanggalKembaliSeharusnya = util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa());
                int dendaHari = 0;
                int bedaHari;
                if (tanggalKembali.equalsIgnoreCase(tanggalKembaliSeharusnya)) {
                    System.out.println("Angkot dikembalikan tepat waktu");
                    dendaHari += 0;
                } else {
                    if ((bedaHari = util.getDifferenceDays(tanggalKembali, tanggalKembaliSeharusnya)) > 0) {
                        System.out.printf("Angkot dikembalikan %d hari terlambat\n", bedaHari);
                        dendaHari = bedaHari * 50000;
                    } else {
                        System.out.println("Angkot dikembalikan lebih awal");
                        dendaHari += 0;
                    }
                }
                //Cek angkot dari class angkot
                int dendaCek = Angkot.cekTransport() * 50000;
                //Hitung total denda pelanggan
                int totalDenda = dendaHari + dendaCek - pinjam.getDeposit();

                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeInput, pinjams);
                Pelanggan.updatePelanggan(peminjaman.getPelangganPinjam().getKodePelanggan(), "lunas");
                Angkot.updateAngkot(peminjaman.getAngkotPinjam().getKodeTransport(), "Tersedia");
                TransaksiPeminjaman.updatePinjam(kodeInput, "Berhasil");

                //Data masuk kembali ke ArrayList
                kembalis.add(new TransaksiPengembalian(kodeInput, lokasiKembali, tanggalKembali, totalDenda));
                //Data dicetak kembali ke file
                try (FileWriter pwKembali = new FileWriter("data/pengembalian.txt", true)) {
                    pwKembali.append("\n" + kodeInput + "," + lokasiKembali + "," + tanggalKembali + "," + totalDenda);
                }
                util.clearScreen();
                TransaksiPengembalian.cetakRecieptKembali(kodeInput, kembalis, pinjams);
                break;
            }
        }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk mengurutkan dan menampilkan angkot berdasarkan harga terendah ke tertinggi menggunakan shell sort
    public static void displayAturanAngkotAsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/angkot.txt"))) {
            String s = "";
            ArrayList <Angkot> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    asc.add (new Angkot(data[0], data[1], data[2], Integer.parseInt(data[3]),data[4], Integer.parseInt(data[5])));
                }
            }
            for (int gap = asc.size()/2; gap > 0; gap /= 2) {
                for (int i = gap; i < asc.size(); i += 1) {
                    Angkot temp = new Angkot();
                    temp = asc.get(i);
                    int j;
                    for (j = i; j >= gap && asc.get(j-gap).getHargaSewa() > temp.getHargaSewa(); j -= gap) {
                        asc.set(j, asc.get(j-gap));
                    }
                    asc.set(j, temp);
                }
            }

            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            for (Angkot angkot : asc) {
                System.out.println(angkot);
            }
        }
    }

    //Nama         : Jevincent Ong
    //NIM          : 03081210037
    //Deskripsi    : berfungsi untuk mengurutkan dan menampilkan angkot berdasarkan harga tertinggi ke terendah menggunakan shell sort
    public static void displayAturanAngkotDsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/angkot.txt"))) {
            String s = "";
            ArrayList <Angkot> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    asc.add (new Angkot(data[0], data[1], data[2], Integer.parseInt(data[3]),data[4], Integer.parseInt(data[5])));
                }
            }
            for (int gap = asc.size()/2; gap > 0; gap /= 2) {
                for (int i = gap; i < asc.size(); i += 1) {
                    Angkot temp = new Angkot();
                    temp = asc.get(i);
                    int j;
                    for (j = i; j >= gap && asc.get(j-gap).getHargaSewa() < temp.getHargaSewa(); j -= gap) {
                        asc.set(j, asc.get(j-gap));
                    }
                    asc.set(j, temp);
                }
            }

            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            for (Angkot angkot : asc) {
                System.out.println(angkot);
            }
        }
    }

    @Override
    public String toString() {
        return "|" +  getKodeTransport() + "\t|"
        + getNamaTransport() + "\t|" 
        + getJumlahPenumpang() + "\t\t|"
        + getHargaSewa() + "\t|";
    }

}
