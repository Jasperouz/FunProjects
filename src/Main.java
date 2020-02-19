import java.util.Arrays;

public class Main {

    public static boolean isValueAllowedInRow(final int[] oneRow, final int value){
        if (value<0){
            return false;
        }

        return Arrays.stream(oneRow).noneMatch(eachItem -> eachItem == value);
    }



    public static void main(String[] args) {
        int result = calculateNumberOfOnesNeeded(9);
    }






    public static int calculateNumberOfOnesNeeded(int n){
        // init stuff
        int[] oneRowInResultArray = new int[n];
        int[][] dynamicProgrammingmatrix = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    dynamicProgrammingmatrix[i][j] = j + 1;
                } else {
                    dynamicProgrammingmatrix[i][j] = -1;
                }
            }
        }

        int result =-1;
        outerLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //für alle werte oberhalb der dreiecksmatrix
                if (j > i) {
                    int valueForTimes=-1;
                    int valueForPlus=-1;
                    for (int k = 0; k < (i+1)/2; k++)   {
                        int randO=k;
                        int randU = i-1-k;
                        for (int l = 0; l < n; l++) {
                            for (int m = 0; m < n; m++) {
                                if ((dynamicProgrammingmatrix[randO][l]==-1 ||dynamicProgrammingmatrix[randU][m]==-1)){
                                    continue;
                                }
                                valueForTimes = dynamicProgrammingmatrix[randO][l]*dynamicProgrammingmatrix[randU][m];
                                valueForPlus = dynamicProgrammingmatrix[randO][l]+dynamicProgrammingmatrix[randU][m];
                                if (valueForTimes>n){
                                    valueForTimes=-1;
                                }
                                if (valueForPlus>n){
                                    valueForPlus=-1;
                                }
                                if (valueForPlus==valueForTimes){
                                    valueForPlus=-1;
                                }
                                if (valueForPlus==n||valueForTimes==n){
                                    result=i+1;
                                    break outerLoop;
                                }
                                if (isValueAllowedInRow(dynamicProgrammingmatrix[i],valueForTimes)&&isValueAllowedInRow(dynamicProgrammingmatrix[i],valueForPlus)){
                                    dynamicProgrammingmatrix[i][j]=valueForTimes;
                                    dynamicProgrammingmatrix[i][j+1]=valueForPlus;
                                }
                                else if (isValueAllowedInRow(dynamicProgrammingmatrix[i],valueForTimes)){
                                    dynamicProgrammingmatrix[i][j]=valueForTimes;
                                }
                                else if (isValueAllowedInRow(dynamicProgrammingmatrix[i], valueForPlus)){
                                    dynamicProgrammingmatrix[i][j]=valueForPlus;
                                }
                            }

                        }
                    }

                }
            }

        }
        if (result == -1 ){
            result = n;
        }
        for (int i = 0; i < n ; i++) {
            System.out.println(Arrays.toString(dynamicProgrammingmatrix[i]));
        }
        //System.out.println(Arrays.deepToString(dynamicProgrammingmatrix));
        System.out.println("Die Lösung auf Eingabe n = "+n +" ist: "+result);
        return 0;
    }




}
