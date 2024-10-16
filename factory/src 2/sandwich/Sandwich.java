package sandwich;

import java.util.List;

public class Sandwich {
	private String name;
	private String bread;
	private String patty;
	private List<String> others;
	private int price;

	public Sandwich() {
		// for classic way to create an object 
	}

	public Sandwich(Builder builder) {
		this.name = builder.name;
		this.bread = builder.bread;
		this.patty = builder.patty;
		this.others = builder.others;
		this.price = builder.price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBread() {
		return bread;
	}

	public void setBread(String bread) {
		this.bread = bread;
	}

	public String getPatty() {
		return patty;
	}

	public void setPatty(String patty) {
		this.patty = patty;
	}

	public List<String> getOthers() {
		return others;
	}

	public void setOthers(List<String> others) {
		this.others = others;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static class Builder {
		private String name;
		private String bread;
		private String patty;
		private List<String> others;
		private int price;
		
		public Builder name(String name) {
            this.name = name;
            return this;
        }
		
		public Builder bread(String bread) {
			this.bread = bread;
			return this;
		}
		
		public Builder patty(String patty) {
			this.patty = patty;
			return this;
		}
		
		public Builder price(int price) {
			this.price = price;
			return this;
		}
		
		public Builder others(List<String> others) {
            this.others = others;
            return this;
        }

		public Sandwich build() {
			return new Sandwich(this);
		}
	}

	@Override
	public String toString() {
		return "Sandwich [name=" + name + ", bread=" + bread + ", patty=" + patty + ", others=" + others + ", price="
				+ price + "]";
	}
}
