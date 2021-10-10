package naivebayes;

import java.util.Scanner;

/**
 * 
 * @author Made Rahano Satryani Widhi
 * @NRP 2110191028
 * @kelas 3 D4 IT A
 */
public class NaiveBayes {
    static DataTrainingCuaca dataCuaca = new DataTrainingCuaca();
    static DataTrainingBuah dataBuah = new DataTrainingBuah();
    static DataTrainingHypertensi dataHypertensi = new DataTrainingHypertensi();
    private static final int jumlahBaris = dataHypertensi.dataTraining.length;
    private static final int jumlahKolom = dataHypertensi.dataTraining[0].length;
    static String[][] atribut = new String[jumlahBaris][jumlahKolom];
    static String[] label = new String[jumlahBaris];
    static String[] key = new String[2];
    static int[] jumlahKey = new int[2];
    
    public static void main(String[] args) {
        String[] input = new String[jumlahKolom - 1];
        float[] hasil = new float[2];
        key[0] = "Ya";
        key[1] = "Tidak";
        
        input = inputDataTestingHypertensi();
        separatingData(dataHypertensi.dataTraining);
        hasil = naiveBayes(input);
        for (int i = 0; i < 2; i++) {
            System.out.println("P(X1="+input[0]+",X2="+input[1]+" | Y="+key[i]+") = "+hasil[i]);
        }
        if(hasil[0] > hasil[1])
            System.out.println("Apakah ia memiliki hipertensi atau tidak? = Ya");
        else
            System.out.println("Apakah ia memiliki hipertensi atau tidak? = Tidak");
    }
    
    private static String[] inputDataTestingBuah() {
        Scanner input = new Scanner(System.in);
        String[] dataTesting = new String[jumlahKolom-1];
        boolean[] check = new boolean[jumlahKolom-1];
        
        do{
            System.out.print("Panjang (Panjang/Sedang/Pendek)\t: ");
            dataTesting[0] = input.nextLine();
            if (dataTesting[0].equalsIgnoreCase("Panjang") || 
                    dataTesting[0].equalsIgnoreCase("Sedang") ||
                    dataTesting[0].equalsIgnoreCase("Pendek")) {
                check[0] = true;
            } else {
                System.out.println("Salah Input!\n");
                dataTesting[0] = "";
            }
        }while(check[0] == false);
        
        do{
            System.out.print("Lebar (Panjang/Sedang/Pendek)\t: ");
            dataTesting[1] = input.nextLine();
            if (dataTesting[1].equalsIgnoreCase("Panjang") || 
                    dataTesting[1].equalsIgnoreCase("Sedang") ||
                    dataTesting[1].equalsIgnoreCase("Pendek")) {
                check[1] = true;
            } else {
                System.out.println("Salah Input!\n");
                dataTesting[1] = "";
            }
        }while(check[1] == false);
        System.out.println("");
        
        return dataTesting;
    }
    
    private static String[] inputDataTestingHypertensi() {
        Scanner input = new Scanner(System.in);
        String[] dataTesting = new String[jumlahKolom-1];
        boolean[] check = new boolean[jumlahKolom-1];
        
        do{
            System.out.print("Umur (Muda/Paruh Baya/Tua)\t\t: ");
            dataTesting[0] = input.nextLine();
            if (dataTesting[0].equalsIgnoreCase("Muda") || 
                    dataTesting[0].equalsIgnoreCase("Paruh Baya") || 
                    dataTesting[0].equalsIgnoreCase("Tua")) {
                check[0] = true;
            } else {
                System.out.println("Wrong Input!\n");
                dataTesting[0] = "";
            }
        }while(check[0] == false);
        
        do{
            System.out.print("Kegemukan (Gemuk/Sangat Gemuk/Terlalu Gemuk)\t: ");
            dataTesting[1] = input.nextLine();
            if (dataTesting[1].equalsIgnoreCase("Gemuk") || 
                    dataTesting[1].equalsIgnoreCase("Sangat Gemuk") ||
                    dataTesting[1].equalsIgnoreCase("Terlalu Gemuk")) {
                check[1] = true;
            } else {
                System.out.println("Wrong Input\n");
                dataTesting[1] = "";
            }
        }while(check[1] == false);
        System.out.println("");
        
        return dataTesting;
    }
    
