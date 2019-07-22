import java.util.ArrayList;

public class AccountsService {

    private DBService dbService;

    public AccountsService(DBService dbService) {
        this.dbService = dbService;
        //dbService.connect();
    }


    public String searchInRepository(String name){

        ArrayList<String> user = dbService.getAccount(name);
        StringBuilder sb = new StringBuilder();

        if (user != null){
            for (String s:user) {
                sb.append(s).append(" ");
            }
            return sb.toString().trim();
        }else return "";

    }

    public boolean changeSurname(String user, String newSurname){
        if (dbService.changeField(user, newSurname) > 0){
            return true;
        }else return false;
    }
}
