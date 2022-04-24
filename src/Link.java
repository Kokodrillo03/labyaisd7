
import java.util.Objects;

public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}
	public Link(String ref, int weight) {
		this.ref=ref;
		this.weight=weight;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Link link = (Link) o;
		return Objects.equals(ref, link.ref);
	}
	@Override
	public String toString() {
		return ref+"("+weight+")";
	}
	@Override
	public int compareTo(Link another) {
		//TODO
		//if(this.ref.compareToIgnoreCase(another.ref)!=0)
		return this.ref.compareToIgnoreCase(another.ref);
		//return Integer.valueOf(this.weight).compareTo(Integer.valueOf(another.weight));
	}
}

