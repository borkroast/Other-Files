import java.util.Scanner;

public class HexDex {
// Hex code

    public static short hexCharDecode(char hexChar){ //Decode single character
        short ret;
        char hexUpChar = Character.toUpperCase(hexChar);

        if ('A' <= hexUpChar && 'F' >= hexUpChar) //If character is letter
            ret = (short) (hexUpChar - 'A' + 10);
        else if ('0' <= hexUpChar && '9' >= hexUpChar) //If character is number
            ret = (short) (-1 * (48 - hexUpChar));
        else
            ret = 0;

        return ret;
    }


// Hex Code
    public static long hexStringDecode(String hex) {   //Decode whole hex string

        long decimal = 0;

        //Formats string
        hex = hex.trim();
        if (hex.charAt(1) == 'x') {

            hex = hex.substring(2); //gets rid of 0x
        }

        hex = hex.toUpperCase();



        int[] integerArray = new int[hex.length()]; //Integer array


        for (int i = 0; i < hex.length(); i++) {

            integerArray[i] = hexCharDecode(hex.charAt(i));

        }

        for (int i = hex.length(); i > 0; --i) { //Starts from right and goes left

            decimal += (int) (Math.pow(16, hex.length() - i) * integerArray[i-1]);

        }

        return decimal;
    }
// binary string code
    public static long binaryStringDecode(String bin){ //Convert from binary to dec

        long decimal = 0;

        //Formats string

        if (bin.charAt(1) == 'b') {

            bin = bin.substring(2); //gets rid of 0bad
        }

        int[] integerArray = new int[bin.length()]; //Integer array

        for (int i = 0; i < bin.length(); i++) {
            integerArray[i] = (short) (-1 * (48 - bin.charAt(i)));
        }

        for (int i = bin.length(); i > 0; --i) { //Starts from right and goes left

            decimal += (int) (Math.pow(2, bin.length() - i) * integerArray[i-1]);

        }

        return decimal;
    }


    // Binary to hex conversion
    public static String binaryToHex(String bin){
        long decimal = binaryStringDecode(bin);
        long hex = 0;
        String ret = "";
        long temp;
        char charTemp = ' ';

        int j = 0;
        for(int i = 7; i > -1; i--) {

            if (decimal / ((long) Math.pow(16,i)) != 0) {
                temp = decimal / ((long) Math.pow(16,i));
                decimal = decimal - ((long) Math.pow(16,i) * temp);

                if (temp < 10)
                    charTemp = (char) (temp + 48);
                else
                    charTemp = (char) (temp + 55);
            }
            else{
                charTemp = ' ';

            }

            ret = ret + charTemp;
        }

        return ret.trim();
    }


    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in); //Import scanner

        String hexString;
        String binaryString;
        int userChoice;


        while(true){
            //Menu
            System.out.println("Choose an option: ");
            System.out.println("1. Decode a hex string.");
            System.out.println("2. Decode a binary string.");
            System.out.println("3. Convert binary to hex.");
            System.out.println("4. Quit.");

            userChoice = scnr.nextInt();

            switch (userChoice){
                case 1:
                    System.out.println("Please enter the hex string: ");
                    hexString = scnr.next();
                    System.out.println("Result: " + hexStringDecode(hexString));
                    break;
                case 2:
                    System.out.println("Please enter the binary string: ");
                    binaryString = scnr.next();
                    System.out.println("Result: " + binaryStringDecode(binaryString));
                    break;
                case 3:
                    System.out.println("Please enter the binary string: ");
                    binaryString = scnr.next();
                    System.out.println("Result: " + binaryToHex(binaryString));
                    break;
                case 4:
                    System.out.println("Quitting program.");
                    System.exit(0);
                    break;
            }
        }
    }
}

