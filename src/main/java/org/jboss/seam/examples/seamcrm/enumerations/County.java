package org.jboss.seam.examples.seamcrm.enumerations;

public enum County {
	BEAVERHEAD("Beaverhead", "Dillon", State.MT, 1),
	BIG_HORN("Big Horn", "Hardin", State.MT, 3),
	BLAINE("Blaine", "Chinook", State.MT, 5),
	BROADWATER("Broadwater", "Townsend", State.MT, 7),
	CARBON("Carbon", "Red Lodge", State.MT, 9),
	CARTER("Carter", "Ekalaka", State.MT, 11),
	CASCADE("Cascade", "Great Falls", State.MT, 13),
	CHOUTEAU("Chouteau", "Fort Benton", State.MT, 15),
	CUSTER("Custer", "Miles City", State.MT, 17),
	DANIELS("Daniels", "Scobey", State.MT, 19),
	DAWSON("Dawson", "Glendive", State.MT, 21),
	DEER_LODGE("Deer Lodge", "Anaconda", State.MT, 23),
	FALLON("Fallon", "Baker", State.MT, 25),
	FERGUS("Fergus", "Lewistown", State.MT, 27),
	FLATHEAD("Flathead", "Kalispell", State.MT, 29),
	GALLATIN("Gallatin", "Bozeman", State.MT, 31),
	GARFIELD("Garfield", "Jordan", State.MT, 33),
	GLACIER("Glacier", "Cut Bank", State.MT, 35),
	GOLDEN_VALLEY("Golden Valley", "Ryegate", State.MT, 37),
	GRANITE("Granite", "Philipsburg", State.MT, 39),
	HILL("Hill", "Havre", State.MT, 41),
	JEFFERSON("Jefferson", "Boulder", State.MT, 43),
	JUDITH_BASIN("Judith Basin", "Stanford", State.MT, 45),
	LAKE("Lake", "Polson", State.MT, 47),
	LEWIS_CLARK("Lewis and Clark", "Helena", State.MT, 49),
	LIBERTY("Liberty", "Chester", State.MT, 51),
	LINCOLN("Lincoln", "Libby", State.MT, 53),
	MCCONE("McCone", "Circle", State.MT, 55),
	MADISON("Madison", "Virginia City", State.MT, 57),
	MEAGHER("Meagher", "White Sulphur Springs", State.MT, 59),
	MINERAL("Mineral", "Superior", State.MT, 61),
	MISSOULA("Missoula", "Missoula", State.MT, 63),
	MUSSELSHELL("Musselshell", "Roundup", State.MT, 65),
	PARK("Park", "Livingston", State.MT, 67),
	PETROLEUM("Petroleum", "Winnett", State.MT, 69),
	PHILLIPS("Phillips", "Malta", State.MT, 71),
	PONDERA("Pondera", "Conrad", State.MT, 73),
	POWDER_RIVER("Powder River", "Broadus", State.MT, 75),
	POWELL("Powell", "Deer Lodge", State.MT, 77),
	PRAIRIE("Prairie", "Terry", State.MT, 79),
	RAVALLI("Ravalli", "Hamilton", State.MT, 81),
	RICHLAND("Richland", "Sidney", State.MT, 83),
	ROOSEVELT("Roosevelt", "Wolf Point", State.MT, 85),
	ROSEBUD("Rosebud", "Forsyth", State.MT, 87),
	SANDERS("Sanders", "Thompson Falls", State.MT, 89),
	SHERIDAN("Sheridan", "Plentywood", State.MT, 91),
	SILVER_BOW("Silver Bow", "Butte", State.MT, 93),
	STILLWATER("Stillwater", "Columbus", State.MT, 95),
	SWEET_GRASS("Sweet Grass", "Big Timber", State.MT, 97),
	TETON("Teton", "Choteau", State.MT, 99),
	TOOLE("Toole", "Shelby", State.MT, 101),
	TREASURE("Treasure", "Hysham", State.MT, 103),
	VALLEY("Valley", "Glasgow", State.MT, 105),
	WHEATLAND("Wheatland", "Harlowton", State.MT, 107),
	WIBAUX("Wibaux", "Wibaux", State.MT, 109),
	YELLOWSTONE("Yellowstone", "Billings", State.MT, 111);

	private String displayName;
	private String countySeat;
	private State state;
	private int fipsCode;

	private County(String displayName, String countySeat, State state,
			int fipsCode) {
		this.displayName = displayName;
		this.countySeat = countySeat;
		this.state = state;
		this.fipsCode = fipsCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getCountySeat() {
		return countySeat;
	}

	public State getState() {
		return state;
	}

	public int getFipsCode() {
		return fipsCode;
	}

	public String getFullName() {
		return displayName + " County";
	}

	@Override
	public String toString() {
		return displayName;
	}
}
