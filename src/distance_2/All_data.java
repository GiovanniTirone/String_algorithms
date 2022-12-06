package distance_2;

public class All_data {

    private int distance_matrix [][];

    private String operations_matrix [][];

    private String sub_problems_matrix [][];

    public All_data (int distance_matrix [][] , String operations_matrix [][], String sub_problems_matrix [][] ){
        this.distance_matrix = distance_matrix;
        this.operations_matrix = operations_matrix;
        this.sub_problems_matrix = sub_problems_matrix;
    }

    public int[][] getDistance_matrix() {
        return distance_matrix;
    }

    public String[][] getOperations_matrix() {
        return operations_matrix;
    }

    public String[][] getSub_problems_matrix() {
        return sub_problems_matrix;
    }
}
