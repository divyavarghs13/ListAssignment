package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Transactions {
	public static List<String> rejectedList = new ArrayList<String>();

	public static List<String> findRejectedTransactions(List<String> csvData, int creditLimit) {

		List<Transactionmodel> transactions = getDataFromCSVtoList(csvData);
		List<String> keylist = new ArrayList<String>();
		List<Integer> amountlist = new ArrayList<Integer>();
		List<String> trIDlist = new ArrayList<String>();

		for (Transactionmodel transaction : transactions) {
			keylist.add(transaction.getKey());
			amountlist.add(transaction.getAmount());
			trIDlist.add(transaction.getTrID());
		}
		int lsize = keylist.size();

		if (creditLimit > 0) {
			if (lsize > 1) {
				for (int i = 0; i < (lsize - 1); i++) {
					if (amountlist.get(i) > creditLimit) {
						rejectedList.add(trIDlist.get(i).toString());
					} else {
						if (keylist.get(i).equals(keylist.get(i + 1))) {
							if (amountlist.get(i) > creditLimit) {
								rejectedList.add(trIDlist.get(i).toString());
							} else if (amountlist.get(i) + amountlist.get(i + 1) > creditLimit) {

								rejectedList.add(trIDlist.get(i + 1).toString());
							}
						}
					}
				}
			}
			 
			else if(amountlist.get(0) > creditLimit) {
				rejectedList.add(trIDlist.get(0).toString());
			}

		}
		// System.out.println("Rejected Transactions: " + rejectedList);
		return rejectedList;
	}

	/** convert the csv file to objects to match Tranaction model **/
	public static List<Transactionmodel> getDataFromCSVtoList(List<String> transactionsStrings) {
		List<Transactionmodel> transactions = new ArrayList<>();

		/**
		 * concatenate fname,lname, email to one field as Key to make the list unique
		 **/
		for (String transactionString : transactionsStrings) {
			String[] column = transactionString.split(",");
			String fName = column[0];
			String lName = column[1];
			String email = column[2];
			String key = fName + lName + email;
			int amount = Integer.valueOf(column[3]);
			String trID = column[4];
			transactions.add(new Transactionmodel(key, amount, trID));
		}
		/**
		 * Sort each row of the file in ascending order considering key field, and if
		 * key is same this will sort according to transactionID field
		 **/
		Collections.sort(transactions, Transactionmodel.NameComparator);
		return transactions;
	}


}