    private static String[] inputDataTestingCuaca() {
        Scanner input = new Scanner(System.in);
        String[] dataTesting = new String[jumlahKolom-1];
        boolean[] check = new boolean[jumlahKolom-1];
        
        do{
            System.out.print("Cuaca (Cerah/Hujan)\t: ");
            dataTesting[0] = input.nextLine();
            if (dataTesting[0].equalsIgnoreCase("Cerah") || 
                    dataTesting[0].equalsIgnoreCase("Hujan") || 
                    dataTesting[0].equalsIgnoreCase("-")) {
                check[0] = true;
            } else {
                System.out.println("Salah Input!\n");
                dataTesting[0] = "";
            }
        }while(check[0] == false);
        
        do{
            System.out.print("Kecepatan Angin (Pelan/Kencang)\t: ");
            dataTesting[1] = input.nextLine();
            if (dataTesting[1].equalsIgnoreCase("Pelan") || 
                    dataTesting[1].equalsIgnoreCase("Kencang") ||
                    dataTesting[1].equalsIgnoreCase("-")) {
                check[1] = true;
            } else {
                System.out.println("Salah Input!\n");
                dataTesting[1] = "";
            }
        }while(check[1] == false);
        
        do{
            System.out.print("Temperatur (Normal/Tinggi)\t: ");
            dataTesting[2] = input.nextLine();
            if (dataTesting[2].equalsIgnoreCase("Normal") || 
                    dataTesting[2].equalsIgnoreCase("Tinggi") ||
                    dataTesting[2].equalsIgnoreCase("-")) {
                check[2] = true;
            } else {
                System.out.println("Salah Input!\n");
                dataTesting[2] = "";
            }
        }while(check[2] == false);
        System.out.println("");
        
        return dataTesting;
    }
    
    private static void separatingData(String[][] data) {
        for (int i = 0; i < jumlahBaris; i++) {
            for (int j = 0; j < jumlahKolom; j++) {
                if(j == jumlahKolom - 1)
                    label[i] = data[i][j];
                else 
                    atribut[i][j] = data[i][j];
            }
        }
        
        for (int i = 0; i < label.length; i++) {
            if(label[i] == key[0])
                jumlahKey[0]++;
            else if (label[i] == key[1])
                jumlahKey[1]++;
        }
    }
    
    private static float[] naiveBayes(String[] dataTesting) {
        int[] jumlahPX1 = new int[2];
        int[] jumlahPX2 = new int[2];
        float[] probabilitasPX1 = new float[2];
        float[] probabilitasPX2 = new float[2];
        float[] probabilitasY = new float[2];
        float[] probabilitasAkhir = new float[2];
        
        for (int i = 0; i < jumlahBaris; i++) {
            for (int j = 0; j < jumlahKolom - 1; j++) {
                if(dataTesting[j] == "-")
                    continue;
                
                if((atribut[i][j].equals(dataTesting[0])) && (label[i].equals(key[0])))
                    jumlahPX1[0]++;
                else if((atribut[i][j].equals(dataTesting[0])) && (label[i].equals(key[1])))
                    jumlahPX1[1]++;
                
                if((atribut[i][j].equals(dataTesting[1])) && (label[i].equals(key[0])))
                    jumlahPX2[0]++;
                else if((atribut[i][j].equals(dataTesting[1])) && (label[i].equals(key[1])))
                    jumlahPX2[1]++;
            }
        }
        
        for (int i = 0; i < 2; i++) {
            probabilitasY[i] = ((float) jumlahKey[i] / (jumlahKey[0]+jumlahKey[1]));
            probabilitasPX1[i] = ((float) jumlahPX1[i]/jumlahKey[i]);
            probabilitasPX2[i] = ((float) jumlahPX2[i]/jumlahKey[i]);
        }

        
        for (int i = 0; i < 2; i++)  
            probabilitasAkhir[i] = (float)(probabilitasPX1[i] * probabilitasPX2[i]) * probabilitasY[i];
    
        return probabilitasAkhir;
    }
}
