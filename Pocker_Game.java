package Zensar_cisco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Pocker_Game {
	//method for get random card
	static int getCard() {
		return (int) (Math.random() * 52);
	}

	//to check card's are same colors or not
	static boolean allsamecolor(ArrayList<String> arr) {
		String tmp = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			if (!(tmp.equals(arr.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	//to check card's are same numbers or not
	static boolean allsameNumber(ArrayList<Integer> arr) {
		int tmp = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			if (tmp != arr.get(i)) {
				return false;
			}
		}
		return true;
	}
	//to check card's are in sequence or not(color wont be same)
	static boolean SequenceWithoutColor(ArrayList<Integer> arr) {
		Collections.sort(arr);
		int tmp = arr.get(0);
		if (tmp == arr.get(1)) {
			if (arr.get(2) == arr.get(1))
				return true;
			else {
				return false;
			}
		} else {
			return false;
		}

	}

	//to check card's have pair or not
	static boolean pair(ArrayList<Integer> arr) {
		int tmp = arr.get(0);
		int cnt = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (tmp == arr.get(i)) {
				cnt++;
			}
		}
		if (cnt == 2) {
			return true;
		}
		int tmp1 = arr.get(1);
		int cnt1 = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (tmp1 == arr.get(i)) {
				cnt1++;
			}
		}
		if (cnt1 == 2) {
			return true;

		}
		return false;

	}

	//to find out High Card from Cards
	static int highCard(ArrayList<Integer> arr) {
		Collections.sort(arr);

		return arr.get(arr.size() - 1);

	}

	
	//to get addition of all 3 cards
	static int addition(ArrayList<Integer> arr) {
		int total = 0;

		for (int digit : arr) {
			total += digit;
		}

		return total;

	}
//main method
	public static void main(String[] args) {
		String[] cardColor = { "Heart", "Diamond", "Club", "Spade" };
		String[] cardNo = { "02", "03", "04", "05", "06", "07", "08", "09", "10", "0J", "0Q", "0K", "0A" };
		String totalCardsArr[] = new String[52];
		//priorityMap  for storing player id and  priority 
		HashMap<Integer, Integer> priorityMap = new HashMap<>();
		//for storing playerid and additon of it's card
		HashMap<Integer, Integer> fAddMap = new HashMap<>();
		//store playerid and same max priority
		HashMap<Integer, Integer> maxAdd = new HashMap<>();
		int increment = 0;
		//creting array of 52 cards
		for (int i = 0; i < cardNo.length; i++) {
			for (int j = 0; j < cardColor.length; j++) {
				totalCardsArr[increment] = cardNo[i] + cardColor[j];
				increment++;
			}
		}
		
		//scanner for input data from user
		Scanner sc = new Scanner(System.in);
		
			System.out.println("how many players do we have ");
			int totalPlayer = sc.nextInt();
			while (totalPlayer < 2) {
				System.out.println(" players must be greater than 1");
				System.out.println("how many players do we have ");
				totalPlayer = sc.nextInt();
			}
			System.out.println("choose mode");
			System.out.println(" 1: Normal Mode ");
			System.out.println("2 : Any Card Mode");
			System.out.println("3: Low Card Mode");
			System.out.println("4: High Card Mode");
			int mode = sc.nextInt();
			//to get number from 1 to 4 only
			while (true) {
				if (mode >= 1 && mode <= 4) {
					break;

				} else {
					System.out.println("choose coorect mode");
					mode = sc.nextInt();

				}
			}

			String[] players = new String[totalPlayer];
			HashMap<Integer, ArrayList<String>> hmap = new HashMap<>();
            //create player 
			for (int playerID = 0; playerID < totalPlayer; playerID++) {
				System.out.println("Enter name of " + (playerID + 1) + " player");
				players[playerID] = sc.next();
				//cards of each player
				ArrayList<String> cards = new ArrayList<String>();
				//number on  cards
				ArrayList<Integer> noOnCards = new ArrayList<Integer>();
				//colors of cards
				ArrayList<String> colors = new ArrayList<String>();
				//assign 3 cards to each player
				for (int j = 0; j < 3; j++) {
					String card = totalCardsArr[getCard()];
					cards.add(card);
					String noOfCard = card.substring(0, 2);
					String colorOfCard = card.substring(2);
					int no = 0;
					//assign number for jack ,Queen,King and Ace
					if (noOfCard.equals("0J")) {
						no = 11;
					} else if (noOfCard.equals("0Q")) {
						no = 12;
					} else if (noOfCard.equals("0K")) {
						no = 13;
					} else if (noOfCard.equals("0A")) {
						no = 14;
					} else {
						no = Integer.parseInt(noOfCard);
					}
					
					noOnCards.add(no);
					colors.add(colorOfCard);
				}
				//total players with its cards
				hmap.put(playerID, cards);

				System.out.println("card number Before Mode" + noOnCards.toString());
				System.out.println(colors.toString());
				
				//switching as per selected mode
				switch (mode) {
				case 1:
					//no changes in normal mode
					System.out.println(" you have choosed Normal Mode");
					
					break;
				case 2:
					System.out.println("you have choosed Any Card Mode");

					System.out.println(noOnCards.toString());

					System.out.println("chose index of Number  as  1 or 2 or 3");
					int anyno = sc.nextInt();
					//select card which user want to change
					while (!(anyno >= 1 && anyno <= 3)) {
						System.out.println("plz select 1 or 2 or 3");
						anyno = sc.nextInt();
					}

					noOnCards.set(anyno - 1, 0);
					Collections.sort(noOnCards);
					int any = noOnCards.get(noOnCards.size() - 1);
					noOnCards.set(0, any);
					System.out.println("card numbers after ANY Card Mode" + noOnCards.toString());
					break;
				case 3:

					System.out.println("you have choosed Low Card  Mode");
					Collections.sort(noOnCards);
					int lowNo = noOnCards.get(noOnCards.size() - 1);
					noOnCards.set(0, lowNo);
					System.out.println("card numbers after Low Card Mode" + noOnCards.toString());

					break;
				case 4:
					System.out.println("you have choosed High Card Mode");
					Collections.sort(noOnCards);
					int highNo = noOnCards.get(noOnCards.size() - 2);
					noOnCards.set(noOnCards.size() - 1, highNo);
					System.out.println("card numbers after High Card Mode" + noOnCards.toString());
					break;
				default:
					System.out.println("something going wrong");

					break;
				}
				//
				fAddMap.put(playerID, addition(noOnCards));
					
				if (allsameNumber(noOnCards)) {
					priorityMap.put(playerID, 6);
					System.out.println("all same number");
				} else if (SequenceWithoutColor(noOnCards) && allsamecolor(colors)) {
					priorityMap.put(playerID, 5);
				} else if (SequenceWithoutColor(noOnCards)) {

					priorityMap.put(playerID, 4);
				} else if (allsamecolor(colors)) {
					priorityMap.put(playerID, 3);

				} else if (pair(noOnCards)) {
					priorityMap.put(playerID, 2);
				} else {
					int maxcard = (highCard(noOnCards) * (-1));

					priorityMap.put(playerID, maxcard);
				}
			}
			
			int maxvalue = (Collections.max(priorityMap.values()));
			if (maxvalue < 0) {
				maxvalue = (Collections.min(priorityMap.values()));
			}
			for (Map.Entry<Integer, Integer> en : priorityMap.entrySet()) {

				int playeridtmp = 0;
				if (en.getValue() == maxvalue) {
					playeridtmp = en.getKey();
					maxAdd.put(playeridtmp, maxvalue);
				}
			}
			;

			
			Set<Integer> arSet=new HashSet<Integer>();
			arSet=maxAdd.keySet();
			ArrayList<Integer> sameno=new ArrayList<>(arSet);
			int pid=0;
			int tvalue=0;
			for(int i:sameno)
			{
				if(tvalue<fAddMap.get(i))
				{
					pid=i;
					tvalue=fAddMap.get(i);
				}
			 
			}
		

			
			
			//print winner with its reason

			if (maxvalue == 6) {
				System.out.println(players[pid] + " is winner because of All Same Number");
			} else if (maxvalue == 5) {
				System.out.println(players[pid] + " is winner because of Pure Sequence With Color");
			} else if (maxvalue == 4) {
				System.out.println(players[pid] + " is winner because of  Sequence With out Color");
			} else if (maxvalue == 3) {
				System.out.println(players[pid] + " is winner because of Same Colors of card");
			} else if (maxvalue == 2) {
				System.out.println(players[pid] + " is winner because of Pair");
			} else {
				System.out.println(players[pid] + " is winner because of High Card");

			}

			

			//scanner close
		sc.close();
	}
}
