import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class arrayversion {
    public  static void menu() {
        System.out.println("-----MENU-----\n" +
                "100 or VFQ: View all Fuel Queues.\n" +
                "101 or VEQ: View all Empty Queues.\n" +
                "102 or ACQ: Add customer to a Queue.\n" +
                "103 or RCQ: Remove a customer from a Queue. (From a specific location)\n" +
                "104 or PCQ: Remove a served customer.\n" +
                "105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)\n" +
                "106 or SPD: Store Program Data into file. 107 or LPD: Load Program Data from file.\n" +
                "107 or LPD: Load Program Data from file\n" +
                "108 or STK: View Remaining Fuel Stock.\n" +
                "109 or AFS: Add Fuel Stock.\n" +
                "999 or EXT: Exit the Program.");

    }
    public static Integer valid(String choice) {
        String[][] choice1 = {{"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "999"}, {"VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS", "EXT"}};
        int num = 100;
        for (int i = 0; i < 10; i++) {
            if ((Objects.equals(choice1[0][i], choice)) || (Objects.equals(choice1[1][i], choice))) {
                //value of i is saved in num, so we can use it later
                System.out.println("you choose " + choice1[0][i] + ", " + choice1[1][i]);
                num = i;
                break;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//pump array
        String pump[][] = new String[3][6];
        for (String[] row : pump) {
            java.util.Arrays.fill(row, null);
        }

        menu();
        boolean loop = true;
        int stock = 6600;

        while (loop) {

            boolean found = true;
            System.out.println("\n pls choose an option ");
            String choice = scanner.nextLine();
            //val is called validation
            int val = valid(choice);

            switch (val) {
                case 0 -> {
                    int out = 0;
                    int in = 0;
                    System.out.println("View all Fuel Queues");
                    for (out = 0; out < 3; out++) {
                        for (in = 0; in < 6; in++) {
                            if (pump[out][in] == null) {
                                System.out.println("pump " + out + ",row " + in + " : empty");

                            } else  {
                                System.out.println("pump " + out + ",row " + in + " : "+pump[out][in]);
                            }
                        }
                    }


//view al empty elements
                }
                case 1 -> {
                    int out1 = 0;
                    int in1 = 0;
                    System.out.println("View all Empty Queues.");
                    for (out1 = 0; out1 < 3; out1++) {
                        for (in1 = 0; in1 < 6; in1++) {
                            if (pump[out1][in1] == null) {
                                System.out.println("pump " + out1 + ",row " + in1 + " is empty");

                            }
                        }
                    }


                }
                case 2-> {

                    int in2 = 0;
                    System.out.println("Add customer to a Queue");
                    System.out.println("pls enter customer name");
                    String name = scanner.nextLine();
//when the first empty slot is found the customer is added.
                    System.out.println("pls enter which pump (0,1,2)");
                    int out2 = scanner.nextInt();

//checking whether the pump is full
                    if(pump[out2][5] ==null) {
                        for (in2 = 0; in2 < 6; in2++) {
                            if (Objects.equals(pump[out2][in2], null)) {
                                System.out.println("a customer "+name+" is added to pump " + out2 + ",row " + in2 + ".");
                                pump[out2][in2] =name;
                                break;
                            }
                        }
                    }else{
                        System.out.println(" pump " + out2 + " is full.");
                    }

//removing a customer from a specific location
                }case 3-> {
                    System.out.println("Remove a customer from a Queue. (From a specific location)");
                    int out3 = 0;
                    int in3 = 0;
                    System.out.println("pls enter from which row you want to remove(0,1,2)");
                    out3 = scanner.nextInt();
                    System.out.println("pls enter from which column you want to remove(0,1,2,3,4,5)");
                    in3 = scanner.nextInt();
                    if (Objects.equals(pump[out3][in3], null)) {
                        System.out.println("there is no customer at the given location");
                    } else {
                        pump[out3][in3] = null;
                        System.out.println("pump " + out3 + ",row " + in3 + " customer has been removed.");
                        for (int inLoop = in3 + 1; inLoop < 6; inLoop++) {
                            if (pump[out3][inLoop] != null) {
                                String temp = pump[out3][inLoop - 1];
                                pump[out3][inLoop - 1] = pump[out3][inLoop];
                                pump[out3][inLoop] = temp;
                            } else{
                                break;

                            }

                        }
                    }
                }
                case 4-> {
                    System.out.println("Remove a served customer.");
                    int out4 = 0;
                    if(stock==0){
                        System.out.println("NO stocks remaining");
                    }else {
                        System.out.println("pls enter from which row you want to remove(0,1,2)");
                        out4 = scanner.nextInt();
                        for (int in4 = 0; in4 < 6; in4++) {

                            if (!Objects.equals(pump[out4][in4], null)) {
                                System.out.println("pump " + out4 + ",row " + in4 + " severed customer has been removed.");
                                pump[out4][in4] = null;
                                stock -= 10;

                                for (int inLoop1 = in4 + 1; inLoop1 < 6; inLoop1++) {

                                    String temp = pump[out4][inLoop1 - 1];
                                    pump[out4][inLoop1 - 1] = pump[out4][inLoop1];
                                    pump[out4][inLoop1] = temp;
                                }
                            } else {
                                System.out.println("pump " + out4 + " has no customers.");
                            }
                            break;
                        }
                        if(stock<=500){
                            System.out.println("Only "+stock+" liters stocks remaining");
                        }
                    }
                }
                case 5-> {
                    System.out.println("View Customers Sorted in alphabetical order");
                    int out5 = 0;
                    int in5 = 0;
                    int count=0;
                    //0
                    boolean fo=true;
                    while(fo){
                        for (in5= 0;in5 <pump.length; in5++){
                            if(pump[0][in5+1]!=null ){
                                if(pump[0][in5].compareTo(pump[0][in5+1])>0){

                                    String temp = pump[0][in5];
                                    pump[0][in5] = pump[0][in5+1];
                                    pump[0][in5+1]=temp;
                                }
                            }
                            count++;
                        }
                        if(pump.length*pump.length==count){
                            fo=false;
                        }
                    }
                    //1
                    out5 = 0;
                    in5 = 0;
                    count=0;
                    fo=true;
                    while(fo){
                        for (in5= 0;in5 <pump.length; in5++){
                            if(pump[1][in5+1]!=null ){
                                if(pump[1][in5].compareTo(pump[1][in5+1])>0){

                                    String temp = pump[1][in5];
                                    pump[1][in5] = pump[1][in5+1];
                                    pump[1][in5+1]=temp;
                                }
                            }
                            count++;
                        }
                        if(pump.length*pump.length==count){
                            fo=false;
                        }
                    }
                    //2
                    out5 = 0;
                    in5 = 0;
                    count=0;
                    fo=true;
                    while(fo){
                        for (in5= 0;in5 <pump.length; in5++){
                            if(pump[2][in5+1]!=null ){
                                if(pump[2][in5].compareTo(pump[2][in5+1])>0){

                                    String temp = pump[2][in5];
                                    pump[2][in5] = pump[2][in5+1];
                                    pump[2][in5+1]=temp;
                                }
                            }
                            count++;
                        }
                        if(pump.length*pump.length==count){
                            fo=false;
                        }
                    }
                }
                case 6-> {
                    System.out.println("Store Program Data into file.");
                    int out6=0;
                    int in6=0;
                    System.out.println("please enter the file name ");
                    String fileName=scanner.nextLine();

                    try {
                        File txt = new File(fileName+".txt");
                        boolean x = (txt.createNewFile());
                        if (!x) {
                            System.out.println("You have already created txt file using this name!");
                        } else {
                            PrintWriter writeFile = new PrintWriter(txt);
                            for (out6 = 0; out6 < 3; out6++) {
                                for (in6 = 0; in6 < 6; in6++) {
                                    if (pump[out6][in6] == null) {
                                        writeFile.println("pump " + out6 + ",row " + in6 + " is empty");

                                    }else{
                                        writeFile.println("pump " + out6 + ",row " + in6 + " : "+pump[out6][in6]);
                                    }
                                }
                            }
                            writeFile.close();
                            System.out.println("\ndata saved into file");
                        }
                    }catch (IOException e){
                        System.out.println("An error occurred.");
                    }
                }
                case 7-> {
                    System.out.println("Load Program Data from file.");
                    System.out.println("please enter the file name ");
                    String fileName=scanner.nextLine();
                    try{
                            FileReader load = new FileReader(fileName+".txt");
                            int line = load.read();
                            while(line != -1){
                                System.out.print((char)line);
                                line=load.read();
                            }
                            load.close();
                    }catch(Exception e){
                            System.out.println("couldn't find a file named "+fileName);
                    }
                }
                case 8-> {
                    System.out.println("View Remaining Fuel Stock.");
                    System.out.println(stock);
                }
                case 9-> {
                    System.out.println("Add Fuel Stock.");
                    System.out.println("Enter the amount of stock to add.");
                    int addStock= scanner.nextInt();
                    stock+=addStock;

                }
                case 10-> {
                    System.out.println("Exit the Program.");
                    loop=false;
                }
                default ->{
                    System.out.println("You have entered a wrong option , please re-enter !");
                }
            }
        }
    }
}





