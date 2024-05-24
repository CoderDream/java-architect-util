public class Main {

    public static void main(String[] args){

        int[] data = {24, 18, 12, 9, 16, 66, 32, 4};

//        LinearSearch ls = new LinearSearch();

        int res = LinearSearch.search(data, 16);
        System.out.println(res);

        int res2 = LinearSearch.search(data, 666);
        System.out.println(res2);
    }
}