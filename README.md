# UAS Praktikum Pengujian Perangkat Lunak

*NPM*: 230309006  
*Nama*: Dimas Angka Wijaya  
*Kelas*: 3 A  
*Dosen Pengampu*: Krisna Nuresa Qodri  
*Program Studi*: DIV Rekayasa Keamanan Siber, Politeknik Negeri Cilacap

---

## 🎯 Tujuan
Laporan ini merupakan hasil ujian akhir semester (UAS) Praktikum Pengujian Perangkat Lunak. Fokus pengujian adalah pada aplikasi e-commerce demo [Demoblaze](https://www.demoblaze.com/) dengan tujuan:
- Memverifikasi alur pengguna inti (login, keranjang, checkout)
- Mengidentifikasi bug kritis yang berdampak pada pengalaman pengguna
- Mendokumentasikan hasil eksekusi manual dan otomasi

---

## 📁 Struktur Repo
UASPPL_3A_230309006/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com.praktikum.testing.otomation.pages/
│ └── test/
│ └── java/
│ └── com.praktikum.testing.otomation.tests/
├── target/
├── pom.xml
├── testng.xml
├── README.md


---

## 🧪 Hasil Pengujian
- *Total Test Case*: 10 (7 positive, 3 negative)
- *Bug Ditemukan*: 3 (semua Critical)
  - *BUG-001*: Guest cart hilang setelah login
  - *BUG-002*: Checkout tanpa login
  - *BUG-003*: Place Order saat keranjang kosong
- *Status Otomasi*: Script Selenium + TestNG siap dieksekusi

---

## 📝 Dokumen Pendukung
Semua dokumen pendukung tersedia dalam folder target/ atau di file UASPPL_3A_230309006.PDF:
- Test Strategy Document
- Test Plan Document
- Manual Testing Execution Report
- Bug Reports Document
- Laporan Akhir (PDF)

---

## 🚀 Cara Menjalankan Script Otomasi
1. Pastikan Java 11+ dan Maven terinstal
2. Clone repo ini
3. Buka terminal di root folder
4. Jalankan:
   ```bash
   mvn clean test
