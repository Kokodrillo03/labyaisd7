


import java.util.Arrays;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

public class Document implements IWithName {
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;

	public Document(String name, Scanner scan) {
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}

	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Document link = (Document) o;
		return Objects.equals(name, link.name);
	}

	public void load(Scanner scan) {
		//TODO
		String line = scan.nextLine();
		while (!line.equals("eod")) {
			String[] words = line.split("\\s");
			for (String w : words) {
				if (w.length() > 5) {
					if (w.substring(0, 5).toLowerCase().equals("link=") && isCorrectId(w.substring(5))) {
						int ind1 = w.indexOf("(") + 1;
						int ind2 = w.indexOf(")");
						if (ind1 != 0 && ind2 != -1) {
							int temp = Integer.parseInt(w.substring(ind1, ind2));
							link.add(new Link(w.substring(5, ind1 - 1).toLowerCase(), temp));
						} else
							link.add(new Link(w.substring(5).toLowerCase()));
					}
				}
			}
			line = scan.nextLine();
		}
	}

	public static boolean isCorrectId(String id) {
		//TODO
		Boolean flag = Character.isLetter(id.charAt(0));
		int ind1 = id.indexOf("(") + 1;
		int ind2 = id.indexOf(")");
		if ((ind1 != 0 && ind2 == -1) || (ind1 == 0 && ind2 != -1))
			flag = false;
		if (ind1 != 0 && ind2 != -1) {
			try {
				int num = Integer.parseInt(id.substring(ind1, ind2));
				if (num <= 0)
					return false;
				return true;
			} catch (NumberFormatException e) {
				flag = false;
			}
		}
		return flag;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static Link createLink(String w) {
		if (isCorrectId(w)) {
			int ind1 = w.indexOf("(") + 1;
			int ind2 = w.indexOf(")");
			if (ind1 != 0 && ind2 != -1) {
				int temp = Integer.parseInt(w.substring(ind1, ind2));
				return (new Link(w.substring(0, ind1 - 1).toLowerCase(), temp));
			} else
				return (new Link(w));
		}
		return null;
	}

	@Override
	public String toString() {
		//TODO
		String docToStr = "Document: " + name;
		if (!link.isEmpty()) docToStr += "\n";
		for (int i = 0; i < link.size(); i++) {
			if (i % 9 == 0 && i != 0)
				docToStr += link.get(i).toString() + "\n";
			else if (i != link.size() - 1)
				docToStr += link.get(i).toString() + " ";
			else
				docToStr += link.get(i).toString();
		}
		return docToStr;
	}

	public String toStringReverse() {
		String retStr = "Document: " + name;
		if (!link.isEmpty()) retStr += "\n";
		ListIterator<Link> iter = link.listIterator();
		while (iter.hasNext())
			iter.next();
		//TODO
		int i = 0;
		while (iter.hasPrevious()) {
			if (i % 9 == 0 && i != 0) {
				retStr += iter.previous() + "\n";
			} else {
				retStr += iter.previous();
				if (iter.hasPrevious()) retStr += " ";
			}
			i++;
		}
		return retStr;
	}


	@Override
	public String getName () {
		// TODO
		return null;
	}
}
