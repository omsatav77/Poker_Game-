package Zensar_cisco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pocker_Game {

	static int getCard() {
		return (int) (Math.random() * 52);
	}

	static boolean allsamecolor(ArrayList<String> arr) {
		String tmp = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			if (!(tmp.equals(arr.get(i)))) {
				return false;
			}
		}
		return true;
	}

	static boolean allsameNumber(ArrayList<Integer> arr) {
		int tmp = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			if (tmp != arr.get(i)) {
				return false;
			}
		}
		return true;
	}

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

	static int highCard(ArrayList<Integer> arr) {
		Collections.sort(arr);

		return arr.get(arr.size() - 1);

	}

	static int Addiion(ArrayList<Integer> arr) {
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

		HashMap<Integer, Integer> finalMap = new HashMap<>();
		HashMap<Integer, Integer> finalAddMap = new HashMap<>();
		HashMap<Integer, Integer> maxAdd = new HashMap<>();
		int increment = 0;
		//creting array of 52 cards
		for (int i = 0; i < cardNo.length; i++) {
			for (int j = 0; j < cardColor.length; j++) {
				totalCardsArr[increment] = cardNo[i] + cardColor[j];
				increment++;
			}
		}
		
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
				ArrayList<String> cards = new ArrayList<String>();
				ArrayList<Integer> noOnCards = new ArrayList<Integer>();
				ArrayList<String> colors = new ArrayList<String>();
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
				
				//switching as per mode
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
				finalAddMap.put(playerID, Addiion(noOnCards));

				if (allsameNumber(noOnCards)) {
					finalMap.put(playerID, 6);
					System.out.println("all same number");
				} else if (SequenceWithoutColor(noOnCards) && allsamecolor(colors)) {
					finalMap.put(playerID, 5);
				} else if (SequenceWithoutColor(noOnCards)) {

					finalMap.put(playerID, 4);
				} else if (allsamecolor(colors)) {
					finalMap.put(playerID, 3);

				} else if (pair(noOnCards)) {
					finalMap.put(playerID, 2);
				} else {
					int maxcard = (highCard(noOnCards) * (-1));

					finalMap.put(playerID, maxcard);
				}
			}
			
			int maxvalue = (Collections.max(finalMap.values()));
			if (maxvalue < 0) {
				maxvalue = (Collections.min(finalMap.values()));
			}
			for (Map.Entry<Integer, Integer> en : finalMap.entrySet()) {

				int playeridtmp = 0;
				if (en.getValue() == maxvalue) {
					playeridtmp = en.getKey();
					// finalAddMap.put(playeridtmp, Addiion(noOnCards));
					maxAdd.put(playeridtmp, maxvalue);
				}
			}
			;

			int pid = (Collections.max(finalAddMap.values()));
			
			Map.Entry<Integer, Integer> maxEntry = null;
			for (Map.Entry<Integer, Integer> en : finalAddMap.entrySet()) {

				if (maxEntry == null || en.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = en;
				}
			}

			
			pid = maxEntry.getKey();
			


			if (maxvalue == 6) {
				System.out.println(players[pid] + "is winner because of All Same Number");
			} else if (maxvalue == 5) {
				System.out.println(players[pid] + "is winner because of Pure Sequence With Color");
			} else if (maxvalue == 4) {
				System.out.println(players[pid] + "is winner because of  Sequence With out Color");
			} else if (maxvalue == 3) {
				System.out.println(players[pid] + "is winner because of Same Colors of card");
			} else if (maxvalue == 2) {
				System.out.println(players[pid] + "is winner because of Pair");
			} else {
				System.out.println(players[pid] + "is winner because of High Card");

			}

			

		sc.close();
	}
}
