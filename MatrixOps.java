
public abstract class MatrixOps{
    /**
     * @author Martijn Courteaux 
     **/
    public static double[][] rref(double[][] mat){
        double[][] rref = new double[mat.length][mat[0].length];

        /* Copy matrix */
        for (int r = 0; r < rref.length; ++r)
        {
            for (int c = 0; c < rref[r].length; ++c)
            {
                rref[r][c] = mat[r][c];
            }
        }

        for (int p = 0; p < rref.length; ++p)
        {
            /* Make this pivot 1 */
            double pv = rref[p][p];
            if (pv != 0)
            {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < rref[p].length; ++i)
                {
                    rref[p][i] *= pvInv;
                }
            }

            /* Make other rows zero */
            for (int r = 0; r < rref.length; ++r)
            {
                if (r != p)
                {
                    double f = rref[r][p];
                    for (int i = 0; i < rref[r].length; ++i)
                    {
                        rref[r][i] -= f * rref[p][i];
                    }
                }
            }
        }
        return rref;
    }

    public static double[][] rrefNoStasis(double[][] input){
        double total = input[0].length;
        double bases = input.length;
        for(int i = 1; i < bases; i++){
            input = columnSwap(input,i,(int)(Math.round(total/bases)*i));
        }
        input = rref(input);
        input = rref(input);
        printMatrix(input);
        for(double i = bases-1; i > 0; i--){
            input = columnSwap(input,(int)i,(int)(Math.round(total/bases)*i));
        }
        printMatrix(input);
        return input;
    }

    public static double[][] columnSwap(double[][] input, int col1, int col2){
        for(int i = 0; i < input.length; i++){
            double temp = input[i][col1];
            input[i][col1] = input[i][col2];
            input[i][col2] = temp;
        }
        return input;
    }

    public static void printMatrix(double[][] input){
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                System.out.print(Math.round(input[i][j]*100.0)/100.0 + "\t");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
}