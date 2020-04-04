package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Paths.get(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder resultInfo = new StringBuilder();

        CarSeedDto[] carSeedDtos = this.gson
                .fromJson(new FileReader(CARS_FILE_PATH), CarSeedDto[].class);

        Arrays.stream(carSeedDtos)
                .forEach(carSeedDto -> {

                    if (this.validationUtil.isValid(carSeedDto)) {

                        if (this.carRepository
                                .findByMakeAndModelAndKilometers(
                                        carSeedDto.getMake(),
                                        carSeedDto.getModel(),
                                        carSeedDto.getKilometers()) == null){

                            Car car = this.modelMapper.map(carSeedDto, Car.class);

                            LocalDate date =
                                    LocalDate.parse(carSeedDto.getRegisteredOn(),
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                            car.setRegisteredOn(date);

                            this.carRepository.saveAndFlush(car);

                            resultInfo.append(
                                    String.format(SUCCESSFUL_IMPORT_CAR_MESSAGE,
                                            car.getMake(),
                                            car.getModel()));

                        } else {
                            resultInfo.append(ALREADY_IN_DB);
                        }

                    } else {
                        resultInfo.append(INVALID_CAR);
                    }
                    resultInfo.append(System.lineSeparator());
                });

        return resultInfo.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return this.carRepository.findAllByMake()
                .stream()
                .map(car -> {
                    return String.format("%nCar make - %s, model - %s%n" +
                            "\tKilometers - %d%n" +
                            "\tRegistered on - %s%n" +
                            "\tNumber of pictures - %d%n",
                            car.getMake(),
                            car.getModel(),
                            car.getKilometers(),
                            car.getRegisteredOn(),
                            car.getPictures().size());
                }).collect(Collectors.joining(". . ."));
    }

    @Override
    public Car getById(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }
}
