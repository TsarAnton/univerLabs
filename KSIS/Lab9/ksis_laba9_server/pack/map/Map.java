package pack.map;

public class Map {
    public static Building building;
    public static CityCenter cityCenter;
    public static Coast coast;
    public static Crypt crypt;
    public static Docks docks;
    public static Factory factory;
    public static Forest forest;
    public static Graveyard graveyard;
    public static IndustrialArea industrialArea;
    public static Park park;
    public static Port port;
    public static RailwayStation railwayStation;
    public static Residence residence;

    public static void initialize() {
        building = new Building();
        cityCenter = new CityCenter();
        coast = new Coast();
        crypt = new Crypt();
        docks = new Docks();
        factory = new Factory();
        forest = new Forest();
        graveyard = new Graveyard();
        industrialArea = new IndustrialArea();
        park = new Park();
        port = new Port();
        railwayStation = new RailwayStation();
        residence = new Residence();

        building.west = park;
        building.south = graveyard;
        cityCenter.north = park;
        cityCenter.west = port;
        cityCenter.south = industrialArea;
        cityCenter.east = graveyard;
        coast.east = port;
        docks.north = port;
        docks.east = industrialArea;
        crypt.west = graveyard;
        factory.north = industrialArea;
        forest.south = park;
        graveyard.north = building;
        graveyard.west = cityCenter;
        graveyard.south = residence;
        graveyard.east = crypt;
        industrialArea.north = cityCenter;
        industrialArea.west = docks;
        industrialArea.south = factory;
        industrialArea.east = residence;
        park.north = forest;
        park.west = railwayStation;
        park.south = cityCenter;
        park.east = building;
        port.north = railwayStation;
        port.west = coast;
        port.south = docks;
        port.east = cityCenter;
        railwayStation.east = park;
        railwayStation.south = port;
        residence.north = graveyard;
        residence.west = industrialArea;
    }
}
