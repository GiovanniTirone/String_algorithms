package distance_all_data;

public class Distance_datas {

    private int distance_matrix [][];

    private String operations_matrix [][];

    private String sub_problems_matrix [][];

  //  private String paths_matrix [][];

    public Distance_datas(int distance_matrix [][] , String operations_matrix [][], String sub_problems_matrix [][]){
        this.distance_matrix = distance_matrix;
        this.operations_matrix = operations_matrix;
        this.sub_problems_matrix = sub_problems_matrix;
     //   this.paths_matrix = paths_matrix;
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

  //  public String [][] getPaths_matrix() {
 //      return paths_matrix;
 //   }
}
