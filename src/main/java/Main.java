public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        AccountsService ac = new AccountsService(dbService);

        System.out.println(ac.searchInRepository("admin1"));
        dbService.closeConnection();
    }
}
