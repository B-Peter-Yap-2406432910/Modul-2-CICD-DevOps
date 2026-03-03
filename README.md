# Module 1
# Reflection 1

**Meaningful Names**
- pada code saya, saya sudah mengimplementasikan nama variabel, method, dan class yang dapat mudah dimengerti serta jelas. ex: createProduct, deleteProduct,etc.

**Single Responsibility Principle**
- pada code saya, saya sudah memisahkan masing-masing tanggung jawab code saya dengan MVC. serta semua method yang sudah saya buat hanya melakukan 1 tugas saja. 

**Secure Coding**
- saya menggunakan UUID yang diubah ke string pada saat pembentukan produk yang lebih aman dan juga sudah menerapkan beberapa metode HTTP yang sesuai pada REST

## Mistakes
- Pada fitur delete, saya masih menggunakan method GetMapping instead of DeleteMapping yang lebih sesuai pada best practice sehingga melanggar standar REST
- Redundant pada interface. In default, semua method dalam interface sudah pasti dalam bentuk public sehingga tidak perlu menambahkan public lagi

# Reflection 2
1. Saya rasa unit test memang sangat dibutuhkan untuk mencoba apakah ada celah kesalahan pada code kita. Namun dilain sisi testing cukup menyusahkan menurut saya karena untuk membuat testnya, kita harus kepikiran dulu case-casenya.
Lalu untuk jumlah unit-test yang harus dibuat per-class secara general tidak ada angka pastinya karena sangat tergantung dengan complexity dari code kita namun untuk kasus proyek ini mungkin saya akan mengestimasinya sekitar 9-10 unit test memperkirakan per 1 method bisa banyak edge casenya. 
Menurut saya salah satu cara menilai apakah test kita sudah cukup adalah coverage test yang 100%. Dalam kelas kemarin, Pak Edy sebenarnya sudah mengajarkan kepada kita bahwa tidak ada program yang aman maka dari itu, test yang banyak-banyak juga pasti nanti ada celahnya makanya dalam developing apps kita mengenal maintenance

2. Menurut saya itu akan menurunkan quality of code dari proyeknya karena akan terjadi duplikasi code atau melanggar prinsip Don't Repeat Yourself. Maksudnya adalah ketika kita membuat func test lagi maka kita pasti membuat variabel lagi seperti baseUrl, serverPort, atau method seperti @BeforeEach. Akibatnya jika suatu saat kita ingin mengubah konfigurasi setupnya (misal baseURL), kita harus mengubahnya di semua file 1 per 1 dimana tidak efisien dan berpotensi mengakibatkan human error.
Solusi yang terpikirkan oleh saya adalah dengan inheritance dimana base configuration (variabel konfigurasi sepeerti baseUrl dan method setup) bisa kita berikan di parent class sedangkan test class lainnya bisa mengextend parent class tersebut sehingga kita hanya perlu mengsetup sekali dan code jadi lebih rapi serta mudah di maintenance   

---
# Module 2
# Refleksi 1

**List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them**
- Coverage test: Pertama-tama saya coba analisis `index.html` dan lihat bagian code mana yang belum pernah saya cover. lalu saya tinggal coba buat test case tapi yang melewati path yang belum ter-cover dan coba ulang lagi sampai semua test casenya bisa 100%. tentu agar bisa mencapai 100%, saya juga harus mencoba mock sehingga saya mencoba mengerti mock dengan bantuan LLM.
- Code Smell: pada `ProductController.java` dan `ProductServiceImpl.java`, saya menggunakan anotasi @Autowired langsung pada field variabel (contoh: @Autowired private ProductService service;. Hal ini bukan merupakan best practice karena membuat class susah untuk ditest secara sendiri-sendiri sehingga diperingati oleh sonarcloud. Untuk mengatasinya, saya refactor codenya dgn mengubahnya jadi constructor injection dengan saran dari LLM. Hal ini saya lakukan dengan cara menambahkan `final` di variabel dependency lalu membuat constructor yang diatasnya diberi @Autowired
Selain itu, saya juga menerima laporan Code Smell pada `build.gradle.kts` dimana saya hanya tidak rapi saat mengelompokkan dependencynya. Jadi, saya hanya menukar testImplementation yang paling bawah dengan testRuntimeOnly yang ditengah-tengah testImplementation.
- Security Hotspot: SonarCloud mendeteksi bahwa proyek mengunduh library/dependencies dari internet tanpa adanya proses verifikasi keaslian sehingga melakukan warning dan merekomendasikan untuk membuat `verification-metadata.xml`. Karena hal ini diluar scope materi ini maka saya hanya review peringatannya dan mengubah statusnya mnejadi "Safe" saja.
- CI Pipeline Failure: Ketika saya coba run workflowsnya, ada peringatan khususnya pada `ProductControllerTest` dengan error TemplateInputException. Intinya saya hanya typo createProduct padahal seharusnya CreateProduct (nama return yang di controller beda dengan nama file html aslinya). Solusi saya hanya mengubahnya menjadi CreateProduct begitupun dengan file testnya.

**Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
implementation has met the definition of Continuous Integration and Continuous
Deployment? Explain the reasons (minimum 3 sentences)!**
- Menurut saya sudah memenuhi. Dari segi CI, sudah terpenuhi dengan pengaplikasian `ci.yml` dan `scorecard.yml`. Mereka akan mencoba compile (ci.yml), mengeksekusi test-test yang sudah dibuat (ci.yml), memeriksa code quality (ci.yml), lalu mengecek keamanan (scorecard.yml) dari code saya ketika saya melakukan push atau PR (Pull Request). Ketiga workflow itu memastikan bahwa code yang saya submit selalu terintegrasi dengan baik dan bebas dari error sebelum dimerge ke main.
Dari segi CD, kriteria ini sudah terpenuhi juga karena `deploy.yml`. workflow ini akan dijalankan hanya ketika kita ingin mengepush/commit ke main dimana untuk auto-deploy app kita sehingga kita tidak perlu deploy manual lagi.

# Module 3
# Refleksi

**Explain what principles you apply to your project!**
- S (Single Responsibility Principle): Memindahkan CarController ke file khusus untuk class itu sendiri untuk mengimplementasikan SRP. dimana 1 file atau class khusus untuk controller masing-masing aspek saja.
Lalu untuk pemasukan UUID juga saya pindahkan ke service rather than di repository karena tugas repository adalah hanya untuk menyimpan CRUD bukan untuk business logic. Saya juga mengubah edit pada  
- O (Open-Close Principle): 
- L (Liskov Substitution Principle): menghapus inheritance pada CarController terhadap ProductController karena Car tidak seharusnya mengimplementasikan method-method yang ada di Product.
- I (Interface Segregation Principle): 
- D (Dependency Inversion Principle): 

*Extra (Clean Code):*
- DRY (Don't Repeat Yourself): saya menghapus loop pada method update di CarRepository karena mengeloop lagi untuk mencari id Car padahal kita sudah memiliki function tersendiri untuk itu.
- YAGNI (You Aren't Gonna Need It): saya menghapus atribut static int id pada CarRepository karena tampaknya kita tidak menggunakan id berupa angka tapi berupa UUID. karena tidak digunakan sama sekali maka saya hapus.

**Explain the advantages of applying SOLID principles to your project with examples.**
- lorem ipsum

**Explain the disadvantages of not applying SOLID principles to your project with examples.**
- lorem ipsum