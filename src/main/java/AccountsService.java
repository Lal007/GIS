import java.util.ArrayList;
import java.util.stream.Collectors;

public class AccountsService {

    private DBService dbService;

    public AccountsService(DBService dbService) {
        this.dbService = dbService;
        dbService.connect();
    }


    public String searchInRepository(String name){

        ArrayList<String> user = dbService.getAccount(name);
        StringBuilder sb = new StringBuilder();

        if (user != null){
            return user.stream().collect(Collectors.joining(" "));
        }else return "";

    }

    public boolean changeSurname(String user, String newSurname){
        if (dbService.changeField(user, newSurname) > 0){
            return true;
        }else return false;
    }
}
