import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

// Merupakan inheritance dari Transaksi
public class TransaksiPengembalian extends Transaksi{

    // atribut
    String nomorTransaksi;
    private int denda;
    private String lokasiKembali;
    private String tanggalKembali;

    // constructor
    public TransaksiPengembalian() {
    }

    public TransaksiPengembalian(String nomorTransaksi, String lokasiKembali, String tanggalKembali, int denda) {
        this.nomorTransaksi = nomorTransaksi;
        this.denda = denda;
        this.lokasiKembali = lokasiKembali;
        this.tanggalKembali = tanggalKembali;
    }

    // getter setter
    public int getDenda() {
        return this.denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public String getLokasiKembali() {
        return this.lokasiKembali;
    }

    public void setLokasiKembali(String lokasiKembali) {
        this.lokasiKembali = lokasiKembali;
    }

    public String getTanggalKembali() {
        return this.tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getNomorTransaksi() {
        return this.nomorTransaksi;
    }

    public void setNomorTransaksi(String nomorTransaksi) {
        this.nomorTransaksi = nomorTransaksi;
    }


    /* Nama                 : Jevincent Ong
     * NIM                  : 03081210037
     * Deskripsi Singkat    : berfungsi untuk membalikkan arraylist dari text file
     */
    public static ArrayList<TransaksiPengembalian> updateKembali (ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException {
        try (BufferedReader read = new BufferedReader(new FileReader("pengembalian.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                kembalis.add(new TransaksiPengembalian(data[0], data[1], data[2], Integer.parseInt(data[3])));
            }
        }
        return kembalis;
    }

    /* Nama                 : Jevincent Ong
     * NIM                  : 03081210037
     * Deskripsi Singkat    : berfungsi untuk mencetak bon transaksi pengembalian dari kode Transaksi
     */
    public static void cetakRecieptKembali (String kodeTransaksi, ArrayList<TransaksiPengembalian> kembalis,  ArrayList<TransaksiPeminjaman> pinjams) {
        for (TransaksiPengembalian kembali : kembalis) {
            if (kembali.getNomorTransaksi().equalsIgnoreCase(kodeTransaksi)) {
                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeTransaksi, pinjams);
                System.out.println("Kelompok 4 Rental");
                System.out.println("---------------------");
                System.out.println("Transaksi " + kodeTransaksi);

                if (peminjaman.getMobilPinjam() != null) {
                    System.out.println("Sewa mobil " + peminjaman.getMobilPinjam().getNamaTransport() + " " + peminjaman.getMobilPinjam().getPlatTransportasi());
                } else if (peminjaman.getAngkotPinjam() != null) {
                    System.out.println("Sewa angkot " + peminjaman.getAngkotPinjam().getNamaTransport() + " " + peminjaman.getAngkotPinjam().getPlatTransportasi());
                }
                System.out.println("---------------------");
                // jika denda>deposit , bayar denda; jika deposit<denda, kasih balek deposit sisa
                if (kembali.getDenda() > 0) {
                    System.out.println("Denda yang harus yang dibayar : " + kembali.getDenda());
                } else if (kembali.getDenda() == 0) {
                    System.out.println("Tidak ada denda dan deposit");
                } else {
                    System.out.println("Deposit yang harus dikembalikan : " + kembali.getDenda()*(-1) );
                }
                break;
            }
        }
    }
}
