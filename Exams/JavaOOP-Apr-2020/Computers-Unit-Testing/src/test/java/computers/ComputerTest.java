package computers;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    @Test
    public void ConstructorShouldCreateValidInstance() {
        Computer computer = new Computer("ThinkPad");

        String expected = "ThinkPad";
        String actual = computer.getName();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidNameShouldThrowException() {

        Computer computer = new Computer("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getPartsShouldReturnUnmodifiableList() {

        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);

        computer.getParts().add(part);

    }

    @Test
    public void getTotalPriceShouldReturnValidSum() {
        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);
        Part part1 = new Part("Fs", 4.5);
        Part part2 = new Part("Fsf", 5.5);

        computer.addPart(part);
        computer.addPart(part1);
        computer.addPart(part2);

        double expected = 13.50;
        double actual = computer.getTotalPrice();

        Assert.assertEquals(expected, actual, 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPartShouldThrowExceptionIfNull() {

        Computer computer = new Computer("ThinkPad");
        computer.addPart(null);
    }

    @Test
    public void addPartShouldSuccessfullyAdd() {
        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);

        computer.addPart(part);

        int expected = 1;
        int actual = computer.getParts().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removePartShouldActCorrectly() {

        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);

        computer.addPart(part);

        Assert.assertTrue(computer.removePart(part));
    }

    @Test
    public void removePartShouldRemoveCorrectly() {

        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);


        Assert.assertFalse(computer.removePart(part));
    }

    @Test
    public void removePartShouldRemoveFromParts() {

        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);

        computer.addPart(part);
        computer.removePart(part);

        int expected = 0;
        int actual = computer.getParts().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPartShouldReturnCorrectPart() {
        Computer computer = new Computer("ThinkPad");
        Part part = new Part("Ram", 3.5);
        Part part1 = new Part("Fs", 4.5);
        Part part2 = new Part("Fsf", 5.5);

        computer.addPart(part);
        computer.addPart(part1);
        computer.addPart(part2);

        String expected = "Fs";
        String actual = computer.getPart("Fs").getName();

        Assert.assertEquals(expected, actual);
    }


}