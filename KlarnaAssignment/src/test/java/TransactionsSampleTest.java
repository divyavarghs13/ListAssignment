package test.java;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import Assignment.Transactions;


public class TransactionsSampleTest {

    @Test
    public void shouldReturnEmptyListIfThereIsNoTransactions() {
        assertThat(Transactions.findRejectedTransactions(new ArrayList<>(), 0).size(), is(0));
    }

    @Test
    public void shouldReturnEmptyListIfThereIsATransactionWithinCreditLimit() {
        List<String> transactions = Arrays.asList("John,Doe,john@doe.com,200,TR0001");

        List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

        assertThat(rejectedTransactions.size(), is(0));
    }

    @Test
      public void shouldReturnTransationThatIsOverCreditLimit() {
          List<String> transactions = Arrays.asList("John,Doe,john@doe.com,201,TR0001");

          List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

          assertThat(rejectedTransactions, is(Arrays.asList("TR0001")));
      }

 @Test
    public void shouldReturnTransationThatIsOverCreditLimit2List() {
        List<String> transactions = Arrays.asList("John,Doe,john@doe.com,20,TR0001","John,Doe,john@doe.com,209,TR0002");

        List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

        assertThat(rejectedTransactions, is(Arrays.asList("TR0002")));
    }

    
}
