import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TestAccount {

    DBService dbService = mock(DBService.class);

    @Test
    public void testChangeSurnameCorrect(){

        AccountsService ac = new AccountsService(dbService);
        when(dbService.changeField(anyString(), anyString())).thenReturn(1);

        boolean test1 = ac.changeSurname("user1", "Aleksandrov");
        assertTrue(test1);

        verify(dbService).changeField("user1", "Aleksandrov");
    }

    @Test
    public  void testChangeSurnameIncorrect(){
        AccountsService ac = new AccountsService(dbService);
        when(dbService.changeField(anyString(), anyString())).thenReturn(0);

        boolean test1 = ac.changeSurname("user1", "Aleksandrov");
        assertFalse(test1);

        verify(dbService).changeField("user1", "Aleksandrov");
    }

    @Test
    public void testSearchCorrect(){

        AccountsService ac = new AccountsService(dbService);
        ArrayList<String> correct = new ArrayList<String>(Arrays.asList("2", "admin1", "Andrei", "Sidorov", "administrator"));
        when(dbService.getAccount(anyString())).thenReturn(correct);

        String test1 = ac.searchInRepository("admin1");
        assertEquals("2 admin1 Andrei Sidorov administrator", test1);

        verify(dbService).getAccount("admin1");
    }

    @Test
    public void testSearchIncorrect(){
        AccountsService ac = new AccountsService(dbService);
        when(dbService.getAccount(anyString())).thenReturn(null);

        String test1 = ac.searchInRepository("admin1");
        assertEquals("", test1);

        verify(dbService).getAccount("admin1");
    }

    @Test
    public void testConnectVerify(){
        AccountsService ac = new AccountsService(dbService);
        verify(dbService).connect();
    }

    @Test
    public void expectedValue(){
        AccountsService ac = new AccountsService(dbService);

        ArrayList<String> correct = new ArrayList<String>(Arrays.asList("1", "user1", "Ivan", "Petrov", "moderator"));
        when(dbService.getAccount("user1")).thenReturn(correct);

        String testString = ac.searchInRepository("user1");
        assertEquals("1 user1 Ivan Petrov moderator", testString);

        verify(dbService).getAccount("user1");
    }

}
