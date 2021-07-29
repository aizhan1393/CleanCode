import Planes.ExperimentalPlane;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        Assert.assertEquals(transportMilitaryPlanes.size(), 1);
        Assert.assertEquals(transportMilitaryPlanes.get(0).getType(), MilitaryType.TRANSPORT);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.equals(planeWithMaxPassengerCapacity));
    }

    @Test
    public void testPlacesSortedByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        Assert.assertEquals(planesSortedByMaxLoadCapacity.size(), 16);
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(0).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(1).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(1).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(2).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(2).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(3).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(3).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(4).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(4).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(5).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(5).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(6).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(6).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(7).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(7).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(8).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(8).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(9).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(9).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(10).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(10).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(11).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(11).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(12).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(12).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(13).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(13).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(14).getMinLoadCapacity());
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(14).getMinLoadCapacity() <= planesSortedByMaxLoadCapacity.get(15).getMinLoadCapacity());


    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        Assert.assertEquals(bomberMilitaryPlanes.size(), 3);
        Assert.assertEquals(bomberMilitaryPlanes.get(0).getType(), MilitaryType.BOMBER);
        Assert.assertEquals(bomberMilitaryPlanes.get(1).getType(), MilitaryType.BOMBER);
        Assert.assertEquals(bomberMilitaryPlanes.get(2).getType(), MilitaryType.BOMBER);
        }



    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> ExperimentalPlanes = airport.getExperimentalPlanes();
        Assert.assertEquals(ExperimentalPlanes.size(), 2);
        Assert.assertNotEquals(ExperimentalPlanes.get(0).getClassificationLevel(), ClassificationLevel.UNCLASSIFIED);
        Assert.assertNotEquals(ExperimentalPlanes.get(1).getClassificationLevel(), ClassificationLevel.UNCLASSIFIED);
         }
}
